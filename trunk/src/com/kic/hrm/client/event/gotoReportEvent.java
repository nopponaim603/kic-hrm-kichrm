package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoReportEvent  extends GwtEvent<gotoReportEventHandler>{

	public static Type<gotoReportEventHandler> TYPE = new Type<gotoReportEventHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<gotoReportEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoReportEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoReport(this);
	}

}
