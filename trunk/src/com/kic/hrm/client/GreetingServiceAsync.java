package com.kic.hrm.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.kic.hrm.shared.*;
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	
	void ApplyLeaving(String input, AsyncCallback<Boolean> callback);
	
	void EnableOauth(Boolean input, AsyncCallback<Boolean> callback);
	
	// TODO #10: create login helper methods in service asynchronous interface	
	void getUserEmail(String token, AsyncCallback<String> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

	void loginDetails(String token, AsyncCallback<LoginInfo> asyncCallback);
	// TODO #10:> end	
	
	void googleDrive(String token, AsyncCallback<String> callback);
}
