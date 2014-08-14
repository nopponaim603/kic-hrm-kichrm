package com.kic.hrm.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoAdministratorEvent;
import com.kic.hrm.client.event.gotoLeaveEvent;
import com.kic.hrm.client.event.gotoNewEvent;


public class DashBoardPresenter implements Presenter{

	public interface Display {
		HasClickHandlers getNewsButton();
		HasClickHandlers getLeaveButton();
		HasClickHandlers getReportButton();
		HasClickHandlers getAdminButton();
		
		Widget asWidget();
	}
	
	@SuppressWarnings("unused")
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public DashBoardPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		bind();
	}
	
	private void bind() {
		// TODO Auto-generated method stub
	
		
		display.getAdminButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new gotoAdministratorEvent());
			}
		});
		
		
		
		display.getLeaveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new gotoLeaveEvent());
			}
		});
		
		display.getNewsButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new gotoNewEvent());
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
