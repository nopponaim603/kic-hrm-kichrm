package com.kic.hrm.client.businesslogic;

import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.Employee.role;

public class ConditionHR {
	
	public static boolean isHR(Employee.role inputRole) {
		boolean m_isHR = false;
			
		if(inputRole == role.Administration)
			m_isHR = true;
		
		return m_isHR;
	}
}
