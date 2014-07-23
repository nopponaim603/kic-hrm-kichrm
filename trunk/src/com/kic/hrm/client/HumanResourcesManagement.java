package com.kic.hrm.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.logging.server.RemoteLoggingServiceUtil.RemoteLoggingException;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.requestfactory.server.Logging;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.auth.oauth
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;

import com.google.gwt.user.client.Window;

import java.io.IOException;
import java.util.logging.Level;
//import com.sw_engineering_candies.oauth2.shared.LoginInfo;
import java.util.logging.Logger;

import sun.util.logging.resources.logging;

import com.kic.hrm.shared.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("unused")
public class HumanResourcesManagement implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	
//https://accounts.google.com/AccountChooser?service=lso&continue=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fauth%3Fscope%3Dhttps%3A%2F%2Fwww.googleapis.com%2Fauth%2Fplus.me%26response_type%3Dtoken%26redirect_uri%3Dhttp%3A%2F%2Fgwt-oauth2.googlecode.com%2Fsvn%2Ftrunk%2Fsamples%2Fmulti%2Fdemo%2FoauthWindow.html%26client_id%3D452237527106.apps.googleusercontent.com%26hl%3Den-US%26from_login%3D1%26as%3D2c063392a3d1ac6d&btmpl=authsub&hl=en
	//http://1-dot-xz-plasma-weft-8.appspot.com/humanresourcesmanagement/oauthWindow.html
	//client_id=392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com

	  private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

	  // This app's personal client ID assigned by the Google APIs Console
	  // (http://code.google.com/apis/console).
	  private static final String GOOGLE_CLIENT_ID = "392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com";

	  // The auth scope being requested. This scope will allow the application to
	  // identify who the authenticated user is.
	  private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.me";
	  //private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.login";
	  
	  private static final String REDIRECT_URI = "http://xz-plasma-weft-8.appspot.com/oauth2callback";
	// TODO #05: add constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID)
		private static final Auth AUTH = Auth.get();
		
			
		
		private static final String APP_CLIENT_URL = "http://royalbear42.appspot.com/";
		// TODO #05:> end

		// TODO #06: define controls for login
		private final HorizontalPanel loginPanel = new HorizontalPanel();
		private final Anchor signInLink = new Anchor("");
		private final Image loginImage = new Image();
		private final TextBox nameField = new TextBox();
		// TODO #06:> end
		
		
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	// TODO #07: add helper methods for Login, Logout and AuthRequest

		private void loadLogin(final LoginInfo loginInfo) {
			signInLink.setHref(loginInfo.getLoginUrl());
			signInLink.setText("Please, sign in with your Google Account");
			signInLink.setTitle("Sign in");
		}

		private void loadLogout(final LoginInfo loginInfo) {
			signInLink.setHref(loginInfo.getLogoutUrl());
			signInLink.setText(loginInfo.getName());
			signInLink.setTitle("Sign out");
		}

		private void addGoogleAuthHelper() throws IOException {
			
			
			//GoogleAuthorizationCodeFlow authFlow = Utils.initializeFlow();
			
			
			final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
			.withScopes(PLUS_ME_SCOPE);
			
			AUTH.login(	req, new Callback<String, Throwable>() {
				
				@Override
				public void onSuccess(String result) {
					// TODO Auto-generated method stub
					if (!result.isEmpty()) {
						
						rpcService.loginDetails(result, new AsyncCallback<LoginInfo>() {
							
							@Override
							public void onFailure(final Throwable caught) {
								GWT.log("loginDetails -> onFailure : " + caught.getMessage());
								log.severe(caught.getMessage());
							}

							@Override
							public void onSuccess(final LoginInfo loginInfo) {
								System.out.println("Google H : on Success");
								
								signInLink.setText(loginInfo.getName());
								nameField.setText(loginInfo.getName());
								signInLink.setStyleName("login-area");
								loginImage.setUrl(loginInfo.getPictureUrl());
								loginImage.setVisible(false);
								loginPanel.add(loginImage);
								loginImage.addLoadHandler(new LoadHandler() {
									@Override
									public void onLoad(final LoadEvent event) {
										final int newWidth = 24;
										final com.google.gwt.dom.client.Element element = event
												.getRelativeElement();
										if (element.equals(loginImage.getElement())) {
											final int originalHeight = loginImage.getOffsetHeight();
											final int originalWidth = loginImage.getOffsetWidth();
											if (originalHeight > originalWidth) {
												loginImage.setHeight(newWidth + "px");
											} else {
												loginImage.setWidth(newWidth + "px");
											}
											loginImage.setVisible(true);
										}
									}
								});
							}
						});
					}
				}
				
				@Override
				public void onFailure(Throwable reason) {
					// TODO Auto-generated method stub
					GWT.log("Error -> loginDetails\n" + reason.getMessage());
				}
			});
			
		}

		// TODO #07:> end
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */

	/**
	 * This is the entry point method.
	 */
		private static final Logger log = Logger.getLogger(HumanResourcesManagement.class.getName());
		GreetingServiceAsync rpcService;
		
	public void onModuleLoad() {
		
		System.out.println("Client HRM Start here.");
		
		rpcService = GWT.create(GreetingService.class);
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(rpcService, eventBus);
	    appViewer.go(RootPanel.get());
	    
	    System.out.println("onModuleLoad Complete.");
	    	    
	    
	    addGoogleAuth();
	    
	    // Export the JS method that can be called in pure JS
	    Auth.export();
	    addGoogleAuthNative();

	    addClearTokens();
	    
	    //log.log(Level.INFO,"Test");
	    /*
	     * log.info("Test");
	    log.warning("A warning message.");
        log.severe("An error message.");
        */
	    	// TODO #08: create login controls
	 		nameField.setEnabled(false);	

	 		signInLink.getElement().setClassName("login-area");
	 		signInLink.setTitle("sign out");
	 		loginImage.getElement().setClassName("login-area");
	 		loginPanel.add(signInLink);
	 		RootPanel.get("loginPanelContainer").add(loginPanel);
	 		final StringBuilder userEmail = new StringBuilder();
	 		System.out.println(GWT.getHostPageBaseURL());
	 		//APP_CLIENT_URL
	 		rpcService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	 			@Override
	 			public void onFailure(final Throwable caught) {
	 				GWT.log("login -> onFailure");
	 				System.out.println("on Failure");
	 			}

	 			@Override
	 			public void onSuccess(final LoginInfo result) {
	 				if (result.getName() != null && !result.getName().isEmpty()) {
	 					System.out.println("on Success");
	 					//addGoogleAuthHelper();
						loadLogout(result);
	 				
	 					nameField.setEnabled(true);
	 				} else {
	 					loadLogin(result);
	 				}
	 				userEmail.append(result.getEmailAddress());
	 			}
	 		});
	 		// TODO #08:> end
	}
	
	// //////////////////////////////////////////////////////////////////////////
	  // AUTHENTICATING WITH GOOGLE ///////////////////////////////////////////////
	  // //////////////////////////////////////////////////////////////////////////


	  // Adds a button to the page that asks for authentication from Google.
	  private void addGoogleAuth() {
	    // Since the auth flow requires opening a popup window, it must be started
	    // as a direct result of a user action, such as clicking a button or link.
	    // Otherwise, a browser's popup blocker may block the popup.
	    Button button = new Button("Authenticate with Google");
	    button.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
	        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
	            .withScopes(PLUS_ME_SCOPE);

	        // Calling login() will display a popup to the user the first time it is
	        // called. Once the user has granted access to the application,
	        // subsequent calls to login() will not display the popup, and will
	        // immediately result in the callback being given the token to use.
	        AUTH.login(req, new Callback<String, Throwable>() {
	          @Override
	          public void onSuccess(String token) {
	            Window.alert("Got an OAuth token:\n" + token + "\n"
	                + "Token expires in " + AUTH.expiresIn(req) + " ms\n");
	          }

	          @Override
	          public void onFailure(Throwable caught) {
	            Window.alert("Error:\n" + caught.getMessage());
	          }
	        });
	      }
	    });
	    RootPanel.get().add(button);
	  }
		
	  
	// //////////////////////////////////////////////////////////////////////////
	  // AUTHENTICATING WITH INSTAGRAM ////////////////////////////////////////////
	  // //////////////////////////////////////////////////////////////////////////

	  private static final String INSTAGRAM_AUTH_URL = "https://instagram.com/oauth/authorize/";

	  // This app's personal client ID assigned by Instagram
	  // (http://instagr.am/developer/manage/)
	  private static final String INSTAGRAM_CLIENT_ID = "833d710a11064825a55a2374d4990d26";

	  // The auth scopes being requested.

	  // This scope will allow the application to read comments as if it was the
	  // user.
	  private static final String INSTAGRAM_COMMENTS_SCOPE = "comments";
	  // This scope will allow the application to read likes as if it was the user.
	  private static final String INSTAGRAM_LIKES_SCOPE = "likes";

	  // Adds a button to the page that asks for authentication from Instagram.
	  private void addInstagramAuth() {
	    // Since the auth flow requires opening a popup window, it must be started
	    // as a direct result of a user action, such as clicking a button or link.
	    // Otherwise, a browser's popup blocker may block the popup.
	    Button button = new Button("Authenticate with Instagram");
	    button.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
	        final AuthRequest req = new AuthRequest(INSTAGRAM_AUTH_URL, INSTAGRAM_CLIENT_ID)
	            .withScopes(INSTAGRAM_COMMENTS_SCOPE, INSTAGRAM_LIKES_SCOPE)
	            // Instagram expects a plus-delimited list of scopes
	            .withScopeDelimiter("+");
	        AUTH.login(req, new Callback<String, Throwable>() {
	          @Override
	          public void onSuccess(String token) {
	            Window.alert("Got an OAuth token:\n" + token + "\n"
	                + "Token expires in " + AUTH.expiresIn(req) + " ms\n");
	          }

	          @Override
	          public void onFailure(Throwable caught) {
	            Window.alert("Error:\n" + caught.getMessage());
	          }
	        });
	      }
	    });
	    RootPanel.get().add(button);
	  }
	  
	  
	  // //////////////////////////////////////////////////////////////////////////
	  // AUTHENTICATING WITH GOOGLE (Using native JavaScript) /////////////////////
	  // //////////////////////////////////////////////////////////////////////////

	  // Adds a button to the page that asks for authentication from Google, using
	  // native JS.
	  // This demonstrates how a GWT app can export the JS function so that regular
	  // JS on the page can use the same OAuth 2.0 code. In this sample, we use
	  // JSNI, even though this would likely only rarely be useful, but it
	  // demonstrates that it's possible at least.
	  // See the other sample app for a demonstration of how to use this library in
	  // pure JS.
	  private void addGoogleAuthNative() {
	    Button button = new Button("Authenticate with Google (using native JS)");
	    button.addClickHandler(new ClickHandler() {
	      @Override
	      public native void onClick(ClickEvent event) /*-{
	        $wnd.oauth2.login({
	          "authUrl" : "https://accounts.google.com/o/oauth2/auth",
	          "clientId" : "392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com",
	          "scopes" : [
	            "https://www.googleapis.com/auth/plus.me"
	          ]
	        }, function(token) {
	          $wnd.alert("Got an OAuth token:\n" + token + "\n"
	              + "Token expires in " + $wnd.oauth2.expiresIn(req) + " ms\n");
	        }, function(error) {
	          $wnd.alert("Error:\n" + error);
	        });
	      }-*/;
	    });
	    RootPanel.get().add(button);
	  }

	  // //////////////////////////////////////////////////////////////////////////
	  // CLEARING STORED TOKENS ///////////////////////////////////////////////////
	  // //////////////////////////////////////////////////////////////////////////

	  // Clears all tokens stored in the browser by this library. Subsequent calls
	  // to login() will result in the popup being shown, though it may immediately
	  // disappear if the token has not expired.
	  private void addClearTokens() {
	    Button button = new Button("Clear stored tokens");
	    button.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
	        Auth.get().clearAllTokens();
	        Window.alert("All tokens cleared");
	      }
	    });
	    RootPanel.get().add(button);
	  }

//	
//	
//	// Add the nameField and sendButton to the RootPanel
//	// Use RootPanel.get() to get the entire body element
//	RootPanel rootPanel = RootPanel.get("nameFieldContainer");
//	rootPanel.setSize("800px", "600px");
//	rootPanel.setStyleName("h1");
//
//	TabPanel tabPanel = new TabPanel();
//	tabPanel.setVisible(true);
//	tabPanel.setAnimationEnabled(true);
//	rootPanel.add(tabPanel, 10, 10);
//	tabPanel.setSize("780px", "580px");
//
//
//	// Create the popup dialog box
//	final DialogBox dialogBox = new DialogBox();
//	dialogBox.setText("Remote Procedure Call");
//	dialogBox.setAnimationEnabled(true);
//	final Button closeButton = new Button("Close");
//	// We can set the id of a widget by accessing its Element
//	closeButton.getElement().setId("closeButton");
//	final Label textToServerLabel = new Label();
//	final HTML serverResponseLabel = new HTML();
//	VerticalPanel dialogVPanel = new VerticalPanel();
//	dialogVPanel.addStyleName("dialogVPanel");
//	dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//	dialogVPanel.add(textToServerLabel);
//	dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//	dialogVPanel.add(serverResponseLabel);
//	dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//	dialogVPanel.add(closeButton);
//	dialogBox.setWidget(dialogVPanel);
//
//	tabPanel.selectTab(0);
//	// Add a handler to close the DialogBox
//
//	closeButton.addClickHandler(new ClickHandler() {
//		public void onClick(ClickEvent event) {
//			/*
//			 * dialogBox.hide(); sendButton.setEnabled(true);
//			 * sendButton.setFocus(true);
//			 */
//		}
//	});
////
////	// Create a handler for the sendButton and nameField
////	class MyHandler implements ClickHandler, KeyUpHandler {
////		/**
////		 * Fired when the user clicks on the sendButton.
////		 */
////		public void onClick(ClickEvent event) {
////			onSubmitLeaving();
////		}
////
////		/**
////		 * Fired when the user types in the nameField.
////		 */
////		public void onKeyUp(KeyUpEvent event) {
////			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
////				sendNameToServer();
////			}
////		}
////
//
//		/**
//		 * Send the name from the nameField to the server and wait for a
//		 * response.
//		 */
////		
////		private void sendNameToServer() {
////			// First, we validate the input.
////		
////			  errorLabel.setText(""); String textToServer =
////			  nameField.getText(); if
////			  (!FieldVerifier.isValidName(textToServer)) {
////			  errorLabel.setText("Please enter at least four characters");
////			  return; }
////			  
////			  // Then, we send the input to the server.
////			  sendButton.setEnabled(false);
////			  textToServerLabel.setText(textToServer);
////			  serverResponseLabel.setText("");
////			  greetingService.greetServer(textToServer, new
////			  AsyncCallback<String>() { public void onFailure(Throwable
////			  caught) { // Show the RPC error message to the user dialogBox
////			  .setText("Remote Procedure Call - Failure");
////			  serverResponseLabel
////			  .addStyleName("serverResponseLabelError");
////			  serverResponseLabel.setHTML(SERVER_ERROR);
////			  dialogBox.center(); closeButton.setFocus(true); }
////			 
////
////			 });
////
////		}
//		
////		
////		public void onSuccess(String result) {
////			dialogBox.setText("Remote Procedure Call");
////			serverResponseLabel.removeStyleName("serverResponseLabelError");
////			serverResponseLabel.setHTML(result);
////			dialogBox.center();
////			closeButton.setFocus(true);
////		}
//		
//	}
//	
//	// Add a handler to send the name to the server
//	MyHandler handler = new MyHandler();
//	btnNewButton_5.addClickHandler(handler);
//	
	
}
