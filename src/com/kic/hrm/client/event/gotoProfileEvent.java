package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class gotoProfileEvent extends GwtEvent<gotoProfileEventHandler>{

	public static Type<gotoProfileEventHandler> TYPE = new Type<gotoProfileEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<gotoProfileEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoProfileEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoProfile(this);
	}

}
