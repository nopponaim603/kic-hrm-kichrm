package com.kic.hrm.server.businesslogic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Iterable;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.FileIDStartTimeLog;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLogService;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.CSVServiceImpl;
import com.kic.hrm.server.DataStoreControl;
import com.kic.hrm.server.DriveServiceImpl;

public class RecordLog {
	
	@SuppressWarnings("unused")
	
	//private static Dictionary<Integer,Integer> m_employeeAbsence;
	
	//"0BxCzuY_jk0HhQlNNRXJEdVJmRVU"
	public static String SaveStartTime(String token,String folderID) {
		String problem = "None";
		Drive service = DriveServiceImpl.BuildDriveAPIbyTOKEN(token);
		
		try {
			//com.google.api.services.drive.Drive.Children.List items = service.children().list("0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
			List<String> listIDFile = DriveServiceImpl.printFilesInFolder(service,folderID);
			
			for(String IDFile : listIDFile) {
				File thisFile = DriveServiceImpl.getFileFormGoogleDrive(service,IDFile);
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
					
					List<Entity> ListFileID = DataStoreControl.Query(FileIDStartTimeLog.class, SortDirection.DESCENDING);
					System.out.println("Size fileID Log : " + ListFileID.size());
							for(Entity en : ListFileID) {
								if(en.getProperty(FileIDStartTimeLog.property.fileId.toString())
										.toString().contentEquals(thisFile.getId())) {
									System.out.println("File is Already in DataStore.");
									isFileAlready = true;
									dateofFileinDatastore = (Date)en.getProperty(FileIDStartTimeLog.property.lastUpdate.toString());
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

		System.out.println("End Insert Log");
		//List<Entity> ListFileID = DataStoreControl.Query(FileIDStartTimeLog.class, SortDirection.DESCENDING);
		
		return problem;
	}
	
	static void saveFileID(String fileID,Date lastUpdate) {
		//Date time = new  Date(lastUpdate.getValue());
		Entity d_file = DataStoreControl.CreateEntity(FileIDStartTimeLog.class);
		d_file.setProperty(FileIDStartTimeLog.property.fileId.toString(), fileID);
		d_file.setProperty(FileIDStartTimeLog.property.lastUpdate.toString(), lastUpdate);
		//System.out.println("DataTime : "+ lastUpdate.toStringRfc3339() + " : " + lastUpdate.getValue() + " : " + time.toString());
		DataStoreControl.SaveEntity(d_file);
	}
	
	static void pushLeaveLogToDataStore(Drive service,File thisFile,Boolean isAlready){
		
		Date keepDate = null;
		Map<Integer, Integer> m_employeeAbsence = new HashMap<Integer, Integer>();
		//Load Data Employee key : ID and Value : ID
		//m_employeeAbsence
		
		
		
		InputStream fileInput = DriveServiceImpl.downloadFile(service, thisFile);
		if(fileInput != null) {
			Reader reader = new InputStreamReader(fileInput);
			List<String[]> LeaveLog = CSVServiceImpl.ReadCSVFile(reader);
			//printDataListinArry(LeaveLog);
			//List<Entity> startTimeLogLists = new ArrayList();
			for(String[] datalog : LeaveLog) {
				Entity d_starttimelog = null;
				d_starttimelog = DataStoreControl.CreateEntity(StartTimeLog.class);
				
				StartTimeLog startTime = StartTimeLogService.AddCVSData(datalog);
				
				ConditionOnTime(startTime);
				
				d_starttimelog = StartTimeLogService.FlashData(d_starttimelog, startTime);
				DataStoreControl.SaveEntity(d_starttimelog);
				
				m_employeeAbsence.remove(startTime.getM_employeeID());
				keepDate = startTime.getM_date();
			}
			
			for(Integer employeeID : m_employeeAbsence.keySet()) {
				StartTimeLog startTime = new StartTimeLog();
				
				startTime.setM_date(keepDate);
				startTime.setM_employeeID(employeeID);
				startTime.setM_type(type.Absence);
				//
			}
			
			if(isAlready)
				System.out.println("New Logic to clean duplicate data.");
		}
		
		System.out.println("Save is Done.");
	}
	
	public static void ConditionOnTime(StartTimeLog m_startTimeLog) {
		m_startTimeLog.setM_type(type.OnTime);
		
	}
	
	
}
