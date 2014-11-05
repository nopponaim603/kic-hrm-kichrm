package com.kic.hrm.server;

import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.client.GreetingService;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.LeaveTask;

import com.kic.hrm.data.model.LeaveTask.progress;

import com.kic.hrm.data.model.StartTimeLog.type;

import com.kic.hrm.server.businesslogic.AttendanceServiceImpl;
import com.kic.hrm.server.businesslogic.LeaveTaskServiceImpl;
import com.kic.hrm.server.businesslogic.ProfileServiceImpl;
import com.kic.hrm.server.businesslogic.RecordLog;
import com.kic.hrm.server.businesslogic.ReportServiceImpl;

import com.kic.hrm.shared.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.google.api.services.calendar.*;

import com.google.api.services.calendar.model.Event;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(GreetingServiceImpl.class.getName());

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	// #11: implement login helper methods in service implementation
	@Override
	public String getUserEmail(final String token) {
		return LoginServiceImpl.getUserEmail(token);
	}

	@Override
	public LoginInfo login(String requestUri) {
		return LoginServiceImpl.login(requestUri);
	}

	@Override
	public LoginInfo loginDetails(String token) {
		LoginInfo userInfo = LoginServiceImpl.loginDetails(token);
		log("Login Details see email : " + userInfo.getEmailAddress());
		return LoginServiceImpl.MemberOrGuest(userInfo);
	}

	// #11:> end

	// https://www.google.com/calendar/feeds/camtedu.net_ffupfdej93dc7td5rop26gvp1s%40group.calendar.google.com/public/basic

	// camtedu.net_ffupfdej93dc7td5rop26gvp1s@group.calendar.google.com
	String m_calenderID = "camtedu.net_ffupfdej93dc7td5rop26gvp1s@group.calendar.google.com";

	public void getcalender(String token) {

		// Find m_calenderID
		String HR_calenderID = m_calenderID;

		Calendar calender = CalendarServiceImpl.BuildCalendarAPIbyTOKEN(token,
				CloudHRM.getAPPLICATION_NAME());

		try {
			System.out.println("First Test to Create Event.");

			Date startDate = new Date();
			Date endDate = new Date(startDate.getTime() + 86400000);

			Event tempevent = CalendarServiceImpl.createEvent("Appointment",
					"Description", startDate, endDate);
			Event createdEvent = calender.events()
					.insert(HR_calenderID, tempevent).execute();
			System.out.println(createdEvent.getId());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * com.google.api.services.calendar.model.CalendarList calendarList; try
		 * { calendarList = calender.calendarList().list()
		 * .setPageToken(pageToken).execute(); for (CalendarListEntry
		 * calendarListEntry : calendarList.getItems()) {
		 * 
		 * System.out.println("Test : " + calendarListEntry.getId());
		 * System.out.println("Test : " + calendarListEntry.getSummary());
		 * //System.out.println("Test : " + calendarListEntry.);
		 * 
		 * }
		 * 
		 * //calender.calendarList().get(calender) } catch (IOException e) { //
		 * TODO Auto-generated catch block System.out.println("Bug.");
		 * e.printStackTrace(); }
		 */
		// calender.get
		// calender.calendars().
		// List<CalendarListEntry> items = calendarList.getItems();

		// calender.calendarList().
	}

	// Add Edit Delete Profile
	@Override
	public Employee addProfile(Employee userEmployee, EmployeeQuota userQuota,
			state registerMode) {
		return ProfileServiceImpl.addProfile(userEmployee, userQuota,
				registerMode);
	}

	@Override
	public ArrayList<String> UpdateList(String targetEntity) {
		return ProfileServiceImpl.UpdateList(targetEntity);
	}

	@Override
	public Employee getProfile(Integer targetEmployee) {
		return ProfileServiceImpl.getProfile(targetEmployee);
	}

	@Override
	public boolean deleteProfile(Integer targetEmployee) {
		return ProfileServiceImpl.deleteProfile(targetEmployee);
	}

	// Add Edit Delete Profile > end

	// SaveCSV
	@Override
	public String saveCSVtoDrive(String token, String FolderID) {
		String getresouce = RecordLog.SaveStartTime(token, FolderID);
		return getresouce;
	}

	// SaveCSV > end

	@Override
	public String getFileFormGoogleDrive(String token, String idFile) {
		// TODO Auto-generated method stub
		String getresouce = DriveServiceImpl.getFile(token, idFile);
		return getresouce;
	}

	@Override
	public String QuickTest(String testParametor) {
		// TODO Auto-generated method stub

		// MapsEngine engine = BuildMapAPIbyTOKEN(testParametor);
		// TestgetLocation(testParametor);

		// testParametor = getAddress(testParametor);
		// System.out.println("Pass getAddress : " + testParametor);
		/*
		 * try { //readFeaturesFromTable(engine); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		getcalender(testParametor);

		return testParametor;
	}

	/*
	 * public static MapsEngine BuildMapAPIbyTOKEN(String token) {
	 * 
	 * HttpTransport httpTransport = new NetHttpTransport(); JacksonFactory
	 * jsonFactory = new JacksonFactory();
	 * 
	 * // This request initializer will ensure the API key is sent with every //
	 * HTTP request. MapsEngineRequestInitializer apiKeyInitializer = new
	 * MapsEngineRequestInitializer( PUBLIC_API_KEY);
	 * 
	 * GoogleCredential credential = new GoogleCredential()
	 * .setAccessToken(token); MapsEngine service = new
	 * MapsEngine.Builder(httpTransport, jsonFactory,
	 * credential).setMapsEngineRequestInitializer(apiKeyInitializer)
	 * .setApplicationName(APPLICATION_NAME).build();
	 * 
	 * return service; }
	 */

	@Override
	public EmployeeQuota getEmployeeQuota(int employeeID) {
		// TODO Auto-generated method stub
		return EmployeeQuotaService.getEmployeeQuota(employeeID);
	}

	@Override
	public boolean createLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		return LeaveTaskServiceImpl.createLeaveTask(leavetask);
	}

	@Override
	public boolean approveLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		return LeaveTaskServiceImpl.approveLeaveTask(leavetask);
	}

	@Override
	public List<LeaveTask> getLeaveTask(progress InProgress, int targetID) {
		// TODO Auto-generated method stub
		return LeaveTaskServiceImpl.getLeaveTask(InProgress, targetID);
	}

	@Override
	public boolean deleteLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		return LeaveTaskServiceImpl.deleteLeaveTask(leavetask);
	}


	@Override
	public boolean LoginAttendance(LoginInfo userInfo, type leaveType,
			String address) {
		// TODO Auto-generated method stub
		return AttendanceServiceImpl.LoginAttendance(userInfo, leaveType,
				address);
	}

	@Override
	public String getAddressWithLatLong(String latLong) {
		// TODO Auto-generated method stub
		return GeoLocationServiceImpl.getAddressWithLatLong(latLong);
	}

	@Override
	public boolean sendReportDairyToEmail(String email) {
		// TODO Auto-generated method stub
		return ReportServiceImpl.sendReportDairyToEmail(email);
	}

}
