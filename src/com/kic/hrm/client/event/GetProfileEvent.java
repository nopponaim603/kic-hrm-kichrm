package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class GetProfileEvent extends GwtEvent<GetProfileEventHandler>{

	public static Type<GetProfileEventHandler> TYPE = new Type<GetProfileEventHandler>();
	
	@Override
	public Type<GetProfileEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(GetProfileEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onGetProfile(this);
	}

}
