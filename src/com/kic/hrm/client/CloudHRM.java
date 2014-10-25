package com.kic.hrm.client;

import java.util.logging.Logger;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.mapsengine.MapsEngine;
import com.google.api.services.mapsengine.MapsEngineRequestInitializer;
import com.google.api.services.mapsengine.model.Feature;
import com.google.api.services.mapsengine.model.FeaturesListResponse;
import com.google.api.services.mapsengine.model.GeoJsonPoint;

import java.io.IOException;




//import com.google.gwt.geolocation.client.Coordinates;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Geolocation.PositionOptions;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.Position.Coordinates;
//import com.google.gwt.geolocation.client.PositionCallback;
import com.google.gwt.geolocation.client.PositionError;

public class CloudHRM implements EntryPoint {

	// OAuth2Login.

	/**
	 * This is the entry point method.
	 */
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(CloudHRM.class.getName());

	private static final String APPLICATION_NAME = "xz-plasma-weft-8/1.0";

	public static String getAPPLICATION_NAME() {
		return APPLICATION_NAME;
	}

	private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

	public static String getGOOGLE_AUTH_URL() {
		return GOOGLE_AUTH_URL;
	}

	// This app's personal client ID assigned by the Google APIs Console
	// (http://code.google.com/apis/console).
	private static final String GOOGLE_CLIENT_ID = "392232398516-hdd0r2biksrovka8a6v93roambr2b54r.apps.googleusercontent.com";

	public static String getGOOGLE_CLIENT_ID() {
		return GOOGLE_CLIENT_ID;
	}

	// The auth scope being requested. This scope will allow the application to
	// identify who the authenticated user is.
	private static final String[] PLUS_ME_SCOPE = {
			"https://www.googleapis.com/auth/plus.login",
			"https://www.googleapis.com/auth/userinfo.email" };

	// private static final String PLUS_ME_SCOPE = ;
	public static String[] getPLUS_ME_SCOPE() {
		return PLUS_ME_SCOPE;
	}

	private static final String[] DRIVE_SCOPESArry = {
			"https://www.googleapis.com/auth/plus.login",
			"https://www.googleapis.com/auth/drive.install",
			"https://www.googleapis.com/auth/drive",
			"https://www.googleapis.com/auth/drive.file",
			"https://www.googleapis.com/auth/userinfo.email",
			"https://www.googleapis.com/auth/userinfo.profile" };

	public static final String[] getDRIVE_SCOPESArry() {
		return DRIVE_SCOPESArry;
	}

	private static final String MapsEngine_SCOPES = "https://www.googleapis.com/auth/mapsengine";
	@SuppressWarnings("unused")
	private static String CLIENT_ID = "392232398516-0kkqbokr4hkp3ou3s6spr9u78r1ens93.apps.googleusercontent.com";
	@SuppressWarnings("unused")
	private static String CLIENT_SECRET = "ZGNTRofblwZ3TTnlgJ6N7eyE";

	@SuppressWarnings("unused")
	private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	// private static final String REDIRECT_URI =
	// "http://xz-plasma-weft-8.appspot.com/oauth2callback";
	// private static final String APP_CLIENT_URL =
	// "http://royalbear42.appspot.com/";
	// public static final String API_KEY = "CK3GHEBlWNYwVu5r717Q3VwL";

	// TODO #05: add constants for OAuth2 (don't forget to update
	// GOOGLE_CLIENT_ID)
	private static final Auth AUTH = Auth.get();
	// TODO #05:> end

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	@SuppressWarnings("unused")
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	public void onModuleLoad() {

		System.out.println("Client HRM Start here.");

		GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(rpcService, eventBus);
		appViewer.go(RootPanel.get());

		System.out.println("onModuleLoad Complete.");

		// test
		// Register(eventBus);
		//QuickTest(rpcService);
		// addGoogleAuth(rpcService);

	}

	@SuppressWarnings("unused")
	private void QuickTest(final GreetingServiceAsync rpcService) {
		Button button = new Button("QuickTest");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
					public void onUncaughtException(Throwable e) {
						RootPanel.get().add(
								new Label("Uncaught exception: " + e));
					}
				});
				final VerticalPanel main = new VerticalPanel();
				RootPanel.get().add(main);

				main.add(new Label("Geolocation provider: "
						+ com.google.gwt.geolocation.client.Geolocation
								.getIfSupported()));
				// main.add(new Label("GWT strongname: " +
				// GWT.getPermutationStrongName())); // GWT2.0!

				Label l1 = new Label("Obtaining Geolocation...");
				main.add(l1);
				if (!com.google.gwt.geolocation.client.Geolocation
						.isSupported()) {
					l1.setText("Obtaining Geolocation FAILED! Geolocation API is not supported.");
					return;
				}
				
				final Geolocation geo = Geolocation.getIfSupported();
				if (geo == null) {
					l1.setText("Obtaining Geolocation FAILED! Object is null.");
					return;
				}
				l1.setText("Obtaining Geolocation DONE!");
				 obtainPosition(main, geo , rpcService);

			}
		});

		RootPanel.get().add(button);
	}

	private void obtainPosition(final VerticalPanel main, Geolocation geo,final GreetingServiceAsync rpcService) {
		final Label l2 = new Label("Obtaining position (timeout: 15 sec)...");
		main.add(l2);
		
		geo.getCurrentPosition(new Callback<Position, PositionError>() {
			
			@Override
			public void onSuccess(Position result) {
				// TODO Auto-generated method stub
				l2.setText("Obtaining position DONE - acquired at "
						+ result.getTimestamp());
				Coordinates c = result.getCoordinates();
				main.add(new Label("lat, lon: " + c.getLatitude() + ", "
						+ c.getLongitude()));
				main.add(new Label("Accuracy (in meters): " + c.getAccuracy()));
				main.add(new Label("Altitude: "
						+ (c.getAltitude() )));
				main.add(new Label("Altitude accuracy (in meters): "
						+ (c.getAltitudeAccuracy()
								)));
				main.add(new Label("Heading: "
						+ (c.getHeading()  )));
				main.add(new Label("Speed: "
						+ ( c.getSpeed() )));
				
				String latlong = c.getLatitude() + "," + c.getLongitude();
				rpcService.QuickTest(latlong, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						main.add(new Label("Addess: "
								+ result ));
					}
				});
			}
			
			@Override
			public void onFailure(PositionError reason) {
				// TODO Auto-generated method stub
				String message = "";
				switch (reason.getCode()) {
				case PositionError.UNKNOWN_ERROR:
					message = "Unknown Error";
					break;
				case PositionError.PERMISSION_DENIED:
					message = "Permission Denied";
					break;
				case PositionError.POSITION_UNAVAILABLE:
					message = "Position Unavailable";
					break;
				case PositionError.TIMEOUT:
					message = "Time-out";
					break;
				default:
					message = "Unknown error code.";
				}
				l2.setText("Obtaining position FAILED! Message: '"
						+ reason.getMessage() + "', code: " + reason.getCode()
						+ " (" + message + ")");
			}
		});
		
		
	}

	@SuppressWarnings("unused")
	private void Register(final HandlerManager eventBus) {
		Button button = new Button("Register");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// eventBus.fireEvent(new RegisterEvent());
			}

		});

		RootPanel.get().add(button);
	}

	// //////////////////////////////////////////////////////////////////////////
	// AUTHENTICATING WITH GOOGLE
	// ///////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	// Adds a button to the page that asks for authentication from Google.
	@SuppressWarnings("unused")
	private void addGoogleAuth(final GreetingServiceAsync rpcService) {
		// Since the auth flow requires opening a popup window, it must be
		// started
		// as a direct result of a user action, such as clicking a button or
		// link.
		// Otherwise, a browser's popup blocker may block the popup.
		Button button = new Button("Authenticate with Google");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL,
						GOOGLE_CLIENT_ID).withScopes(DRIVE_SCOPESArry);

				// AUTH.
				// Calling login() will display a popup to the user the first
				// time it is
				// called. Once the user has granted access to the application,
				// subsequent calls to login() will not display the popup, and
				// will
				// immediately result in the callback being given the token to
				// use.
				// AUTH.
				AUTH.login(req, new Callback<String, Throwable>() {
					@Override
					public void onSuccess(String token) {
						// Window.alert("Got an OAuth token:\n" + token + "\n"
						// + "Token expires in " + AUTH.expiresIn(req) +
						// " ms\n");

					}

					@Override
					public void onFailure(Throwable caught) {
						// Window.alert("Error:\n" + caught.getMessage());
					}
				});
			}
		});
		RootPanel.get().add(button);
	}

	// //////////////////////////////////////////////////////////////////////////
	// AUTHENTICATING WITH INSTAGRAM
	// ////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	private static final String INSTAGRAM_AUTH_URL = "https://instagram.com/oauth/authorize/";

	// This app's personal client ID assigned by Instagram
	// (http://instagr.am/developer/manage/)
	private static final String INSTAGRAM_CLIENT_ID = "833d710a11064825a55a2374d4990d26";

	// The auth scopes being requested.

	// This scope will allow the application to read comments as if it was the
	// user.
	private static final String INSTAGRAM_COMMENTS_SCOPE = "comments";
	// This scope will allow the application to read likes as if it was the
	// user.
	private static final String INSTAGRAM_LIKES_SCOPE = "likes";

	// Adds a button to the page that asks for authentication from Instagram.
	@SuppressWarnings("unused")
	private void addInstagramAuth() {
		// Since the auth flow requires opening a popup window, it must be
		// started
		// as a direct result of a user action, such as clicking a button or
		// link.
		// Otherwise, a browser's popup blocker may block the popup.
		Button button = new Button("Authenticate with Instagram");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final AuthRequest req = new AuthRequest(INSTAGRAM_AUTH_URL,
						INSTAGRAM_CLIENT_ID).withScopes(
						INSTAGRAM_COMMENTS_SCOPE, INSTAGRAM_LIKES_SCOPE)
				// Instagram expects a plus-delimited list of scopes
						.withScopeDelimiter("+");
				AUTH.login(req, new Callback<String, Throwable>() {
					@Override
					public void onSuccess(String token) {
						Window.alert("Got an OAuth token:\n" + token + "\n"
								+ "Token expires in " + AUTH.expiresIn(req)
								+ " ms\n");
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
	// AUTHENTICATING WITH GOOGLE (Using native JavaScript)
	// /////////////////////
	// //////////////////////////////////////////////////////////////////////////

	// Adds a button to the page that asks for authentication from Google, using
	// native JS.
	// This demonstrates how a GWT app can export the JS function so that
	// regular
	// JS on the page can use the same OAuth 2.0 code. In this sample, we use
	// JSNI, even though this would likely only rarely be useful, but it
	// demonstrates that it's possible at least.
	// See the other sample app for a demonstration of how to use this library
	// in
	// pure JS.
	@SuppressWarnings("unused")
	private void addGoogleAuthNative() {
		Button button = new Button("Authenticate with Google (using native JS)");
		button.addClickHandler(new ClickHandler() {
			@Override
			public native void onClick(ClickEvent event) /*-{
		$wnd.oauth2
				.login(
						{
							"authUrl" : "https://accounts.google.com/o/oauth2/auth",
							"clientId" : "392232398516-9lg977lv9qm97hus7deli7v0jpr294ko.apps.googleusercontent.com",
							"scopes" : [ "https://www.googleapis.com/auth/plus.me" ]
						}, function(token) {
							$wnd.alert("Got an OAuth token:\n" + token + "\n"
									+ "Token expires in "
									+ $wnd.oauth2.expiresIn(req) + " ms\n");
						}, function(error) {
							$wnd.alert("Error:\n" + error);
						});
	}-*/;
		});
		RootPanel.get().add(button);
	}

	// //////////////////////////////////////////////////////////////////////////
	// CLEARING STORED TOKENS
	// ///////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////

	// Clears all tokens stored in the browser by this library. Subsequent calls
	// to login() will result in the popup being shown, though it may
	// immediately
	// disappear if the token has not expired.
	@SuppressWarnings("unused")
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
	// // Add the nameField and sendButton to the RootPanel
	// // Use RootPanel.get() to get the entire body element
	// RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	// rootPanel.setSize("800px", "600px");
	// rootPanel.setStyleName("h1");
	//
	// TabPanel tabPanel = new TabPanel();
	// tabPanel.setVisible(true);
	// tabPanel.setAnimationEnabled(true);
	// rootPanel.add(tabPanel, 10, 10);
	// tabPanel.setSize("780px", "580px");
	//
	//
	// // Create the popup dialog box
	// final DialogBox dialogBox = new DialogBox();
	// dialogBox.setText("Remote Procedure Call");
	// dialogBox.setAnimationEnabled(true);
	// final Button closeButton = new Button("Close");
	// // We can set the id of a widget by accessing its Element
	// closeButton.getElement().setId("closeButton");
	// final Label textToServerLabel = new Label();
	// final HTML serverResponseLabel = new HTML();
	// VerticalPanel dialogVPanel = new VerticalPanel();
	// dialogVPanel.addStyleName("dialogVPanel");
	// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
	// dialogVPanel.add(textToServerLabel);
	// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
	// dialogVPanel.add(serverResponseLabel);
	// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
	// dialogVPanel.add(closeButton);
	// dialogBox.setWidget(dialogVPanel);
	//
	// tabPanel.selectTab(0);
	// // Add a handler to close the DialogBox
	//
	// closeButton.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// /*
	// * dialogBox.hide(); sendButton.setEnabled(true);
	// * sendButton.setFocus(true);
	// */
	// }
	// });
	// //
	// // // Create a handler for the sendButton and nameField
	// // class MyHandler implements ClickHandler, KeyUpHandler {
	// // /**
	// // * Fired when the user clicks on the sendButton.
	// // */
	// // public void onClick(ClickEvent event) {
	// // onSubmitLeaving();
	// // }
	// //
	// // /**
	// // * Fired when the user types in the nameField.
	// // */
	// // public void onKeyUp(KeyUpEvent event) {
	// // if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	// // sendNameToServer();
	// // }
	// // }
	// //
	//
	// /**
	// * Send the name from the nameField to the server and wait for a
	// * response.
	// */
	// //
	// // private void sendNameToServer() {
	// // // First, we validate the input.
	// //
	// // errorLabel.setText(""); String textToServer =
	// // nameField.getText(); if
	// // (!FieldVerifier.isValidName(textToServer)) {
	// // errorLabel.setText("Please enter at least four characters");
	// // return; }
	// //
	// // // Then, we send the input to the server.
	// // sendButton.setEnabled(false);
	// // textToServerLabel.setText(textToServer);
	// // serverResponseLabel.setText("");
	// // greetingService.greetServer(textToServer, new
	// // AsyncCallback<String>() { public void onFailure(Throwable
	// // caught) { // Show the RPC error message to the user dialogBox
	// // .setText("Remote Procedure Call - Failure");
	// // serverResponseLabel
	// // .addStyleName("serverResponseLabelError");
	// // serverResponseLabel.setHTML(SERVER_ERROR);
	// // dialogBox.center(); closeButton.setFocus(true); }
	// //
	// //
	// // });
	// //
	// // }
	//
	// //
	// // public void onSuccess(String result) {
	// // dialogBox.setText("Remote Procedure Call");
	// // serverResponseLabel.removeStyleName("serverResponseLabelError");
	// // serverResponseLabel.setHTML(result);
	// // dialogBox.center();
	// // closeButton.setFocus(true);
	// // }
	//
	// }
	//
	// // Add a handler to send the name to the server
	// MyHandler handler = new MyHandler();
	// btnNewButton_5.addClickHandler(handler);
	//

}