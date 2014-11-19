package com.kic.hrm.server.businesslogic;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.EmployeeService;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTaskService;
import com.kic.hrm.data.model.ModelService;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLogService;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.CalendarServiceImpl;
import com.kic.hrm.server.DataStoreControl;

public class LeaveTaskServiceImpl {

	// Create Leave Task
	public static boolean createLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		Entity d_LeaveTask = null;
		d_LeaveTask = DataStoreControl.CreateEntity(LeaveTask.class);
		d_LeaveTask = LeaveTaskService.FlashData(d_LeaveTask, leavetask);
		DataStoreControl.SaveEntity(d_LeaveTask);
		return true;
	}

	// Approve Leave Task
	public static boolean approveLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		Entity d_LeaveTask = null;
		d_LeaveTask = DataStoreControl.CreateEntity(LeaveTask.class);
		try {
			d_LeaveTask = DataStoreControl.EditEntity(leavetask.getKind(),
					leavetask.getKeyID());
			d_LeaveTask = LeaveTaskService.FlashData(d_LeaveTask, leavetask);
			DataStoreControl.SaveEntity(d_LeaveTask);

			// Task Complete
			if (leavetask.getM_leaveprogress() == progress.Complete) {
				List<EmployeeQuota> temp_Em = null;
				List<Entity> temp_E;
				temp_E = DataStoreControl.Query(EmployeeQuota.class,
						SortDirection.ASCENDING, EmployeeQuotaService
								.findEmployeeByEmployeeID(leavetask
										.getM_employeeID()));
				temp_Em = EmployeeQuotaService.Clone(temp_E);

				if (temp_Em.size() == 1) {
					Long deltaTime = leavetask.getM_end().getTime()
							- leavetask.getM_start().getTime();
					Date tempTime = new Date(deltaTime);
					@SuppressWarnings("deprecation")
					int DeltaDay = tempTime.getDate();

					//////////////////////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////////////////////
					
					if (leavetask.getM_leavetype() == type.Leave) {
						temp_Em.get(0).setM_leave(temp_Em.get(0).getM_leave() - DeltaDay);
					}	
					else if (leavetask.getM_leavetype() == type.Holiday) {
						temp_Em.get(0).setM_holiday(temp_Em.get(0).getM_holiday() - DeltaDay);
					}
					Entity newDumpData = DataStoreControl.EditEntity(temp_Em.get(0).getKind(), temp_Em.get(0).getKeyID());
					newDumpData = EmployeeQuotaService.FlashData(newDumpData,temp_Em.get(0));
					DataStoreControl.SaveEntity(newDumpData);

					// If day have Active in StartLog
					// UpdateStartLog(leavetask);
					
					//////////////////////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////////////////////
				}
			}

			return true;
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private static void UpdateStartLog(LeaveTask leavetask) {

		Filter startDate = new FilterPredicate(
				StartTimeLog.property.date.toString(),
				FilterOperator.GREATER_THAN_OR_EQUAL, leavetask.getM_start());

		Filter endDate = new FilterPredicate(
				StartTimeLog.property.date.toString(),
				FilterOperator.LESS_THAN_OR_EQUAL, leavetask.getM_end());

		Filter currentUser = new FilterPredicate(
				StartTimeLog.property.employeeID.toString(),
				FilterOperator.EQUAL, leavetask.getM_employeeID());

		Filter isAbsence = new FilterPredicate(
				StartTimeLog.property.type.toString(), FilterOperator.EQUAL,
				StartTimeLog.type.Absence.toString());

		Filter m_composite = ModelService.CompositeAndFilter(startDate,
				endDate, currentUser, isAbsence);
		List<Entity> temp_entity = DataStoreControl.Query(StartTimeLog.class,
				SortDirection.DESCENDING, m_composite);
		List<StartTimeLog> m_starttimelog = StartTimeLogService
				.Clone(temp_entity);

		for (StartTimeLog startTimeLog : m_starttimelog) {
			if (leavetask.getM_leavetype() == type.Leave)
				startTimeLog.setM_type(StartTimeLog.type.Leave);
			else if (leavetask.getM_leavetype() == type.Holiday)
				startTimeLog.setM_type(StartTimeLog.type.Holiday);

			try {
				Entity newDumpData = DataStoreControl.EditEntity(
						startTimeLog.getKind(), startTimeLog.getKeyID());
				newDumpData = StartTimeLogService.FlashData(newDumpData,
						startTimeLog);
				DataStoreControl.SaveEntity(newDumpData);

			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// temp_entity = null;
		// for(StartTimeLog log : m_starttimelog) {
		// temp_entity = StartTimeLogService.FlashData(temp_entity, log);
		// }

		// StartTimeLogService
	}

	// ///////////////////

	// Get Leave Task
	public static List<LeaveTask> getLeaveTask(progress InProgress, int targetID) {
		// TODO Auto-generated method stub
		List<LeaveTask> results;// = new ArrayList<Employee>();

		List<Entity> entities = null;
		if (InProgress == progress.LeaderApprove)
			entities = DataStoreControl.Query(LeaveTask.class,
					SortDirection.ASCENDING,
					LeaveTaskService.findLeaderByLeaderID(targetID));
		else if (InProgress == progress.HRApprove)
			entities = DataStoreControl.Query(LeaveTask.class,
					SortDirection.ASCENDING, LeaveTaskService.findHRApprove());
		else
			entities = DataStoreControl.Query(LeaveTask.class,
					SortDirection.ASCENDING,
					LeaveTaskService.findEmployeeByEmployeeID(targetID));
		results = LeaveTaskService.Clone(entities);

		return results;
	}

	// Delete Leave Task
	public static boolean deleteLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		System.out.println("delete Leave Task id : " + leavetask.getKeyID());
		DataStoreControl
				.DeleteEntity(leavetask.getKind(), leavetask.getKeyID());
		return true;
	}

	private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";

	public static boolean addLeaveTaskToCalendar(String token,
			LeaveTask leavetask) {
		// TODO Auto-generated method stub
		String HR_calenderID = m_calenderID;

		Calendar calender = CalendarServiceImpl.BuildCalendarAPIbyTOKEN(token,
				APPLICATION_NAME);

		Date startDate = leavetask.getM_start();
		Date endDate = leavetask.getM_end();

		/*
		 * Date startDate = new Date(); Date endDate = new
		 * Date(startDate.getTime() + 86400000);
		 */
		try {
			addTaskToCalender(calender, HR_calenderID, leavetask);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// https://www.google.com/calendar/feeds/camtedu.net_ffupfdej93dc7td5rop26gvp1s%40group.calendar.google.com/public/basic

	// camtedu.net_ffupfdej93dc7td5rop26gvp1s@group.calendar.google.com
	static String m_calenderID = "camtedu.net_ffupfdej93dc7td5rop26gvp1s@group.calendar.google.com";

	public static void addTaskToCalender(Calendar calender,
			String HR_calenderID, LeaveTask leavetask) throws IOException {
		// Find m_calenderID

		System.out.println("First Test to Create Event.");
		Employee m_employee = EmployeeService.getEmployee(leavetask
				.getM_employeeID());
		String m_employeeName = String.valueOf(leavetask.getM_employeeID());
		if (m_employee != null) {
			m_employeeName = m_employee.getM_name();
		}

		Event tempevent = CalendarServiceImpl.createEvent(
				m_employeeName,
				String.valueOf(leavetask.getM_sendmessage() + "\nComment : "
						+ leavetask.getM_commentmessage()),
				leavetask.getM_start(), leavetask.getM_end());
		Event createdEvent = calender.events().insert(HR_calenderID, tempevent)
				.execute();
		System.out.println("Create Event " + createdEvent.getId()
				+ " To Calendar Success.");
	}

	/*
	 * com.google.api.services.calendar.model.CalendarList calendarList; try {
	 * calendarList = calender.calendarList().list()
	 * .setPageToken(pageToken).execute(); for (CalendarListEntry
	 * calendarListEntry : calendarList.getItems()) {
	 * 
	 * System.out.println("Test : " + calendarListEntry.getId());
	 * System.out.println("Test : " + calendarListEntry.getSummary());
	 * //System.out.println("Test : " + calendarListEntry.);
	 * 
	 * }
	 * 
	 * //calender.calendarList().get(calender) } catch (IOException e) { // TODO
	 * Auto-generated catch block System.out.println("Bug.");
	 * e.printStackTrace(); }
	 */
	// calender.get
	// calender.calendars().
	// List<CalendarListEntry> items = calendarList.getItems();

	// calender.calendarList().
}
