package com.kic.hrm.server;

import com.kic.hrm.client.GreetingService;
import com.kic.hrm.client.presenter.RegisterPresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeService;
import com.kic.hrm.data.model.Employee.property;
import com.kic.hrm.shared.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

	@Override
	public boolean ApplyLeaving(String input) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Server ask Is coming");
		//SentEmail();
		//LoadDatastore();
		//SaveDatastore();
		//TestOAth2();
		//AppIdentityCredential.Builder()
		//DriveAccessingServiceImpl.RUN();
		//m_driveAcc.RUN();
		System.out.println("Test Drive");
		
	
		return false;
	}
		
	@Override
	public boolean EnableOauth(Boolean input) {
		// TODO Auto-generated method stub
		System.out.println("S| EnableOauth Mathod. : " + input);
		
		
		//OauthServiceImpl.TestOAth2();
		
		return true;
	}

	// TODO #11: implement login helper methods in service implementation	

	@Override
	public String getUserEmail(final String token) {
			final UserService userService = UserServiceFactory.getUserService();
			final User user = userService.getCurrentUser();
			if (null != user) {
				return user.getEmail();
			} else {
				return "noreply@sample.com";
			}
	}

	@Override
	public LoginInfo login(String requestUri) {
		
		System.out.println("Test");
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		final LoginInfo loginInfo = new LoginInfo();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setName(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}
		
	@Override
	public LoginInfo loginDetails(String token) {
		//System.out.print(token);
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;

		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				log.log(Level.SEVERE, ex.getMessage());
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					log.log(Level.SEVERE, ex.getMessage());
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		final LoginInfo loginInfo = new LoginInfo();
		
		try {
			JsonFactory f = new JsonFactory();
			com.fasterxml.jackson.core.JsonParser jp;
			
			jp = f.createJsonParser(r.toString());
			
			//jp = f.createJsonParser(r.toString());
			jp.nextToken();
			//jp.nextToken()
			//JsonToken.
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				final String fieldname = jp.getCurrentName();
				jp.nextToken();
				if ("picture".equals(fieldname)) {
					loginInfo.setPictureUrl(jp.getText());
				} else if ("name".equals(fieldname)) {
					loginInfo.setName(jp.getText());
				} else if ("email".equals(fieldname)) {
					loginInfo.setEmailAddress(jp.getText());
				}
			}
			
		} catch (JsonParseException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (com.fasterxml.jackson.core.JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginInfo;
	}

	@Override
	public String googleDrive(String token) {
		// TODO Auto-generated method stub
		DriveServiceImpl.RUN(token);
		
		return null;
	}

	@Override
	public String QuickTest(String testParametor) {
		// TODO Auto-generated method stub
		DataStoreControllingServiceImpl.Process();
		
		return testParametor;
	}

	@Override
	public String Register(String userID) {
		// TODO Auto-generated method stub
		
		return userID;
	}

	@Override
	public Employee addProfile(Employee userEmployee, state registerMode) {
		// TODO Auto-generated method stub
		Entity d_employee = null;
		if(registerMode == state.add)
			d_employee = DataStoreControl.CreateEntity(Employee.class);
		else if(registerMode == state.edit) {
			try {
				d_employee = DataStoreControl.editEntity(userEmployee.getKind(), userEmployee.getKeyID());
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		//System.out.println(d_employee.toString() + " : " + d_employee.getProperty(property.employeeID.toString()));
		 
		d_employee = EmployeeService.FlashData(d_employee, userEmployee);  
		DataStoreControl.Save(d_employee);
		 
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

	// TODO #11:> end	

}

