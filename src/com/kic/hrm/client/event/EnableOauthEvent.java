package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

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
		handler.onEnableOauth(this);
	}

}
