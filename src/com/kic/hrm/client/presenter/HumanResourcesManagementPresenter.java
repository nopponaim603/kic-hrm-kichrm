package com.kic.hrm.client.presenter;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.ApplyLeavingEvent;
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
		
		HasClickHandlers getAddProfileButton();
		HasClickHandlers getEditProfileButton();
		HasClickHandlers getRefreshButton();
		
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
		
		bind();
		
		 //	    
		System.out.println(HumanResourcesManagementPresenter.class.getSimpleName() + " : constructor" );
	}
	
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	    container.clear();
	    container.add(display.asWidget());
	    
	   
	}

	public void bind() {
		// TODO Auto-generated method stub
		display.getApplyLeavingButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		       System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
		        //eventBus.fireEvent(new ApplyLeavingEvent());
		       //eventBus
		    	 eventBus.fireEvent(new ApplyLeavingEvent());
		    	 System.out.println("HRM_P| After call eventBus fireEvent");
		    	  //History.fireCurrentHistoryState();
		    	  //ApplyLeaving();
		      }
		    });

		display.getAddProfileButton().addClickHandler(new ClickHandler() {
			
		
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
				eventBus.fireEvent(new RegisterEvent());
				System.out.println("HRM_P| After call eventBus fireEvent");
				//this.e
			}
		});
		
		display.getToggleOauth().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
				// eventBus.fireEvent(new RegisterEvent());
				//ToggleOauth();
			}
			
		});
		

		
		/*
		display.getEditProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//eventBus.fireEvent(new RegisterEvent());
			}
		});
		*/
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
				System.out.println("HRM Presenter on Click onFailure");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onSuccess");
				//eventBus.fireEvent(new RegisterEvent());
				//eventBus.
			}
		});
	}
	
	
}
