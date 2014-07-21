package com.kic.hrm.server;

import java.io.IOException;
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

import com.google.api.client.http.HttpResponseException;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

//DataStore import

import com.google.api.services.datastore.*;
//import com.google.api.services.datastore.

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

//import com.google.api.services.datastore.Datastore;

/* 
import com.google.appengine.api.datastore.GeoPt;
import com.google.api.services.datastore.model.EntityResult;

*/

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.Drive;

import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.FileList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;



import java.io.InputStream;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
	  // https://drive.google.com/file/d/0BxCzuY_jk0HhM1R3QzZuRVRlYWc/edit?usp=sharing
	/*
	  "auth_uri":"https://accounts.google.com/o/oauth2/auth" 
	    ,"client_secret":"i9IiT4L1wI9fysmYo_VX79ZK"
		,"token_uri" : "https://accounts.google.com/o/oauth2/token"
		,"client_email":"392232398516@project.googleusercontent.com"
		,"client_x509_cert_url":"https://www.googleapis.com/robot/v1/metadata/x509/392232398516@project.googleusercontent.com"
		,"client_id":"392232398516.project.googleusercontent.com"
		,"auth_provider_x509_cert_url":"https://www.googleapis.com/oauth2/v1/certs"
		
	*/

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
	public boolean ApplyLeaving(String input) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Server ask Is coming");
		//SentEmail();
		//LoadDatastore();
		TestOAth2();
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
	
	private  String CLIENT_ID = "392232398516-0kkqbokr4hkp3ou3s6spr9u78r1ens93.apps.googleusercontent.com";
	private  String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";

	private  String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	  
	// private static final String CLIENT_ID = "392232398516.project.googleusercontent.com";
	  private static final String API_KEY = "i9IiT4L1wI9fysmYo_VX79ZK";
	  private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";
	  
	public  void TestOAth2(){
		
		System.out.println("TestOAth2.");
		
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		   
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		   httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
		   .setAccessType("online")
		   .setApprovalPrompt("auto").build();
		    
		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		System.out.println("Please open the following URL in your browser then type the authorization code:");
		System.out.println("  " + url);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			//Try to throws 1
			String code = br.readLine();
		   
		   //Try to throws 2
		    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
		    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
		    
		    //Create a new authorized API client
		    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
		    
		    InsertFile(service);
		    
		}catch (HttpResponseException e) {
			if (e.getStatusCode() == 401) {
		        // Credentials have been revoked.
		        // TODO: Redirect the user to the authorization URL.
		        throw new UnsupportedOperationException();
		      }
		}catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		}
 
	} 
	
	void InsertFile( Drive service)  {
		try {
			//Insert a file  
		    File body = new File();
		    body.setTitle("My document");
		    body.setDescription("A test document");
		    body.setMimeType("text/plain");
		    
		    java.io.File fileContent = new java.io.File("document.txt");
		    FileContent mediaContent = new FileContent("text/plain", fileContent);
		    //Try to throws 3
		    File file = service.files().insert(body, mediaContent).execute();
		    System.out.println("File ID: " + file.getId());  
			
		} catch (HttpResponseException e) {
			if (e.getStatusCode() == 401) {
		        // Credentials have been revoked.
		        // TODO: Redirect the user to the authorization URL.
		        throw new UnsupportedOperationException();
		      }
		}catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		}
		
	}

	  /**
	    * Print a file's metadata.
	    *
	    * @param service Drive API service instance.
	    * @param fileId ID of the file to print metadata for.
	    */
	   void printFile(Drive service, String fileId) {
	    try {
	      File file = service.files().get(fileId).execute();

	      System.out.println("Title: " + file.getTitle());
	      System.out.println("Description: " + file.getDescription());
	      System.out.println("MIME type: " + file.getMimeType());
	    } catch (HttpResponseException e) {
	      if (e.getStatusCode() == 401) {
	        // Credentials have been revoked.
	        // TODO: Redirect the user to the authorization URL.
	        throw new UnsupportedOperationException();
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred: " + e);
	    }
	  }
	  
	  /**
	   * Retrieve a list of File resources.
	   *
	   * @param service Drive API service instance.
	   * @return List of File resources.
	   */
	  private  List<File> retrieveAllFiles(Drive service) throws IOException {
	    List<File> result = new ArrayList<File>();
	    Files.List request = service.files().list();

	    do {
	      try {
	        FileList files = request.execute();

	        result.addAll(files.getItems());
	        request.setPageToken(files.getNextPageToken());
	      } catch (IOException e) {
	        System.out.println("An error occurred: " + e);
	        request.setPageToken(null);
	      }
	    } while (request.getPageToken() != null &&
	             request.getPageToken().length() > 0);

	    return result;
	  }
	  
	  /**
	   * Download a file's content.
	   *
	   * @param service Drive API service instance.
	   * @param file Drive File instance.
	   * @return InputStream containing the file's content if successful,
	   *         {@code null} otherwise.
	   */
	@SuppressWarnings("unused")
	private static InputStream downloadFile(Drive service, File file) {
	    if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
	      try {
	        HttpResponse resp =
	            service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
	                .execute();
	        return resp.getContent();
	      } catch (IOException e) {
	        // An error occurred.
	        e.printStackTrace();
	        return null;
	      }
	    } else {
	      // The file doesn't have any content stored on Drive.
	      return null;
	    }
	  }

}
