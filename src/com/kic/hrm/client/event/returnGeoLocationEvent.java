package com.kic.hrm.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.geolocation.client.Position.Coordinates;
import com.kic.hrm.data.model.Employee.role;
import com.kic.hrm.data.model.StartTimeLog.timetable;
import com.kic.hrm.data.model.StartTimeLog.type;


public class returnGeoLocationEvent extends GwtEvent<returnGeoLocationHandler>{
	private Coordinates position;
	private String address;
	private type checkIntype;
	private role memberTimetable;
	
	public static Type<returnGeoLocationHandler> TYPE = new Type<returnGeoLocationHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<returnGeoLocationHandler> getAssociatedType() {
		// TODO Auto-generated method stub
		return TYPE;
	}
	
	
	protected void SentPositionAndAddress(Coordinates position , String address) {
		// TODO Auto-generated method stub
		this.setPosition(position);
		this.setAddress(address);
	}

	@Override
	protected void dispatch(returnGeoLocationHandler handler) {
		// TODO Auto-generated method stub
		handler.returnGeoLocation(this);
	}


	public Coordinates getPosition() {
		return position;
	}


	public void setPosition(Coordinates position) {
		this.position = position;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public type getCheckIntype() {
		return checkIntype;
	}


	public void setCheckIntype(type checkIntype) {
		this.checkIntype = checkIntype;
	}


	public role getMemberTimetable() {
		return memberTimetable;
	}


	public void setMemberTimetable(role memberTimetable) {
		this.memberTimetable = memberTimetable;
	}

}
