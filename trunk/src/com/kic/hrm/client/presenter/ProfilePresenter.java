package com.kic.hrm.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter.Display;

public abstract class ProfilePresenter implements Presenter {
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public ProfilePresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
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
