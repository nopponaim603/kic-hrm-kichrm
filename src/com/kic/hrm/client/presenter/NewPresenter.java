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
import com.kic.hrm.client.businesslogic.ConditionHR;
import com.kic.hrm.client.businesslogic.ConditionLeader;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.shared.LoginInfo;

public class NewPresenter implements Presenter{
	
	public enum taskRole {
		Leader,
		HR,
		worker
	}
	
	public interface Display {
		HasClickHandlers getBackButton();
		void createTake(GreetingServiceAsync rpcService ,LeaveTask leavetask,taskRole Owner);
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
		
		//Leader Load
		if(ConditionLeader.isIMLeader(m_loginInfo.getEmployeeRole()))
		rpcService.getLeaveTask(LeaveTask.progress.LeaderApprove, m_loginInfo.getEmployeeID() , new AsyncCallback<List<LeaveTask>>() {
			
			@Override
			public void onSuccess(List<LeaveTask> result) {
				// TODO Auto-generated method stub
				for(LeaveTask temp : result)
					//if(ConditionLeader.isFollower(m_loginInfo.getEmployeeRole(), temp.getM_employeeID()))
					if(temp.getM_leaveprogress() == progress.LeaderApprove)
						display.createTake(rpcService, temp , taskRole.Leader);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//HR
		if(ConditionHR.isHR(m_loginInfo.getEmployeeRole())) {
			rpcService.getLeaveTask(LeaveTask.progress.HRApprove, m_loginInfo.getEmployeeID() , new AsyncCallback<List<LeaveTask>>() {
				
				@Override
				public void onSuccess(List<LeaveTask> result) {
					// TODO Auto-generated method stub
					for(LeaveTask temp : result)
						if(temp.getM_leaveprogress() == progress.HRApprove)
							display.createTake(rpcService, temp , taskRole.HR);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
			
			//System.out.println("My is HR.");
		}
		
		//Worker
		rpcService.getLeaveTask(LeaveTask.progress.None, m_loginInfo.getEmployeeID() , new AsyncCallback<List<LeaveTask>>() {
			
			@Override
			public void onSuccess(List<LeaveTask> result) {
				// TODO Auto-generated method stub
				for(LeaveTask temp : result)
					display.createTake(rpcService, temp , taskRole.worker);
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
