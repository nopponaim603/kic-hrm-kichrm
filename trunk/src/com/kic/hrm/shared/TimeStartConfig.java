package com.kic.hrm.shared;

import java.util.Date;

public class TimeStartConfig {
	
	private static Date AdminOndutyTime;
	private static Date AdminOffdutyTime;
	private static Date AdminEarly;
	private static Date ProjectOndutyTime;
	private static Date ProjectOffdutyTime;
	private static Date ProjectEarly;
	
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
	
	@SuppressWarnings("deprecation")
	public static void setDefult() {
		
		AdminOndutyTime = new Date(0);
		AdminOndutyTime.setHours(8);
		AdminOndutyTime.setMinutes(30);
		AdminOffdutyTime = new Date(0);
		AdminOffdutyTime.setHours(16);
		AdminOffdutyTime.setMinutes(30);
		AdminEarly = new Date(0);
		ProjectEarly.setHours(0);
		AdminEarly.setMinutes(15);
		
		ProjectOndutyTime = new Date(0);
		ProjectOndutyTime.setHours(9);
		ProjectOffdutyTime = new Date(0);
		ProjectOffdutyTime.setHours(17);
		ProjectEarly = new Date(0);
		ProjectEarly.setHours(0);
		ProjectEarly.setMinutes(15);
		
		System.out.println(AdminOndutyTime);
		System.out.println(AdminOffdutyTime);
		System.out.println(AdminEarly);
		System.out.println(ProjectOndutyTime);
		System.out.println(ProjectOffdutyTime);
		System.out.println(ProjectEarly);
		
	}


}
