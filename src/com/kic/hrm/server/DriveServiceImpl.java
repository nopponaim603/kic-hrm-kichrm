package com.kic.hrm.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.Oauth2.Userinfo;

//import com.google.api.gwt.services.
public class DriveServiceImpl {
	
	//private static String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";
	private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";
	public static void RUN(String token) {
		System.out.println("RUNNN");
	
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credential = new GoogleCredential().setAccessToken(token);
		Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(APPLICATION_NAME).build();
		
		InsertFile(service);
		
		System.out.println("End InsertFile");
	}
	
	  /**
	   * Exception thrown when no user ID could be retrieved.
	   */
	  private static class NoUserIdException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	  }
	  
	  /**
	   * Send a request to the UserInfo API to retrieve the user's information.
	   *
	   * @param credentials OAuth 2.0 credentials to authorize the request.
	   * @return User's information.
	   * @throws NoUserIdException An error occurred.
	   */
	  static Userinfo getUserInfo(Credential credentials)
	      throws NoUserIdException, IOException {
	    Oauth2 userInfoService =
	        new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credentials).build();
	    Userinfo userInfo = null;
	    userInfo = userInfoService.userinfo();
	    
	    if (userInfo != null && userInfo.get() != null) {
	      return userInfo;
	    } else {
	      throw new NoUserIdException();
	    }
	  }
	  
	
	public static void InsertFile(Drive service)  {
		try {
			System.out.println("File ID: ");  
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
		      System.out.println("InsertFile | An error occurred: " + e);
		}
		
	}
	
	  /**
	    * Print a file's metadata.
	    *
	    * @param service Drive API service instance.
	    * @param fileId ID of the file to print metadata for.
	    */
	  public static void printFile(Drive service, String fileId) {
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
	  public static  List<File> retrieveAllFiles(Drive service) throws IOException {
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

	public static InputStream downloadFile(Drive service, File file) {
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
	
	/*
	 public static void main(String[] args) throws Exception{
	        // TODO Auto-generated method stub

	        HttpTransport httpTransport = new NetHttpTransport();
	        JacksonFactory jsonFactory = new JacksonFactory();

	        Collection<String> SCOPES = new ArrayList<String>();
	        SCOPES.add("https://www.googleapis.com/auth/admin.directory.group");
	        SCOPES.add("https://www.googleapis.com/auth/admin.directory.user");


	        GoogleCredential credential =  new GoogleCredential.Builder()
	            .setTransport(httpTransport)
	            .setJsonFactory(jsonFactory)
	            .setServiceAccountId("244204474315-l08ah6g350oofeosi7p8pqmotlrmgion.apps.googleusercontent.com")
	            .setServiceAccountUser("244204474315-l08ah6g350oofeosi7p8pqmotlrmgion@developer.gserviceaccount.com")
	            .setServiceAccountScopes(SCOPES)
	            .setServiceAccountPrivateKeyFromP12File(
	                    new java.io.File("C:\\Users\\nbaser\\Desktop\\76ba0ac39b06e8419bbb670734f3b2affeec43b2-privatekey.p12"))
	            .build();


	        Directory directory = new Directory.Builder(httpTransport, jsonFactory, credential).build();

//	      Directory.Users.List list = directory.users().list();
//	      list.setDomain("yourDomain.com");
//	      Users users = list.execute();


	        //Directory.Users.Get user = directory.users().get("nishant.baser@ahold.com");
	        //user.get

	        Directory.Groups.Get group = directory.groups().get("ausa.googleams.group@ahold.com");
	        Group groups = group.execute();

//	      Directory.Members.List list = directory.members().list("ausa.googleams.group@ahold.com");

//	      Members members = list.execute();

//	      System.out.println(members);
	    }
	 */
}
