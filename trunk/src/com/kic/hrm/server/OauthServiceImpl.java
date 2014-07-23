package com.kic.hrm.server;

import java.io.IOException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

public class OauthServiceImpl {
	

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

	private static String CLIENT_ID = "392232398516-0kkqbokr4hkp3ou3s6spr9u78r1ens93.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";

	private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	  
	// private static final String CLIENT_ID = "392232398516.project.googleusercontent.com";
	  private static final String API_KEY = "i9IiT4L1wI9fysmYo_VX79ZK";
	  private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";
	  
	public static GoogleAuthorizationCodeFlow TestOAth2() throws IOException{
		
		System.out.println("TestOAth2.");
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		//GoogleAuthorizationCodeFlow flow = authGoogleWebServer.getFlow(CLIENT_ID,CLIENT_SECRET);
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		   httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
		   .setAccessType("online")
		   .setApprovalPrompt("auto").build();
				
		
		
		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		System.out.println("Please open the following URL in your browser then type the authorization code:");
		System.out.println("  " + url);
			
		String test = flow.getTokenServerEncodedUrl();
		
		System.out.println("Code ? : " + test);
		return flow;
	} 
	
	public static void PushCode(String input , GoogleAuthorizationCodeFlow flow) {
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// "4/9pMzM__H0up4cBVMYgj58xyfVbPM.4lLqZ0FyTBgRgrKXntQAax00m9yyjgI"
				try {
					//Try to throws 1
					String code = input;
					//= br.readLine();
				   
				   //Try to throws 2
				    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
				    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
				    
				    //Create a new authorized API client
				    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
				    System.out.println(code);
				    DriveAccessingServiceImpl.InsertFile(service);
				    
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
    /*
  HttpWebRequest request = (HttpWebRequest)WebRequest.Create(
            new Uri(file.DownloadUrl));
        authenticator.ApplyAuthenticationToRequest(request);
        HttpWebResponse response = (HttpWebResponse) request.GetResponse();
        if (response.StatusCode == HttpStatusCode.OK) {
          return response.GetResponseStream();
        } else {
          Console.WriteLine(
              "An error occurred: " + response.StatusDescription);
          return null;
        }
        */
	
	
}
