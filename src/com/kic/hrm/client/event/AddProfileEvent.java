package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class AddProfileEvent extends GwtEvent<AddProfileEventHandler>{

	public static Type<AddProfileEventHandler> TYPE = new Type<AddProfileEventHandler>();
	
	@Override
	public Type<AddProfileEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(AddProfileEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onAddProfile(this);
	}

}
