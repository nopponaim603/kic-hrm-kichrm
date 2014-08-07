package com.kic.hrm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;


import com.kic.hrm.client.event.gotoProfileAndEditEvent;
import com.kic.hrm.client.event.gotoProfileAndEditEventHandler;
import com.kic.hrm.client.presenter.AdministratorPresenter;
import com.kic.hrm.client.presenter.DashBoardPresenter;
import com.kic.hrm.client.presenter.LoginPlusPresenter;
import com.kic.hrm.client.presenter.Presenter;
import com.kic.hrm.client.presenter.ProfilePresenter;
import com.kic.hrm.client.view.AdministratorView;
import com.kic.hrm.client.view.DashBoardView;
import com.kic.hrm.client.view.ProfileView;
import com.kic.hrm.shared.LoginInfo;


public class AppController implements Presenter, ValueChangeHandler<String> {

	public enum eventFire {
		Main,
		Administrator,
		Profile,
		Edit
	}
	
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
		eventBus.addHandler(gotoProfileAndEditEvent.TYPE, new gotoProfileAndEditEventHandler() {
			
			@Override
			public void gotoProfileAndEdit(gotoProfileAndEditEvent event) {
				// TODO Auto-generated method stub
				doEditProfile(event.getEmployeeid());
			}
		});
		
	}
	
	private void doEditProfile(int employeeID) {
	    History.newItem(eventFire.Edit.toString(), false);
	    Presenter presenter = new ProfilePresenter(rpcService, eventBus, new ProfileView(), employeeID);
	    presenter.go(container);
	}

	@Override
	public void go(final HasWidgets container) {
		// TODO Auto-generated method stub
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem(eventFire.Main.toString());
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

	      if (token.equals(eventFire.Main.toString())) {
		        presenter = new DashBoardPresenter(rpcService, eventBus, new DashBoardView());
		  }
		  else if (token.equals(eventFire.Administrator.toString())) {
	        presenter = new AdministratorPresenter(rpcService, eventBus, new AdministratorView());
	      }
	      else if (token.equals(eventFire.Profile.toString())) {
	        presenter = new ProfilePresenter(rpcService, eventBus, new ProfileView());
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
