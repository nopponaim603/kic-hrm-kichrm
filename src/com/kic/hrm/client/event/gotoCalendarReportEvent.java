package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoCalendarReportEvent extends GwtEvent<gotoCalendarReportEventHandler>{

	public static Type<gotoCalendarReportEventHandler> TYPE = new Type<gotoCalendarReportEventHandler>();
	
	@Override
	public Type<gotoCalendarReportEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoCalendarReportEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotocalendarReport(this);
	}

}
