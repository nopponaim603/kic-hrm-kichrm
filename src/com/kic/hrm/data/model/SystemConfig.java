package com.kic.hrm.data.model;

import java.util.Date;

public class SystemConfig {
	
	public static int limitQuota_Leave = 5;
	public static int limitQuota_Holiday = 10;
	
	private String m_Drive_folderID;
	private String m_Report_email;
	
	private static Date AdminOndutyTime;
	private static Date AdminOffdutyTime;
	private static Date AdminEarly;
	private static Date ProjectOndutyTime;
	private static Date ProjectOffdutyTime;
	private static Date ProjectEarly;
	
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
	
	public static Date getAdminOndutyTime() {
		return AdminOndutyTime;
	}
	
	public static void setAdminOndutyTime(Date adminOndutyTime) {
		AdminOndutyTime = adminOndutyTime;
	}
	
	public static Date getAdminOffdutyTime() {
		return AdminOffdutyTime;
	}
	
	public static void setAdminOffdutyTime(Date adminOffdutyTime) {
		AdminOffdutyTime = adminOffdutyTime;
	}
	
	public static Date getAdminEarly() {
		return AdminEarly;
	}
	
	public static void setAdminEarly(Date adminEarly) {
		AdminEarly = adminEarly;
	}

	public static Date getProjectOndutyTime() {
		return ProjectOndutyTime;
	}

	public static void setProjectOndutyTime(Date projectOndutyTime) {
		ProjectOndutyTime = projectOndutyTime;
	}

	public static Date getProjectOffdutyTime() {
		return ProjectOffdutyTime;
	}

	public static void setProjectOffdutyTime(Date projectOffdutyTime) {
		ProjectOffdutyTime = projectOffdutyTime;
	}

	public static Date getProjectEarly() {
		return ProjectEarly;
	}

	public static void setProjectEarly(Date projectEarly) {
		ProjectEarly = projectEarly;
	}
	
	
	
}
