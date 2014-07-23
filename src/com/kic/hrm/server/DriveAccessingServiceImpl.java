package com.kic.hrm.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class DriveAccessingServiceImpl {
	
	  /**
	    * Build a Drive service object.
	    *
	    * @param credentials OAuth 2.0 credentials.
	    * @return Drive service object.
	    */
	  static Drive buildService(GoogleCredential credentials) {
	    HttpTransport httpTransport = new NetHttpTransport();
	    JacksonFactory jsonFactory = new JacksonFactory();

	    return new Drive.Builder(httpTransport, jsonFactory, credentials)
	        .build();
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
}
