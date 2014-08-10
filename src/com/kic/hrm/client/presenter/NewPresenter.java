package com.kic.hrm.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.shared.LoginInfo;

public class NewPresenter implements Presenter{
	
	public interface Display {
		HasClickHandlers getBackButton();
		void createTake(GreetingServiceAsync rpcService ,LeaveTask leavetask);
		Widget asWidget();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo m_loginInfo;
	
	public NewPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view ,LoginInfo m_loginInfo) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.m_loginInfo = m_loginInfo;
		bind();
		
	}
	
	private void bind() {
		// TODO Auto-generated method stub
		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new gotoDashBoardEvent());
			}
		});
		
		rpcService.getLeaveTaskByID(LeaveTask.progress.None, 55000, new AsyncCallback<List<LeaveTask>>() {
			
			@Override
			public void onSuccess(List<LeaveTask> result) {
				// TODO Auto-generated method stub
				for(LeaveTask temp : result)
					display.createTake(rpcService, temp );
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		//LeaveTask temp = new LeaveTask();
		
		
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		 container.clear();
		 container.add(display.asWidget());
	}

}
