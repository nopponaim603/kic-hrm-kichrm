package com.kic.hrm.data.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.kic.hrm.data.model.LeaveTask.property;

public class LeaveTaskService {

	public static LeaveTask addDataLeaveTask(Entity entity) {
		LeaveTask m_leavetask = new LeaveTask();
		
		m_leavetask.setKind(entity.getKind());
		m_leavetask.setKeyID(entity.getKey().getId());
		
		String tempLong = Long.toString((Long)entity.getProperty(property.employeeID.toString()));
		m_leavetask.setM_employeeID(Integer.parseInt(tempLong));
		
		tempLong = Long.toString((Long)entity.getProperty(property.leaderID.toString()));
		m_leavetask.setM_leaderID(Integer.parseInt(tempLong));
		
		m_leavetask.setM_leavetype(StartTimeLog.type.valueOf(entity.getProperty(property.leavetype.toString()).toString()));
		m_leavetask.setM_leaveprogress(LeaveTask.progress.valueOf(entity.getProperty(property.leaveprogress.toString()).toString()));
		
		//Date test = new Date();
		
		m_leavetask.setM_start((Date)entity.getProperty(property.start.toString()));
		m_leavetask.setM_end((Date)entity.getProperty(property.end.toString()));
		
		m_leavetask.setM_sendmessage(entity.getProperty(property.sendmessage.toString()).toString());
		m_leavetask.setM_commentmessage(entity.getProperty(property.commentmessage.toString()).toString());
		
		return m_leavetask;
	}
	
	public static Entity FlashData(Entity entity , LeaveTask m_leavetask) {
			
			entity.setProperty(property.employeeID.toString(), m_leavetask.getM_employeeID());
			entity.setProperty(property.leaderID.toString(), m_leavetask.getM_leaderID());
			entity.setProperty(property.leavetype.toString(), m_leavetask.getM_leavetype().toString());
			entity.setProperty(property.leaveprogress.toString(), m_leavetask.getM_leaveprogress().toString());
			
			entity.setProperty(property.start.toString(), m_leavetask.getM_start());
			entity.setProperty(property.end.toString(), m_leavetask.getM_end());
			
			entity.setProperty(property.sendmessage.toString(), m_leavetask.getM_sendmessage());
			entity.setProperty(property.commentmessage.toString(), m_leavetask.getM_commentmessage());
			
			return entity;
	}
	
	public static List<LeaveTask> Clone(List<Entity> entities) {
		List<LeaveTask> results = new ArrayList<LeaveTask>();
		for (Entity entity : entities) 		
			results.add(addDataLeaveTask(entity));
	
		return results;
	}
	
	public static Filter findEmployeeByEmployeeID(int employeeID) {
		Filter m_emailFilter =
				  new FilterPredicate(LeaveTask.property.employeeID.toString(),
				                      FilterOperator.EQUAL,
				                      employeeID);
		return m_emailFilter;
	}
	
	public static Filter findLeaderByLeaderID(int LeaderID) {
		Filter m_emailFilter =
				  new FilterPredicate(LeaveTask.property.leaderID.toString(),
				                      FilterOperator.EQUAL,
				                      LeaderID);
		return m_emailFilter;
	}
	
	public static Filter findHRApprove() {
		Filter m_emailFilter =
				  new FilterPredicate(LeaveTask.property.leaveprogress.toString(),
				                      FilterOperator.EQUAL,
				                      LeaveTask.progress.HRApprove.toString());
		return m_emailFilter;
	}
	
	public static Filter CompositeAndFilter(Filter... subFilters) {
		return CompositeFilterOperator.and(Arrays.asList(subFilters));
	}
}
