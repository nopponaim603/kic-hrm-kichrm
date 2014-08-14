package com.kic.hrm.data.model;

import java.io.Serializable;

//import org.slim3.datastore.Attribute;
//import com.kic.hrm.data.model.Sync;

@SuppressWarnings("serial")
public class Employee implements Serializable {
	public enum sex {
		Mr,
		Mrs,
		Ms
	}
	
	public enum role {
		Director,
		ProjectManager,
		Secretary,
		Administration,
		ChiefFinancialOfficer,
		ProcurementOfficer,
		ITOffice,
		ProjectOfficer,
		InformationOfficer,
		Advisor,
		Designer,
		ProjectCoordinator,
		ResearchAssistant,
		Researchers
	}
	
	public enum segment {
		Director,
		Project,
		Office,
		Researchers,
		Advisor
	}
	
	public enum property {
		employeeID,
		sex,
		name,
		surname,
		nameT,
		surnameT,
		shortName,
		role,
		segment,
		email,
		phone
	}


	//@Attribute(primaryKey = true)
    //@Sync(true)
    private String Kind;

    //@Attribute(version = true)
    private Long keyID;
    
    private Integer schemaVersion = 1;
    
    private int 	m_employeeID;
    private sex 	m_sex;
    private String 	m_name;
    private String 	m_surname;
    private String 	m_nameT;
    private String 	m_surnameT;
    private String 	m_shortName;
    private role 	m_role;
    private segment m_segment;
    private String	m_email;
    private String	m_phone;
    
	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public Long getKeyID() {
		return keyID;
	}

	public void setKeyID(Long keyID) {
		this.keyID = keyID;
	}
   
	/**
	 * @return the schemaVersion
	 */
	public Integer getSchemaVersion() {
		return schemaVersion;
	}

	/**
	 * @param schemaVersion the schemaVersion to set
	 */
	public void setSchemaVersion(Integer schemaVersion) {
		this.schemaVersion = schemaVersion;
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
	 * @return the m_sex
	 */
	public sex getM_sex() {
		return m_sex;
	}

	/**
	 * @param m_sex the m_sex to set
	 */
	public void setM_sex(sex m_sex) {
		this.m_sex = m_sex;
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
	 * @return the m_surname
	 */
	public String getM_surname() {
		return m_surname;
	}

	/**
	 * @param m_surname the m_surname to set
	 */
	public void setM_surname(String m_surname) {
		this.m_surname = m_surname;
	}

	/**
	 * @return the m_nameT
	 */
	public String getM_nameT() {
		return m_nameT;
	}

	/**
	 * @param m_nameT the m_nameT to set
	 */
	public void setM_nameT(String m_nameT) {
		this.m_nameT = m_nameT;
	}

	/**
	 * @return the m_surnameT
	 */
	public String getM_surnameT() {
		return m_surnameT;
	}

	/**
	 * @param m_surnameT the m_surnameT to set
	 */
	public void setM_surnameT(String m_surnameT) {
		this.m_surnameT = m_surnameT;
	}

	/**
	 * @return the m_shortName
	 */
	public String getM_shortName() {
		return m_shortName;
	}

	/**
	 * @param m_shortName the m_shortName to set
	 */
	public void setM_shortName(String m_shortName) {
		this.m_shortName = m_shortName;
	}

	/**
	 * @return the m_role
	 */
	public role getM_role() {
		return m_role;
	}

	/**
	 * @param m_role the m_role to set
	 */
	public void setM_role(role m_role) {
		this.m_role = m_role;
	}

	/**
	 * @return the m_segment
	 */
	public segment getM_segment() {
		return m_segment;
	}

	/**
	 * @param m_segment the m_segment to set
	 */
	public void setM_segment(segment m_segment) {
		this.m_segment = m_segment;
	}
	
	/**
	 * @return the m_email
	 */
	public String getM_email() {
		return m_email;
	}

	/**
	 * @param m_email the m_email to set
	 */
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	
	/**
	 * @return the m_phone
	 */
	public String getM_phone() {
		return m_phone;
	}

	/**
	 * @param m_phone the m_phone to set
	 */
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	    
	public Employee() {
		
	}




	

}

