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
import com.kic.hrm.client.event.RegisterEvent;
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
	
	
	public HumanResourcesManagementPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		 
		 //
		    
		System.out.println(HumanResourcesManagementPresenter.class.getSimpleName() + " : constructor" );
	}
	
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		bind();
	    container.clear();
	    container.add(display.asWidget());
	    
	   
	}


	private void bind() {
		// TODO Auto-generated method stub
		display.getApplyLeavingButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	  System.out.println("HRM Presenter on Click");
		        //eventBus.fireEvent(new ApplyLeavingEvent());
		    	  eventBus.fireEvent(new RegisterEvent());
		    	  //ApplyLeaving();
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
		
		/*
		display.getAddButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		        
		      }
		    });
		*/
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
	
	
}
