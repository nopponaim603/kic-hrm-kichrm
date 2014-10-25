package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class guiMemberEvent extends GwtEvent<guiMemberEventHandler>{

	public static Type<guiMemberEventHandler> TYPE = new Type<guiMemberEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<guiMemberEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(guiMemberEventHandler handler) {
		// TODO Auto-generated method stub
		handler.guiMember(this);
	}

}
