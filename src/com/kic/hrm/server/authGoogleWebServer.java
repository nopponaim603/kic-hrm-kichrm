package com.kic.hrm.server;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;


import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.json.
//import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.Oauth2.Userinfo;
//import com.google.api.services.oauth2.model.Userinfoplus;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class authGoogleWebServer {
	  // Path to client_secrets.json which should contain a JSON document such as:
	  //   {
	  //     "web": {
	  //       "client_id": "[[YOUR_CLIENT_ID]]",
	  //       "client_secret": "[[YOUR_CLIENT_SECRET]]",
	  //       "auth_uri": "https://accounts.google.com/o/oauth2/auth",
	  //       "token_uri": "https://accounts.google.com/o/oauth2/token"
	  //     }
	  //   }
	  @SuppressWarnings("unused")
	private static final String CLIENTSECRETS_LOCATION = "client_secret_392232398516-0kkqbokr4hkp3ou3s6spr9u78r1ens93.apps.googleusercontent.com.json";
	  private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	  
	  private static final List<String> SCOPES = Arrays.asList(
	      "https://www.googleapis.com/auth/drive",
	      "https://www.googleapis.com/auth/drive.file",
	      "https://www.googleapis.com/auth/userinfo.email",
	      "https://www.googleapis.com/auth/userinfo.profile");

	  private static GoogleAuthorizationCodeFlow flow = null;

	  /**
	   * Exception thrown when an error occurred while retrieving credentials.
	   */
	  public static class GetCredentialsException extends Exception {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected String authorizationUrl;

	    /**
	     * Construct a GetCredentialsException.
	     *
	     * @param authorizationUrl The authorization URL to redirect the user to.
	     */
	    public GetCredentialsException(String authorizationUrl) {
	      this.authorizationUrl = authorizationUrl;
	    }

	    /**
	     * Set the authorization URL.
	     */
	    public void setAuthorizationUrl(String authorizationUrl) {
	      this.authorizationUrl = authorizationUrl;
	    }

	    /**
	     * @return the authorizationUrl
	     */
	    public String getAuthorizationUrl() {
	      return authorizationUrl;
	    }
	  }

	  /**
	   * Exception thrown when a code exchange has failed.
	   */
	  public static class CodeExchangeException extends GetCredentialsException {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
	     * Construct a CodeExchangeException.
	     *
	     * @param authorizationUrl The authorization URL to redirect the user to.
	     */
	    public CodeExchangeException(String authorizationUrl) {
	      super(authorizationUrl);
	    }

	  }

	  /**
	   * Exception thrown when no refresh token has been found.
	   */
	  public static class NoRefreshTokenException extends GetCredentialsException {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
	     * Construct a NoRefreshTokenException.
	     *
	     * @param authorizationUrl The authorization URL to redirect the user to.
	     */
	    public NoRefreshTokenException(String authorizationUrl) {
	      super(authorizationUrl);
	    }

	  }

	  /**
	   * Exception thrown when no user ID could be retrieved.
	   */
	  public static class NoUserIdException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	  }

	  /**
	   * Retrieved stored credentials for the provided user ID.
	   *
	   * @param userId User's ID.
	   * @return Stored Credential if found, {@code null} otherwise.
	   */
	  static Credential getStoredCredentials(String userId) {
	    // TODO: Implement this method to work with your database. Instantiate a new
	    // Credential instance with stored accessToken and refreshToken.
	    throw new UnsupportedOperationException();
	  }

	  /**
	   * Store OAuth 2.0 credentials in the application's database.
	   *
	   * @param userId User's ID.
	   * @param credentials The OAuth 2.0 credentials to store.
	   */
	  static void storeCredentials(String userId, Credential credentials) {
	    // TODO: Implement this method to work with your database.
	    // Store the credentials.getAccessToken() and credentials.getRefreshToken()
	    // string values in your database.
	    throw new UnsupportedOperationException();
	  }

	  /**
	   * Build an authorization flow and store it as a static class attribute.
	   *
	   * @return GoogleAuthorizationCodeFlow instance.
	   * @throws IOException Unable to load client_secrets.json.
	   */
	  static GoogleAuthorizationCodeFlow getFlow(String CLIENT_ID , String CLIENT_SECRET) throws IOException {
	    if (flow == null) {
	      HttpTransport httpTransport = new NetHttpTransport();
	      JacksonFactory jsonFactory = new JacksonFactory();
	      
	      // GoogleClientSecrets.load(jsonFactory,
          //   authGoogleWebServer.class.getResourceAsStream(CLIENTSECRETS_LOCATION));
     
	/*      
	      InputStream is = authGoogleWebServer.class.getResourceAsStream(CLIENTSECRETS_LOCATION);
	      Reader rd = new InputStreamReader(is);
	    		  
	      GoogleClientSecrets clientSecrets =  GoogleClientSecrets.load(jsonFactory, rd);
*/
	      //GoogleClientSecrets.
	      flow =
	          new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, SCOPES)
	              .setAccessType("offline").setApprovalPrompt("force").build();
	    }
	    return flow;
	    
	  }

	  /**
	   * Exchange an authorization code for OAuth 2.0 credentials.
	   *
	   * @param authorizationCode Authorization code to exchange for OAuth 2.0
	   *        credentials.
	   * @return OAuth 2.0 credentials.
	   * @throws CodeExchangeException An error occurred.
	   */
	  static Credential exchangeCode(String CLIENT_ID , String CLIENT_SECRET , String authorizationCode)
	      throws CodeExchangeException {
	    try {
	      GoogleAuthorizationCodeFlow flow = getFlow(CLIENT_ID ,CLIENT_SECRET);
	      GoogleTokenResponse response =
	          flow.newTokenRequest(authorizationCode).setRedirectUri(REDIRECT_URI).execute();
	      return flow.createAndStoreCredential(response, null);
	    } catch (IOException e) {
	      System.err.println("An error occurred: " + e);
	      throw new CodeExchangeException(null);
	    }
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

	  /**
	   * Retrieve the authorization URL.
	   *
	   * @param emailAddress User's e-mail address.
	   * @param state State for the authorization URL.
	   * @return Authorization URL to redirect the user to.
	   * @throws IOException Unable to load client_secrets.json.
	   */
	  public static String getAuthorizationUrl(String CLIENT_ID , String CLIENT_SECRET ,String emailAddress, String state) throws IOException {
	    GoogleAuthorizationCodeRequestUrl urlBuilder =
	        getFlow(CLIENT_ID,CLIENT_SECRET).newAuthorizationUrl().setRedirectUri(REDIRECT_URI).setState(state);
	    urlBuilder.set("user_id", emailAddress);
	    return urlBuilder.build();
	  }

	  /**
	   * Retrieve credentials using the provided authorization code.
	   *
	   * This function exchanges the authorization code for an access token and
	   * queries the UserInfo API to retrieve the user's e-mail address. If a
	   * refresh token has been retrieved along with an access token, it is stored
	   * in the application database using the user's e-mail address as key. If no
	   * refresh token has been retrieved, the function checks in the application
	   * database for one and returns it if found or throws a NoRefreshTokenException
	   * with the authorization URL to redirect the user to.
	   *
	   * @param authorizationCode Authorization code to use to retrieve an access
	   *        token.
	   * @param state State to set to the authorization URL in case of error.
	   * @return OAuth 2.0 credentials instance containing an access and refresh
	   *         token.
	   * @throws NoRefreshTokenException No refresh token could be retrieved from
	   *         the available sources.
	   * @throws IOException Unable to load client_secrets.json.
	   */
	  public static Credential getCredentials(String CLIENT_ID , String CLIENT_SECRET,String authorizationCode, String state)
	      throws CodeExchangeException, NoRefreshTokenException, IOException {
	    String emailAddress = "";
	    try {
	      Credential credentials = exchangeCode(CLIENT_ID,CLIENT_SECRET,authorizationCode);
	      Userinfo userInfo = getUserInfo(credentials);
	      String userId = userInfo.get().toString();
	      
	      //emailAddress = userInfo.getEmail();
	      
	      if (credentials.getRefreshToken() != null) {
	        storeCredentials(userId, credentials);
	        return credentials;
	      } else {
	        credentials = getStoredCredentials(userId);
	        if (credentials != null && credentials.getRefreshToken() != null) {
	          return credentials;
	        }
	      }
	    } catch (CodeExchangeException e) {
	      e.printStackTrace();
	      // Drive apps should try to retrieve the user and credentials for the current
	      // session.
	      // If none is available, redirect the user to the authorization URL.
	      e.setAuthorizationUrl(getAuthorizationUrl(CLIENT_ID,CLIENT_SECRET,emailAddress, state));
	      throw e;
	    } catch (NoUserIdException e) {
	      e.printStackTrace();
	    }
	    // No refresh token has been retrieved.
	    String authorizationUrl = getAuthorizationUrl(CLIENT_ID,CLIENT_SECRET,emailAddress, state);
	    throw new NoRefreshTokenException(authorizationUrl);
	  }
}
