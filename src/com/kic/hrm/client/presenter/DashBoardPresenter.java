package com.kic.hrm.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;


public class DashBoardPresenter implements Presenter{

	public interface Display {
		HasClickHandlers getNewsButton();
		HasClickHandlers getLeaveButton();
		HasClickHandlers getReportButton();
		HasClickHandlers getAdminButton();
		
		Widget asWidget();
	}
	
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
		
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
		 container.clear();
		 container.add(display.asWidget());
	}
	
}
