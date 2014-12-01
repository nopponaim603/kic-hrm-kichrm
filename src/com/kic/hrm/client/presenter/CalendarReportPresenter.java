package com.kic.hrm.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.client.event.gotoReportEvent;
import com.kic.hrm.data.model.SystemConfig;

public class CalendarReportPresenter implements Presenter {

	public interface Display {
		Widget asWidget();
		void SetupCalendar(String calendarID);
		HasClickHandlers getBack();
	}

	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public CalendarReportPresenter(GreetingServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
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
				// System.out.println("getCancel  : on Click and back to Admin.");
				eventBus.fireEvent(new gotoReportEvent());
			}
		});

		rpcService.getSystemConfig(new AsyncCallback<SystemConfig>() {

			@Override
			public void onSuccess(SystemConfig result) {
				// TODO Auto-generated method stub
				display.SetupCalendar(result.getM_calendarID());
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
