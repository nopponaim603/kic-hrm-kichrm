package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class gotoProfileAndEditEvent extends GwtEvent<gotoProfileAndEditEventHandler>{

	public static Type<gotoProfileAndEditEventHandler> TYPE = new Type<gotoProfileAndEditEventHandler>();
	
	@Override
	public Type<gotoProfileAndEditEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(gotoProfileAndEditEventHandler handler) {
		// TODO Auto-generated method stub
		handler.gotoProfileAndEdit(this);
	}
	
	private int Employeeid;
	
	public int getEmployeeid() {
		return Employeeid;
	}
	
	public gotoProfileAndEditEvent(int inputID) {
		// TODO Auto-generated constructor stub
		this.Employeeid = inputID;
	}

}
