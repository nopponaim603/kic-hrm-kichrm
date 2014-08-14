package com.kic.hrm.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter.Display;

public abstract class LeaveFormPresenter implements Presenter {
	
	@SuppressWarnings("unused")
	private final GreetingServiceAsync rpcService;
	@SuppressWarnings("unused")
	private final HandlerManager eventBus;
	@SuppressWarnings("unused")
	private final Display display;
	
	public LeaveFormPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		bind();
		
	}
	
	public void bind() {
		// Display to Add Event 
		
	}
	
}
