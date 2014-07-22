package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ApplyLeavingEvent extends GwtEvent<ApplyLeavingEventHandler>{
	public static Type<ApplyLeavingEventHandler> TYPE = new Type<ApplyLeavingEventHandler>();
	
	@Override
	public Type<ApplyLeavingEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ApplyLeavingEventHandler handler) {
		handler.onApplyLeaving(this);
	}

}
