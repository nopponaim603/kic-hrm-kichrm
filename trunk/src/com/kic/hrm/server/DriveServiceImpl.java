package com.kic.hrm.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.Oauth2.Userinfo;



import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthSigner;
import com.kic.hrm.client.CloudHRM;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;





import java.io.FileOutputStream;
import java.io.OutputStream;



//import com.google.api.gwt.services.
public class DriveServiceImpl {
	
	private static final Logger _logger = Logger.getLogger(DriveServiceImpl.class.getName());
	//private static String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";

	/** Email of the Service Account */
	public static final String SERVICE_ACCOUNT_EMAIL = "392232398516-7nei78mpn8rl47pknpofrv4rtmt0id96@developer.gserviceaccount.com";

	/** Path to the Service Account's Private Key file */
	private static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "xz-plasma-weft-8-4f36c102c352.p12";
	private static final String SERVICE_ACCOUNT_JSON_FILE_PATH = "client_secrets.json";
	
	
	  private static String CLIENT_ID = "392232398516-hdd0r2biksrovka8a6v93roambr2b54r.apps.googleusercontent.com";
	  private static String CLIENT_SECRET = "CK3GHEBlWNYwVu5r717Q3VwL";

	  private static String[] REDIRECT_URI = {
		  	"http://xz-plasma-weft-8.appspot.com/humanresourcesmanagement/oauthWindow.html",
		  	"http://1-dot-xz-plasma-weft-8.appspot.com/humanresourcesmanagement/oauthWindow.html",
		  	"http://127.0.0.1:8888/humanresourcesmanagement/oauthWindow.html"
	  };
	  
	public static void RUN(String token) throws GeneralSecurityException, IOException {
		System.out.println("RUNNN");
		//System.out.println("Path : " + DriveServiceImpl.class.getResourceAsStream("/client_secret.json").toString() );
		_logger.severe("Token is : " + token);
		
		
		try {
			Drive service = BuildDriveAPIbyTOKEN(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				//getDriveService("noppon.w@vr.camt.info");
		
		//InsertFile(service);
		//printFile(service,"0BxCzuY_jk0HhUENoallIWHdqc28");
		//String fileID = "105Ti_vBb46tz6znc6O1zp_yA1HzgQ-q-SULUFlSeCWY";
		//printFile(service,fileID);
		//_logger.severe("End InsertFile");
		
		System.out.println("End InsertFile");
	}
	
	public static void getFile(String FileID) {
		
	}
	
	public static void runByCronService() {
		_logger.severe("runByCronService");
		//Drive service = getDriveService("noppon.w@vr.camt.info");
		_logger.severe("runByCronService2");
		//java.io.File f = new java.io.File("document.txt");
	}
	  /**
	   * Exception thrown when no user ID could be retrieved.
	   */
	  private static class NoUserIdException extends Exception {
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

	  static Drive BuildDriveAPIbyTOKEN(String token) throws Exception {
		  	HttpTransport httpTransport = new NetHttpTransport();
			//httpTransport.
			//_logger.severe("create http : press");
			JacksonFactory jsonFactory = new JacksonFactory();
			//_logger.severe("create http , json  : press");
			//GoogleCredential credential = new GoogleCredential().Builder().setAccessToken(token);
		 // System.out.println("Path : " + DriveServiceImpl.class.getResourceAsStream("/client_secret.json").toString() );
		  TokenResponse tokennn = new TokenResponse();
		  tokennn.setAccessToken(token);
		  
			Credential credential = createCredentialWithRefreshToken(httpTransport,jsonFactory,tokennn);
			//_logger.severe("create http , json , credential : press");
			Drive service = new Drive.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(CloudHRM.getAPPLICATION_NAME()).build();
			
			return service;
	  }

	  /** Global instance of the HTTP transport. */
	  private static HttpTransport httpTransport;
	  
	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	  
	  private static FileDataStoreFactory dataStoreFactory;
	  
	  /** Authorizes the installed application to access user's protected data. */
	  private static Credential authorize() throws Exception {
	
	    // load client secrets
	    GoogleClientSecrets clientSecrets =
	        GoogleClientSecrets.load(JSON_FACTORY,
	            new InputStreamReader(DriveServiceImpl.class.getResourceAsStream("/client_secret.json")));
	    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
	        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
	      System.out
	          .println("Enter Client ID and Secret from https://code.google.com/apis/console/?api=drive "
	              + "into drive-cmdline-sample/src/main/resources/client_secrets.json");
	      System.exit(1);
	    }
	    // set up authorization code flow
	    GoogleAuthorizationCodeFlow flow =
	        new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
	            Collections.singleton(DriveScopes.DRIVE_FILE)).setDataStoreFactory(dataStoreFactory)
	            .build();
	    // authorize
	    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	  }
	  
	  /**
	   * No need to go through OAuth dance, get an access token using the
	   * existing refresh token.
	   */
	  public static GoogleCredential createCredentialWithRefreshToken(HttpTransport transport,
	      JsonFactory jsonFactory, TokenResponse tokenResponse) {
		  GoogleClientSecrets credential = null;
	    return new GoogleCredential.Builder().setTransport(transport)
	        .setJsonFactory(jsonFactory)
	        .setClientSecrets(credential)
	        .build()
	        .setFromTokenResponse(tokenResponse);
	  }
	  
	  /**
	   * Build and returns a Drive service object authorized with the service accounts
	   * that act on behalf of the given user.
	   *
	   * @param userEmail The email of the user.
	   * @return Drive service object that is ready to make requests.
	   */
	public static Drive getDriveService(String userEmail) throws GeneralSecurityException,
	      IOException {
		  _logger.severe("File key : " +  new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH).exists() + " : " + new java.io.File(SERVICE_ACCOUNT_JSON_FILE_PATH).exists());
		 
		  HttpTransport httpTransport = new NetHttpTransport();
		  JacksonFactory jsonFactory = new JacksonFactory();
		  //Credential credentials 		  
			   /*
		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
		        .setAccessType("online")
		        .setApprovalPrompt("auto").build();
		    
		    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI[0]).build();
		    */
		  
		  Credential credential = new GoogleCredential.Builder()
		      .setTransport(httpTransport)
		      .setJsonFactory(jsonFactory)
		      .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
		      .setServiceAccountScopes(Arrays.asList(DriveScopes.DRIVE))
		      .setServiceAccountUser(userEmail)
		      .setServiceAccountPrivateKeyFromP12File(new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
		      //.setServiceAccountPrivateKeyFromPemFile(new java.io.File(SERVICE_ACCOUNT_JSON_FILE_PATH))
		      //.setClientSecrets("392232398516-hdd0r2biksrovka8a6v93roambr2b54r.apps.googleusercontent.com", "CK3GHEBlWNYwVu5r717Q3VwL")
		      .build();
		//  credential.setRefreshToken(GoogleJsonWebToken.CreateJsonToken());
		  /*
		  
		  Userinfo userInfo = null;
		try {
			userInfo = authGoogleWebServer.getUserInfo(credential);
		} catch (com.kic.hrm.server.authGoogleWebServer.NoUserIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      String userId = userInfo.get().toString();
	      _logger.severe("User" + userId + " : " + userInfo + 
	    		  " : re Token " + credential.getRefreshToken() + 
	    		  " token : " + credential.getTokenServerEncodedUrl());
	      //emailAddress = userInfo.getEmail();
	      
	      
	      if (credential.getRefreshToken() != null) {
	    	  authGoogleWebServer.storeCredentials(userId, credential);
	        //return credential;
	      } else {
	    	  credential = authGoogleWebServer.getStoredCredentials(userId);
	        if (credential != null && credential.getRefreshToken() != null) {
	          //return credential;
	        }
	      }
	      */
	      
	      Drive service = new Drive.Builder(httpTransport, jsonFactory, null)
	      .setHttpRequestInitializer(credential) .setApplicationName(CloudHRM.getAPPLICATION_NAME()).build();
		  		  
		  return service;
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
	      System.out.println("Description: " + file.getDescription());
	      System.out.println("MIME type: " + file.getMimeType());
	      
	      _logger.severe("Title: " + file.getTitle() + "\n" + "MIME type: " + file.getMimeType());
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
	  
	
	void TestDirectoryAPI(String[] args) throws Exception{
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


	       // Directory directory = new Directory.Builder(httpTransport, jsonFactory, credential).build();

//	      Directory.Users.List list = directory.users().list();
//	      list.setDomain("yourDomain.com");
//	      Users users = list.execute();


	        //Directory.Users.Get user = directory.users().get("nishant.baser@ahold.com");
	        //user.get

	      //  Directory.Groups.Get group = directory.groups().get("ausa.googleams.group@ahold.com");
	      //  Group groups = group.execute();

//	      Directory.Members.List list = directory.members().list("ausa.googleams.group@ahold.com");

//	      Members members = list.execute();

//	      System.out.println(members);
	    }

	void TestDriveAPI() throws IOException {
		
		HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	   
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
	        .setAccessType("online")
	        .setApprovalPrompt("auto").build();
	    
	    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI[0]).build();
	    
	    //AUTH.setOAuthWindowUrl(url);
	    
	    System.out.println("Please open the following URL in your browser then type the authorization code:");
	    System.out.println("  " + url);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String code = br.readLine();
	    
	    
	    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI[0]).execute();
	    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	    
	    //Create a new authorized API client
	    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();

	    //Insert a file  
	    File body = new File();
	    body.setTitle("My document");
	    body.setDescription("A test document");
	    body.setMimeType("text/plain");
	    
	    java.io.File fileContent = new java.io.File("document.txt");
	    FileContent mediaContent = new FileContent("text/plain", fileContent);

	    File file = service.files().insert(body, mediaContent).execute();
	    System.out.println("File ID: " + file.getId());

	}

	
}
