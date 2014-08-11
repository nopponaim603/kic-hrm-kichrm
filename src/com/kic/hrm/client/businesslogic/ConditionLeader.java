package com.kic.hrm.client.businesslogic;

import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.Employee.role;

public class ConditionLeader {
	
	public static boolean isIMLeader(Employee.role inputRole) {
		boolean m_isLeader = false;
		
		if(inputRole == role.Director)
			m_isLeader = true;
		else if(inputRole == role.Administration)
			m_isLeader = true;
		else if(inputRole == role.ProjectManager)
			m_isLeader = true;
		else if(inputRole == role.ResearchAssistant)
			m_isLeader = true;
		
		return m_isLeader;
	}
	
	public static boolean isFollower(Employee.role inputRole,Employee.role Order) {
		boolean m_isLeader = false;
		
		if(inputRole == role.Director)
			m_isLeader = true;
		else if(inputRole == role.Administration && Order == role.Administration)
			m_isLeader = true;
		else if(inputRole == role.ProjectManager && Order == role.ProjectOfficer)
			m_isLeader = true;
		
		return m_isLeader;
	}
}
