package com.kic.hrm.server.businesslogic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CreateStartTimeLogCronServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(CreateStartTimeLogCronServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		_logger.log(Level.SEVERE,"TEST.");
		AttendanceServiceImpl.CreateDailyData();
		try {
			_logger.info("CreateStartTimeLogCronServlet doGet.");
		}catch (Exception e) {
			
		}
		

		//AttendanceServiceImpl.CreateDailyData();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
