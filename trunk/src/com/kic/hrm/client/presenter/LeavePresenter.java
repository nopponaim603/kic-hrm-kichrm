package com.kic.hrm.client.presenter;


import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.shared.LoginInfo;


public class LeavePresenter implements Presenter{
	
	public interface Display {
		Button getbtnLeaveButton();
		HasClickHandlers getLeaveButton();
		HasClickHandlers getFullFormLeaveButton();
		HasClickHandlers getBackButton();
		
		Label getQuotaLabel();
		
		HasValue<Date> getStartDateLeave();
		HasValue<Date> getEndDateLeave();
		ListBox getTypeLeaveListBox();
		//HasValue<ListBox>
		
		Widget asWidget();
	}
	

	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private EmployeeQuota m_userQuota;
	private Date m_startDate;
	private Date m_endDate;
	
	private LoginInfo m_loginInfo;
	private LeaveTask m_leavetask;
	// 
	public LeavePresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view ,LoginInfo m_loginInfo) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		bind();

		this.m_loginInfo = m_loginInfo;
		
		m_userQuota = new EmployeeQuota();
		//m_userQuota.setM_leave(10);
		//m_userQuota.setM_holiday(10);
		
		m_startDate = m_endDate = new Date();
		display.getStartDateLeave().setValue(m_startDate);
		display.getEndDateLeave().setValue(m_endDate);

		if(this.m_loginInfo != null) {
			System.out.println("Id : " + this.m_loginInfo.getEmployeeID());
			
			this.rpcService.getEmployeeQuota(this.m_loginInfo.getEmployeeID(), new AsyncCallback<EmployeeQuota>() {
				
				@Override
				public void onSuccess(EmployeeQuota result) {
					// TODO Auto-generated method stub
					System.out.println("Result : " + result);
					
					if(result != null)
					{
						m_userQuota.setM_leave(result.getM_leave());
						m_userQuota.setM_holiday(result.getM_holiday());
					}else System.out.println("Result : " + result);
					
					checkCodition();
					display.getQuotaLabel().setText(String.valueOf(m_userQuota.getM_leave()));
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					System.out.println("Result : " + caught);
					m_userQuota.setM_leave(-1);
					m_userQuota.setM_holiday(-1);
					
					checkCodition();
					display.getQuotaLabel().setText(String.valueOf(m_userQuota.getM_leave()));
					
				}
			});
		}else System.out.println("m_login" + m_loginInfo);

	}
	
	private void bind() {
		// TODO Auto-generated method stub
		//display.getStartDateLeave().s
		//display.getTypeLeaveListBox().
		
		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new gotoDashBoardEvent());
			}
		});
		
		display.getTypeLeaveListBox().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				String valueTarget = display.getTypeLeaveListBox().getValue(display.getTypeLeaveListBox().getSelectedIndex());
				System.out.println("event : " + valueTarget);
				if(valueTarget.equals(StartTimeLog.type.Leave.toString())) {
					display.getQuotaLabel().setText(String.valueOf(m_userQuota.getM_leave()));
				}else if(valueTarget.equals(StartTimeLog.type.Holiday.toString())) {
					display.getQuotaLabel().setText(String.valueOf(m_userQuota.getM_holiday()));
				}
			}
		});
		
		display.getStartDateLeave().addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				m_startDate = event.getValue();
				display.getEndDateLeave().setValue(m_startDate);
				m_endDate = m_startDate;
				display.getEndDateLeave().setValue(m_endDate);
				//display.getEndDateLeave().
				checkCodition();
			}
		});
		
		display.getEndDateLeave().addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				m_endDate = event.getValue();
				
				display.getEndDateLeave().setValue(m_endDate);
				checkCodition();
			}
		});
	
		display.getLeaveButton().addClickHandler(new ClickHandler() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Click to Leave.");
				m_leavetask = new LeaveTask();
				
				m_leavetask.setM_employeeID(m_loginInfo.getEmployeeID());
				
				//FixLeader
				m_leavetask.setM_leaderID(55000);
				
				m_leavetask.setM_leavetype(StartTimeLog.type.valueOf(
					display.getTypeLeaveListBox().getValue(
							display.getTypeLeaveListBox().getSelectedIndex())));
				
				m_startDate.setHours(0);
				m_startDate.setMinutes(0);
				m_endDate.setHours(23);
				m_endDate.setMinutes(59);
				
				m_leavetask.setM_start(m_startDate);
				m_leavetask.setM_end(m_endDate);
				
				m_leavetask.setM_sendmessage("Instance Leave Task : " + m_leavetask.getM_leavetype().toString());
				m_leavetask.setM_commentmessage("  ");
				m_leavetask.setM_leaveprogress(LeaveTask.progress.LeaderApprove);
				
				rpcService.createLeaveTask(m_leavetask, new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						System.out.println("Create Task Success.");
						Window.alert("Create Task Success.");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						System.out.println("Create Task Failure.");
						Window.alert("Create Task Failure.");
					}
				});
				
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		 container.clear();
		 container.add(display.asWidget());
	}
	

	void checkCodition() {
		boolean isLeave = false;
		boolean isFuture = false;
		Date currentDay = new Date();
		if(currentDay.equals(m_startDate) || currentDay.before(m_startDate))
			isFuture = true;
		
		if(m_startDate.equals(m_endDate) || m_startDate.before(m_endDate) && isFuture) {
			System.out.println("Codition S - E is : True to Leave |Start Before End");
			Long deltaTime = m_endDate.getTime() - m_startDate.getTime();
			Date tempTime = new Date(deltaTime);
			int DeltaDay = tempTime.getDate();
			System.out.println("Time : " + DeltaDay);
			
			if(StartTimeLog.type.valueOf(
					display.getTypeLeaveListBox().getValue(
							display.getTypeLeaveListBox().getSelectedIndex())) == StartTimeLog.type.Leave) {
				if(DeltaDay <= m_userQuota.getM_leave()) {
					isLeave = true;
				}
			}else if(StartTimeLog.type.valueOf(
					display.getTypeLeaveListBox().getValue(
							display.getTypeLeaveListBox().getSelectedIndex())) == StartTimeLog.type.Holiday) {
				if(DeltaDay <= m_userQuota.getM_holiday()) {
					isLeave = true;
				}
			}
		}
		
		display.getbtnLeaveButton().setEnabled(isLeave);
	}

}
