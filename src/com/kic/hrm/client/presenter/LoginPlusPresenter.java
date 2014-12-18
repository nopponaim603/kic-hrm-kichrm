package com.kic.hrm.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.kic.hrm.client.AppController.eventFire;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.client.event.guiGuestEvent;
import com.kic.hrm.client.event.guiMemberEvent;
import com.kic.hrm.data.model.LoginInfo;
import com.kic.hrm.data.model.Employee.role;
import com.sun.xml.internal.fastinfoset.stax.events.EventBase;
import com.google.api.gwt.services.plus.shared.Plus;
import com.google.api.gwt.services.plus.shared.Plus.ActivitiesContext.ListRequest.Collection;
import com.google.api.gwt.services.plus.shared.Plus.PlusAuthScope;
import com.google.api.gwt.services.plus.shared.model.Activity;
import com.google.api.gwt.services.plus.shared.model.ActivityFeed;
import com.google.api.gwt.services.plus.shared.model.Person;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class LoginPlusPresenter {
	private static final Logger log = Logger.getLogger(LoginPlusPresenter.class
			.getName());

	private static final String LOGINPANEL = "loginPanelContainer";

	// TODO #05: add constants for OAuth2 (don't forget to update
	// GOOGLE_CLIENT_ID)
	private static final Auth AUTH = Auth.get();
	// TODO #05:> end

	// TODO #06: define controls for login
	private final HorizontalPanel loginPanel = new HorizontalPanel();
	private final Anchor signInLink = new Anchor("");
	private final Image loginImage = new Image();
	private final TextBox nameField = new TextBox();
	private final StringBuilder userEmail;
	// TODO #06:> end

	private LoginInfo m_loginInfo;
	private final HandlerManager eventBus;

	public LoginPlusPresenter(HandlerManager eventBus) {
		// TODO Auto-generated constructor stub
		this.eventBus = eventBus;
		// TODO #08: create login controls
		nameField.setEnabled(false);

		signInLink.getElement().setClassName("login-area");
		signInLink.setTitle("sign out");
		loginImage.getElement().setClassName("login-area");
		loginPanel.add(signInLink);
		RootPanel.get(LOGINPANEL).add(loginPanel);
		userEmail = new StringBuilder();
		m_loginInfo = new LoginInfo();
/*
		plus.initialize(new SimpleEventBus(), new GoogleApiRequestTransport(
				CloudHRM.getAPPLICATION_NAME(), CloudHRM.getAPI_KEY()));
*/
	}

	// TODO #07: add helper methods for Login, Logout and AuthRequest
	public void loadLogin(final LoginInfo loginInfo) {
		System.out.println("User Logout : " + loginInfo.getLoginUrl());
		signInLink.setHref(loginInfo.getLoginUrl());
		signInLink.setText("Please, sign in with your Google Account");
		m_loginInfo = new LoginInfo();
		System.out.println("State Member : Sign in.");
		
		AUTH.clearAllTokens();
		
		eventBus.fireEvent(new guiGuestEvent());
	}

	public void loadLogout(final LoginInfo loginInfo) {
		System.out.println("User Login : " + loginInfo.getLoginUrl() + " : " + loginInfo.getName());
		
		signInLink.setHref(loginInfo.getLogoutUrl());
		signInLink.setText(loginInfo.getName());
		signInLink.setTitle("Sign out");
	}

	private static final Plus plus = GWT.create(Plus.class);

	/**
	 * @param rpcService
	 */
	public void addGoogleAuthHelper(final GreetingServiceAsync rpcService) {

		/*
		 * OAuth2Login.get().authorize(CloudHRM.getCLIENT_ID(),
		 * PlusAuthScope.PLUS_ME, new Callback<Void, Exception>() {
		 * 
		 * @Override public void onSuccess(Void v) { getMe(); }
		 * 
		 * @Override public void onFailure(Exception e) {
		 * System.out.println(e.getMessage()); } });
		 */

		final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(),
				CloudHRM.getCLIENT_ID())
				.withScopes(CloudHRM.getPLUS_ME_SCOPE());
		//AUTH.setOAuthWindowUrl("tesr");
		//AUTH.
		AUTH.login(req, new Callback<String, Throwable>() {

			@Override
			public void onSuccess(String result) { // TODO
				// Auto-generated method stub if (!result.isEmpty()) { //
				//!!!!!!!!!!!!!!!!
				System.out.println("AUTH result : " + result);

				rpcService.loginDetails(result, new AsyncCallback<LoginInfo>() {

					@Override
					public void onFailure(final Throwable caught) {
						GWT.log("loginDetails -> onFailure : "
								+ caught.getMessage()); //
						log.log(Level.SEVERE ,"loginDetails -> onFailure : " + caught.getMessage());
						
					}

					@Override
					public void onSuccess(LoginInfo loginInfo) {
						log.log(Level.SEVERE ,"C:LP| Google H : on Success. ");

						// System.out.println("email : " + //
						// loginInfo.getEmailAddress());
						setM_loginInfo(loginInfo);
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
									final int originalHeight = loginImage
											.getOffsetHeight();
									final int originalWidth = loginImage
											.getOffsetWidth();
									if (originalHeight > originalWidth) {
										loginImage.setHeight(newWidth + "px");
									} else {
										loginImage.setWidth(newWidth + "px");
									}
									loginImage.setVisible(true);
								}
							}
						});
						// userEmail.append(result.getEmailAddress());

						System.out.println("AUTH User Login : " + loginInfo.getLoginUrl() + " : " + loginInfo.getName());
						

						if (loginInfo.getEmployeeRole() == role.Guest)
							eventBus.fireEvent(new guiGuestEvent());
						else
							eventBus.fireEvent(new guiMemberEvent());

					}
				});

			}

			@Override
			public void onFailure(Throwable reason) { // TODO
				// Auto-generated method stub
				GWT.log("Error -> loginDetails\n" + reason.getMessage());
				log.severe("Error -> loginDetails\n" + reason.getMessage());
			}
		});

	}

	public void processLoginSucess(LoginInfo result,
			final GreetingServiceAsync rpcService) {
		log.log(Level.SEVERE ,"C| Login Success : ");

		if (result.getName() != null && !result.getName().isEmpty()) {
			// setM_loginInfo(result);
			// System.out.println("result Login : " +
			// this.m_loginInfo.getEmployeeID());
			// System.out.println("on Success");
			addGoogleAuthHelper(rpcService);
			// System.out.println("result have name and is not Empty.");
			System.out.println("User Login : " + result.getLoginUrl() + " : " + result.getName());
			
			loadLogout(result);
			
			nameField.setEnabled(true);
		} else {
			System.out.println("C:LP| result is Else not run addGoogleAuthHelper. when First Time.");
			log.log(Level.SEVERE , " Result is Else not run Auth : " + result.getName());
			loadLogin(result);
		}
		userEmail.append(result.getEmailAddress());

		// System.out.println("userEmail : " + userEmail.toString());
	}

	/**
	 * @return the m_loginInfo
	 */
	public LoginInfo getM_loginInfo() {
		return m_loginInfo;
	}

	/**
	 * @param m_loginInfo
	 *            the m_loginInfo to set
	 */
	private void setM_loginInfo(LoginInfo m_loginInfo) {
		this.m_loginInfo = m_loginInfo;
	}
	
	private void getMe() {
		plus.people().get("me").to(new Receiver<Person>() {
			@Override
			public void onSuccess(Person person) {
				System.out.println("Hello " + person.getDisplayName());

				getMyActivities();
			}
		}).fire();
	}
	
	private void getMyActivities() {
		plus.activities().list("me", Collection.PUBLIC)
				.to(new Receiver<ActivityFeed>() {
					@Override
					public void onSuccess(ActivityFeed feed) {
						System.out.println("===== PUBLIC ACTIVITIES =====");
						if (feed.getItems() == null
								|| feed.getItems().isEmpty()) {
							System.out.println("You have no public activities");
						} else {
							for (Activity a : feed.getItems()) {
								System.out.println(a.getTitle());
							}
						}
					}
				}).fire();
	}
}
