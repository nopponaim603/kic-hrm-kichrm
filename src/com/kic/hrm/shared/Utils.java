package com.kic.hrm.shared;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
//import com.google.api.client.json.jackson.
import com.google.api.client.util.Preconditions;
import com.google.api.services.plus.PlusScopes;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("deprecation")
public class Utils {
	 private static GoogleClientSecrets clientSecrets = null;
	  private static final Set<String> SCOPES = Collections.singleton(PlusScopes.PLUS_ME);
	  public static final UrlFetchTransport HTTP_TRANSPORT = new UrlFetchTransport();
	  public static final JacksonFactory JSON_FACTORY = new JacksonFactory();
	  public static final String MAIN_SERVLET_PATH = "/plussampleservlet";
	  public static final String AUTH_CALLBACK_SERVLET_PATH = "/oauth2callback";

	  private static GoogleClientSecrets getClientSecrets() throws IOException {
	    if (clientSecrets == null) {
	      clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	          new InputStreamReader(Utils.class.getResourceAsStream("/client_secrets.json")));
	      Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("Enter ")
	          && !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
	          "Download client_secrets.json file from https://code.google.com/apis/console/?api=plus "
	          + "into plus-preview-appengine-sample/src/main/resources/client_secrets.json");
	    }
	    return clientSecrets;
	  }

	  public static GoogleAuthorizationCodeFlow initializeFlow() throws IOException {
	    return new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT, JSON_FACTORY, getClientSecrets(), SCOPES).setCredentialStore(
	        new AppEngineCredentialStore()).setAccessType("offline").build();
	  }

	  static String getRedirectUri(HttpServletRequest req) {
	    GenericUrl requestUrl = new GenericUrl(req.getRequestURL().toString());
	    requestUrl.setRawPath(AUTH_CALLBACK_SERVLET_PATH);
	    return requestUrl.build();
	  }

	  public static String getUserId(HttpServletRequest req) {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    return user.getUserId();
	  }
}
