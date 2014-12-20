package com.kic.hrm.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.JsonParseException;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.LoginInfo;
import com.kic.hrm.data.model.Employee.role;
import com.kic.hrm.data.model.EmployeeService;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.server.businesslogic.ProfileServiceImpl;

public class LoginServiceImpl {
	
	enum field {
		id,
		email,
		verified_email,
		name,
		given_name,
		family_name,
		link,
		picture,
		gender,
		locale,
		hd
		// Domain Name
	}
	
	private static final Logger log = Logger.getLogger(LoginServiceImpl.class.getName());
	
	public static String getUserEmail(final String token) {
			final UserService userService = UserServiceFactory.getUserService();
			final User user = userService.getCurrentUser();
			if (null != user) {
				return user.getEmail();
			} else {
				return "noreply@sample.com";
			}
	}
	
	public static LoginInfo login(String requestUri) {
		
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		final LoginInfo loginInfo = new LoginInfo();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setName(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			if(loginInfo.getEmailAddress() != null) {
				loginInfo.setEmployeeID(setEmployeeIDbyEmail(loginInfo.getEmailAddress()));
				System.out.println("Employee ID : " + loginInfo.getEmployeeID());
			}
				
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
			
			loginInfo.setEmployeeID(-1);
		}
		return loginInfo;
	}

	public static LoginInfo loginDetails(String token) {
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

		LoginInfo loginInfo = new LoginInfo();
		//System.out.println("New LoginInfo");
		try {
			JsonFactory f = new JsonFactory();
			com.fasterxml.jackson.core.JsonParser jp;
			System.out.println(r);
			jp = f.createJsonParser(r.toString());
			
			//jp = f.createJsonParser(r.toString());
			jp.nextToken();
			//jp.nextToken()
			//JsonToken.
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				final String fieldname = jp.getCurrentName();
				//System.out.println("field :" + fieldname);
				jp.nextToken();
				//"picture".equals(fieldname)
				if (field.email.toString().equals(fieldname)) {
					loginInfo.setEmailAddress(jp.getText());
					//System.out.println("email : " + loginInfo.getEmailAddress());
					//loginInfo.setEmployeeID(setEmployeeIDbyEmail(loginInfo.getEmailAddress()));
				} else if (field.name.toString().equals(fieldname)) {
					loginInfo.setName(jp.getText());
				} else if (field.picture.toString().equals(fieldname)) {
					loginInfo.setPictureUrl(jp.getText());
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
		
		loginInfo = setupEmployeeData(loginInfo);
		//loginInfo.setEmployeeID(setEmployeeIDbyEmail(loginInfo.getEmailAddress()));
		//System.out.println(" Role : " + loginInfo.getEmployeeRole().toString());
		
		return loginInfo;
	}
	
	public static LoginInfo MemberOrGuest(LoginInfo userInfo) {
		if (ProfileServiceImpl.isMember(userInfo.getEmailAddress())) {
			System.out.println("Email : " + userInfo.getEmailAddress()
					+ " is Member.");
			return userInfo;
		} else {
			userInfo.setEmployeeRole(role.Guest);
			userInfo.setName(role.Guest.toString() + " " + userInfo.getName());
			System.out.println("Email : " + userInfo.getEmailAddress()
					+ " is Guest!");
			return userInfo;
		}
	}
	
	private static LoginInfo setupEmployeeData(LoginInfo loginInfo) {
		System.out.println("email : " + loginInfo.getEmailAddress());
		//int employeeId = -1;
		List<Employee> m_employeeS = EmployeeService.Clone(DataStoreControl.Query(Employee.class 
				, SortDirection.DESCENDING
				,EmployeeService.findEmployeeByEmail(loginInfo.getEmailAddress())));
		
		System.out.println("Login Detecte User in list : " + m_employeeS.size());
		
		if(m_employeeS.size() == 1) {
			loginInfo.setEmployeeID( m_employeeS.get(0).getM_employeeID());
			loginInfo.setEmployeeRole(m_employeeS.get(0).getM_role());
		}
		
		return loginInfo;
	}
	
	private static int setEmployeeIDbyEmail(String emailAddress) {
		System.out.println("email : " + emailAddress);
		int employeeId = -1;
		List<Employee> m_employeeS = EmployeeService.Clone(DataStoreControl.Query(Employee.class 
				, SortDirection.DESCENDING
				,EmployeeService.findEmployeeByEmail(emailAddress)));
		
		System.out.println("Login Detecte User in list : " + m_employeeS.size());
		
		if(m_employeeS.size() == 1)
			employeeId = m_employeeS.get(0).getM_employeeID();
		
		return employeeId;
	}
	
	
}
