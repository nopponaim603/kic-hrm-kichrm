package com.kic.hrm.shared;
import java.io.Serializable;

import com.google.gwt.geolocation.client.Position.Coordinates;

@SuppressWarnings("serial")
public class GeoPosition implements Serializable{
	private Double Accuracy = 0d;
	private Double Altitude = 0d;
	private Double AltitudeAccuracy = 0d;
	private Double Heading = 0d;
	private double Latitude = 0;
	private double Longitude = 0;
	private Double Speed = 0d;
	
	public GeoPosition() {
		// TODO Auto-generated method stub
		this.Accuracy = 0d;
		this.Altitude = 0d;
		this.AltitudeAccuracy = 0d;
		this.Heading = 0d;
		this.Latitude = 0;
		this.Longitude = 0;
		this.Speed = 0d;
	}
	
	public GeoPosition(Coordinates c) {
		// TODO Auto-generated method stub
		this.Accuracy = c.getAccuracy();
		this.Altitude = c.getAltitude();
		this.AltitudeAccuracy = c.getAltitudeAccuracy();
		this.Heading = c.getHeading();
		this.Latitude = c.getLatitude();
		this.Longitude = c.getLongitude();
		this.Speed = c.getSpeed();
	}
	
	public Double getAccuracy() {
		return Accuracy;
	}
	public void setAccuracy(Double accuracy) {
		Accuracy = accuracy;
	}
	public Double getAltitude() {
		return Altitude;
	}
	public void setAltitude(Double altitude) {
		Altitude = altitude;
	}
	public Double getAltitudeAccuracy() {
		return AltitudeAccuracy;
	}
	public void setAltitudeAccuracy(Double altitudeAccuracy) {
		AltitudeAccuracy = altitudeAccuracy;
	}
	public Double getHeading() {
		return Heading;
	}
	public void setHeading(Double heading) {
		Heading = heading;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public Double getSpeed() {
		return Speed;
	}
	public void setSpeed(Double speed) {
		Speed = speed;
	}
	
}
