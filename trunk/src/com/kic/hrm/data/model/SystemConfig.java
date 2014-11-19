package com.kic.hrm.data.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SystemConfig implements Serializable {
	
	public enum property {
		Drive_folderID,
		Report_email,
		AdminOndutyTime,
		AdminOffdutyTime,
		AdminEarly,
		ProjectOndutyTime,
		ProjectOffdutyTime,
		ProjectEarly,

	}
	
	public static int limitQuota_Leave = 5;
	public static int limitQuota_Holiday = 10;
	
	private String m_Drive_folderID;
	private String m_Report_email;
	
	private  Date AdminOndutyTime;
	private  Date AdminOffdutyTime;
	private  Date AdminEarly;
	private  Date ProjectOndutyTime;
	private  Date ProjectOffdutyTime;
	private  Date ProjectEarly;
	
	//@Attribute(primaryKey = true)
    //@Sync(true)
    private String Kind;
    //@Attribute(version = true)
    private Long keyID;
    
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
	
	public String getM_Drive_folderID() {
		return m_Drive_folderID;
	}
	
	public void setM_Drive_folderID(String m_Drive_folderID) {
		this.m_Drive_folderID = m_Drive_folderID;
	}
	
	public String getM_Report_email() {
		return m_Report_email;
	}
	
	public void setM_Report_email(String m_Report_email) {
		this.m_Report_email = m_Report_email;
	}
	
	public  Date getAdminOndutyTime() {
		return AdminOndutyTime;
	}
	
	public  void setAdminOndutyTime(Date adminOndutyTime) {
		AdminOndutyTime = adminOndutyTime;
	}
	
	public  Date getAdminOffdutyTime() {
		return AdminOffdutyTime;
	}
	
	public  void setAdminOffdutyTime(Date adminOffdutyTime) {
		AdminOffdutyTime = adminOffdutyTime;
	}
	
	public  Date getAdminEarly() {
		return AdminEarly;
	}
	
	public  void setAdminEarly(Date adminEarly) {
		AdminEarly = adminEarly;
	}

	public  Date getProjectOndutyTime() {
		return ProjectOndutyTime;
	}

	public  void setProjectOndutyTime(Date projectOndutyTime) {
		ProjectOndutyTime = projectOndutyTime;
	}

	public  Date getProjectOffdutyTime() {
		return ProjectOffdutyTime;
	}

	public  void setProjectOffdutyTime(Date projectOffdutyTime) {
		ProjectOffdutyTime = projectOffdutyTime;
	}

	public  Date getProjectEarly() {
		return ProjectEarly;
	}

	public  void setProjectEarly(Date projectEarly) {
		ProjectEarly = projectEarly;
	}


	public SystemConfig() {
		// TODO Auto-generated constructor stub
		AdminOndutyTime = new Date(0);
		AdminOndutyTime.setHours(8);
		AdminOndutyTime.setMinutes(30);
		AdminOffdutyTime = new Date(0);
		AdminOffdutyTime.setHours(16);
		AdminOffdutyTime.setMinutes(30);
		AdminEarly = new Date(0);
		AdminEarly.setHours(0);
		AdminEarly.setMinutes(15);
		
		ProjectOndutyTime = new Date(0);
		ProjectOndutyTime.setHours(9);
		ProjectOffdutyTime = new Date(0);
		ProjectOffdutyTime.setHours(17);
		ProjectEarly = new Date(0);
		ProjectEarly.setHours(0);
		ProjectEarly.setMinutes(15);
		
		/*
		System.out.println(AdminOndutyTime);
		System.out.println(AdminOffdutyTime);
		System.out.println(AdminEarly);
		System.out.println(ProjectOndutyTime);
		System.out.println(ProjectOffdutyTime);
		System.out.println(ProjectEarly);*/
	}


}
