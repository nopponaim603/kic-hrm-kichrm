package com.kic.hrm.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.google.api.client.util.DateTime;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Children;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.FileIDLeaveLog;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLogService;

//import com.google.api.gwt.services.
public class DriveServiceImpl {
	
	private static final Logger _logger = Logger.getLogger(DriveServiceImpl.class.getName());
	//private static String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";

	/** Email of the Service Account */
	//public static final String SERVICE_ACCOUNT_EMAIL = "392232398516-7nei78mpn8rl47pknpofrv4rtmt0id96@developer.gserviceaccount.com";

	  private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";
	  
	public static void RUN(String token){
		System.out.println("RUNNN");
		//System.out.println("Path : " + DriveServiceImpl.class.getResourceAsStream("/client_secret.json").toString() );
		//_logger.severe("Token is : " + token);
		//getDriveService("noppon.w@vr.camt.info");
		Drive service = BuildDriveAPIbyTOKEN(token);
				
		try {
			//com.google.api.services.drive.Drive.Children.List items = service.children().list("0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
			List<String> listIDFile = printFilesInFolder(service,"0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
			
			for(String IDFile : listIDFile) {
				File thisFile = getFileFormGoogleDrive(service,IDFile);
				System.out.println("File Type : " + thisFile.getMimeType());
				if(thisFile.getMimeType().contentEquals("text/csv")) {
					System.out.println("This is CSV File.");
					System.out.println("Last update :" 
							+ thisFile.getLastModifyingUserName() 
							+ " : " + thisFile.getLastModifyingUser().getDisplayName() 
							+ " : " + thisFile.getLastViewedByMeDate().toString());
					
					System.out.println("This File is ID : " + 	thisFile.getId());
					//String getQuryIDformDataStore = "test_0BxCzuY_jk0HhQlNNRXJEdVJmRVU";
					boolean isFileAlready = false;
					Date FileLastUpdate = new Date(thisFile.getModifiedByMeDate().getValue());
					Date dateofFileinDatastore = null;
					
					List<Entity> ListFileID = DataStoreControl.Query(FileIDLeaveLog.class, SortDirection.DESCENDING);
					System.out.println("Size fileID Log : " + ListFileID.size());
							for(Entity en : ListFileID) {
								if(en.getProperty(FileIDLeaveLog.property.fileId.toString())
										.toString().contentEquals(thisFile.getId())) {
									System.out.println("File is Already in DataStore.");
									isFileAlready = true;
									dateofFileinDatastore = (Date)en.getProperty(FileIDLeaveLog.property.lastUpdate.toString());
									break;
								}
							}
					if(isFileAlready) {
						System.out.println("File is already.but double check if file have update.");
						//Test Time
						
						//DateTime TestSecound = thisFile.getLastViewedByMeDate();
						//DateTime TestSecound = new DateTime(dateofFileinDatastore.getTime());
						System.out.println("Time Modified : " + FileLastUpdate.toString() + " : " + dateofFileinDatastore.toString());
						
						if(FileLastUpdate.equals(dateofFileinDatastore)) {
							System.out.println("Is Updated to datastore.");
						}else {
							System.out.println("Is new update and save to datastore.");
							pushLeaveLogToDataStore(service,thisFile,true);
							saveFileID(thisFile.getId(),FileLastUpdate);
						}
					}else {
						System.out.println("Is New File LeaveLog. and Save to Datastore.");
						pushLeaveLogToDataStore(service,thisFile,false);
						saveFileID(thisFile.getId(),FileLastUpdate);
					}
					
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("End InsertFile");
	}
	static void saveFileID(String fileID,Date lastUpdate) {
		//Date time = new  Date(lastUpdate.getValue());
		Entity d_file = DataStoreControl.CreateEntity(FileIDLeaveLog.class);
		d_file.setProperty(FileIDLeaveLog.property.fileId.toString(), fileID);
		d_file.setProperty(FileIDLeaveLog.property.lastUpdate.toString(), lastUpdate);
		//System.out.println("DataTime : "+ lastUpdate.toStringRfc3339() + " : " + lastUpdate.getValue() + " : " + time.toString());
		
		
		DataStoreControl.SaveEntity(d_file);
	}
	
	static void pushLeaveLogToDataStore(Drive service,File thisFile,Boolean isAlready){
		InputStream fileInput = downloadFile(service, thisFile);
		if(fileInput != null) {
			Reader reader = new InputStreamReader(fileInput);
			List<String[]> LeaveLog = ReadCSVFile(reader);
			//printDataListinArry(LeaveLog);
			//List<Entity> startTimeLogLists = new ArrayList();
			for(String[] datalog : LeaveLog) {
				Entity d_starttimelog = null;
				d_starttimelog = DataStoreControl.CreateEntity(StartTimeLog.class);
				d_starttimelog = StartTimeLogService.FlashData(d_starttimelog, StartTimeLogService.AddCVSData(datalog));
				DataStoreControl.SaveEntity(d_starttimelog);
			}
			
			if(isAlready)
				System.out.println("New Logic to clean duplicate data.");
		}
		
		System.out.println("Save is Done.");
	}
	
 	public static String getFile(String token ,String FileID) {
		System.out.println("Come to Drive Service. \nToken is :" + token + " \nFile ID is : " + FileID);
		String problem = "None";
		Drive service = BuildDriveAPIbyTOKEN(token);
		
		File m_file = getFileFormGoogleDrive(service,FileID);
		if(m_file != null) {
			InputStream fileInput = downloadFile(service, m_file);
			if(fileInput != null) {
				Reader reader = new InputStreamReader(fileInput);
				List<String[]> DataLog = ReadCSVFile(reader);
				printDataListinArry(DataLog);
				
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
		
	static Drive BuildDriveAPIbyTOKEN(String token)  {
		  HttpTransport httpTransport = new NetHttpTransport();
			JacksonFactory jsonFactory = new JacksonFactory();
			GoogleCredential credential = new GoogleCredential().setAccessToken(token);
			Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(APPLICATION_NAME).build();
			return service;
	  }

	static File getFileFormGoogleDrive(Drive service ,String FileID) {
		try {
			File file = service.files().get(FileID).execute();
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	static List<String[]> ReadCSVFile(Reader reader) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		List<String[]> listData = new ArrayList<String[]>();
		
		
		try {
			br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {
				String[] tempLine;
				tempLine = line.split(cvsSplitBy);
				//System.out.println(tempLine.toString());
				listData.add(tempLine);	          
			};
			
			System.out.println("Skip header : " + listData.get(0)[0].toString());
			if(listData.get(0)[0].contentEquals("AC-No.")){
				listData.remove(0);
				System.out.println("Skip Header AC-No.");
			}
				
			//printDataListinArry
			
			System.out.println("CSV Read File is Done.What long data is : " + listData.size());
			return listData;
			//System.out.println("Count : " + country.length);
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("Done,Data is Null.");
		return null;
	}
	
	static void printDataListinArry(List<String[]> LeaveLog) {
		
		for(int x = 0 ; x < LeaveLog.size() ; x++)
		{
			System.out.print("Line : " + x + "Size : " + LeaveLog.get(x).length);
			for(int y = 0 ; y < LeaveLog.get(x).length; y++)
			{
				System.out.print("	| Value : " + LeaveLog.get(x)[y]);
			}
			System.out.println("");
		}
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
	private static List<String> printFilesInFolder(Drive service, String folderId)
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
