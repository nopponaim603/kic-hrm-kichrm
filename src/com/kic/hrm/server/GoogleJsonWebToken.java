package com.kic.hrm.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.text.MessageFormat;
import java.util.logging.Logger;

import com.google.api.client.json.webtoken.JsonWebToken;
import com.google.api.client.util.Base64;
import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthSigner;
import com.google.gwt.http.client.URL;

public class GoogleJsonWebToken {
	
	private static final Logger _logger = Logger.getLogger(DriveServiceImpl.class.getName());
	
	private String doTokenExchange(String jwt) throws IOException {
		String token = "";
		/*
		  URLFetchService service = URLFetchServiceFactory.getURLFetchService();
		  URL url = new URL(this.tokenEndpoint);
		  HTTPRequest request =
		      new HTTPRequest(url, HTTPMethod.POST, FetchOptions.Builder.doNotFollowRedirects()
		          .setDeadline(10.0));
		  HTTPHeader header = new HTTPHeader("Content-Type", "application/x-www-form-urlencoded");
		  request.setHeader(header);

		  String payload = "grant_type=assertion&assertion_type=";
		  payload += SignedJsonAssertionToken.GRANT_TYPE_VALUE;
		  payload += "&assertion=";
		  payload += jwt;
		  request.setPayload(payload.getBytes());
		  HTTPResponse response = service.fetch(request);
		  JsonParser parser = new JsonParser();
		  String token =
		      parser.parse(new String(response.getContent())).getAsJsonObject().get("access_token")
		          .getAsString();
		  
		  */
		 
		return token;
	}
	
	String OAuthOne() throws OAuthException {
		String token = "";
		GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		oauthParameters.setOAuthConsumerKey("APPCONSTANTS.Google.CONSUMER_KEY");

		OAuthSigner signer;
		//if ("APPCONSTANTS.Google.USE_RSA_SIGNING" != "") {
             
		signer = new OAuthRsaSha1Signer("APPCONSTANTS.Google.CONSUMER_SECRET");
				
		//} else {
		//	oauthParameters.setOAuthConsumerSecret("APPCONSTANTS.Google.CONSUMER_SECRET");
		//	signer = new OAuthHmacSha1Signer();
		//}
     
		GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(signer);
		oauthParameters.setScope("APPCONSTANTS.Google.SCOPES");
		oauthHelper.getUnauthorizedRequestToken(oauthParameters);
   	 	String requestUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
   	 	token = oauthHelper.getAccessToken(oauthParameters);
      
   	 	return token;
	}
	
	public static String CreateJsonToken() {
		 String header = "{\"alg\":\"RS256\"}";
		 String claimTemplate = "'{'\"iss\": \"{0}\", \"sub\": \"{1}\", \"scope\": \"{2}\", \"aud\": \"{3}\", \"exp\": \"{4}\"'}', \"iat\": \"{5}\"'}'";
		 StringBuffer token = null;
		    try {
		      token = new StringBuffer();

		      //Encode the JWT Header and add it to our string to sign
		      token.append(Base64.encodeBase64URLSafeString(header.getBytes("UTF-8")));

		      //Separate with a period
		      token.append(".");

		      //Create the JWT Claims Object
		      /*
		      {
				  "iss":"761326798069-r5mljlln1rd4lrbhg75efgigp36m78j5@developer.gserviceaccount.com",
				  "sub":"some.user@somecorp.com"
				  "scope":"https://www.googleapis.com/auth/prediction",
				  "aud":"https://accounts.google.com/o/oauth2/token",
				  "exp":1328554385,
				  "iat":1328550785
				}
		      */
		      String[] claimArray = new String[6];
		      //iss
		     // claimArray[0] = DriveServiceImpl.SERVICE_ACCOUNT_EMAIL;
		      claimArray[1] = "noppon.w@vr.camt.info";
		      claimArray[2] = "https://www.googleapis.com/auth/drive.file";
		      claimArray[3] = "https://accounts.google.com/o/oauth2/token";
		      claimArray[4] = Long.toString( ( System.currentTimeMillis()/1000 ) + 5000);
		      claimArray[5] = Long.toString( ( System.currentTimeMillis()/1000 ) );
		      //Long.toString( ( System.currentTimeMillis()/1000 ) + 300)
		      
		      _logger.severe("Time exp: " + claimArray[4] + " | Time iat: " + claimArray[5]);
		      
		      MessageFormat claims;
		      claims = new MessageFormat(claimTemplate);
		      String payload = claims.format(claimArray);

		      //Add the encoded claims object
		      token.append(Base64.encodeBase64URLSafeString(payload.getBytes("UTF-8")));

		      //Load the private key from a keystore
		      KeyStore keystore = KeyStore.getInstance("JKS");
		      keystore.load(new FileInputStream("./path/to/keystore.jks"), "keystorepassword".toCharArray());
		      PrivateKey privateKey = (PrivateKey) keystore.getKey("certalias", "privatekeypassword".toCharArray());

		      //Sign the JWT Header + "." + JWT Claims Object
		      Signature signature = Signature.getInstance("SHA256withRSA");
		      signature.initSign(privateKey);
		      signature.update(token.toString().getBytes("UTF-8"));
		      String signedPayload = Base64.encodeBase64URLSafeString(signature.sign());

		      //Separate with a period
		      token.append(".");

		      //Add the encoded signature
		      token.append(signedPayload);

		      System.out.println(token.toString());

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    return token.toString();
	}
	
	public static void Encode(String email, String certificateFilePath)
	    {
		   /*
		  String[] test =  {
			   "iss":"761326798069-r5mljlln1rd4lrbhg75efgigp36m78j5@developer.gserviceaccount.com",
			   "scope":"https://www.googleapis.com/auth/devstorage.readonly",
			   "aud":"https://accounts.google.com/o/oauth2/token",
			   "exp":1328554385,
			   "iat":1328550785
			}
		  */
		  // String test;
		   /*
	        var utc0 = new DateTime(1970,1,1,0,0,0,0, DateTimeKind.Utc);
	        var issueTime = DateTime.Now;

	        var iat = (int)issueTime.Subtract(utc0).TotalSeconds;
	        var exp = (int)issueTime.AddMinutes(55).Subtract(utc0).TotalSeconds; // Expiration time is up to 1 hour, but lets play on safe side

	       

	        var certificate = new X509Certificate2(certificateFilePath, "notasecret");

	        var privateKey = certificate.Export(X509ContentType.Cert);
*/
	       // return JsonWebToken.Encode(payload, privateKey, JwtHashAlgorithm.RS256);
	}
}
