package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditProfileEvent extends GwtEvent<EditProfileEventHandler>{
	
	private int Employeeid;
	
	public int getEmployeeid() {
		return Employeeid;
	}

	public static Type<EditProfileEventHandler> TYPE = new Type<EditProfileEventHandler>();
	
	public EditProfileEvent(int inputID) {
		// TODO Auto-generated constructor stub
		this.Employeeid = inputID;
	}
	@Override
	public Type<EditProfileEventHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}

	@Override
	protected void dispatch(EditProfileEventHandler handler) {
		// TODO Auto-generated method stub
		handler.onEditProfile(this);
	}

}
