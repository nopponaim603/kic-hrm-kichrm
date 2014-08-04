package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoAdministratorEvent extends GwtEvent<gotoAdministratorEventHandler>{
	
	public static Type<gotoAdministratorEventHandler> TYPE = new Type<gotoAdministratorEventHandler>();
	
	@Override
	public Type<gotoAdministratorEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoAdministratorEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoAdministrator(this);
	}

}
