package com.kic.hrm.client.presenter;

import java.util.logging.Logger;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.shared.LoginInfo;

public class LoginPlusPresenter {
	private static final Logger log = Logger.getLogger(LoginPlusPresenter.class.getName());
	
	private static final String LOGINPANEL = "loginPanelContainer";

	// TODO #05: add constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID)
	private static final Auth AUTH = Auth.get();
	// TODO #05:> end

	// TODO #06: define controls for login
    private final HorizontalPanel loginPanel = new HorizontalPanel();
    private final Anchor signInLink = new Anchor("");
    private final Image loginImage = new Image();
    private final TextBox nameField = new TextBox();   
    private final StringBuilder userEmail;
    // TODO #06:> end
    
    @SuppressWarnings("unused")
	private LoginInfo m_loginInfo;
	
    public LoginPlusPresenter() {
		// TODO Auto-generated constructor stub
    	// TODO #08: create login controls
    	nameField.setEnabled(false);	

    	signInLink.getElement().setClassName("login-area");
    	signInLink.setTitle("sign out");
    	loginImage.getElement().setClassName("login-area");
    	loginPanel.add(signInLink);
    	RootPanel.get(LOGINPANEL).add(loginPanel);
    	userEmail = new StringBuilder();
    }
    // TODO #07: add helper methods for Login, Logout and AuthRequest
    public void loadLogin(final LoginInfo loginInfo) {
			signInLink.setHref(loginInfo.getLoginUrl());
			signInLink.setText("Please, sign in with your Google Account");
			signInLink.setTitle("Sign in");
	}

	public void loadLogout(final LoginInfo loginInfo) {
			signInLink.setHref(loginInfo.getLogoutUrl());
			signInLink.setText(loginInfo.getName());
			signInLink.setTitle("Sign out");
	}
	
	public void addGoogleAuthHelper(final GreetingServiceAsync rpcService) {

		final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(), CloudHRM.getGOOGLE_CLIENT_ID())
		.withScopes(CloudHRM.getPLUS_ME_SCOPE());
		
		AUTH.login(	req, new Callback<String, Throwable>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				if (!result.isEmpty()) {
					
					
						rpcService.loginDetails(result, new AsyncCallback<LoginInfo>() {
						
						@Override
						public void onFailure(final Throwable caught) {
							GWT.log("loginDetails -> onFailure : " + caught.getMessage());
							//log.severe(caught.getMessage());
						}

						@Override
						public void onSuccess(final LoginInfo loginInfo) {
							System.out.println("C:LP| Google H : on Success");
							m_loginInfo = loginInfo;
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
				log.severe("Error -> loginDetails\n" + reason.getMessage());
			}
		});
		
}

	public void processLoginSucess(LoginInfo result , final GreetingServiceAsync rpcService) {
		System.out.println("C| Login Success : " + result);
			if (result.getName() != null && !result.getName().isEmpty()) {

				//System.out.println("on Success");
				addGoogleAuthHelper(rpcService);
				//System.out.println("result have name and is not Empty.");
				
				loadLogout(result);
				nameField.setEnabled(true);
			} else {
				System.out.println("C:LP| result is Else not run addGoogleAuthHelper.");
				loadLogin(result);
			}
			userEmail.append(result.getEmailAddress());
	}
}
