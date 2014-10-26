package com.kic.hrm.data.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class StartTimeLog implements Serializable {
	
	public enum type {
		None,
		Absence,
		Office,
		Onsite,
		OnTime,
		Late,
		Leave,
		Holiday
	}
	
	public enum timetable {
		None,
		Admin,
		Project
	}
	
	public enum property {
		employeeID,
		name,
		date,
		timetable,
		clockin,
		clockout,
		clocklate,
		type,
		Note
	}
	
	//@Attribute(primaryKey = true)
    //@Sync(true)
    private String Kind;

    //@Attribute(version = true)
    private Long keyID;
    
    private int 		m_employeeID;
    private String 		m_name;
    private Date		m_date;
    private timetable	m_timeTable;
    private Date		m_clockIn;
    private Date		m_clockOut;
    
    
    private Date		m_clockLate;
    private type		m_type;
    private String		m_Note;
	
	/**
	 * @return the kind
	 */
	public String getKind() {
		return Kind;
	}
	/**
	 * @param kind the kind to set
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
	 * @param keyID the keyID to set
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
	 * @param m_employeeID the m_employeeID to set
	 */
	public void setM_employeeID(int m_employeeID) {
		this.m_employeeID = m_employeeID;
	}
	
	/**
	 * @return the m_name
	 */
	public String getM_name() {
		return m_name;
	}
	/**
	 * @param m_name the m_name to set
	 */
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	/**
	 * @return the m_date
	 */
	public Date getM_date() {
		return m_date;
	}
	/**
	 * @param m_date the m_date to set
	 */
	public void setM_date(Date m_date) {
		this.m_date = m_date;
	}
	/**
	 * @return the m_timeTable
	 */
	public timetable getM_timeTable() {
		return m_timeTable;
	}
	/**
	 * @param m_timeTable the m_timeTable to set
	 */
	public void setM_timeTable(timetable m_timeTable) {
		this.m_timeTable = m_timeTable;
	}
	/**
	 * @return the m_clockIn
	 */
	public Date getM_clockIn() {
		return m_clockIn;
	}
	/**
	 * @param m_clockIn the m_clockIn to set
	 */
	public void setM_clockIn(Date m_clockIn) {
		this.m_clockIn = m_clockIn;
	}
	/**
	 * @return the m_clockOut
	 */
	public Date getM_clockOut() {
		return m_clockOut;
	}
	/**
	 * @param m_clockOut the m_clockOut to set
	 */
	public void setM_clockOut(Date m_clockOut) {
		this.m_clockOut = m_clockOut;
	}
	/**
	 * @return the m_type
	 */
	public type getM_type() {
		return m_type;
	}
	/**
	 * @param m_type the m_type to set
	 */
	public void setM_type(type m_type) {
		this.m_type = m_type;
	}

	/**
	 * @return the m_clockLate
	 */
	public Date getM_clockLate() {
		return m_clockLate;
	}
	/**
	 * @param m_clockLate the m_clockLate to set
	 */
	public void setM_clockLate(Date m_clockLate) {
		this.m_clockLate = m_clockLate;
	}
	public String getM_Note() {
		return m_Note;
	}
	public void setM_Note(String m_Note) {
		this.m_Note = m_Note;
	}


    
    //private 
    
}
