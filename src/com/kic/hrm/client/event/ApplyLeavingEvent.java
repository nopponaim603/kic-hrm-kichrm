package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ApplyLeavingEvent extends GwtEvent<ApplyLeavingEventHandler>{
	public static Type<ApplyLeavingEventHandler> TYPE = new Type<ApplyLeavingEventHandler>();
	@Override
	public Type<ApplyLeavingEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(ApplyLeavingEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onApplyLeaving(this);
	}

}
