package com.kic.hrm.client.view.event;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.data.model.LeaveTask;

public class NewViewLeadReject implements ClickHandler{

	private final GreetingServiceAsync rpcService;
	private LeaveTask m_leavetask;
	
	public NewViewLeadReject(GreetingServiceAsync rpcService , LeaveTask m_leavetask) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.m_leavetask = m_leavetask;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		rpcService.deleteLeaveTask(this.m_leavetask, new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("Delete Task Success.");
				Window.alert("Delete Task Success.");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("Delete Task Failure.");
				Window.alert("Delete Task Failure.");
			}
		});
	}

}
