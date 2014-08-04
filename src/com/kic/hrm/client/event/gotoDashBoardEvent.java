package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoDashBoardEvent extends GwtEvent<gotoDashBoardEventHandler>{

	public static Type<gotoDashBoardEventHandler> TYPE = new Type<gotoDashBoardEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<gotoDashBoardEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoDashBoardEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoDashBoard(this);
	}

}
