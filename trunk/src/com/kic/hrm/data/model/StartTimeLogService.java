package com.kic.hrm.data.model;

import java.util.Date;




import com.google.appengine.api.datastore.Entity;
import com.kic.hrm.data.model.StartTimeLog.timetable;

public class StartTimeLogService {
	private final static int MAPWORKID = 0;
	private final static int MAPNAME = 1;
	private final static int MAPDATE 	= 2;
	private final static int MAPTIMETABLE = 3;
	private final static int MAPCLOCKIN = 6;
	private final static int MAPCLOCKOUT = 7;
	
	@SuppressWarnings("deprecation")
	public static StartTimeLog AddCVSData(String[] inputLog) {
		StartTimeLog m_startTimeLog = new StartTimeLog();
		
		if(TestData(inputLog,MAPWORKID)) 
			m_startTimeLog.setM_employeeID(Integer.parseInt(inputLog[MAPWORKID].substring(1, inputLog[MAPWORKID].length()-1)));		
		
		if(TestData(inputLog,MAPNAME))
			m_startTimeLog.setM_name(inputLog[MAPNAME].substring(1, inputLog[MAPNAME].length()-1));
		
		if(TestData(inputLog,MAPDATE)) {
			
			System.out.println("Date : " + inputLog[MAPDATE].toString());
			String[] splitDate = inputLog[MAPDATE].split("/");
			m_startTimeLog.setM_date(new Date(Integer.parseInt(
					splitDate[2] ) - 1900, 
					Integer.parseInt(splitDate[1]) - 1
					, Integer.parseInt(splitDate[0])));
			//m_startTimeLog.getM_date().s
			//m_startTimeLog.getM_date().set
			System.out.println("Date : " + m_startTimeLog.getM_date());
		}
			
		
		if(TestData(inputLog,MAPTIMETABLE))
			m_startTimeLog.setM_timeTable(timetable.valueOf(inputLog[MAPTIMETABLE]));
		
		if(TestData(inputLog,MAPCLOCKIN)){
			//if(!inputLog[MAPCLOCKOUT].isEmpty())
			if(!inputLog[MAPCLOCKIN].isEmpty()) {
				String[] splitDate = inputLog[MAPCLOCKIN].split(":");
				Date temp_clockin = new Date(0,0,0,Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]));
				m_startTimeLog.setM_clockIn(temp_clockin);
				System.out.println("Clock in | " + m_startTimeLog.getM_clockIn());
			}else System.out.println("this field MAPCLOCKIN is Empty.");
			
		}else System.out.println("this field MAPCLOCKIN is out of Bournd.");
			
		
		if(TestData(inputLog,MAPCLOCKOUT)){
			if(!inputLog[MAPCLOCKOUT].isEmpty()) {
				String[] splitDate = inputLog[MAPCLOCKOUT].split(":");
				Date temp_clockin = new Date(0,0,0,Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]));
				m_startTimeLog.setM_clockIn(temp_clockin);
				System.out.println("Clock in | " + m_startTimeLog.getM_clockOut());
			}else System.out.println("this field MAPCLOCKOUT is Empty.");
			
			
		}else System.out.println("this field MAPCLOCKOUT is out of Bournd.");
			
		//if(TestData(inputLog,MAPDATE))
			//m_startTimeLog.setM_date(new Date);
		
		return m_startTimeLog;
	}
	
	private static boolean TestData(String[] inputLog,int slotdata) {
		
		if(inputLog.length > slotdata)
			return true;
		
		return false;
	}
	
	public static Entity FlashData(Entity entity,StartTimeLog start) {
		
		entity.setProperty(StartTimeLog.property.employeeID.toString(), start.getM_employeeID());
		entity.setProperty(StartTimeLog.property.name.toString(), start.getM_name());
		entity.setProperty(StartTimeLog.property.timetable.toString(), start.getM_timeTable().toString());
		entity.setProperty(StartTimeLog.property.date.toString(), start.getM_date());
		entity.setProperty(StartTimeLog.property.clockin.toString(), start.getM_clockIn());
		entity.setProperty(StartTimeLog.property.clockout.toString(), start.getM_clockOut());
		
		return entity;
	}
}
