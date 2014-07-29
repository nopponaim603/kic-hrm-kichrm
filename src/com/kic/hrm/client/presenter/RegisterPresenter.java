package com.kic.hrm.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.ProfileUpdateEvent;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter.Display;

public class RegisterPresenter implements Presenter{

	public interface Display {
		Widget asWidget();

		HasClickHandlers getSubmit();
		HasClickHandlers getCancel();

	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public RegisterPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		bind();
		
	}

	private void bind() {
		// TODO Auto-generated method stub
		display.getSubmit().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	  System.out.println("getSubmit  : on Click");
		    	  eventBus.fireEvent(new ProfileUpdateEvent());
		    	  History.fireCurrentHistoryState();
		      }
		    });
		
		display.getCancel().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	  System.out.println("getCancel  : on Click");
		    	  //History.fireCurrentHistoryState();
		    	  //eventBus.fireEvent(new ProfileUpdateEvent());
		      }
		    });
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
			container.clear();
			container.add(display.asWidget());
	}
}
