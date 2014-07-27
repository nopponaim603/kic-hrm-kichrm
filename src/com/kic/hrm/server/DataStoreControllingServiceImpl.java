package com.kic.hrm.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.datanucleus.store.mapped.DatastoreContainerObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.Employee;

public class DataStoreControllingServiceImpl {
	
	public static void Process() {
		
		/*
		Entity newEntity = DataStoreControl.CreateEntity("Propre");
		DataStoreControl.Edit(newEntity, "Name", "hair");
		Date date = new Date(2557,7,15,12,17,1);//
		//date.setDate((int)15);
		//int i = 0;
		//date.setMonth(Calendar.AUGUST);
		
		DataStoreControl.Edit(newEntity, "Date", date);
		DataStoreControl.Save(newEntity);
		
		*/
		//CreateData();
		LoadDatastore();
	}
	
	private static void CreateData() {
		
		System.out.println("Save Datastore");

	   	Employee m_employee = new Employee();
	   	
	   	m_employee.setM_employeeID(55001);
		m_employee.setM_sex(Employee.sex.Mr);
	   	m_employee.setM_name("Pradorn");
	   	m_employee.setM_surname("Sureephong");
	   	//m_employee.setM_nameT("¿√“¥√");
	    //m_employee.setM_surnameT(" ÿ√’¬Ïæß…Ï");
	   	m_employee.setM_shortName("Pradorn.S");
	   	m_employee.setM_role(Employee.role.ChiefFinancialOfficer);
	   	m_employee.setM_segment(Employee.segment.Advisor);
	   	m_employee.setM_email("dorn@kic.camt.info");
	   	m_employee.setM_phone("0840417542");
	   
	   	
	    Entity d_employee = DataStoreControl.CreateEntity(Employee.class);
	    d_employee = m_employee.FlashData(d_employee);
	    
	    DataStoreControl.Save(d_employee);
	    
	}
	
	private static void LoadDatastore() {
		System.out.println("Load Datastore");

		//System.out.println("getName + " +Employee.class.getName() + " : Sim" + Employee.class.getSimpleName());
				
		List<Employee> results;// = new ArrayList<Employee>();
		List<Entity> entities = DataStoreControl.Query(Employee.class, SortDirection.DESCENDING);
		//DataStoreControl.
		results = Employee.Filter(entities, Employee.property.role , Employee.role.Director.toString());
		System.out.println("Count resultes "+ results.size());
		for(Employee em : results) {
			System.out.println(em.toString());
		}
		
	
	}
}

/*

Query query = new Query("Propre");
query.addSort(Entity.KEY_RESERVED_PROPERTY, Query.SortDirection.DESCENDING);
//datastore.prepare(query).asIterable()
*/
//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


/*
Query query = new Query(Employee.class.getSimpleName(), KeyFactory.stringToKey(""));
//List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
for (Entity entity : entities) {
	if(entity.getProperty(Employee.property.name.toString()).toString().contentEquals("Target"))
		results.add(new Employee(entity));
}*/
//List<Employee> m_test = Employee.Filter(entities, target, type)
//
/*
List<Entity> lEntires = DataStoreControl.Query("Propre", SortDirection.DESCENDING);

for (final Entity entity : lEntires) {
   
	//entity.
//if(entity.getProperty("name") == )
	//System.out.println("entity" + entity.toString());
	//System.out.println("in entity by key name : " + entity.getProperty("Name"));
	//entity.g
	//if(entity.getProperty("Name").toString().contentEquals("hair")) {
	//	System.out.println("-> in hair");
		final Map<String, Object> properties = entity.getProperties();
        
    	final String[] propertyNames = properties.keySet().toArray(
            new String[properties.size()]);
        for(final String propertyName : propertyNames) {
        		System.out.println("-> " + propertyName + ": " + entity.getProperty(propertyName));
        		if(propertyName.contentEquals("Date")) {
        			Class<? extends Object> m_temp = entity.getProperty(propertyName).getClass() ;
        			//entity.getKey().
        			System.out.println("->" + m_temp);
        		}
        }
	}
	
	*/
	//String name = entity.getProperty("name").toString();
	//entity.getProperties()
	//Date date = entity.getProperty(propertyName)
    
//}
//Entity employee = datastore.get();
