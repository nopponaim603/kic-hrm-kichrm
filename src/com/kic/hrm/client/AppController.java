package com.kic.hrm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.kic.hrm.client.event.AddProfileEvent;
import com.kic.hrm.client.event.AddProfileEventHandler;
import com.kic.hrm.client.event.ApplyLeavingEvent;
import com.kic.hrm.client.event.ApplyLeavingEventHandler;
import com.kic.hrm.client.event.EditProfileEvent;
import com.kic.hrm.client.event.EditProfileEventHandler;
import com.kic.hrm.client.event.EnableOauthEvent;
import com.kic.hrm.client.event.EnableOauthEventHandler;
import com.kic.hrm.client.event.ProfileUpdateEvent;
import com.kic.hrm.client.event.ProfileUpdateEventHandler;
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
		eventBus.addHandler(EnableOauthEvent.TYPE, new EnableOauthEventHandler() {
			@Override
			public void onEnableOauth(EnableOauthEvent event) {
				// TODO Auto-generated method stub
				EnableOauth();
			}
		});
		
		eventBus.addHandler(ApplyLeavingEvent.TYPE, new ApplyLeavingEventHandler() {
			@Override
			public void onApplyLeaving(ApplyLeavingEvent event) {
				// TODO Auto-generated method stub
				System.out.print("App| Call Event ApplyLeaving.");
				//History.newItem("Register");
				//ApplyLeaving();
			}
		});
		
		EventBusMainGoToRegister();
		EventBusRegisterGoToMain();
		
		//eventBus.fireEvent(new ApplyLeavingEvent());	
		//System.out.println("Count of Event ApplyLeaving " + eventBus.getHandlerCount(ApplyLeavingEvent.TYPE));
		//System.out.println("Count of Event RegisterEvent " + eventBus.getHandlerCount(RegisterEvent.TYPE));
		
		//System.out.println("App| Add Handler EventBus Complete.");
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

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub		
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
	
	private void EventBusMainGoToRegister() {
		
		eventBus.addHandler(RegisterEvent.TYPE,new RegisterEventHandler() {
			
			@Override
			public void onRegistering(RegisterEvent event) {
				// TODO Auto-generated method stub
				System.out.print("App| Call Event Register.");
				History.newItem("Register");
			}
		});
		
		eventBus.addHandler(EditProfileEvent.TYPE, new EditProfileEventHandler() {
			
			@Override
			public void onEditProfile(EditProfileEvent event) {
				// TODO Auto-generated method stub
				doEditProfile(event.getEmployeeid());
				//EditProfileEvent.
			}
		});
		
	}
	
	private void doEditProfile(int employeeID) {
	    History.newItem("edit", false);
	    Presenter presenter = new RegisterPresenter(rpcService, eventBus, new RegisterView(), employeeID);
	    presenter.go(container);
	}
	
	private void EventBusRegisterGoToMain() {
		eventBus.addHandler(AddProfileEvent.TYPE, new AddProfileEventHandler() {

			@Override
			public void onAddProfile(AddProfileEvent event) {
				// TODO Auto-generated method stub
				System.out.print("Add New Profile Suscess and Back to main");
				History.newItem("main");
			}
			
		});
		
		eventBus.addHandler(ProfileUpdateEvent.TYPE	, new ProfileUpdateEventHandler() {
			
			@Override
			public void onProfileUpdated(ProfileUpdateEvent event) {
				// TODO Auto-generated method stub
				System.out.print("Back to main");
				History.newItem("main");
			}
		});
	}
	
	private void ApplyLeaving() {
		System.out.println("Appcontroller run ApplyLeaving Event");
	}
	
	private void EnableOauth() {
		System.out.println("AAAppcontroller run EnableOauth Event");
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
	

	/*
	String token = event.getValue();
	Presenter presenter = null;
	presenter = new HumanResourcesManagementPresenter(rpcService, eventBus,
			new HumanResourcesManagementView());
	if (presenter != null) {
		Presenter presenter = null;
	

	}
	*/
}
