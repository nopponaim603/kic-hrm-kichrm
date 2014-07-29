package com.kic.hrm.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.kic.hrm.server.DataStoreControl;

//import org.slim3.datastore.Attribute;
//import com.kic.hrm.data.model.Sync;

public class Employee implements Serializable{
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Attribute(primaryKey = true)
    //@Sync(true)
    private Key key;

    //@Attribute(version = true)
    private Long version;
    
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
    
    public Key getKey() {
		return key;
	}
    
    public void setKey(Key key) {
		this.key = key;
	}

    public Long getVersion() {
		return version;
	}
    
    public void setVersion(Long version) {
		this.version = version;
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
	
	public Employee(Entity entity) {
		// TODO Auto-generated method stub
		this.key = entity.getKey();

		this.m_employeeID = (int) (long) entity.getProperty(property.employeeID.toString());
		this.m_sex = sex.valueOf(entity.getProperty(property.sex.toString()).toString());
		this.m_name = (String)entity.getProperty(property.name.toString());
		this.m_surname = (String)entity.getProperty(property.surname.toString());
		this.m_nameT = (String)entity.getProperty(property.nameT.toString());
		this.m_surnameT = (String)entity.getProperty(property.surnameT.toString());
		this.m_shortName = (String)entity.getProperty(property.shortName.toString());
		this.m_role = role.valueOf(entity.getProperty(property.role.toString()).toString());
		this.m_segment = segment.valueOf(entity.getProperty(property.segment.toString()).toString());
		this.m_email = (String)entity.getProperty(property.email.toString());
		this.m_phone = (String)entity.getProperty(property.phone.toString());
	}
	
	public Entity EditData() {
		Entity temp = new Entity(this.key);
		return FlashData(temp);
	}
	
	public Entity FlashData(Entity entity) {
		
		//Entity etst = new Entity(kind, id)
		//entity.s
		//entity.
		entity.setProperty(property.employeeID.toString(), this.m_employeeID);
		entity.setProperty(property.sex.toString(), this.m_sex.toString());
		entity.setProperty(property.name.toString(), this.m_name);
		entity.setProperty(property.surname.toString(), this.m_surname);
		entity.setProperty(property.nameT.toString(), this.m_nameT);
		entity.setProperty(property.surnameT.toString(), this.m_surnameT);
		entity.setProperty(property.shortName.toString(), this.m_shortName);
		entity.setProperty(property.role.toString(), this.m_role.toString());
		entity.setProperty(property.segment.toString(), this.m_segment.toString());
		entity.setProperty(property.email.toString(), this.m_email);
		entity.setProperty(property.phone.toString(), this.m_phone);
		return entity;
	}
	
	public static List<Employee> Clone(List<Entity> entities) {
		List<Employee> results = new ArrayList<Employee>();
		for (Entity entity : entities) 
				results.add(new Employee(entity));
				
		return results;
	}
	
	public static List<Employee> Filter(List<Entity> entities ,property type,String target) {
		
		List<Employee> results = new ArrayList<Employee>();
		for (Entity entity : entities) {
			if(entity.getProperty(type.toString()).toString().contentEquals(target))
				results.add(new Employee(entity));
		}
		
		return results;
	}

}
