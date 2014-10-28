package com.kic.hrm.data.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.kic.hrm.data.model.StartTimeLog.property;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.GreetingServiceImpl;

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
		StartTimeLog m_startTimeLog = new StartTimeLog();

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
		StartTimeLog startTime = new StartTimeLog();

		startTime.setKind(entity.getKind());
		startTime.setKeyID(entity.getKey().getId());

		String tempLong = Long.toString((Long) entity
				.getProperty(property.employeeID.toString()));
		startTime.setM_employeeID(Integer.parseInt(tempLong));

		startTime.setM_name((String) entity.getProperty(property.name
				.toString()));
		startTime.setM_date((Date) entity.getProperty(property.date.toString()));
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
		entity.setProperty(StartTimeLog.property.type.toString(),
				start.getM_type().toString());
		entity.setProperty(StartTimeLog.property.Note.toString(),
				start.getM_Note());

		return entity;
	}

	public static StartTimeLog Create(Employee employee,timetable m_timetable,type m_type,String address) {
		StartTimeLog m_startTimelog = new StartTimeLog();
		m_startTimelog.setM_employeeID(employee.getM_employeeID());
		Date time = new Date();
		// + 07.00 
		log.log(Level.SEVERE,"Time Zone : " + time.getTimezoneOffset());
		//(timeZone = 0) | Thai = -420
		if(time.getTimezoneOffset() == 0)
			time.setHours(time.getHours() + 7);
		
		//SimpleDateFormat curFormater = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	   // curFormater.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
	    
	    /*
	    time.setHours(0);
		time.setMinutes(0);
		time.setSeconds(0);*/
		/*
	    try {
			Date timeInZone = (Date)curFormater.parse(curFormater.format(time));
			
			System.out.println(timeInZone + " : " + curFormater.format(timeInZone));
			log.log(Level.SEVERE,"Time : " + time.toString() + " | TimeZone " + timeInZone.toString() + " : currentFormater " + curFormater.format(timeInZone));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
				
		m_startTimelog.setM_name(employee.getM_name());
		
		m_startTimelog.setM_date(time);
		m_startTimelog.setM_timeTable(m_timetable);
		m_startTimelog.setM_clockIn(time);
		/*
		m_startTimelog.setM_clockOut((Date) entity.getProperty(property.clockout
				.toString()));
		
		m_startTimelog.setM_clockLate((Date) entity.getProperty(property.clocklate
				.toString()));
		*/
		Date DefultTime = new Date();
		DefultTime.setHours(8);
		DefultTime.setMinutes(30);
		Long deltaTime = 0L;
		if(time.getTime() > DefultTime.getTime()) {
			
			deltaTime = time.getTime() -  DefultTime.getTime();
		}
		Date LateTime = new Date();
		LateTime.setTime(deltaTime);
		//time.get
		m_startTimelog.setM_clockLate(LateTime);
		m_startTimelog.setM_type(m_type);
		m_startTimelog.setM_Note(address);

		
		return m_startTimelog;
	}

	public static List<StartTimeLog> Clone(List<Entity> entities) {
		List<StartTimeLog> results = new ArrayList<StartTimeLog>();
		for (Entity entity : entities)
			results.add(AddDataStartTimeLog(entity));

		return results;
	}

	public static Filter CompositeAndFilter(Collection<Filter> subFilters) {
		return CompositeFilterOperator.and(subFilters);
	}

	public static Filter CompositeAndFilter(Filter... subFilters) {
		return CompositeFilterOperator.and(Arrays.asList(subFilters));
	}

	public static Filter CompositeOrFilter(Collection<Filter> subFilters) {
		// Filter test = CompositeFilterOperator.and(subFilters)
		return CompositeFilterOperator.or(subFilters);
	}

}
