package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoLeaveEvent  extends GwtEvent<gotoLeaveEventHandler>{

	public static Type<gotoLeaveEventHandler> TYPE = new Type<gotoLeaveEventHandler>();
	
	@Override
	public Type<gotoLeaveEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoLeaveEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoLeave(this);
	}

}
