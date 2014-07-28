package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class RegisterEvent  extends GwtEvent<RegisterEventHandler>{

	public static Type<RegisterEventHandler> TYPE = new Type<RegisterEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RegisterEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(RegisterEventHandler handler) {
		// TODO Auto-generated method stub
		
	}

}
