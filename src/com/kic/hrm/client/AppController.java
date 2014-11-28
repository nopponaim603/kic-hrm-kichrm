package com.kic.hrm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.kic.hrm.client.event.gotoAdministratorEvent;
import com.kic.hrm.client.event.gotoAdministratorEventHandler;
import com.kic.hrm.client.event.gotoDailyReportEvent;
import com.kic.hrm.client.event.gotoDailyReportEventHandler;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.client.event.gotoDashBoardEventHandler;
import com.kic.hrm.client.event.gotoLeaveEvent;
import com.kic.hrm.client.event.gotoLeaveEventHandler;
import com.kic.hrm.client.event.gotoNewEvent;
import com.kic.hrm.client.event.gotoNewEventHandler;
import com.kic.hrm.client.event.gotoProfileAndEditEvent;
import com.kic.hrm.client.event.gotoProfileAndEditEventHandler;
import com.kic.hrm.client.event.gotoProfileEvent;
import com.kic.hrm.client.event.gotoProfileEventHandler;
import com.kic.hrm.client.event.gotoReportEvent;
import com.kic.hrm.client.event.gotoReportEventHandler;
import com.kic.hrm.client.event.guiGuestEvent;
import com.kic.hrm.client.event.guiGuestEventHandler;
import com.kic.hrm.client.event.guiMemberEvent;
import com.kic.hrm.client.event.guiMemberEventHandler;
import com.kic.hrm.client.presenter.AdministratorPresenter;
import com.kic.hrm.client.presenter.DailyReportPresenter;
import com.kic.hrm.client.presenter.DashBoardPresenter;
import com.kic.hrm.client.presenter.LeavePresenter;
import com.kic.hrm.client.presenter.LoginPlusPresenter;
import com.kic.hrm.client.presenter.NewPresenter;
import com.kic.hrm.client.presenter.Presenter;
import com.kic.hrm.client.presenter.ProfilePresenter;
import com.kic.hrm.client.presenter.QuotaPresenter;
import com.kic.hrm.client.view.AdministratorView;
import com.kic.hrm.client.view.DailyReportView;
import com.kic.hrm.client.view.DashBoardView;
import com.kic.hrm.client.view.LeaveView;
import com.kic.hrm.client.view.NewView;
import com.kic.hrm.client.view.ProfileView;
import com.kic.hrm.client.view.QuotaView;
import com.kic.hrm.shared.LoginInfo;

public class AppController implements Presenter, ValueChangeHandler<String> {

	public enum eventFire {
		Main, Administrator, Profile, Edit, Leave, LeaveForm, New, Report , DailyReport
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;

	private final LoginPlusPresenter m_loginPlus;
	public LoginPlusPresenter getLoginPlus() {
		return m_loginPlus;
	}
	
	public AppController(GreetingServiceAsync rpcService,
			HandlerManager eventBus) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		bind();

		m_loginPlus = new LoginPlusPresenter(this.eventBus);

		System.out.println("AppController Complete!!");
	}

	private void bind() {
		History.addValueChangeHandler(this);

		// Event Bus
		eventBus.addHandler(gotoAdministratorEvent.TYPE,
				new gotoAdministratorEventHandler() {

					@Override
					public void gotoAdministrator(gotoAdministratorEvent event) {
						// TODO Auto-generated method stub
						History.newItem(AppController.eventFire.Administrator.toString());
					}
				});

		eventBus.addHandler(gotoDashBoardEvent.TYPE,
				new gotoDashBoardEventHandler() {

					@Override
					public void gotoDashBoard(gotoDashBoardEvent event) {
						// TODO Auto-generated method stub
						History.newItem(AppController.eventFire.Main.toString());
					}
				});

		eventBus.addHandler(gotoLeaveEvent.TYPE, new gotoLeaveEventHandler() {

			@Override
			public void gotoLeave(gotoLeaveEvent event) {
				// TODO Auto-generated method stub
				History.newItem(AppController.eventFire.Leave.toString());
			}
		});
		
		eventBus.addHandler(gotoReportEvent.TYPE,new gotoReportEventHandler() {
			
			@Override
			public void gotoReport(gotoReportEvent event) {
				// TODO Auto-generated method stub
				History.newItem(AppController.eventFire.Report.toString());
			}
		});
		
		eventBus.addHandler(gotoDailyReportEvent.TYPE,new gotoDailyReportEventHandler() {
			
			@Override
			public void gotoDailyReport(gotoDailyReportEvent event) {
				// TODO Auto-generated method stub
				History.newItem(AppController.eventFire.DailyReport.toString());
			}
		});

		eventBus.addHandler(gotoNewEvent.TYPE, new gotoNewEventHandler() {

			@Override
			public void gotoNew(gotoNewEvent event) {
				// TODO Auto-generated method stub
				History.newItem(AppController.eventFire.New.toString());
			}
		});

		eventBus.addHandler(gotoProfileEvent.TYPE, new gotoProfileEventHandler() {

					@Override
					public void gotoProfile(gotoProfileEvent event) {
						// TODO Auto-generated method stub
						History.newItem(AppController.eventFire.Profile.toString());
					}
				});

		eventBus.addHandler(gotoProfileAndEditEvent.TYPE, new gotoProfileAndEditEventHandler() {

					@Override
					public void gotoProfileAndEdit(gotoProfileAndEditEvent event) {
						// TODO Auto-generated method stub
						doEditProfile(event.getEmployeeid());
					}
				});	
	}

	private void doEditProfile(int employeeID) {
		History.newItem(eventFire.Edit.toString(), false);
		Presenter presenter = new ProfilePresenter(rpcService, eventBus,
				new ProfileView(), employeeID);
		presenter.go(container);
	}

	@Override
	public void go(final HasWidgets container) {
		// TODO Auto-generated method stub
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem(eventFire.Main.toString());
		} else {
			//System.out.println("Appcontroller fireCurrentHistoryState");
			History.fireCurrentHistoryState();
		}

		LoginGooglePlus(this.rpcService);

		//System.out.println("AppController go Complete!!");
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub
		String token = event.getValue();
		System.out.println("Token Event : " + token);
		if (token != null) {
			Presenter presenter = null;
			
			//Main Could not requires LoginInfor.
			if (token.equals(eventFire.Main.toString())) {
				presenter = new DashBoardPresenter(rpcService, eventBus,
						new DashBoardView());
			} else if (token.equals(eventFire.Administrator.toString())) {
				presenter = new AdministratorPresenter(rpcService, eventBus,
						new AdministratorView());
			} else if (token.equals(eventFire.Profile.toString())) {
				presenter = new ProfilePresenter(rpcService, eventBus,
						new ProfileView());
			} else if (token.equals(eventFire.Leave.toString())) {
				presenter = new LeavePresenter(rpcService, eventBus,
						new LeaveView(), m_loginPlus.getM_loginInfo());
			} else if (token.equals(eventFire.New.toString())) {
				presenter = new NewPresenter(rpcService, eventBus,
						new NewView(), m_loginPlus.getM_loginInfo());
			} else if (token.equals(eventFire.Report.toString())) {
				presenter = new QuotaPresenter(rpcService, eventBus,
						new QuotaView(), m_loginPlus.getM_loginInfo());
			} else if (token.equals(eventFire.DailyReport.toString())) {
				presenter = new DailyReportPresenter(rpcService, eventBus,
						new DailyReportView());
			}
			

			if (presenter != null) {
				presenter.go(container);
			}
		}

		System.out.println("AppController onValuechange Complete!!");
	}

	private void LoginGooglePlus(final GreetingServiceAsync rpcService) {

		System.out.println("C:P| Login Plus");
		rpcService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onSuccess(LoginInfo result) {
						// TODO Auto-generated method stub
						// System.out.println("email : " +
						// result.getEmailAddress());
						m_loginPlus.processLoginSucess(result, rpcService);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						GWT.log("C:P| login -> onFailure");
						System.out.println("login -> onFailure");
					}
				});

	}

}
