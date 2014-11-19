package com.kic.hrm.data.model;

import java.io.Serializable;
import java.util.Date;

import com.kic.hrm.data.model.StartTimeLog.type;

@SuppressWarnings("serial")
public class LeaveTask implements Serializable{
	
	public enum progress {
		None,
		LeaderApprove,
		HRApprove,
		Complete,
		Eject
	}
	
	public enum property {
		employeeID,
		leaderID,
		leavetype,
		leaveprogress,
		start,
		end,	
		sendmessage,
		commentmessage
	}
	
	//@Attribute(primaryKey = true)
    //@Sync(true)
    private String Kind;

    //@Attribute(version = true)
    private Long keyID;
    
    private int 	m_employeeID;
    private int		m_leaderID;
    private Date	m_start;
    private Date	m_end;
    private type	m_leavetype;
    private progress m_leaveprogress;
    private String	m_sendmessage;
    private String	m_commentmessage;
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
	 * @return the m_leaderID
	 */
	public int getM_leaderID() {
		return m_leaderID;
	}
	/**
	 * @param m_leaderID the m_leaderID to set
	 */
	public void setM_leaderID(int m_leaderID) {
		this.m_leaderID = m_leaderID;
	}
	/**
	 * @return the m_start
	 */
	public Date getM_start() {
		return m_start;
	}
	/**
	 * @param m_start the m_start to set
	 */
	public void setM_start(Date m_start) {
		this.m_start = m_start;
	}
	/**
	 * @return the m_end
	 */
	public Date getM_end() {
		return m_end;
	}
	/**
	 * @param m_end the m_end to set
	 */
	public void setM_end(Date m_end) {
		this.m_end = m_end;
	}
	/**
	 * @return the m_leavetype
	 */
	public type getM_leavetype() {
		return m_leavetype;
	}
	/**
	 * @param m_leavetype the m_leavetype to set
	 */
	public void setM_leavetype(type m_leavetype) {
		this.m_leavetype = m_leavetype;
	}
	/**
	 * @return the m_leaveprogress
	 */
	public progress getM_leaveprogress() {
		return m_leaveprogress;
	}
	/**
	 * @param m_leaveprogress the m_leaveprogress to set
	 */
	public void setM_leaveprogress(progress m_leaveprogress) {
		this.m_leaveprogress = m_leaveprogress;
	}
	/**
	 * @return the m_sendmessage
	 */
	public String getM_sendmessage() {
		return m_sendmessage;
	}
	/**
	 * @param m_sendmessage the m_sendmessage to set
	 */
	public void setM_sendmessage(String m_sendmessage) {
		this.m_sendmessage = m_sendmessage;
	}
	/**
	 * @return the m_commentmessage
	 */
	public String getM_commentmessage() {
		return m_commentmessage;
	}
	/**
	 * @param m_commentmessage the m_commentmessage to set
	 */
	public void setM_commentmessage(String m_commentmessage) {
		this.m_commentmessage = m_commentmessage;
	}
    	
}
