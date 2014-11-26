package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoDailyReportEvent extends GwtEvent<gotoDailyReportEventHandler>{
	
	public static Type<gotoDailyReportEventHandler> TYPE = new Type<gotoDailyReportEventHandler>();

	@Override
	public Type<gotoDailyReportEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoDailyReportEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoDailyReport(this);
	}

}
