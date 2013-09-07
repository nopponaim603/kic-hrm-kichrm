package com.kic.hrm.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import com.kic.hrm.client.event.ApplyLeavingEvent;
import com.kic.hrm.client.presenter.Presenter;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;

	public AppController(GreetingServiceAsync rpcService,
			HandlerManager eventBus) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		bind();

		System.out.println("onModuleLoad Complete!!");
	}

	private void bind() {
		History.addValueChangeHandler(this);

		/*
		eventBus.addHandler(ApplyLeavingEvent.TYPE, new ApplyLeavingEvent() {
			public void onAddContact(ApplyLeavingEvent event) {
				ApplyLeaving();
			}
		});
*/
		
	}

	@Override
	public void go(final HasWidgets container) {
		// TODO Auto-generated method stub
		this.container = container;

	}

	private void ApplyLeaving() {

	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// TODO Auto-generated method stub

	}

}
