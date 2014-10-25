package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class guiGuestEvent  extends GwtEvent<guiGuestEventHandler>{
	
	public static Type<guiGuestEventHandler> TYPE = new Type<guiGuestEventHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<guiGuestEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(guiGuestEventHandler handler) {
		// TODO Auto-generated method stub
		handler.guiGuest(this);
	}

}
