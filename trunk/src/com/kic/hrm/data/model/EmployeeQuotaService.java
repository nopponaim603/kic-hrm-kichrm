package com.kic.hrm.data.model;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.EmployeeQuota.property;
import com.kic.hrm.server.DataStoreControl;

public class EmployeeQuotaService {

	public static EmployeeQuota AddDataEmployeeQuota(Entity entity) {

		EmployeeQuota m_employeeQuota = new EmployeeQuota();

		m_employeeQuota.setKind(entity.getKind());
		m_employeeQuota.setKeyID(entity.getKey().getId());

		String tempLong = Long.toString((Long) entity
				.getProperty(property.employeeID.toString()));
		m_employeeQuota.setM_employeeID(Integer.parseInt(tempLong));

		m_employeeQuota
				.setM_ontime(Integer.parseInt(Long.toString((Long) entity
						.getProperty(property.ontime.toString()))));
		m_employeeQuota
				.setM_onsite(Integer.parseInt(Long.toString((Long) entity
						.getProperty(property.onsite.toString()))));

		m_employeeQuota.setM_late(Integer.parseInt(Long.toString((Long) entity
				.getProperty(property.late.toString()))));
		m_employeeQuota
				.setM_absence(Integer.parseInt(Long.toString((Long) entity
						.getProperty(property.absence.toString()))));

		m_employeeQuota.setM_leave(Integer.parseInt(Long.toString((Long) entity
				.getProperty(property.leave.toString()))));
		m_employeeQuota
				.setM_holiday(Integer.parseInt(Long.toString((Long) entity
						.getProperty(property.holiday.toString()))));

		return m_employeeQuota;
	}

	public static Entity FlashData(Entity entity, EmployeeQuota m_employee) {

		// entity.getProperty("test")

		entity.setProperty(property.employeeID.toString(),
				m_employee.getM_employeeID());
		entity.setProperty(property.ontime.toString(), m_employee.getM_ontime());
		entity.setProperty(property.onsite.toString(), m_employee.getM_onsite());

		entity.setProperty(property.late.toString(), m_employee.getM_late());
		entity.setProperty(property.absence.toString(),
				m_employee.getM_absence());

		entity.setProperty(property.leave.toString(), m_employee.getM_leave());
		entity.setProperty(property.holiday.toString(),
				m_employee.getM_holiday());

		return entity;
	}

	public static List<EmployeeQuota> Clone(List<Entity> entities) {
		List<EmployeeQuota> results = new ArrayList<EmployeeQuota>();
		for (Entity entity : entities)
			results.add(AddDataEmployeeQuota(entity));

		return results;
	}

	public static Filter findEmployeeByEmployeeID(int employeeID) {
		Filter m_emailFilter = new FilterPredicate(
				EmployeeQuota.property.employeeID.toString(),
				FilterOperator.EQUAL, employeeID);
		return m_emailFilter;
	}

	public static EmployeeQuota getEmployeeQuota(int employeeID) {
		// TODO Auto-generated method stub
		EmployeeQuota m_employeeQuota = new EmployeeQuota();

		List<EmployeeQuota> m_employeeQuotas = EmployeeQuotaService
				.Clone(DataStoreControl.Query(EmployeeQuota.class,
						SortDirection.DESCENDING, EmployeeQuotaService
								.findEmployeeByEmployeeID(employeeID)));
		System.out.println("EmployeeQuota :" + m_employeeQuotas.size());

		if (m_employeeQuotas.size() == 1)
			for (EmployeeQuota temp : m_employeeQuotas)
				m_employeeQuota = temp;

		// System.out.println("IS : " + m_employeeQuota + " : " +
		// m_employeeQuota.getM_leave());
		return m_employeeQuota;
	}
}
