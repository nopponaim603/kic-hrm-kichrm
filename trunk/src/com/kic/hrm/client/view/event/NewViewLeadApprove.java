package com.kic.hrm.client.view.event;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.SystemConfig;
import com.kic.hrm.data.model.LeaveTask.progress;

public class NewViewLeadApprove  implements ClickHandler{
	private static final Auth AUTH = Auth.get();
	private final GreetingServiceAsync rpcService;
	private LeaveTask m_leavetask;
	public NewViewLeadApprove(GreetingServiceAsync rpcService , LeaveTask m_leavetask) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.m_leavetask = m_leavetask;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if(m_leavetask.getM_leaveprogress() == progress.LeaderApprove || m_leavetask.getM_leaveprogress() == progress.HRApprove) {
			this.m_leavetask = updateProgress(this.m_leavetask);
			
			this.rpcService.approveLeaveTask(this.m_leavetask, new AsyncCallback<Boolean>() {
				
				@Override
				public void onSuccess(Boolean result) {
					// TODO Auto-generated method stub
					System.out.println("Create Task Success.");
					Window.alert("Create Task Success.");
					
					///
					//if(m_leavetask.getM)
					final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(),
							CloudHRM.getCLIENT_ID()).withScopes("https://www.googleapis.com/auth/calendar");
					
					AUTH.login(req, new Callback<String, Throwable>() {
						
						@Override
						public void onSuccess(String token) {
							rpcService.addLeaveTaskToCalendar(token, m_leavetask , new AsyncCallback<Boolean>() {
										
										@Override
										public void onSuccess(Boolean result) {
											// TODO Auto-generated method stub
											
										}
										
										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}
									});
						}

						@Override
						public void onFailure(Throwable caught) {
							// Window.alert("Error:\n" + caught.getMessage());
						}
					});
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					System.out.println("Create Task Failure.");
					Window.alert("Create Task Failure.");
				}
			});
		}
	}
	
	LeaveTask updateProgress(LeaveTask input_leavetask) {
		if(input_leavetask.getM_leaveprogress() == progress.LeaderApprove)
			input_leavetask.setM_leaveprogress(progress.HRApprove);
		else if(input_leavetask.getM_leaveprogress() == progress.HRApprove)
			input_leavetask.setM_leaveprogress(progress.Complete);
		
		return input_leavetask;
	}
}
