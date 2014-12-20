package com.kic.hrm.server.businesslogic;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.quota.QuotaService;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.EmployeeService;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTaskService;
import com.kic.hrm.data.model.LoginInfo;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLogService;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.DataStoreControl;
import com.kic.hrm.server.GeoLocationServiceImpl;
import com.kic.hrm.server.LoginServiceImpl;

public class AttendanceServiceImpl {

	private static final Logger log = Logger
			.getLogger(AttendanceServiceImpl.class.getName());

	public static boolean LoginAttendance(LoginInfo userInfo, type leaveType ,String address) {
		// TODO Auto-generated method stub
		System.out.println("Employee email: " + userInfo.getEmailAddress());
		log.log(Level.SEVERE, "Employee email: " + userInfo.getEmailAddress());
		Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
		Employee m_employee = ProfileServiceImpl.getProfile(userInfo.getEmailAddress());
		
		if (m_employee != null) {
			log.log(Level.SEVERE, "Employee is Not NULL!!.");
			timetable m_timetable = StartTimeLogService.convertRoleToTimeTable(userInfo.getEmployeeRole());

			// Check Area
			if (leaveType == leaveType.Office) {
				// CAMT Lat Long : 18.8005192,98.9507971
				// Home to camt = 7.378121432299594 : 7.3 KM
				double[] CAMTPosition = { (double) 18.8005192,
						(double) 98.9507971 };
				double[] currentPosition = {
						userInfo.getCurrentPosition().getLatitude(),
						userInfo.getCurrentPosition().getLongitude() };

				/* Test GPS point
				double[] gps1 = { 13.7569891624617, 100.6189513206482 };
				double[] gps2 = { 13.7569991624617, 100.6189613206482 };
				*/
				// double
				
				double Distance = GeoLocationServiceImpl.findDistance(CAMTPosition, currentPosition);
				
				System.out.println("current Position : " + currentPosition[0]
						+ " : " + currentPosition[1] + " | Distance : "
						+ Distance);
				log.log(Level.SEVERE, "current Position : "
						+ currentPosition[0] + " : " + currentPosition[1]
						+ " | Distance : " + Distance);
				
				// Distance lass than 70 Meter
				if (Distance <= 0.07) {
					// On Office
					return AttendanceSaveDate(m_employee,now, m_timetable, leaveType, address);
				} else {
					return false;
				}
			} else {
				// On Site
				return AttendanceSaveDate(m_employee,now, m_timetable, leaveType, address);
			}
		}
		return false;
	}
	
	public static void CreateDailyData() {
		
		log.log(Level.SEVERE,"Create Daily StartTimeLog");
		List<Employee> results = ProfileServiceImpl.getProfileList();
		
		
		for (Employee em : results) {
			//System.out.println("Add StartTimeLog for Employee : " + em.getM_name());
			Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
			log.log(Level.SEVERE ,"Pre add Date StartTimeLog for Employee : " + em.getM_name() + " : " 
					+ "CreateTime Date : " + now.getDay() + " : " + now.getDate() + " : "
					+ now.getMonth() + " : " + now.getYear()
			);
			PreAddData(em,now,type.InProgress,"InProgress");
		}
		//TimeZone.c
	}
	
	public static void AdsceneAllDate() {
		
		//List<StartTimeLog> m_startTimeLog = StartTimeLogService.
		List<StartTimeLog> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(StartTimeLog.class,
				SortDirection.ASCENDING);
		results = StartTimeLogService.Clone(entities);
		// System.out.println("Rusults Size " +results.size());
		//Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
		//List<Employee> results = ProfileServiceImpl.getProfileList();
		
		for (StartTimeLog stl : results) {
			if(stl.getM_type() == type.InProgress || stl.getM_type() == type.Absence) {
				stl.setM_type(type.Absence);
				stl.setM_Note("Absence");
				Entity d_stl;
				try {
					d_stl = DataStoreControl.EditEntity(stl.getKind(), stl.getKeyID());
					d_stl = StartTimeLogService.FlashData(d_stl, stl);
					DataStoreControl.SaveEntity(d_stl);
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//PreAddData(em,now,type.Absence,"Absence");
		}
		
		List<LeaveTask> resultsLT;// = new ArrayList<Employee>();
		List<Entity> entitiesLT = DataStoreControl.Query(LeaveTask.class,
				SortDirection.ASCENDING);
		resultsLT = LeaveTaskService.Clone(entitiesLT);
		for(LeaveTask order : resultsLT) {
			int rangDate = order.getM_end().getDate() - order.getM_start().getDate();
			for(int i = 0 ; i <= rangDate ; i++) {
				Date now = order.getM_start();
				now.setDate(now.getDate() + i);
				List<StartTimeLog> resultsSTLPerDay = StartTimeLogService.getStartTimeLogListDaily(now);
				for (StartTimeLog stlPerDay : resultsSTLPerDay) {
					if(stlPerDay.getM_employeeID() == order.getM_employeeID()) {
						stlPerDay.setM_type(order.getM_leavetype());
						stlPerDay.setM_Note(order.getM_sendmessage());
						Entity d_stl;
						try {
							d_stl = DataStoreControl.EditEntity(stlPerDay.getKind(), stlPerDay.getKeyID());
							d_stl = StartTimeLogService.FlashData(d_stl, stlPerDay);
							DataStoreControl.SaveEntity(d_stl);
						} catch (EntityNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		//StartTimeLogService.
	}
	
	public static void DailyProcess() {
			// Process StartTimeLog
		log.log(Level.SEVERE,"AdsceneDailyDate");
		//Leave Update.
		Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
		List<Employee> results = ProfileServiceImpl.getProfileList();
		
		for (Employee em : results) {
			System.out.println("Add StartTimeLog for Employee : " + em.getM_name());
			log.log(Level.SEVERE ,"Add StartTimeLog for Employee : " + em.getM_name() );
			
			//////////////
			PreAddData(em,now,type.Absence,"Absence");
		}
			// Counting EmployeeQuota
		Date now2 = StartTimeLogService.convertTimeZoneToBankok(new Date());
		List<StartTimeLog> m_startTimeLog = StartTimeLogService.getStartTimeLogListDaily(now);
		
		for (StartTimeLog m_log : m_startTimeLog) {
			ProcessQuota(m_log);
		}
	}
		
	public static void AllProcess() {
		
		//////////// Restate Quota ///////////////////////////////////////////////////////
		List<EmployeeQuota> resultsEQ;// = new ArrayList<Employee>();
		List<Entity> entitiesEQ = DataStoreControl.Query(EmployeeQuota.class,
				SortDirection.ASCENDING);
		resultsEQ = EmployeeQuotaService.Clone(entitiesEQ);
		
		for (EmployeeQuota eQuota : resultsEQ) {
			eQuota.setM_absence(0);
			eQuota.setM_late(0);
			eQuota.setM_leave(0);
			eQuota.setM_onsite(0);
			eQuota.setM_ontime(0);
			eQuota.setM_holiday(10);
			eQuota.setM_leave(10);
			Entity d_stl;
			try {
				d_stl = DataStoreControl.EditEntity(eQuota.getKind(), eQuota.getKeyID());
				d_stl = EmployeeQuotaService.FlashData(d_stl, eQuota);
				DataStoreControl.SaveEntity(d_stl);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		///////////////////////////////////////////////////////////////////////////////
		List<StartTimeLog> resultsSTL;// = new ArrayList<Employee>();
		List<Entity> entitiesSTL = DataStoreControl.Query(StartTimeLog.class,
				SortDirection.ASCENDING);
		resultsSTL = StartTimeLogService.Clone(entitiesSTL);
		// System.out.println("Rusults Size " +results.size());
		//Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
		//List<Employee> results = ProfileServiceImpl.getProfileList();
		
		for (StartTimeLog stl : resultsSTL) {
			ProcessQuota(stl);
		}
	}
	
	private static void ProcessQuota(StartTimeLog m_log) {
		EmployeeQuota m_Quota = EmployeeQuotaService.getEmployeeQuota(m_log.getM_employeeID());
		
		if(m_log.getM_type() == type.Absence) {
			m_Quota.setM_absence(m_Quota.getM_absence() + 1);
		}else if(m_log.getM_type() == type.OnTime || m_log.getM_type() == type.Office) {
			m_Quota.setM_ontime(m_Quota.getM_ontime() + 1);
		}else if(m_log.getM_type() == type.Onsite) {
			m_Quota.setM_onsite(m_Quota.getM_onsite() + 1);
		}else if(m_log.getM_type() == type.Late) {
			m_Quota.setM_late(m_Quota.getM_late() + 1);
		}else if(m_log.getM_type() == type.Leave) {
			m_Quota.setM_leave(m_Quota.getM_leave() - 1);
		}else if(m_log.getM_type() == type.Holiday) {
			m_Quota.setM_holiday(m_Quota.getM_holiday() - 1);
		}
		
		Entity e_Quota;
		try {
			e_Quota = DataStoreControl.EditEntity(m_Quota.getKind(), m_Quota.getKeyID());
			e_Quota = EmployeeQuotaService.FlashData(e_Quota, m_Quota);
			DataStoreControl.SaveEntity(e_Quota);
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void PreAddData(Employee m_employee ,Date saveDate,type m_leaveType ,String address) {
		AttendanceSaveDate(m_employee
				,saveDate
				,StartTimeLogService.convertRoleToTimeTable(m_employee.getM_role())
				,m_leaveType
				,address);
	}
	
	private static boolean AttendanceSaveDate(Employee m_employee,Date saveDate,timetable m_timetable,type m_leaveType ,String address) {
		
		StartTimeLog OnWebStartTimeLog = StartTimeLogService.Create(m_employee,saveDate, m_timetable, m_leaveType, address);

		// One Day Save One Time
		boolean isLogin = OneTimeLogin(OnWebStartTimeLog);

		// Add DataStartTimeLogService
		if (!isLogin) {
			System.out.println("First Time Login");
			return StartTimeLogService.SaveAS(OnWebStartTimeLog,isLogin);
		}else {
			System.out.println("Progress Update");
			return StartTimeLogService.SaveAS(OnWebStartTimeLog,isLogin);
		}
		//System.out.println("Seved. | " + m_employee.getM_employeeID() +" have state today login is : " + isLogin);
		
		//return false;
	}

	private static boolean OneTimeLogin( StartTimeLog m_startTimelog) {
		boolean isLogin = false;
		log.log(Level.SEVERE, "Check One Time Logine | Employee ID : " + m_startTimelog.getM_employeeID());
		List<StartTimeLog> m_starttimelogs = StartTimeLogService.getStartTimeLogListOnlyOne(m_startTimelog.getM_employeeID());

		log.log(Level.SEVERE,
				" Date : " + m_startTimelog.getM_date().getDay() + " : " + m_startTimelog.getM_date().getDate() + " : "
						+ m_startTimelog.getM_date().getMonth() + " : " + m_startTimelog.getM_date().getYear());
		
		for (StartTimeLog m_start : m_starttimelogs) {
			// m_start.getM_date()
			if (CollisionDate(m_startTimelog.getM_date(), m_start.getM_date()) && m_start.getM_employeeID() == m_startTimelog.getM_employeeID()){
				isLogin = true;
				m_startTimelog.setKind(m_start.getKind());
				m_startTimelog.setKeyID(m_start.getKeyID());
				log.log(Level.SEVERE, "Kind and KeyIDSeted : Key ID" + m_startTimelog.getKeyID()
						+ " : Kind :" + m_startTimelog.getKind());
				//log.log(Level.SEVERE, "m_starttimelogs Count : " + m_starttimelogs.size() + " : Is Login : " + isLogin + " |Kind and KeyIDSeted");
				break;
			}
				
		}
		
		log.log(Level.SEVERE, "m_starttimelogs Count : " + m_starttimelogs.size() + " : Is Login : " + isLogin + " |Kind and KeyIDSeted");

		return isLogin;
	}

	public static boolean CollisionDate(Date thisIsToday, Date collisiontDate) {
		boolean isCollision = false;

		if (thisIsToday.getYear() == collisiontDate.getYear()
				&& thisIsToday.getMonth() == collisiontDate.getMonth()
				&& thisIsToday.getDay() == collisiontDate.getDay()) {

			log.log(Level.SEVERE, "Yesr :" + thisIsToday.getYear() + " : " + collisiontDate.getYear());
			log.log(Level.SEVERE, "getMonth :" + thisIsToday.getMonth() + " : " + collisiontDate.getMonth());
			log.log(Level.SEVERE, "getDay :" + thisIsToday.getDay() + " : " + collisiontDate.getDay());
			isCollision = true;
		}
			

		return isCollision;
	}
/*
	void SaveStartTimeLogToDataStore() {

	}
*/
}
