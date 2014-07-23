package com.kic.hrm.server;

import java.io.IOException;

import com.kic.hrm.client.GreetingService;
import com.kic.hrm.client.HumanResourcesManagement;
import com.kic.hrm.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.kic.hrm.shared.*;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;



public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		return false;
	}
		
	@Override
	public boolean EnableOauth(Boolean input) {
		// TODO Auto-generated method stub
		System.out.println("Server-side : EnableOauth Mathod. | " + input);
		
		try {
			OauthServiceImpl.TestOAth2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
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
		public LoginInfo login(final String requestUri) {
			
			final UserService userService = UserServiceFactory.getUserService();
			final User user = userService.getCurrentUser();
			final LoginInfo loginInfo = new LoginInfo();
			
			//System.out.println("Server coming url : " + requestUri);
			
			if (user != null) {
				loginInfo.setLoggedIn(true);
				loginInfo.setName(user.getEmail());
				loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
			} else {
				loginInfo.setLoggedIn(false);
				loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
			}
			
			//log.severe("Server coming url. " + userService.createLogoutURL(requestUri));
			return loginInfo;
		}

		private static final Logger log = Logger.getLogger(GreetingServiceImpl.class.getName());
		
		@Override
		public LoginInfo loginDetails(final String token) {
			
			
			/*
			  GoogleAuthorizationCodeFlow authFlow = Utils.initializeFlow();
			  
			  
			    Credential credential = authFlow.loadCredential(Utils.getUserId(this.getThreadLocalRequest()));
			    if (credential == null) {
			      // If we don't have a token in store, redirect to authorization screen.
			      resp.sendRedirect(
			          authFlow.newAuthorizationUrl().setRedirectUri(Utils.getRedirectUri(this.getThreadLocalRequest())).build());
			    //  return;
			    }
			*/
			
			String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;
			log.severe("An error message. " + url);
			System.out.println("Server coming url : " + url);
			//https://accounts.google.com/o/oauth2/auth?client_id=392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com&response_type=token&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.login&redirect_uri=http%3A%2F%2F127.0.0.1%3A8888%2Fhumanresourcesmanagement%2FoauthWindow.html
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
			
			JsonFactory f = new JsonFactory() {
				
				@Override
				public JsonParser createJsonParser(InputStream in, Charset charset)
						throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public JsonParser createJsonParser(Reader reader) throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public JsonParser createJsonParser(String value) throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public JsonParser createJsonParser(InputStream in) throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public JsonGenerator createJsonGenerator(OutputStream out, Charset enc)
						throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
			};
			
			JsonParser jp = null;
			try {
				jp = f.createJsonParser(r.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.log(Level.SEVERE, e.getMessage());
			}
			try {
				jp.nextToken();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return loginInfo;
		}

		// TODO #11:> end	

}

