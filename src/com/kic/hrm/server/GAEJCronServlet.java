package com.kic.hrm.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GAEJCronServlet extends HttpServlet   {
	
	private static final Logger _logger = Logger.getLogger(GAEJCronServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//_logger.severe("doGet.");
		
		try {
			/*
			_logger.severe("Cronhas been executed");
			String token = GoogleJsonWebToken.CreateJsonToken();
			
			_logger.severe("Token is : " + token);
			String url = "https://accounts.google.com/o/oauth2/" + token;
			resp.sendRedirect(url);
			//resp.
			String reURI = req.getRequestURI();
			 req.setAttribute("token", token);
	        //set and forward the HTTP request and response
	        
			 RequestDispatcher dispatcher = req.getRequestDispatcher("https://accounts.google.com/o/oauth2/" + token);
	        if (dispatcher != null) {
	                dispatcher.forward(req, resp);
	        }
	        
			_logger.severe("URI is : " + reURI);
			*/
			
			//Put your logic here
			//BEGIN
			
			DriveServiceImpl.runByCronService();
			
			//END
		}
		catch (Exception ex) {
			//Log any exceptions in your Cron Job
			_logger.severe("error call CreateJsonToken : " + ex.getMessage());
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String jwt = req.getParameter("jwt");
		_logger.severe("doPost. " + jwt);
		
		
		//req.
		//https://www.googleapis.com/plus/v1/people/userId?access_token=1/fFBGRNJru1FQd44AzqT3Zg
		
		//
		
		doGet(req, resp);	
		
		_logger.severe("After doPost. " + req);
	}
	
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");

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

}

