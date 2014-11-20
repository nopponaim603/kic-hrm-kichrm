package com.kic.hrm.server;

import com.kic.hrm.client.GreetingService;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.data.model.SystemConfig;
import com.kic.hrm.data.model.SystemConfigService;
import com.kic.hrm.server.businesslogic.AttendanceServiceImpl;
import com.kic.hrm.server.businesslogic.LeaveTaskServiceImpl;
import com.kic.hrm.server.businesslogic.ProfileServiceImpl;
import com.kic.hrm.server.businesslogic.RecordLog;
import com.kic.hrm.server.businesslogic.ReportServiceImpl;
import com.kic.hrm.shared.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


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
		//getcalender(testParametor);
		
		//SystemConfig test = SystemConfig.AddData(null);

		return testParametor;
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
		return RecordLog.SaveStartTime(token, FolderID);
	}
	// SaveCSV > end
	
	// Get CSV File form Google Drive
	@Override
	public String getFileFormGoogleDrive(String token, String idFile) {
		// TODO Auto-generated method stub
		return DriveServiceImpl.getFile(token, idFile);
	}
	// Get CSV File form Google Drive > end

	@Override
	public EmployeeQuota getEmployeeQuota(int employeeID) {
		// TODO Auto-generated method stub
		return EmployeeQuotaService.getEmployeeQuota(employeeID);
	}

	// Leave Task
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
	public boolean addLeaveTaskToCalendar(String token, LeaveTask leavetask) {
		// TODO Auto-generated method stub
		return LeaveTaskServiceImpl.addLeaveTaskToCalendar(token, leavetask);
	}
	// Leave Task > end
	
	
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

	@Override
	public SystemConfig getSystemConfig() {
		// TODO Auto-generated method stub
		SystemConfig sysconfig;
		List<Entity> entities = DataStoreControl.Query(SystemConfig.class,
				SortDirection.ASCENDING);
		System.out.println("System Config Data . " + entities.size());
		if(entities.size() > 0 )
		{
			System.out.println("Have Data.");
			sysconfig = SystemConfigService.AddDataSystemConfig(entities.get(0));
		}else {
			System.out.println("No Data.");
			sysconfig = new SystemConfig();
			Entity m_entity = DataStoreControl.CreateEntity(SystemConfig.class);
			m_entity = SystemConfigService.FlashData(m_entity, sysconfig);
			DataStoreControl.SaveEntity(m_entity);
			//sysconfig = SystemConfigService.AddDataSystemConfig(m_entity);
		}
		
		return sysconfig;
	}

	@Override
	public void ApplySystemConfig(SystemConfig sysConfig) {
		// TODO Auto-generated method stub
		Entity d_systemConfig = null;
		
		try {
			d_systemConfig = DataStoreControl.EditEntity(sysConfig.getKind(), sysConfig.getKeyID());
			d_systemConfig = SystemConfigService.FlashData(d_systemConfig, sysConfig);
			DataStoreControl.SaveEntity(d_systemConfig);
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		if(entities.size() > 0 )
		{
			System.out.println("Save Data.");
			entities.set(0, SystemConfigService.FlashData(entities.get(0), sysConfig));
			// = SystemConfigService.AddDataSystemConfig(entities.get(0));
			DataStoreControl.SaveEntity(entities.get(0));
		
		}
		*/
	}

}
