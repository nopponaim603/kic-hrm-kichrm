package com.kic.hrm.data.model;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.Employee.role;
import com.kic.hrm.data.model.StartTimeLog.property;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.DataStoreControl;
import com.kic.hrm.server.businesslogic.AttendanceServiceImpl;

public class StartTimeLogService {

	private static final Logger log = Logger
			.getLogger(StartTimeLogService.class.getName());

	private final static int MAPWORKID = 0;
	private final static int MAPNAME = 1;
	private final static int MAPDATE = 2;
	private final static int MAPTIMETABLE = 3;
	private final static int MAPCLOCKIN = 6;
	private final static int MAPCLOCKOUT = 7;
	private final static int MAPCLOCKLATE = 10;

	@SuppressWarnings("deprecation")
	public static StartTimeLog AddCVSData(String[] inputLog) {
		Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
		StartTimeLog m_startTimeLog = new StartTimeLog();
		m_startTimeLog.setM_date(now);
		if (TestData(inputLog, MAPWORKID))
			m_startTimeLog.setM_employeeID(Integer.parseInt(inputLog[MAPWORKID]
					.substring(1, inputLog[MAPWORKID].length() - 1)));

		if (TestData(inputLog, MAPNAME))
			m_startTimeLog.setM_name(inputLog[MAPNAME].substring(1,
					inputLog[MAPNAME].length() - 1));

		if (TestData(inputLog, MAPDATE)) {

			System.out.println("Date : " + inputLog[MAPDATE].toString());
			String[] splitDate = inputLog[MAPDATE].split("/");
			m_startTimeLog.setM_date(new Date(
					Integer.parseInt(splitDate[2]) - 1900, Integer
							.parseInt(splitDate[1]) - 1, Integer
							.parseInt(splitDate[0])));
			// m_startTimeLog.getM_date().s
			// m_startTimeLog.getM_date().set
			System.out.println("Date : " + m_startTimeLog.getM_date());
		}

		if (TestData(inputLog, MAPTIMETABLE))
			m_startTimeLog.setM_timeTable(timetable
					.valueOf(inputLog[MAPTIMETABLE]));

		if (TestData(inputLog, MAPCLOCKIN)) {
			// if(!inputLog[MAPCLOCKOUT].isEmpty())
			if (!inputLog[MAPCLOCKIN].isEmpty()) {
				String[] splitDate = inputLog[MAPCLOCKIN].split(":");
				Date temp_clockin = new Date(0, 0, 0,
						Integer.parseInt(splitDate[0]),
						Integer.parseInt(splitDate[1]));
				m_startTimeLog.setM_clockIn(temp_clockin);
				System.out.println("Clock in | "
						+ m_startTimeLog.getM_clockIn());
			} else
				System.out.println("this field MAPCLOCKIN is Empty.");

		} else
			System.out.println("this field MAPCLOCKIN is out of Bournd.");

		if (TestData(inputLog, MAPCLOCKOUT)) {
			if (!inputLog[MAPCLOCKOUT].isEmpty()) {
				String[] splitDate = inputLog[MAPCLOCKOUT].split(":");
				Date temp_clockin = new Date(0, 0, 0,
						Integer.parseInt(splitDate[0]),
						Integer.parseInt(splitDate[1]));
				m_startTimeLog.setM_clockIn(temp_clockin);
				System.out.println("Clock in | "
						+ m_startTimeLog.getM_clockOut());
			} else
				System.out.println("this field MAPCLOCKOUT is Empty.");

		} else
			System.out.println("this field MAPCLOCKOUT is out of Bournd.");

		if (TestData(inputLog, MAPCLOCKLATE)) {
			if (!inputLog[MAPCLOCKLATE].isEmpty()) {
				String[] splitDate = inputLog[MAPCLOCKLATE].split(":");
				Date temp_clockin = new Date(0, 0, 0,
						Integer.parseInt(splitDate[0]),
						Integer.parseInt(splitDate[1]));
				m_startTimeLog.setM_clockLate(temp_clockin);
				System.out.println("Clock in | "
						+ m_startTimeLog.getM_clockLate());
			} else
				System.out.println("this field MAPCLOCKLATE is Empty.");

		} else
			System.out.println("this field MAPCLOCKLATE is out of Bournd.");

		return m_startTimeLog;
	}

	public static StartTimeLog AddDataStartTimeLog(Entity entity) {
		Date now = StartTimeLogService.convertTimeZoneToBankok(new Date());
		StartTimeLog startTime = new StartTimeLog();
		startTime.setM_date(now);
		startTime.setKind(entity.getKind());
		startTime.setKeyID(entity.getKey().getId());

		String tempLong = Long.toString((Long) entity
				.getProperty(property.employeeID.toString()));
		startTime.setM_employeeID(Integer.parseInt(tempLong));

		startTime.setM_name((String) entity.getProperty(property.name
				.toString()));
		startTime
				.setM_date((Date) entity.getProperty(property.date.toString()));
		startTime.setM_timeTable(timetable.valueOf(entity.getProperty(
				property.timetable.toString()).toString()));
		startTime.setM_clockIn((Date) entity.getProperty(property.clockin
				.toString()));
		startTime.setM_clockOut((Date) entity.getProperty(property.clockout
				.toString()));
		startTime.setM_clockLate((Date) entity.getProperty(property.clocklate
				.toString()));
		startTime.setM_type(type.valueOf(entity.getProperty(
				property.type.toString()).toString()));
		startTime.setM_Note((String) entity.getProperty(property.Note
				.toString()));

		return startTime;
	}

	private static boolean TestData(String[] inputLog, int slotdata) {

		if (inputLog.length > slotdata)
			return true;

		return false;
	}

	public static Entity FlashData(Entity entity, StartTimeLog start) {

		entity.setProperty(StartTimeLog.property.employeeID.toString(),
				start.getM_employeeID());
		entity.setProperty(StartTimeLog.property.name.toString(),
				start.getM_name());
		entity.setProperty(StartTimeLog.property.timetable.toString(), start
				.getM_timeTable().toString());
		entity.setProperty(StartTimeLog.property.date.toString(),
				start.getM_date());
		entity.setProperty(StartTimeLog.property.clockin.toString(),
				start.getM_clockIn());
		entity.setProperty(StartTimeLog.property.clockout.toString(),
				start.getM_clockOut());
		entity.setProperty(StartTimeLog.property.clocklate.toString(),
				start.getM_clockLate());
		entity.setProperty(StartTimeLog.property.type.toString(), start
				.getM_type().toString());
		entity.setProperty(StartTimeLog.property.Note.toString(),
				start.getM_Note());

		return entity;
	}

	public static List<StartTimeLog> Clone(List<Entity> entities) {
		List<StartTimeLog> results = new ArrayList<StartTimeLog>();
		for (Entity entity : entities)
			results.add(AddDataStartTimeLog(entity));

		return results;
	}

	public static StartTimeLog Create(Employee employee, Date createTime,
			timetable m_timetable, type m_type, String address) {
		StartTimeLog m_startTimelog = new StartTimeLog();
		m_startTimelog.setM_date(createTime);
		m_startTimelog.setM_employeeID(employee.getM_employeeID());
		Date time = createTime;
		// + 07.00
		log.log(Level.SEVERE, "Key ID" + m_startTimelog.getKeyID()
				+ " : Kind :" + m_startTimelog.getKind());
		
		log.log(Level.SEVERE, "Time Zone : " + time.getTimezoneOffset()
				+ " : hours :" + time.getHours());
		log.log(Level.SEVERE,
				" Date : " + time.getDay() + " : " + time.getDate() + " : "
						+ time.getMonth() + " : " + time.getYear());
		

		m_startTimelog.setM_name(employee.getM_name());
		m_startTimelog.setM_timeTable(m_timetable);
		m_startTimelog.setM_type(m_type);
		
		if(m_type == type.Office || m_type == type.Onsite)
			m_startTimelog.setM_clockIn(createTime);
		
		m_startTimelog.setM_Note(address);

		return m_startTimelog;
	}

	public static boolean SaveAS(StartTimeLog StartTimeLog,boolean isLogin) {
		boolean saveSuccess = false;
		Entity d_OnWebStartTimeLog = null;
		
		if (StartTimeLog.getKind() != null && StartTimeLog.getKeyID() != null) {
			log.log(Level.SEVERE, "Have Data. | " + StartTimeLog.getKind()
					+ " : " + StartTimeLog.getKeyID());
			try {
				d_OnWebStartTimeLog = DataStoreControl.EditEntity(
						StartTimeLog.getKind(), StartTimeLog.getKeyID());
				log.log(Level.SEVERE, "StartTimeLog Edit : " + StartTimeLog.getKeyID() + " : " + getTypeEntity(d_OnWebStartTimeLog).toString());
				
				if (getTypeEntity(d_OnWebStartTimeLog) == type.InProgress) {
					d_OnWebStartTimeLog = StartTimeLogService.FlashData(
							d_OnWebStartTimeLog, StartTimeLog);
					DataStoreControl.SaveEntity(d_OnWebStartTimeLog);
					saveSuccess = true;
				}
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.log(Level.SEVERE, "Error : " + e.toString());
			}
		} else {
			System.out.println("Don't Have Data. At Start Time : "
					+ StartTimeLog.getM_employeeID() + " : "
					+ StartTimeLog.getM_name() + " : "
					+ StartTimeLog.getM_date());
			d_OnWebStartTimeLog = DataStoreControl
					.CreateEntity(StartTimeLog.class);
			d_OnWebStartTimeLog = StartTimeLogService.FlashData(
					d_OnWebStartTimeLog, StartTimeLog);
			DataStoreControl.SaveEntity(d_OnWebStartTimeLog);
			saveSuccess = true;
		}

		log.log(Level.SEVERE, "Save Done. | save is : " + saveSuccess);

		return saveSuccess;
	}

	private static type getTypeEntity(Entity entity) {
		return type.valueOf(entity.getProperty(property.type.toString())
				.toString());
	}

	public static timetable convertRoleToTimeTable(role userRole) {

		timetable m_timetable = timetable.None;

		if (userRole == role.Administration) {
			m_timetable = timetable.Admin;
		} else {
			m_timetable = timetable.Project;
		}

		return m_timetable;
	}

	public static Date convertTimeZoneToBankok(Date inputDate) {
		Date BankokTimeZone = inputDate;
		//BankokTimeZone.
		
		// Set Offset Time To bangkok
		if(BankokTimeZone.getTimezoneOffset()==0)
		{
			BankokTimeZone.setTime(BankokTimeZone.getTime() + 25200000L);
			//log.log(Level.SEVERE,"Convert Date : " + BankokTimeZone.toString());
		}
		
		return BankokTimeZone;
		
	}

	public static List<StartTimeLog> getStartTimeLogListDaily(Date m_date) {
		/*
		 * Date gDate = m_date; gDate.setHours(0); gDate.setMinutes(0);
		 * gDate.setSeconds(0); Date lDate = gDate; lDate.setSeconds(10);
		 * 
		 * SimpleDateFormat curFormater = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //
		 * curFormater.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		 * 
		 * log.log(Level.SEVERE , " simpleDate : " +
		 * curFormater.format(gDate).toString()); //Date timeInZone =
		 * (Date)curFormater.parse();
		 * 
		 * 
		 * log.log(Level.SEVERE," gDate : " + gDate.toString() + " : " +
		 * gDate.toLocaleString() + " : " + gDate.toGMTString());
		 * 
		 * Filter beginThisday = new
		 * FilterPredicate(StartTimeLog.property.employeeID
		 * .toString(),FilterOperator.GREATER_THAN_OR_EQUAL, 55000); //Filter
		 * endThisday = new
		 * FilterPredicate(StartTimeLog.property.clockout.toString
		 * (),FilterOperator.LESS_THAN_OR_EQUAL, lDate.getMonth()); //Filter
		 * Combile = ModelService.CompositeAndFilter(beginThisday,endThisday);
		 */

		List<Entity> temp_entity = DataStoreControl.Query(StartTimeLog.class,
				SortDirection.ASCENDING);

		List<StartTimeLog> tempStartTime = StartTimeLogService
				.Clone(temp_entity);
		log.log(Level.SEVERE, "Size : " + tempStartTime.size());
		List<StartTimeLog> tempST = StartTimeLogService.Clone(temp_entity);

		tempST.clear();

		log.log(Level.SEVERE, "Size : " + tempStartTime.size());

		for (StartTimeLog timeLog : tempStartTime) {
			if (AttendanceServiceImpl
					.CollisionDate(m_date, timeLog.getM_date())) {
				tempST.add(timeLog);
			}
		}
		log.log(Level.SEVERE, "Size : " + tempST.size());

		return tempST;
	}

	public static List<StartTimeLog> getStartTimeLogListOnlyOne(int employeeID) {
		Filter currentUser = new FilterPredicate(
				StartTimeLog.property.employeeID.toString(),
				FilterOperator.EQUAL, employeeID);
		List<Entity> temp_entity = DataStoreControl.Query(StartTimeLog.class,
				SortDirection.DESCENDING, currentUser);
		return StartTimeLogService.Clone(temp_entity);
	}
}

//(timeZone = 0) | Thai = -420


		/*
		 * if(time.getTimezoneOffset() == 0) { //time.setHours(time.getHours() +
		 * 7); log.log(Level.SEVERE,"Time Zone : " + time.getTimezoneOffset() +
		 * " : hours :" + time.getHours()); } else {
		 * log.log(Level.SEVERE,"Time Zone : " + time.getTimezoneOffset() +
		 * " : hours :" + time.getHours());
		 * 
		 * }
		 */

		/*
		 * time.setHours(0); time.setMinutes(0); time.setSeconds(0);
		 */

		// SimpleDateFormat curFormater = new
		// SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
		// curFormater.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
		// Date timeInZone = (Date)curFormater.parse(curFormater.format(time));
		/*
		 * try {
		 * 
		 * 
		 * System.out.println(timeInZone + " : " +
		 * curFormater.format(timeInZone)); log.log(Level.SEVERE,"Time : " +
		 * time.toString() + " | TimeZone " + timeInZone.toString() +
		 * " : currentFormater " + curFormater.format(timeInZone)); } catch
		 * (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

// m_startTimelog.setM_date(time);

// m_startTimelog.setM_clockIn(time);
/*
 * m_startTimelog.setM_clockOut((Date)
 * entity.getProperty(property.clockout .toString()));
 * 
 * m_startTimelog.setM_clockLate((Date)
 * entity.getProperty(property.clocklate .toString()));
 */
/*
Date DefultTime = createTime;
DefultTime.setHours(8);
DefultTime.setMinutes(30);
*/
/*
Long deltaTime = 0L;
if (time.getTime() > DefultTime.getTime()) {

	deltaTime = time.getTime() - DefultTime.getTime();
}

Date LateTime = createTime;
LateTime.setTime(deltaTime);*/
// time.get

// m_startTimelog.setM_clockLate(time);

/*
SimpleDateFormat curFormater = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
curFormater.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
curFormater.format(inputDate).toString();
//log.log(Level.SEVERE,"Time Zone : " + curFormater.getTimeZone().toString());
try {
	//log.log(Level.SEVERE,"Convert Date : " + curFormater.parse(curFormater.format(inputDate).toString() ));
	BankokTimeZone = (Date) curFormater.parse(curFormater
			.format(inputDate));
	log.log(Level.SEVERE,"Convert Date : " + BankokTimeZone.toString());
	
	log.log(Level.SEVERE,"Time Zone : " + BankokTimeZone.getTimezoneOffset());
	
	
	
	
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

log.log(Level.SEVERE , "Can't Convert.");
return BankokTimeZone;
*/