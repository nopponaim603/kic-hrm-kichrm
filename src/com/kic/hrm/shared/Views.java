package com.kic.hrm.shared;

import java.util.logging.Logger;

import com.google.api.services.plus.model.Activity;
import com.kic.hrm.server.GAEJCronServlet;

/**
 * Utility methods to print to the command line.
 * 
 * @author Yaniv Inbar
 */

public class Views {
	
	private static final Logger _logger = Logger.getLogger(GAEJCronServlet.class.getName());
	public static void header1(String name) {
		    System.out.println();
		    System.out.println("================== " + name + " ==================");
		    System.out.println();
		  }

	public  static void header2(String name) {
		    System.out.println();
		    System.out.println("~~~~~~~~~~~~~~~~~~ " + name + " ~~~~~~~~~~~~~~~~~~");
		    System.out.println();
		  }
	  
		  public static void show(Activity activity, boolean onServer) {
		    System.out.println("id: " + activity.getId());
		    System.out.println("url: " + activity.getUrl());
		    System.out.println("content: " + activity.getObject().getContent());
		    
		    if(onServer)
		    {
		    	_logger.severe("id: " + activity.getId());
		    	_logger.severe("url: " + activity.getUrl());
		    	_logger.severe("content: " + activity.getObject().getContent());
		    }
		  }
}
