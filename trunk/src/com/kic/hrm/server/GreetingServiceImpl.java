package com.kic.hrm.server;

import com.kic.hrm.client.GreetingService;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.LeaveTaskService;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.data.model.StartTimeLogService;
import com.kic.hrm.server.businesslogic.ProfileServiceImpl;
import com.kic.hrm.server.businesslogic.RecordLog;
import com.kic.hrm.shared.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.mapsengine.MapsEngine;
import com.google.api.services.mapsengine.MapsEngineRequestInitializer;
import com.google.api.services.mapsengine.model.Feature;
import com.google.api.services.mapsengine.model.FeaturesListResponse;
import com.google.api.services.mapsengine.model.GeoJsonPoint;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;


@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@SuppressWarnings("unused")
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
		return LoginServiceImpl.loginDetails(token);
	}
	// #11:> end	
	
	// Add Edit Delete Profile
	@Override
	public Employee addProfile(Employee userEmployee, EmployeeQuota userQuota, state registerMode) {
		return ProfileServiceImpl.addProfile(userEmployee, userQuota, registerMode);
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

		    MapsEngine engine = BuildMapAPIbyTOKEN(testParametor);
		    
		    try {
				readFeaturesFromTable(engine);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		
		return testParametor;
	}
	
	private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";
	
	public static MapsEngine BuildMapAPIbyTOKEN(String token)  {
		
		  	HttpTransport httpTransport = new NetHttpTransport();
			JacksonFactory jsonFactory = new JacksonFactory();
			
			 // This request initializer will ensure the API key is sent with every HTTP request.
		    MapsEngineRequestInitializer apiKeyInitializer =
		        new MapsEngineRequestInitializer(PUBLIC_API_KEY);
		    
			GoogleCredential credential = new GoogleCredential().setAccessToken(token);
			MapsEngine service = new MapsEngine.Builder(httpTransport, jsonFactory, credential)
					.setMapsEngineRequestInitializer(apiKeyInitializer)
					.setApplicationName(APPLICATION_NAME)
					.build();
			
			return service;
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
					@SuppressWarnings("deprecation")
					int DeltaDay = tempTime.getDate();
					
					if(leavetask.getM_leavetype() == type.Leave)
						temp_Em.get(0).setM_leave(temp_Em.get(0).getM_leave() - DeltaDay);
					else if(leavetask.getM_leavetype() == type.Holiday)
						temp_Em.get(0).setM_holiday(temp_Em.get(0).getM_holiday() - DeltaDay);
					
					Entity newDumpData = DataStoreControl.EditEntity(temp_Em.get(0).getKind(), temp_Em.get(0).getKeyID());
					newDumpData = EmployeeQuotaService.FlashData(newDumpData, temp_Em.get(0));
					DataStoreControl.SaveEntity(newDumpData);
					
					UpdateStartLog(leavetask);
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
	
	private void UpdateStartLog(LeaveTask leavetask) {
	
		Filter startDate = new FilterPredicate(StartTimeLog.property.date.toString(),
		                      FilterOperator.GREATER_THAN_OR_EQUAL,
		                      leavetask.getM_start());
		
		Filter endDate = new FilterPredicate(StartTimeLog.property.date.toString(),
                FilterOperator.LESS_THAN_OR_EQUAL,
                leavetask.getM_end());
		
		Filter currentUser = new FilterPredicate(StartTimeLog.property.employeeID.toString(),
                FilterOperator.EQUAL,
                leavetask.getM_employeeID());
		
		Filter isAbsence = new FilterPredicate(StartTimeLog.property.type.toString(),
                FilterOperator.EQUAL,
                StartTimeLog.type.Absence.toString());
				
		Filter m_composite = StartTimeLogService.CompositeAndFilter(startDate,endDate,currentUser,isAbsence);
		List<Entity> temp_entity = DataStoreControl.Query(StartTimeLog.class, SortDirection.DESCENDING, m_composite);
		List<StartTimeLog> m_starttimelog = StartTimeLogService.Clone(temp_entity);
		
		
		
		for(StartTimeLog log : m_starttimelog) {
			if(leavetask.getM_leavetype() == type.Leave)
				log.setM_type(StartTimeLog.type.Leave);
			else if(leavetask.getM_leavetype() == type.Holiday)
				log.setM_type(StartTimeLog.type.Holiday);
			
			try {
				Entity newDumpData = DataStoreControl.EditEntity(log.getKind(), log.getKeyID());
				newDumpData = StartTimeLogService.FlashData(newDumpData, log);
				DataStoreControl.SaveEntity(newDumpData);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//temp_entity = null;
		//for(StartTimeLog log : m_starttimelog) {
		//	temp_entity = StartTimeLogService.FlashData(temp_entity, log);
		//}
		
		//StartTimeLogService
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

	
	
	 static final String SAMPLE_TABLE_ID = "12421761926155747447-06672618218968397709";
	 static final String PUBLIC_API_KEY = "4f36c102c352bcec6c8ee5b40028dc8b6f6602a3";

	  public  static void readFeaturesFromTable(MapsEngine me) throws IOException {
		    // Query the table for offices in WA that are within 100km of Perth.
		    FeaturesListResponse featResp =  me.tables().features().list(SAMPLE_TABLE_ID)
		        .setVersion("published")
		        .setWhere("State='WA' AND ST_DISTANCE(geometry,ST_POINT(115.8589,-31.9522)) < 100000")
		        .execute();

		    for (Feature feat : featResp.getFeatures()) {
		      System.out.println(
		          "Properties: " + feat.getProperties().toString() + "\n\t" +
		          "Name: " + feat.getProperties().get("Fcilty_nam") + "\n\t" +
		          "Geometry Type: " + feat.getGeometry().getType());

		      if (feat.getGeometry() instanceof GeoJsonPoint)  {
		        GeoJsonPoint point = (GeoJsonPoint) feat.getGeometry();
		        System.out.println("\t" +
		            "Longitude: " + point.getCoordinates().get(0) + ", " +
		            "Latitude: " +  point.getCoordinates().get(1));
		      } else {
		        System.out.println("Only points are expected in this table!");
		        return;
		      }
		    }
	}

}

