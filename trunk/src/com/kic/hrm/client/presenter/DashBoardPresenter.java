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
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoAdministratorEvent;
import com.kic.hrm.client.event.gotoLeaveEvent;
import com.kic.hrm.client.event.gotoNewEvent;

public class DashBoardPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getNewsButton();

		HasClickHandlers getLeaveButton();

		HasClickHandlers getReportButton();

		HasClickHandlers getAdminButton();

		HasClickHandlers getCheckInButton();

		HasClickHandlers getOnSiteButton();

		Widget asWidget();
	}

	@SuppressWarnings("unused")
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

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

		display.getCheckInButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Check In with AppEngine.");
				// eventBus.fireEvent(new gotoNewEvent());
			}
		});

		display.getOnSiteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Check In On Site with AppEngine.");
				OnSite();
				// eventBus.fireEvent(new gotoNewEvent());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub

		container.clear();
		container.add(display.asWidget());
	}
	
	private void OnSite() {
		
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				RootPanel.get().add(
						new Label("Uncaught exception: " + e));
			}
		});
		final VerticalPanel main = new VerticalPanel();
		RootPanel.get().add(main);

		main.add(new Label("Geolocation provider: "
				+ com.google.gwt.geolocation.client.Geolocation
						.getIfSupported()));
		// main.add(new Label("GWT strongname: " +
		// GWT.getPermutationStrongName())); // GWT2.0!

		Label l1 = new Label("Obtaining Geolocation...");
		main.add(l1);
		if (!com.google.gwt.geolocation.client.Geolocation
				.isSupported()) {
			l1.setText("Obtaining Geolocation FAILED! Geolocation API is not supported.");
			return;
		}
		
		final Geolocation geo = Geolocation.getIfSupported();
		if (geo == null) {
			l1.setText("Obtaining Geolocation FAILED! Object is null.");
			return;
		}
		l1.setText("Obtaining Geolocation DONE!");
		 obtainPosition(main, geo );

	}

	private void obtainPosition(final VerticalPanel main, final Geolocation geo) {
		final Label l2 = new Label("Obtaining position (timeout: 15 sec)...");
		main.add(l2);

		geo.getCurrentPosition(new Callback<Position, PositionError>() {

			@Override
			public void onSuccess(Position result) {
				// TODO Auto-generated method stub
				l2.setText("Obtaining position DONE - acquired at "
						+ result.getTimestamp());
				Coordinates c = result.getCoordinates();
				main.add(new Label("lat, lon: " + c.getLatitude() + ", "
						+ c.getLongitude()));
				main.add(new Label("Accuracy (in meters): " + c.getAccuracy()));
				main.add(new Label("Altitude: " + (c.getAltitude())));
				main.add(new Label("Altitude accuracy (in meters): "
						+ (c.getAltitudeAccuracy())));
				main.add(new Label("Heading: " + (c.getHeading())));
				main.add(new Label("Speed: " + (c.getSpeed())));

				String latlong = c.getLatitude() + "," + c.getLongitude();
				rpcService.QuickTest(latlong, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						main.add(new Label("Addess: " + result));
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

	}

}
