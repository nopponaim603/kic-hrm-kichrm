package com.kic.hrm.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.ApplyLeavingEvent;
import com.kic.hrm.client.presenter.Presenter;

public class HumanResourcesManagementPresenter implements Presenter {

	public interface Display {
		
		//Apply Leaving
		HasClickHandlers getApplyLeavingButton();
		
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
		
		System.out.println("HRM Presenter");
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
		    	  ApplyLeaving();
		      }
		    });

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

}
