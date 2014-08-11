package com.kic.hrm.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Children;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

//import com.google.api.gwt.services.
public class DriveServiceImpl {
	
	private static final Logger _logger = Logger.getLogger(DriveServiceImpl.class.getName());
	//private static String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";

	/** Email of the Service Account */
	//public static final String SERVICE_ACCOUNT_EMAIL = "392232398516-7nei78mpn8rl47pknpofrv4rtmt0id96@developer.gserviceaccount.com";

	private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";

 	public static String getFile(String token ,String FileID) {
		System.out.println("Come to Drive Service. \nToken is :" + token + " \nFile ID is : " + FileID);
		String problem = "None";
		Drive service = BuildDriveAPIbyTOKEN(token);
		
		File m_file = getFileFormGoogleDrive(service,FileID);
		if(m_file != null) {
			InputStream fileInput = downloadFile(service, m_file);
			if(fileInput != null) {
				Reader reader = new InputStreamReader(fileInput);
				//List<String[]> DataLog = ReadCSVFile(reader);
				//printDataListinArry(DataLog);
				
				//Window.alert("Complete");
			}else {
				System.out.println("File have problem.");
				problem = "File have problem.";
			}
		}else 
		{
			System.out.println("File not Found.");
			problem = "File not Found.";
		}
		
		
		return problem;
		//printFile(service,FileID);
		
		//ReadCSVFile("29-7-2014.csv");
	}
		
	public static Drive BuildDriveAPIbyTOKEN(String token)  {
		  HttpTransport httpTransport = new NetHttpTransport();
			JacksonFactory jsonFactory = new JacksonFactory();
			GoogleCredential credential = new GoogleCredential().setAccessToken(token);
			Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(APPLICATION_NAME).build();
			return service;
	  }

	public static File getFileFormGoogleDrive(Drive service ,String FileID) {
		try {
			File file = service.files().get(FileID).execute();
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void InsertFile(Drive service)  {
		try {
			
			System.out.println("File ID: ?");  
			_logger.severe("File ID: ?");
			//Insert a file  
		    File body = new File();
		    body.setTitle("My document");
		    body.setDescription("A test document");
		    body.setMimeType("text/plain");
		    
		    java.io.File fileContent = new java.io.File("document.txt");
		    if(fileContent.exists()){
		    	 FileContent mediaContent = new FileContent("text/plain", fileContent);
				    //Try to throws 3
				    _logger.severe("before insert File" + service.toString() + " : "+ service.about());
				    File file = service.files().insert(body, mediaContent).execute();
				    System.out.println("File ID: " + file.getId());  
				   // _logger.severe("File ID: ?" + file.getId());
		    }else _logger.severe("InsertFile : File is not Exists.");
		   
		} catch (HttpResponseException e) {
			if (e.getStatusCode() == 401) {
		        // Credentials have been revoked.
		        // TODO: Redirect the user to the authorization URL.
		        throw new UnsupportedOperationException();
		      }
			 _logger.severe("HttpResponseException: " + e);
		}catch (IOException e) {
		      System.out.println("InsertFile | An error occurred: " + e);
		      _logger.severe("InsertFile | An error occurred: " + e);
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
	    _logger.severe("before get File");
	      File file = service.files().get(fileId).execute();
	      
	      System.out.println("Title: " + file.getTitle());
	      System.out.println("Description: " + file.getFileExtension());
	      System.out.println("MIME type: " + file.getMimeType());
	      //String content = FileUtils.readFileToString(file);
	      //_logger.severe("Title: " + file.getTitle() + "\n" + "MIME type: " + file.getMimeType());
	      
	      //service.getRequestFactory().
	      /*
	      InputStream test = downloadFile(service,file);
	      Reader      reader      = new InputStreamReader(test);
	      ReadCSVFile(reader);
	      
	      */
	      //System.out.println("Test : " + test.toString());
	      
	      //BufferedReader br = new BufferedReader(test);
	      //BufferedInputStream bis = new Bu
	      //test.
	      //new FileReader()
	      //System.out.println(test.toString());
	      
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

	 /**
	   * Print files belonging to a folder.
	   *
	   * @param service Drive API service instance.
	   * @param folderId ID of the folder to print files from.
	   */
	  @SuppressWarnings("rawtypes")
	
	  public static List<String> printFilesInFolder(Drive service, String folderId)
	      throws IOException {
	    Children.List request = service.children().list(folderId);
	    @SuppressWarnings("unchecked")
		List<String> listIDFile = new ArrayList();
	    
	    do {
	      try {
	        ChildList children = request.execute();

	        for (ChildReference child : children.getItems()) {
	          System.out.println("File Id: " + child.getId());
	          listIDFile.add(child.getId());
	        }
	        request.setPageToken(children.getNextPageToken());
	      } catch (IOException e) {
	        System.out.println("An error occurred: " + e);
	        request.setPageToken(null);
	      }
	    } while (request.getPageToken() != null &&
	             request.getPageToken().length() > 0);
	    
	    return listIDFile;
	  }
	  
}
