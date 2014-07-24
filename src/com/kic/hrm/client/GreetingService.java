package com.kic.hrm.client;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kic.hrm.shared.LoginInfo;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;

	boolean ApplyLeaving(String input) throws IOException;

	boolean EnableOauth(Boolean input);

	// TODO #09: start create login helper methods in service interface
	String getUserEmail(String token);

	LoginInfo login(String requestUri);

	LoginInfo loginDetails(String token);

	String googleDrive(String token);
}
