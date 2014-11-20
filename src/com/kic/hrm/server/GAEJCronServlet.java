package com.kic.hrm.server;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.model.Activity;
import com.kic.hrm.shared.Views;

@SuppressWarnings("serial")
public class GAEJCronServlet extends HttpServlet   {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		_logger.info("doGet.");
		
		
	}
		
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String jwt = req.getParameter("jwt");
		_logger.severe("doPost. " + jwt);
		
		
		
		doGet(req, resp);	
		
		_logger.severe("After doPost. " + req);
	}
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");

		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = req.getParameterNames();

		while (parameterNames.hasMoreElements()) {

			String paramName = parameterNames.nextElement();
			out.write(paramName);
			out.write("n");

			String[] paramValues = req.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				out.write("t" + paramValue);
				out.write("n");
			}

		}

		out.close();

	}

	/**
	* Be sure to specify the name of your application. If the application name is {@code null} or
	* blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	*/
	private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";
	private static final Logger _logger = Logger.getLogger(GAEJCronServlet.class.getName());
	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;
	/** Global instance of the JSON factory. */
	private static JsonFactory JSON_FACTORY;
	//= JacksonFactory.getDefaultInstance();
	  
	/** E-mail address of the service account. */
	private static final String SERVICE_ACCOUNT_EMAIL = "392232398516-7nei78mpn8rl47pknpofrv4rtmt0id96@developer.gserviceaccount.com";	    
	  
	private static Plus plus;
	
	void processAuthServiceAccounts() {
		try {
					httpTransport = new NetHttpTransport();
					//httpTransport.
					//_logger.severe("create http : press");
					JSON_FACTORY = new JacksonFactory();
					
					//BUG
					String p12Content ="";
					//= File.readFirstLine(new File("xz-plasma-weft-8-4f36c102c352.p12"), Charset.defaultCharset());
			        if (p12Content.startsWith("Please")) {
			        	
			        	_logger.severe("File is Missing");
			          //System.err.println(p12Content);
			          //System.exit(1);
			        }else _logger.severe("IN File is : " + p12Content);
			        
					
			        // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
			        
			        GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
			            .setJsonFactory(JSON_FACTORY)
			            .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
			            .setServiceAccountScopes(Collections.singleton(PlusScopes.PLUS_ME))
			            .setServiceAccountPrivateKeyFromP12File(new File("xz-plasma-weft-8-4f36c102c352.p12"))
			            //.setTokenServerEncodedUrl("https://accounts.google.com/o/oauth2/token")
			            //.setServiceAccountUser("noppon.w@vr.camt.info")
			            .build();
			        
			        
			        _logger.severe("Token : A " + credential.getAccessToken() + " ReToken" + credential.getRefreshToken());
			     // set up global Plus instance
			        plus = new Plus.Builder(httpTransport, JSON_FACTORY, credential)
			            .setApplicationName(APPLICATION_NAME).build();
			        // run commands
			        getActivity();
			        
			        
			        // success!
			        
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	
	/** Get an activity for which we already know the ID. */
	private static void getActivity() throws IOException {
	    // A known public activity ID
	    String activityId = "4f36c102c352bcec6c8ee5b40028dc8b6f6602a3";
	    // We do not need to be authenticated to fetch this activity
	    //View.header1("Get an explicit public activity by ID");
	    Activity activity = plus.activities().get(activityId).execute();
	    Views.show(activity,true);
	}
	
	void processAuthServiceAccountsPL2(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			
			_logger.severe("Cronhas been executed");
			String token = GoogleJsonWebToken.CreateJsonToken();
			
			_logger.severe("Token is : " + token);
			String url = "https://accounts.google.com/o/oauth2/" + token;
			resp.sendRedirect(url);
			
			String reURI = req.getRequestURI();
			 req.setAttribute("token", token);
	        //set and forward the HTTP request and response
			 RequestDispatcher dispatcher = req.getRequestDispatcher("https://accounts.google.com/o/oauth2/" + token);
	        if (dispatcher != null) {
	                dispatcher.forward(req, resp);
	        }
	        
			_logger.severe("URI is : " + reURI);
			
			//Put your logic here
			//BEGIN
			//DriveServiceImpl.runByCronService();
			//END
		
		}
		catch (Exception ex) {
			//Log any exceptions in your Cron Job
			_logger.severe("error call CreateJsonToken : " + ex.getMessage());
		}
		
	}
}

