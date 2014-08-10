package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoNewEvent extends GwtEvent<gotoNewEventHandler>{

	public static Type<gotoNewEventHandler> TYPE = new Type<gotoNewEventHandler>();
	
	@Override
	public Type<gotoNewEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoNewEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoNew(this);
	}

}
