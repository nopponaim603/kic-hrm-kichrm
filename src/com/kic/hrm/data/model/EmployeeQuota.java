package com.kic.hrm.data.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EmployeeQuota implements Serializable {

	public enum property {
		employeeID, ontime, onsite, late, absence, leave, holiday
	}

	// @Attribute(primaryKey = true)
	// @Sync(true)
	private String Kind;

	// @Attribute(version = true)
	private Long keyID;

	private int m_employeeID;
	private int m_ontime;
	private int m_onsite;
	private int m_absence;
	private int m_late;
	private int m_leave;
	private int m_holiday;

	public EmployeeQuota() {
		// TODO Auto-generated constructor stub
		m_employeeID = 0;
		setM_ontime(0);
		setM_onsite(0);
		setM_absence(0);
		setM_late(0);
		setM_leave(0);
		setM_holiday(0);
	}

	/**
	 * @return the kind
	 */
	public String getKind() {
		return Kind;
	}

	/**
	 * @param kind
	 *            the kind to set
	 */
	public void setKind(String kind) {
		Kind = kind;
	}

	/**
	 * @return the keyID
	 */
	public Long getKeyID() {
		return keyID;
	}

	/**
	 * @param keyID
	 *            the keyID to set
	 */
	public void setKeyID(Long keyID) {
		this.keyID = keyID;
	}

	/**
	 * @return the m_employeeID
	 */
	public int getM_employeeID() {
		return m_employeeID;
	}

	/**
	 * @param m_employeeID
	 *            the m_employeeID to set
	 */
	public void setM_employeeID(int m_employeeID) {
		this.m_employeeID = m_employeeID;
	}

	/**
	 * @return the m_ontime
	 */
	public int getM_ontime() {
		return m_ontime;
	}

	/**
	 * @param m_ontime
	 *            the m_ontime to set
	 */
	public void setM_ontime(int m_ontime) {
		this.m_ontime = m_ontime;
	}

	/**
	 * @return the m_onsite
	 */
	public int getM_onsite() {
		return m_onsite;
	}

	/**
	 * @param m_onsite
	 *            the m_onsite to set
	 */
	public void setM_onsite(int m_onsite) {
		this.m_onsite = m_onsite;
	}

	/**
	 * @return the m_absence
	 */
	public int getM_absence() {
		return m_absence;
	}

	/**
	 * @param m_absence
	 *            the m_absence to set
	 */
	public void setM_absence(int m_absence) {
		this.m_absence = m_absence;
	}

	/**
	 * @return the m_late
	 */
	public int getM_late() {
		return m_late;
	}

	/**
	 * @param m_late
	 *            the m_late to set
	 */
	public void setM_late(int m_late) {
		this.m_late = m_late;
	}

	/**
	 * @return the m_leave
	 */
	public int getM_leave() {
		return m_leave;
	}

	/**
	 * @param m_leave
	 *            the m_leave to set
	 */
	public void setM_leave(int m_leave) {
		this.m_leave = m_leave;
	}

	/**
	 * @return the m_holiday
	 */
	public int getM_holiday() {
		return m_holiday;
	}

	/**
	 * @param m_holiday
	 *            the m_holiday to set
	 */
	public void setM_holiday(int m_holiday) {
		this.m_holiday = m_holiday;
	}

}
