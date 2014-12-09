package com.kic.hrm.client.presenter;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoReportEvent;
import com.kic.hrm.client.presenter.QuotaPresenter.Display;
import com.kic.hrm.data.model.StartTimeLog;

public class DailyReportPresenter implements Presenter{

	public interface Display {
		Widget asWidget();
		
		HasClickHandlers getBack();
		
		
		void LoadState(StartTimeLog m_log);
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	public DailyReportPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		bind();
	}
	
	private void bind() {
		// TODO Auto-generated method stub
		display.getBack().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new gotoReportEvent());
			}
		});
		
		
		Date m_daily = new Date();
		rpcService.getStartTimeLogListDaily(m_daily,new AsyncCallback<List<StartTimeLog>>() {
			
			@Override
			public void onSuccess(List<StartTimeLog> result) {
				// TODO Auto-generated method stub
				//Setup Display
				for(StartTimeLog m_log : result) {
					 
					display.LoadState(m_log);	
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
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
