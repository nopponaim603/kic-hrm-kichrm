package com.kic.hrm.client.presenter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.AppController;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.businesslogic.ConditionOffice;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;
import com.kic.hrm.data.model.StartTimeLog.type;
import com.kic.hrm.server.GreetingServiceImpl;
import com.kic.hrm.shared.GeoPosition;
import com.kic.hrm.shared.LoginInfo;

public class NewPresenter implements Presenter {

	public enum taskRole {
		Leader, HR, worker
	}

	public interface Display {
		HasClickHandlers getBackButton();

		HasClickHandlers getCheckInButton();

		HasClickHandlers getOnSiteButton();

		void createTake(GreetingServiceAsync rpcService, LeaveTask leavetask,
				taskRole Owner);

		Widget asWidget();
	}
	
	private static final Logger log = Logger
			.getLogger(NewPresenter.class.getName());
	
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private LoginInfo m_loginInfo;
	//private AppController m_manager;
	
	public NewPresenter(GreetingServiceAsync rpcService,
			HandlerManager eventBus, Display view,LoginInfo m_loginInfo) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		this.m_loginInfo = m_loginInfo;
		//this.m_manager = m_manager;
		
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
		
		display.getCheckInButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Check In with AppEngine.");
				log.log(Level.SEVERE, "Client : " + m_loginInfo.getEmailAddress());
				//log. ("Client : " + m_manager.getLoginPlus().getM_loginInfo().getEmailAddress().toString());
				GetAddressGeoLocation(type.Office,m_loginInfo);
				// eventBus.fireEvent(new gotoNewEvent());
			}
		});

		display.getOnSiteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Get GeoLocation On Site with AppEngine,and go To Event return GeoLocation.");
				GetAddressGeoLocation(type.Onsite,m_loginInfo);
			}
		});

		// Leader Load
		if (ConditionOffice.isIMLeader(m_loginInfo.getEmployeeRole()))
			rpcService.getLeaveTask(LeaveTask.progress.LeaderApprove,
					m_loginInfo.getEmployeeID(),
					new AsyncCallback<List<LeaveTask>>() {

						@Override
						public void onSuccess(List<LeaveTask> result) {
							// TODO Auto-generated method stub
							for (LeaveTask temp : result) {
								// if(ConditionLeader.isFollower(m_loginInfo.getEmployeeRole(),
								// temp.getM_employeeID()))
								if (temp.getM_leaveprogress() == progress.LeaderApprove)
									display.createTake(rpcService, temp,
											taskRole.Leader);
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}
					});

		// HR
		if (ConditionOffice.isHR(m_loginInfo.getEmployeeRole())) {
			rpcService.getLeaveTask(LeaveTask.progress.HRApprove,
					m_loginInfo.getEmployeeID(),
					new AsyncCallback<List<LeaveTask>>() {

						@Override
						public void onSuccess(List<LeaveTask> result) {
							// TODO Auto-generated method stub
							for (LeaveTask temp : result)
								if (temp.getM_leaveprogress() == progress.HRApprove)
									display.createTake(rpcService, temp,
											taskRole.HR);
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}
					});

			// System.out.println("My is HR.");
		}

		// Worker
		rpcService.getLeaveTask(LeaveTask.progress.None,
				m_loginInfo.getEmployeeID(),
				new AsyncCallback<List<LeaveTask>>() {

					@Override
					public void onSuccess(List<LeaveTask> result) {
						// TODO Auto-generated method stub
						for (LeaveTask temp : result)
							display.createTake(rpcService, temp,
									taskRole.worker);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

		// LeaveTask temp = new LeaveTask();

	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		container.add(display.asWidget());
	}

	// Get GeoLocation on Client
	private void GetAddressGeoLocation(type getGeoType,LoginInfo userInfo) {

			GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				public void onUncaughtException(Throwable e) {
					RootPanel.get().add(new Label("Uncaught exception: " + e));
				}
			});
			final VerticalPanel main = new VerticalPanel();
			RootPanel.get().add(main);

			main.add(new Label("Geolocation provider: "
					+ Geolocation.getIfSupported()));
			// main.add(new Label("GWT strongname: " +
			// GWT.getPermutationStrongName())); // GWT2.0!

			Label l1 = new Label("Obtaining Geolocation...");
			main.add(l1);
			if (!com.google.gwt.geolocation.client.Geolocation.isSupported()) {
				l1.setText("Obtaining Geolocation FAILED! Geolocation API is not supported.");
				return ;
			}

			final Geolocation geo = Geolocation.getIfSupported();
			if (geo == null) {
				l1.setText("Obtaining Geolocation FAILED! Object is null.");
				return ;
			}
			l1.setText("Obtaining Geolocation DONE!");

			obtainPosition(main, geo,  getGeoType, userInfo);

		}

	private void obtainPosition(final VerticalPanel main,
			final Geolocation geo,final type getGeoType,final LoginInfo userInfo) {
			final Label l2 = new Label("Obtaining position (timeout: 15 sec)...");
			main.add(l2);
			
			//final returnGeoLocationEvent event = new returnGeoLocationEvent();
			//event.setCheckIntype(getGeoType);
			//event.setMemberTimetable(userTimetable);
			
			geo.getCurrentPosition(new Callback<Position, PositionError>() {

				@Override
				public void onSuccess(Position result) {
					// TODO Auto-generated method stub
					l2.setText("Obtaining position DONE - acquired at "
							+ result.getTimestamp());
					final Coordinates c = result.getCoordinates();
					
					
					
					main.add(new Label("lat, lon: " + c.getLatitude() + ", "
							+ c.getLongitude()));
					main.add(new Label("Accuracy (in meters): " + c.getAccuracy()));
					main.add(new Label("Altitude: " + (c.getAltitude())));
					main.add(new Label("Altitude accuracy (in meters): "
							+ (c.getAltitudeAccuracy())));
					main.add(new Label("Heading: " + (c.getHeading())));
					main.add(new Label("Speed: " + (c.getSpeed())));

					String latlong = c.getLatitude() + "," + c.getLongitude();
					//event.setPosition(c);
					
					userInfo.setCurrentPosition(new GeoPosition(c));
					rpcService.getAddressWithLatLong(latlong,
							new AsyncCallback<String>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(String result) {
									// TODO Auto-generated method stub
									if (result != null) {
										main.add(new Label("Addess: " + result));
										//event.setAddress(result);
										//C is Coordinates 
										System.out.println("Call me. " + getGeoType.toString() + " : " + userInfo);
										rpcService.LoginAttendance(userInfo, getGeoType, result, new AsyncCallback<Boolean>() {
											
											@Override
											public void onSuccess(Boolean result) {
												// TODO Auto-generated method stub
												System.out.println("Call me. onSuccess");
											}
											
											@Override
											public void onFailure(Throwable caught) {
												// TODO Auto-generated method stub
												System.out.println("Call me. onFailure");
											}
										});
										//eventBus.fireEvent(event);
									} else {
										System.out.println("Address is Null");
									}

								}
							});

				}

				@Override
				public void onFailure(PositionError reason) {
					// TODO Auto-generated method stub
					String message = "";
					switch (reason.getCode()) {
					case PositionError.UNKNOWN_ERROR:
						message = "Unknown Error";
						break;
					case PositionError.PERMISSION_DENIED:
						message = "Permission Denied";
						break;
					case PositionError.POSITION_UNAVAILABLE:
						message = "Position Unavailable";
						break;
					case PositionError.TIMEOUT:
						message = "Time-out";
						break;
					default:
						message = "Unknown error code.";
					}
					l2.setText("Obtaining position FAILED! Message: '"
							+ reason.getMessage() + "', code: " + reason.getCode()
							+ " (" + message + ")");
				}
			});

			// Label est = new Label();
			// System.out.println("class " + main.getWidget(main.getWidgetCount() -
			// 1).getClass().toString());
			/*
			if (main.getWidget(main.getWidgetCount() - 1).getClass().toString()
					.equalsIgnoreCase(Label.class.toString())) {
				Label test = (Label) main.getWidget(main.getWidgetCount() - 1);
				m_address = test.getText();
				System.out.println("Get Address OK. | " + m_address);
			}

			return m_address;*/
		}
}
