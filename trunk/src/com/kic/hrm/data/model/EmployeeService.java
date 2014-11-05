package com.kic.hrm.data.model;

import java.util.ArrayList;
import java.util.List;




import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.Employee.property;
import com.kic.hrm.data.model.Employee.role;
import com.kic.hrm.data.model.Employee.segment;
import com.kic.hrm.data.model.Employee.sex;
import com.kic.hrm.server.DataStoreControl;

public class EmployeeService {
	
	public static Employee AddDataEmployee(Entity entity) {
		// TODO Auto-generated method stub
		/*
		System.out.println("AppID : " + entity.getAppId() + " : " + entity.getKind() + " : " + entity.getParent()
				+" : "	+ entity.getKey().getId() + entity.getKey().getName() + " : " + entity.getKey().getParent());
		*/
		
		Employee m_employee = new Employee();
		m_employee.setKind(entity.getKind());
		m_employee.setKeyID(entity.getKey().getId());
		
		String tempLong = Long.toString((Long)entity.getProperty(property.employeeID.toString()));
		m_employee.setM_employeeID(Integer.parseInt(tempLong));		
		
		m_employee.setM_sex(sex.valueOf(entity.getProperty(property.sex.toString()).toString()));
		m_employee.setM_name((String)entity.getProperty(property.name.toString()));
		m_employee.setM_surname((String)entity.getProperty(property.surname.toString()));
		m_employee.setM_nameT((String)entity.getProperty(property.nameT.toString()));
		m_employee.setM_surnameT((String)entity.getProperty(property.surnameT.toString()));
		m_employee.setM_shortName((String)entity.getProperty(property.shortName.toString()));
		m_employee.setM_role(role.valueOf(entity.getProperty(property.role.toString()).toString()));
		m_employee.setM_segment(segment.valueOf(entity.getProperty(property.segment.toString()).toString()));
		m_employee.setM_email((String)entity.getProperty(property.email.toString()));
		m_employee.setM_phone((String)entity.getProperty(property.phone.toString()));
		
		return m_employee;
	}
		
	public static Entity FlashData(Entity entity , Employee m_employee) {
		
		//entity.getProperty("test")
		
		entity.setProperty(property.employeeID.toString(), m_employee.getM_employeeID());
		entity.setProperty(property.sex.toString(), m_employee.getM_sex().toString());
		entity.setProperty(property.name.toString(), m_employee.getM_name());
		entity.setProperty(property.surname.toString(), m_employee.getM_surname());
		entity.setProperty(property.nameT.toString(), m_employee.getM_nameT());
		entity.setProperty(property.surnameT.toString(), m_employee.getM_surnameT());
		entity.setProperty(property.shortName.toString(), m_employee.getM_shortName());
		entity.setProperty(property.role.toString(), m_employee.getM_role().toString());
		entity.setProperty(property.segment.toString(), m_employee.getM_segment().toString());
		entity.setProperty(property.email.toString(), m_employee.getM_email());
		entity.setProperty(property.phone.toString(), m_employee.getM_phone());
		return entity;
	}
	
	public static List<Employee> Clone(List<Entity> entities) {
		List<Employee> results = new ArrayList<Employee>();
		for (Entity entity : entities) 		
			results.add(AddDataEmployee(entity));
	
		return results;
	}
	
	public static Filter findEmployeeByEmail(String email) {
		Filter m_emailFilter =
				  new FilterPredicate(Employee.property.email.toString(),
				                      FilterOperator.EQUAL,
				                      email);
		return m_emailFilter;
	}
	
	public static Filter findEmployeeByEmployeeID(int employeeID) {
		Filter m_emailFilter =
				  new FilterPredicate(Employee.property.employeeID.toString(),
				                      FilterOperator.EQUAL,
				                      employeeID);
		return m_emailFilter;
	}
	
	public static List<Employee> Filter(List<Entity> entities ,property type,String target) {
		
		List<Employee> results = new ArrayList<Employee>();
		for (Entity entity : entities) {
			if(entity.getProperty(type.toString()).toString().contentEquals(target))
				results.add(AddDataEmployee(entity));
		}
		
		return results;
	}
	
	public static Employee getEmployee(int employeeID) {
		// TODO Auto-generated method stub
		Employee m_employee = new Employee();

		List<Employee> m_employees = Clone(DataStoreControl.Query(Employee.class,
						SortDirection.DESCENDING, findEmployeeByEmployeeID(employeeID)));
		System.out.println("EmployeeQuota :" + m_employees.size());

		if (m_employees.size() == 1){
			for (Employee temp : m_employees)
				m_employee = temp;
		
		return m_employee;
		}
			
		return null;
		// System.out.println("IS : " + m_employeeQuota + " : " +
		// m_employeeQuota.getQuota;
	}
	
	
	
	/*
	public static Entity EditData(Employee m_employee) {
		Entity temp = new Entity(Employee.class.getSimpleName(),m_employee.);
		return FlashData(temp , m_employee);
	}
	*/
}
