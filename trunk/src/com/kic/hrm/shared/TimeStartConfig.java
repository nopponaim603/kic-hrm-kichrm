package com.kic.hrm.shared;

import java.util.Date;

public class TimeStartConfig {
	
	private  Date AdminOndutyTime;
	private  Date AdminOffdutyTime;
	private  Date AdminEarly;
	private  Date ProjectOndutyTime;
	private  Date ProjectOffdutyTime;
	private  Date ProjectEarly;
	
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
	
	@SuppressWarnings("deprecation")
	public  void setDefult() {
		
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
