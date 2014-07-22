package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EnableOauthEvent extends GwtEvent<EnableOauthEventHandler>{
	public static Type<EnableOauthEventHandler> TYPE = new Type<EnableOauthEventHandler>();
	@Override
	public Type<EnableOauthEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(EnableOauthEventHandler handler) {
		// TODO Auto-generated method stub
		
	}

}
