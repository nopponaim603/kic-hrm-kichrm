package com.kic.hrm.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
	throws IllegalArgumentException;
	
	void ApplyLeaving(String input, AsyncCallback<Boolean> callback);
	
	void EnableOauth(Boolean input, AsyncCallback<Boolean> callback);
}
