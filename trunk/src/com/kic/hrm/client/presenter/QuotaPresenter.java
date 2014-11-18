package com.kic.hrm.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoAdministratorEvent;
import com.kic.hrm.client.presenter.ProfilePresenter.Display;

public class QuotaPresenter implements Presenter{

	public interface Display {
		Widget asWidget();
		HasValue<String> getLeaveQuota();
		HasValue<String> getHolidayQuota();
		HasClickHandlers getApply();
		HasClickHandlers getBack();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private int employeeID;
	
	public QuotaPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view, int employeeID) {
		// TODO Auto-generated constructor stub
		
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		bind();
		
		this.employeeID = employeeID;
	}
	
	private void bind() {
		// TODO Auto-generated method stub
		display.getApply().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//Save new value to Quota per user
			}
		});
		
		display.getBack().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("getCancel  : on Click and back to Admin.");
		    	  eventBus.fireEvent(new gotoAdministratorEvent());
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
