package com.kic.hrm.server;

import com.kic.hrm.client.GreetingService;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.EmployeeService;
import com.kic.hrm.data.model.Employee.property;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.LeaveTaskService;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.businesslogic.RecordLog;
import com.kic.hrm.shared.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.net.URLConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.JsonParseException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	private static final Logger log = Logger.getLogger(GreetingServiceImpl.class.getName());

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	// TODO #11: implement login helper methods in service implementation	
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
		return LoginServiceImpl.loginDetails(token);
	}
	// TODO #11:> end	
	
	// Add Edit Delete Profile
	@Override
	public String Register(String userID) {
		// TODO Auto-generated method stub
		
		return userID;
	}

	@Override
	public Employee addProfile(Employee userEmployee, EmployeeQuota userQuota, state registerMode) {
		// TODO Auto-generated method stub
		Entity d_employee = null;
		Entity d_quota = null;
		if(registerMode == state.add){
			d_employee = DataStoreControl.CreateEntity(Employee.class);
			
			
			d_quota = DataStoreControl.CreateEntity(EmployeeQuota.class);
			
		}	
		else if(registerMode == state.edit) {
			try {
				d_employee = DataStoreControl.EditEntity(userEmployee.getKind(), userEmployee.getKeyID());
				d_quota = DataStoreControl.EditEntity(userQuota.getKind() , userQuota.getKeyID());
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		//System.out.println(d_employee.toString() + " : " + d_employee.getProperty(property.employeeID.toString()));
		 
		d_employee = EmployeeService.FlashData(d_employee, userEmployee);  
		DataStoreControl.SaveEntity(d_employee);
		
		d_quota = EmployeeQuotaService.FlashData(d_quota, userQuota);  
		DataStoreControl.SaveEntity(d_quota);
		return userEmployee;
	}

	@Override
	public ArrayList<String> UpdateList(String targetEntity) {
		// TODO Auto-generated method stub
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class, SortDirection.ASCENDING);
		results = EmployeeService.Clone(entities);
		ArrayList<String> EmployeeID = new ArrayList<String>();
		
		
		for(Employee em : results) {
			EmployeeID.add(String.valueOf(em.getM_employeeID()));
		}
		
		
		return EmployeeID;
	}

	@Override
	public Employee getProfile(Integer targetEmployee) {
		// TODO Auto-generated method stub
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class, SortDirection.ASCENDING);
		results = EmployeeService.Clone(entities);
		Employee temp_employee = null;
		for(Employee em : results) {
			if(em.getM_employeeID() == targetEmployee)
			{
				temp_employee = em;
				break;
			}
		}
		return temp_employee;
	}

	@Override
	public boolean deleteProfile(Integer targetEmployee) {
		// TODO Auto-generated method stub
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class, SortDirection.ASCENDING,EmployeeService.findEmployeeByEmployeeID(targetEmployee));
		results = EmployeeService.Clone(entities);
		Employee temp_employee = null;
		for(Employee em : results) {
			if(em.getM_employeeID() == targetEmployee)
			{
				temp_employee = em;
				break;
			}
		}
		
		DataStoreControl.DeleteEntity(temp_employee.getKind(), temp_employee.getKeyID());
		
		
		entities = DataStoreControl.Query(EmployeeQuota.class, SortDirection.ASCENDING,EmployeeQuotaService.findEmployeeByEmployeeID(targetEmployee));
		List<EmployeeQuota> resultsQuota = EmployeeQuotaService.Clone(entities);
		
		EmployeeQuota temp_employeeQuota = null;
		for(EmployeeQuota emQT : resultsQuota) {
			if(emQT.getM_employeeID() == targetEmployee)
			{
				temp_employeeQuota = emQT;
				break;
			}
		}
		DataStoreControl.DeleteEntity(temp_employeeQuota.getKind(), temp_employeeQuota.getKeyID());
		
		return false;
	}
	// Add Edit Delete Profile > end
	
	// SaveCSV
	@Override
	public String saveCSVtoDrive(String token, String FolderID) {
		// TODO Auto-generated method stub
		String getresouce = RecordLog.SaveStartTime(token, FolderID);
		return getresouce;
	}
	// SaveCSV > end

	
	@Override
	public boolean ApplyLeaving(String input) throws IOException {
		// TODO Auto-generated method stub
		DriveServiceImpl.RUN(input);	
		return false;
	}
		
	@Override
	public boolean EnableOauth(Boolean input) {
		// TODO Auto-generated method stub
		System.out.println("S| EnableOauth Mathod. : " + input);
		
		
		//OauthServiceImpl.TestOAth2();
		
		return true;
	}

	@Override
	public String googleDrive(String token) {
		// TODO Auto-generated method stub
		DriveServiceImpl.RUN(token);
		
		return "Test Pass";
	}
	
	@Override
	public String getFileFormGoogleDrive(String token, String idFile) {
		// TODO Auto-generated method stub
		String getresouce = DriveServiceImpl.getFile(token, idFile);
		return getresouce;
	}

	@Override
	public String QuickTest(String testParametor) {
		// TODO Auto-generated method stub
		//DataStoreControllingServiceImpl.Process();
		return testParametor;
	}

	@Override
	public EmployeeQuota getEmployeeQuota(int employeeID) {
		// TODO Auto-generated method stub
		EmployeeQuota m_employeeQuota = new EmployeeQuota();
		
		List<EmployeeQuota> m_employeeQuotas = EmployeeQuotaService.Clone(DataStoreControl.Query(EmployeeQuota.class 
				, SortDirection.DESCENDING
				,EmployeeQuotaService.findEmployeeByEmployeeID(employeeID)));
		System.out.println("EmployeeQuota :" + m_employeeQuotas.size());
		
		if(m_employeeQuotas.size() == 1)
			for(EmployeeQuota temp : m_employeeQuotas)
				m_employeeQuota = temp;
		
		//System.out.println("IS : " + m_employeeQuota + " : " + m_employeeQuota.getM_leave());
		return m_employeeQuota;
	}

	@Override
	public boolean createLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		Entity d_LeaveTask = null;
		d_LeaveTask = DataStoreControl.CreateEntity(LeaveTask.class);
		d_LeaveTask = LeaveTaskService.FlashData(d_LeaveTask, leavetask);
		DataStoreControl.SaveEntity(d_LeaveTask);
		
		return true;
	}

	@Override
	public boolean approveLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		Entity d_LeaveTask = null;
		d_LeaveTask = DataStoreControl.CreateEntity(LeaveTask.class);
		try {
			d_LeaveTask = DataStoreControl.EditEntity(leavetask.getKind(), leavetask.getKeyID());
			d_LeaveTask = LeaveTaskService.FlashData(d_LeaveTask, leavetask);
			DataStoreControl.SaveEntity(d_LeaveTask);
			
			if(leavetask.getM_leaveprogress() == progress.Complete) {
				List<EmployeeQuota> temp_Em = null;
				List<Entity> temp_E;
				temp_E = DataStoreControl.Query(EmployeeQuota.class, SortDirection.ASCENDING, EmployeeQuotaService.findEmployeeByEmployeeID(leavetask.getM_employeeID()));
				temp_Em = EmployeeQuotaService.Clone(temp_E);
				
				if(temp_Em.size() == 1) {
					Long deltaTime = leavetask.getM_end().getTime() - leavetask.getM_start().getTime();
					Date tempTime = new Date(deltaTime);
					int DeltaDay = tempTime.getDate();
					
					if(leavetask.getM_leavetype() == type.Leave)
						temp_Em.get(0).setM_leave(temp_Em.get(0).getM_leave() - DeltaDay);
					else if(leavetask.getM_leavetype() == type.Holiday)
						temp_Em.get(0).setM_holiday(temp_Em.get(0).getM_holiday() - DeltaDay);
					
					Entity newDumpData = DataStoreControl.EditEntity(temp_Em.get(0).getKind(), temp_Em.get(0).getKeyID());
					
					newDumpData = EmployeeQuotaService.FlashData(newDumpData, temp_Em.get(0));
					
					DataStoreControl.SaveEntity(newDumpData);
					//if()
				}
			}
			
			return true;
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return false;
	}

	@Override
	public List<LeaveTask> getLeaveTask(progress InProgress ,int targetID) {
		// TODO Auto-generated method stub
		List<LeaveTask> results;// = new ArrayList<Employee>();
		
		List<Entity> entities = null;
		if(InProgress == progress.LeaderApprove)
			entities = DataStoreControl.Query(LeaveTask.class
							, SortDirection.ASCENDING
							,LeaveTaskService.findLeaderByLeaderID(targetID));
		else if(InProgress == progress.HRApprove)
			entities = DataStoreControl.Query(LeaveTask.class
					, SortDirection.ASCENDING
					,LeaveTaskService.findHRApprove());
		else entities = DataStoreControl.Query(LeaveTask.class
				, SortDirection.ASCENDING
				,LeaveTaskService.findEmployeeByEmployeeID(targetID));
		results = LeaveTaskService.Clone(entities);
		
		return results;
	}

	@Override
	public boolean deleteLeaveTask(LeaveTask leavetask) {
		// TODO Auto-generated method stub
		System.out.println("delete Leave Task id : " + leavetask.getKeyID());
		DataStoreControl.DeleteEntity(leavetask.getKind(), leavetask.getKeyID());
		return true;
	}





	

}

