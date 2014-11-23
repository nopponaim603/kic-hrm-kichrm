package com.kic.hrm.client.presenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoAdministratorEvent;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.client.presenter.ProfilePresenter.Display;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.shared.LoginInfo;

public class QuotaPresenter implements Presenter{

	public interface Display {
		Widget asWidget();
		
		void setOnTimeText(int ontime);
		void setOnSiteText(int onsite);
		void setLate(int late);
		void setAbsence(int absence);
		void setLeaveQuota(int leaveQuota);
		void setHolidayQuota(int holidayQuota);
		HasClickHandlers getDailyReport();
		HasClickHandlers getBack();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private LoginInfo m_loginInfo;
	private EmployeeQuota m_employeeQuota;
	
	public QuotaPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view, LoginInfo m_loginInfo) {
		// TODO Auto-generated constructor stub
		
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.m_loginInfo = m_loginInfo;
		bind();
	
	}
	
	private void bind() {
		// TODO Auto-generated method stub
		display.getDailyReport().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//Save new value to Quota per user
				/*
				rpcService.QuickTest("Test", new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				*/
			}
		});
		
		display.getBack().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("getCancel  : on Click and back to Admin.");
		    	  eventBus.fireEvent(new gotoDashBoardEvent());
			}
		});
		
		System.out.println("Test");
		
		rpcService.getEmployeeQuota(m_loginInfo.getEmployeeID(), new AsyncCallback<EmployeeQuota>() {
			
			@Override
			public void onSuccess(EmployeeQuota result) {
				// TODO Auto-generated method stub
				m_employeeQuota = result;
				System.out.println(m_employeeQuota.getM_leave());
				QuotadisplaySetting(m_employeeQuota);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("Error : " + caught);
			}
		});
		
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		container.add(display.asWidget());
	}

	private void QuotadisplaySetting(EmployeeQuota m_employeequota) {
		
		display.setOnTimeText(m_employeequota.getM_ontime());
		display.setOnSiteText(m_employeequota.getM_onsite());
		display.setLate(m_employeequota.getM_late());
		display.setAbsence(m_employeequota.getM_absence());
		display.setLeaveQuota(m_employeequota.getM_leave());
		display.setHolidayQuota(m_employeequota.getM_holiday());
		
	}
		
}
