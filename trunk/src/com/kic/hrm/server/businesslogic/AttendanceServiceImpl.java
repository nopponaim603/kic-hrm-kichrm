package com.kic.hrm.server.businesslogic;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLogService;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.DataStoreControl;
import com.kic.hrm.server.GeoLocationServiceImpl;
import com.kic.hrm.server.LoginServiceImpl;
import com.kic.hrm.shared.LoginInfo;

public class AttendanceServiceImpl {

	private static final Logger log = Logger
			.getLogger(AttendanceServiceImpl.class.getName());

	public static boolean LoginAttendance(LoginInfo userInfo, type leaveType ,String address) {
		// TODO Auto-generated method stub
		System.out.println("Employee email: " + userInfo.getEmailAddress());
		log.log(Level.SEVERE, "Employee email: " + userInfo.getEmailAddress());
		
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
					return AttendanceSaveDate(m_employee, m_timetable, leaveType, address);
				} else {
					return false;
				}
			} else {
				// On Site
				return AttendanceSaveDate(m_employee, m_timetable, leaveType, address);
			}
		}
		return false;
	}
	
	public static void CreateDailyData() {
		log.log(Level.SEVERE,"CreateDailyData");
		List<Employee> results = ProfileServiceImpl.getProfileList();
		
		for (Employee em : results) {
			System.out.println("Add StartTimeLog for Employee : " + em.getM_name());
			log.log(Level.SEVERE ,"Add StartTimeLog for Employee : " + em.getM_name() );
			
			PreAddData(em,type.InProgress,"InProgress");
		}
	}
	
	public static void AdsceneDailyDate() {
		log.log(Level.SEVERE,"AdsceneDailyDate");
		//Leave Update.
		
		List<Employee> results = ProfileServiceImpl.getProfileList();
		
		for (Employee em : results) {
			System.out.println("Add StartTimeLog for Employee : " + em.getM_name());
			log.log(Level.SEVERE ,"Add StartTimeLog for Employee : " + em.getM_name() );
			
			//////////////
			PreAddData(em,type.Absence,"InProgress");
		}
	}
	
	public static void PreAddData(Employee m_employee ,type m_leaveType ,String address) {
		AttendanceSaveDate(m_employee
				,StartTimeLogService.convertRoleToTimeTable(m_employee.getM_role())
				,m_leaveType
				,address);
	}
	
	private static boolean AttendanceSaveDate(Employee m_employee,timetable m_timetable,type m_leaveType ,String address) {
		
		StartTimeLog OnWebStartTimeLog = StartTimeLogService.Create(m_employee, m_timetable, m_leaveType, address);

		// One Day Save One Time
		boolean isLogin = OneTimeLogin(m_employee.getM_employeeID(),OnWebStartTimeLog);

		// Add DataStartTimeLogService
		if (!isLogin) {
			System.out.println("First Time Login");
			return StartTimeLogService.SaveAS(OnWebStartTimeLog);
		}else {
			System.out.println("Progress Update");
			return StartTimeLogService.SaveAS(OnWebStartTimeLog);
		}
		//System.out.println("Seved. | " + m_employee.getM_employeeID() +" have state today login is : " + isLogin);
		
		//return false;
	}

	private static boolean OneTimeLogin(int employeeID, StartTimeLog m_startTimelog) {
		boolean isLogin = false;
		log.log(Level.SEVERE, "Check One Time Logine | Employee ID : " + employeeID);
		List<StartTimeLog> m_starttimelogs = StartTimeLogService.getStartTimeLogListOnlyOne(employeeID);

		for (StartTimeLog m_start : m_starttimelogs) {
			// m_start.getM_date()
			if (CollisionDate(m_startTimelog.getM_date(), m_start.getM_date())){
				isLogin = true;
				m_startTimelog.setKind(m_start.getKind());
				m_startTimelog.setKeyID(m_start.getKeyID());
				break;
			}
				
		}
		
		log.log(Level.SEVERE, "Is Login : " + isLogin);

		return isLogin;
	}

	private static boolean CollisionDate(Date thisIsToday, Date collisiontDate) {
		boolean isCollision = false;

		if (thisIsToday.getYear() == collisiontDate.getYear()
				&& thisIsToday.getMonth() == collisiontDate.getMonth()
				&& thisIsToday.getDay() == collisiontDate.getDay())
			isCollision = true;

		return isCollision;
	}

	void SaveStartTimeLogToDataStore() {

	}

}
