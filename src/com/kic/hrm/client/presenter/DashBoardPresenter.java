package com.kic.hrm.client.presenter;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.geolocation.client.Position.Coordinates;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoAdministratorEvent;
import com.kic.hrm.client.event.gotoLeaveEvent;
import com.kic.hrm.client.event.gotoNewEvent;
import com.kic.hrm.client.event.guiGuestEvent;
import com.kic.hrm.client.event.guiGuestEventHandler;
import com.kic.hrm.client.event.guiMemberEvent;
import com.kic.hrm.client.event.guiMemberEventHandler;
import com.kic.hrm.client.event.returnGeoLocationEvent;
import com.kic.hrm.client.event.returnGeoLocationHandler;
import com.kic.hrm.data.model.Employee.role;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.LoginServiceImpl;
import com.kic.hrm.shared.GeoPosition;
import com.kic.hrm.shared.LoginInfo;

public class DashBoardPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getNewsButton();

		HasClickHandlers getLeaveButton();

		HasClickHandlers getReportButton();

		HasClickHandlers getAdminButton();



		Button getButtonNew();

		Button getButtonLeave();

		Button getButtonReport();

		Button getButtonAdmin();



		Widget asWidget();
	}

	@SuppressWarnings("unused")
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	//Bug This Page is First m_loginInfo is Null.
	//private LoginInfo m_loginInfo;

	public DashBoardPresenter(GreetingServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
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

		eventBus.addHandler(guiGuestEvent.TYPE, new guiGuestEventHandler() {

			@Override
			public void guiGuest(guiGuestEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Setup GUI to Guest.");
				display.getButtonNew().setEnabled(false);
				display.getButtonLeave().setEnabled(false);
				display.getButtonReport().setEnabled(false);
				display.getButtonAdmin().setEnabled(false);
				
			}
		});

		eventBus.addHandler(guiMemberEvent.TYPE, new guiMemberEventHandler() {

			@Override
			public void guiMember(guiMemberEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Setup GUI to Member.");
				display.getButtonNew().setEnabled(true);
				display.getButtonLeave().setEnabled(true);
				display.getButtonReport().setEnabled(true);

				

				// Admin
				display.getButtonAdmin().setEnabled(true);
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
