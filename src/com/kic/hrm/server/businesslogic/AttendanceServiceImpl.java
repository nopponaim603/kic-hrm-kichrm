package com.kic.hrm.server.businesslogic;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	public static boolean LoginAttendance(LoginInfo userInfo, type leaveType,
			String address) {
		// TODO Auto-generated method stub
		System.out.println("Employee email: " + userInfo.getEmailAddress());

		log.log(Level.SEVERE, "Employee email: " + userInfo.getEmailAddress());
		Employee m_employee = ProfileServiceImpl.getProfile(userInfo
				.getEmailAddress());
		System.out.println("Employee : " + m_employee + " : "
				+ userInfo.getEmailAddress());
		log.log(Level.SEVERE,
				"Employee : " + m_employee + " : " + userInfo.getEmailAddress());
		if (m_employee != null) {
			log.log(Level.SEVERE, "Employee is Not NULL!!.");
			timetable m_timetable = LoginServiceImpl
					.convertRoleToTimeTable(userInfo.getEmployeeRole());

			type m_leaveType = leaveType;

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
				double Distance = GeoLocationServiceImpl.findDistance(
						CAMTPosition, currentPosition);
				System.out.println("current Position : " + currentPosition[0]
						+ " : " + currentPosition[1] + " | Distance : "
						+ Distance);
				log.log(Level.SEVERE, "current Position : "
						+ currentPosition[0] + " : " + currentPosition[1]
						+ " | Distance : " + Distance);
				// Distance lass than 70 Meter
				if (Distance <= 0.07) {

					// On Office
					StartTimeLog OnWebStartTimeLog = StartTimeLogService
							.Create(m_employee, m_timetable, m_leaveType,
									address);

					// One Day Save One Time
					boolean isLogin = OneTimeLogin(
							m_employee.getM_employeeID(),
							OnWebStartTimeLog.getM_date());

					// Add DataStartTimeLogService
					if (!isLogin) {
						Entity d_OnWebStartTimeLog = null;
						d_OnWebStartTimeLog = DataStoreControl
								.CreateEntity(StartTimeLog.class);
						d_OnWebStartTimeLog = StartTimeLogService.FlashData(
								d_OnWebStartTimeLog, OnWebStartTimeLog);
						DataStoreControl.SaveEntity(d_OnWebStartTimeLog);
					}
					return true;
				} else {
					return false;
				}
			} else {
				// On Site
				StartTimeLog OnWebStartTimeLog = StartTimeLogService.Create(
						m_employee, m_timetable, m_leaveType, address);

				// One Day Save One Time
				boolean isLogin = OneTimeLogin(m_employee.getM_employeeID(),
						OnWebStartTimeLog.getM_date());

				// Add DataStartTimeLogService
				if (!isLogin) {

					Entity d_OnWebStartTimeLog = null;
					d_OnWebStartTimeLog = DataStoreControl
							.CreateEntity(StartTimeLog.class);
					d_OnWebStartTimeLog = StartTimeLogService.FlashData(
							d_OnWebStartTimeLog, OnWebStartTimeLog);
					DataStoreControl.SaveEntity(d_OnWebStartTimeLog);

					log.log(Level.SEVERE, "Save Done.");
				}

				return true;
			}
		}
		return false;
	}

	static boolean OneTimeLogin(int employeeID, Date TodayLogin) {
		boolean isLogin = false;
		log.log(Level.SEVERE, "Employee ID : " + employeeID);
		Filter currentUser = new FilterPredicate(
				StartTimeLog.property.employeeID.toString(),
				FilterOperator.EQUAL, employeeID);
		List<Entity> temp_entity = DataStoreControl.Query(StartTimeLog.class,
				SortDirection.DESCENDING, currentUser);
		List<StartTimeLog> m_starttimelog = StartTimeLogService
				.Clone(temp_entity);

		for (StartTimeLog m_start : m_starttimelog) {
			// m_start.getM_date()
			if (CollisionDate(TodayLogin, m_start.getM_date()))
				isLogin = true;
		}
		log.log(Level.SEVERE, "Is Login : " + isLogin);

		return isLogin;
	}

	static boolean CollisionDate(Date thisIsToday, Date collisiontDate) {
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
