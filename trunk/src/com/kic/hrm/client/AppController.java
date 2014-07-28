package com.kic.hrm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.kic.hrm.client.event.ApplyLeavingEvent;
import com.kic.hrm.client.event.ApplyLeavingEventHandler;
import com.kic.hrm.client.event.EnableOauthEvent;
import com.kic.hrm.client.event.EnableOauthEventHandler;
import com.kic.hrm.client.event.RegisterEvent;
import com.kic.hrm.client.event.RegisterEventHandler;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter;
import com.kic.hrm.client.presenter.LoginPlusPresenter;
import com.kic.hrm.client.presenter.Presenter;
import com.kic.hrm.client.presenter.RegisterPresenter;
import com.kic.hrm.client.view.HumanResourcesManagementView;
import com.kic.hrm.client.view.RegisterView;
import com.kic.hrm.shared.LoginInfo;


public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;

	private final LoginPlusPresenter m_loginPlus;
	
	public AppController(GreetingServiceAsync rpcService,HandlerManager eventBus) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		bind();
		
		m_loginPlus = new LoginPlusPresenter();
		
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
				System.out.print("Test Register");
				History.newItem("Register");
				//ApplyLeaving();
			}
		});
		
		eventBus.addHandler(EnableOauthEvent.TYPE, new EnableOauthEventHandler() {
			@Override
			public void onEnableOauth(ApplyLeavingEvent event) {
				// TODO Auto-generated method stub
				EnableOauth();
			}
		});

		eventBus.addHandler(RegisterEvent.TYPE,new RegisterEventHandler() {
			
			@Override
			public void onRegistering(RegisterEvent event) {
				// TODO Auto-generated method stub

			}
		});
		
		//System.out.print("Add EventBus Complete.");
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
		
		LoginGooglePlus(this.rpcService);

		System.out.println("AppController go Complete!!");
	}

	
	private void ApplyLeaving() {
		System.out.println("Appcontroller run ApplyLeaving Event");
	}
	
	private void EnableOauth() {
		System.out.println("AAAppcontroller run EnableOauth Event");
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub
		/*
		String token = event.getValue();
		Presenter presenter = null;
		presenter = new HumanResourcesManagementPresenter(rpcService, eventBus,
				new HumanResourcesManagementView());
		if (presenter != null) {
			Presenter presenter = null;
		

		}
		*/
		
		String token = event.getValue();
	    System.out.println("Token Event : " + token);
	    if (token != null) {
	      Presenter presenter = null;

	      if (token.equals("main")) {
	        presenter = new HumanResourcesManagementPresenter(rpcService, eventBus, new HumanResourcesManagementView());
	      }
	      else if (token.equals("Register")) {
	        presenter = new RegisterPresenter(rpcService, eventBus, new RegisterView());
	      }
	      else if (token.equals("edit")) {
	    	  //presenter = new EditContactPresenter(rpcService, eventBus, new EditContactView());
	      }
	      
	      if (presenter != null) {
	        presenter.go(container);
	      }
	    }
		
		System.out.println("AppController onValuechange Complete!!");
	}
	
	private void LoginGooglePlus(final GreetingServiceAsync rpcService) {
		
		System.out.println("C:P| Login Plus");
		rpcService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			
			@Override
			public void onSuccess(LoginInfo result) {
				// TODO Auto-generated method stub
				m_loginPlus.processLoginSucess(result, rpcService);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				GWT.log("C:P| login -> onFailure");
 				System.out.println("login -> onFailure");
			}
		});
		
	}
}
