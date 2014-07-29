package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class ProfileUpdateEvent extends GwtEvent<ProfileUpdateEventHandler>{

	 public static Type<ProfileUpdateEventHandler> TYPE = new Type<ProfileUpdateEventHandler>();
	@Override
	public Type<ProfileUpdateEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(ProfileUpdateEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onProfileUpdated(this);
	}

}
