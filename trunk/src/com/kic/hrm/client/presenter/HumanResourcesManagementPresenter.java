package com.kic.hrm.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.presenter.Presenter;

import com.kic.hrm.shared.LoginInfo;

public class HumanResourcesManagementPresenter implements Presenter {

	public interface Display {
		
		//Apply Leaving
		HasClickHandlers getApplyLeavingButton();
		
		//Toggle
		HasClickHandlers getToggleOauth();
		/*
		void setData(List<String> data);
		int getClickedRow(ClickEvent event);
		List<Integer> getSelectedRows();
		*/
		
		Widget asWidget();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private final LoginPlusPresenter m_loginPlus;
	public HumanResourcesManagementPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		 m_loginPlus = new LoginPlusPresenter();
		 //
		    
		System.out.println("HRM Presenter Constructor");
	}
	
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		bind();
	    container.clear();
	    container.add(display.asWidget());
	    
	   LoginGooglePlus(this.rpcService);
	}


	private void bind() {
		// TODO Auto-generated method stub
		display.getApplyLeavingButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	  System.out.println("HRM Presenter on Click");
		        //eventBus.fireEvent(new ApplyLeavingEvent());
		    	  ApplyLeaving();
		      }
		    });

		display.getToggleOauth().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter from : " + event.toString());
				ToggleOauth();
			}
			
		});
		
		//
	}
	
	private void ApplyLeaving(){
		System.out.println("HRM Presenter Before call rpcService");
		
		rpcService.ApplyLeaving("test", new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onSuccess");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onFailure");
			}
		});
	}
	
	private void ToggleOauth() {
		System.out.println("HRM Presenter in ToggleOauth : Before call rpcService : By press True Only");
		rpcService.EnableOauth(true, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onSuccess");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onFailure");
			}
		});
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
