package com.kic.hrm.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.kic.hrm.client.event.ApplyLeavingEvent;
import com.kic.hrm.client.event.ApplyLeavingEventHandler;
import com.kic.hrm.client.event.EnableOauthEvent;
import com.kic.hrm.client.event.EnableOauthEventHandler;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter;
import com.kic.hrm.client.presenter.Presenter;
import com.kic.hrm.client.view.HumanResourcesManagementView;


public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;

	public AppController(GreetingServiceAsync rpcService,HandlerManager eventBus) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		bind();

		System.out.println("AppController Complete!!");
	}

	private void bind() {
		History.addValueChangeHandler(this);

		//Event Bus
		
		eventBus.addHandler(ApplyLeavingEvent.TYPE, 
		new ApplyLeavingEventHandler() {
			@Override
			public void onApplyLeaving(ApplyLeavingEvent event) {
				// TODO Auto-generated method stub
				ApplyLeaving();
			}
		});
		
		
		eventBus.addHandler(EnableOauthEvent.TYPE, new EnableOauthEventHandler() {
			
			@Override
			public void onEnableOauth(ApplyLeavingEvent event) {
				// TODO Auto-generated method stub
				EnableOauth();
			}
		});

	}

	@Override
	public void go(final HasWidgets container) {
		// TODO Auto-generated method stub
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("main");
		} else {
			System.out.println("Appcontroller fireCurrentHistoryState");
			History.fireCurrentHistoryState();
		}

		System.out.println("AppController go Complete!!");
	}

	
	private void ApplyLeaving() {
		System.out.println("Appcontroller run ApplyLeaving Event");
	}
	
	private void EnableOauth() {
		System.out.println("Appcontroller run EnableOauth Event");
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub
		String token = event.getValue();
		Presenter presenter = null;
		presenter = new HumanResourcesManagementPresenter(rpcService, eventBus,
				new HumanResourcesManagementView());
		if (presenter != null) {
			presenter.go(container);
		}

		if(token.equals("Profile")) {
			//presenter = new ProfileView()
		}
		
		System.out.println("AppController onValuechange Complete!!");
	}

}
