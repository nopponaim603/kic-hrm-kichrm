package com.kic.hrm.data.model;

import java.io.Serializable;

import com.google.gwt.geolocation.client.Position.Coordinates;

@SuppressWarnings("serial")
public class LoginInfo  implements Serializable {

	private boolean loggedIn = false;

	private String loginUrl;

	private String logoutUrl;

	private String emailAddress;

	private String nickname;

	private String pictureUrl;
	
	private int employeeID;
	
	private Employee.role employeeRole;
	
	private GeoPosition currentPosition;
	
	public LoginInfo() {
		// TODO Auto-generated constructor stub
		currentPosition = new GeoPosition();
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(final boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(final String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(final String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return nickname;
	}

	public void setName(final String nickname) {
		this.nickname = nickname;
	}

	public void setPictureUrl(final String pictureUrl) {
		this.pictureUrl = pictureUrl;

	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the employeeRole
	 */
	public Employee.role getEmployeeRole() {
		return employeeRole;
	}

	/**
	 * @param employeeRole the employeeRole to set
	 */
	public void setEmployeeRole(Employee.role employeeRole) {
		this.employeeRole = employeeRole;
	}

	public GeoPosition getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(GeoPosition currentPosition) {
		this.currentPosition = currentPosition;
	}

}
