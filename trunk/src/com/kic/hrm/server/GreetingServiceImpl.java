package com.kic.hrm.server;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kic.hrm.client.GreetingService;
import com.kic.hrm.shared.FieldVerifier;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

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
	public boolean ApplyLeaving(String input) {
		// TODO Auto-generated method stub
		
		System.out.println("Server ask Is coming");
		//SentEmail();
		LoadDatastore();
		return false;
	}
	
	private void SaveDatastore() {
		
		System.out.println("Save Datastore");
		
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	   	    
	    Entity employee = new Entity("Employee");
	    employee.setProperty("name", "Antonio Salieri");
	    employee.setProperty("hireDate", new Date());
	    
	    datastore.put(employee);
	    
	}
	
	private void LoadDatastore() {
		System.out.println("Load Datastore");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Employee");
	    query.addSort(Entity.KEY_RESERVED_PROPERTY, Query.SortDirection.DESCENDING);

	    for (final Entity entity : datastore.prepare(query).asIterable()) {
	       
	    	System.out.println("entity" + entity.toString());
	    	System.out.println("in entity by key name : " + entity.getProperty("name"));
	    	//String name = entity.getProperty("name").toString();
	    	//entity.getProperties()
	    	//Date date = entity.getProperty(propertyName)
	        final Map<String, Object> properties = entity.getProperties();
	        
	    	final String[] propertyNames = properties.keySet().toArray(
	            new String[properties.size()]);
	        for(final String propertyName : propertyNames) {
	           System.out.println("-> " + propertyName + ": " + entity.getProperty(propertyName));
	        }
	    }
		//Entity employee = datastore.get();
		
	}
	
	public void SentEmail() 
	{
		System.out.println("Server ask SentEmail");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "...";

		try {
		    Message msg = new MimeMessage(session);
		    msg.setFrom(new InternetAddress("noppon.w@vr.camt.info", "Example.com Admin"));
		    msg.addRecipient(Message.RecipientType.TO,
		     new InternetAddress("noppon.w@vr.camt.info", "Mr. User"));
		    msg.setSubject("Your Example.com account has been activated");
		    msg.setText(msgBody);
		    Transport.send(msg);
		    
		    System.out.println("Server ask SentEmail end");
		    
		} catch (AddressException e) {
			System.out.println("AddreesException : " + e);
		    // ...
		} catch (MessagingException e) {
			System.out.println("MessagingException : " + e);
		    // ...
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("UnsupportedEncodingException : " + e);
			e.printStackTrace();
		}
	}

}
