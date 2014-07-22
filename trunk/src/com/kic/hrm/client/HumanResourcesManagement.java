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
//import com.google.api.client.auth.oauth
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;

//import com.sw_engineering_candies.oauth2.shared.LoginInfo;

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
	

	//http://1-dot-xz-plasma-weft-8.appspot.com/humanresourcesmanagement/oauthWindow.html
	//client_id=392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com

		
	// TODO #05: add constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID)
		private static final Auth AUTH = Auth.get();
		private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
		private static final String GOOGLE_CLIENT_ID = "392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com";
		private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.login";
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

		private void addGoogleAuthHelper(final GreetingServiceAsync rpcService) {
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
								GWT.log("loginDetails -> onFailure");
							}

							@Override
							public void onSuccess(final LoginInfo loginInfo) {
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
		
	public void onModuleLoad() {
		
		System.out.println("Client HRM Start here.");
		
		final GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
	    HandlerManager eventBus = new HandlerManager(null);
	    AppController appViewer = new AppController(rpcService, eventBus);
	    appViewer.go(RootPanel.get());
	    
	    System.out.println("onModuleLoad Complete.");
	   
	    //final Button sendButton = new Button("Send");
	    
	    	// TODO #08: create login controls
	 		//sendButton.setEnabled(false);		
	 		nameField.setEnabled(false);	

	 		signInLink.getElement().setClassName("login-area");
	 		signInLink.setTitle("sign out");
	 		loginImage.getElement().setClassName("login-area");
	 		loginPanel.add(signInLink);
	 		RootPanel.get("loginPanelContainer").add(loginPanel);
	 		final StringBuilder userEmail = new StringBuilder();
	 		rpcService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	 			@Override
	 			public void onFailure(final Throwable caught) {
	 				GWT.log("login -> onFailure");
	 			}

	 			@Override
	 			public void onSuccess(final LoginInfo result) {
	 				if (result.getName() != null && !result.getName().isEmpty()) {
	 					addGoogleAuthHelper(rpcService);
	 					loadLogout(result);
	 					//sendButton.setEnabled(true);	
	 					nameField.setEnabled(true);
	 				} else {
	 					loadLogin(result);
	 				}
	 				userEmail.append(result.getEmailAddress());
	 			}
	 		});
	 		// TODO #08:> end
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
