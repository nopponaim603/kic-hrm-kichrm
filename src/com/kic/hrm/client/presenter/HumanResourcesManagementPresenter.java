package com.kic.hrm.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
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
	
	//private final ContactsServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	  
	public HumanResourcesManagementPresenter(
			//ContactsServiceAsync rpcService, 
			HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.eventBus = eventBus;
		this.display = view;
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
		        eventBus.fireEvent(new ApplyLeavingEvent());
		      }
		    });

	}

}
