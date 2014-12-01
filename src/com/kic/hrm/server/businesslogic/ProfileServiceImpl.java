package com.kic.hrm.server.businesslogic;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.client.presenter.ProfilePresenter.state;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.EmployeeQuota;
import com.kic.hrm.data.model.EmployeeQuotaService;
import com.kic.hrm.data.model.EmployeeService;
import com.kic.hrm.server.DataStoreControl;
import com.kic.hrm.server.GreetingServiceImpl;

public class ProfileServiceImpl {

	private static final Logger log = Logger
			.getLogger(GreetingServiceImpl.class.getName());

	public static boolean isMember(String email) {
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class,
				SortDirection.ASCENDING);
		results = EmployeeService.Clone(entities);
		// System.out.println("Rusults Size " +results.size());
		for (Employee em : results) {
			// System.out.println("Email : " + em.getM_email());
			//log.log(Level.SEVERE, "Data Email " + em.getM_email() + " : input Email " + email);	
			if (em.getM_email().equalsIgnoreCase(email)) {
				//log.log(Level.SEVERE, "Finded.");
				// System.out.println("This's email :" + email + " Member.");
				return true;
			}
		}
		//log.log(Level.SEVERE, "Could not Finded.");
		return false;
	}
	
	public static Employee addProfile(Employee userEmployee,EmployeeQuota userQuota, state registerMode) {
		// TODO Auto-generated method stub
		Entity d_employee = null;
		Entity d_quota = null;
		if (registerMode == state.add) {
			d_employee = DataStoreControl.CreateEntity(Employee.class);

			d_quota = DataStoreControl.CreateEntity(EmployeeQuota.class);

		} else if (registerMode == state.edit) {
			try {
				//FIx Bug : Duplicate User Data
				d_employee = DataStoreControl.EditEntity(userEmployee.getKind(), userEmployee.getKeyID());
				d_quota = DataStoreControl.EditEntity(userQuota.getKind(),userQuota.getKeyID());
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// System.out.println(d_employee.toString() + " : " +
		// d_employee.getProperty(property.employeeID.toString()));

		d_employee = EmployeeService.FlashData(d_employee, userEmployee);
		DataStoreControl.SaveEntity(d_employee);

		d_quota = EmployeeQuotaService.FlashData(d_quota, userQuota);
		DataStoreControl.SaveEntity(d_quota);
		return userEmployee;
	}

	public static Employee getProfile(Integer targetEmployee) {
		// TODO Auto-generated method stub
		List<Employee> results;
		List<Entity> entities = DataStoreControl.Query(Employee.class,SortDirection.ASCENDING);
		results = EmployeeService.Clone(entities);
		Employee temp_employee = null;
		
		for (Employee em : results) {
			if (em.getM_employeeID() == targetEmployee) {
				temp_employee = em;
				break;
			}
		}
		
		return temp_employee;
	}
	
	public static Employee getProfile(String email) {
		// TODO Auto-generated method stub
		List<Employee> results;
		List<Entity> entities = DataStoreControl.Query(Employee.class,SortDirection.ASCENDING);
		results = EmployeeService.Clone(entities);
		Employee temp_employee = null;
		
		for (Employee em : results) {
			if (em.getM_email().equalsIgnoreCase(email)) {
				temp_employee = em;
				break;
			}
		}
		
		return temp_employee;
	}

	public static boolean deleteProfile(Integer targetEmployee) {
		// TODO Auto-generated method stub
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class,
				SortDirection.ASCENDING,
				EmployeeService.findEmployeeByEmployeeID(targetEmployee));
		results = EmployeeService.Clone(entities);
		Employee temp_employee = null;
		for (Employee em : results) {
			if (em.getM_employeeID() == targetEmployee) {
				temp_employee = em;
				break;
			}
		}

		DataStoreControl.DeleteEntity(temp_employee.getKind(),
				temp_employee.getKeyID());

		entities = DataStoreControl.Query(EmployeeQuota.class,
				SortDirection.ASCENDING,
				EmployeeQuotaService.findEmployeeByEmployeeID(targetEmployee));
		List<EmployeeQuota> resultsQuota = EmployeeQuotaService.Clone(entities);

		EmployeeQuota temp_employeeQuota = null;
		for (EmployeeQuota emQT : resultsQuota) {
			if (emQT.getM_employeeID() == targetEmployee) {
				temp_employeeQuota = emQT;
				break;
			}
		}
		DataStoreControl.DeleteEntity(temp_employeeQuota.getKind(),
				temp_employeeQuota.getKeyID());

		return false;
	}
	// Add Edit Delete Profile > end

	public static List<Employee> getProfileList() {
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class,
				SortDirection.ASCENDING);
		results = EmployeeService.Clone(entities);
		
		return results;
	}
	
	public static ArrayList<String> UpdateList(String targetEntity) {
		// TODO Auto-generated method stub
		List<Employee> results = getProfileList();// = new ArrayList<Employee>();
		ArrayList<String> EmployeeID = new ArrayList<String>();

		for (Employee em : results) {
			EmployeeID.add(String.valueOf(em.getM_employeeID()));
		}

		return EmployeeID;
	}
}
