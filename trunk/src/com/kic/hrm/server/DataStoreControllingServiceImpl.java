package com.kic.hrm.server;

import java.util.Date;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

public class DataStoreControllingServiceImpl {
	private static void SaveDatastore() {
		
		System.out.println("Save Datastore");
		
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	   	    
	    Entity employee = new Entity("Employee");
	    employee.setProperty("name", "Antonio Salieri");
	    employee.setProperty("hireDate", new Date());
	    
	    datastore.put(employee);
	    
	}
	
	private static void LoadDatastore() {
		System.out.println("Load Datastore");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Employee");
	    query.addSort(Entity.KEY_RESERVED_PROPERTY, Query.SortDirection.DESCENDING);

	    for (final Entity entity : datastore.prepare(query).asIterable()) {
	       
	    	System.out.println("entity" + entity.toString());
	    	System.out.println("in entity by key name : " + entity.getProperty("name"));
	    	//String name = entity.getProperty("name").toString();
	    	//entity.getProperties()
	    	//Date date = entity.getProperty(propertyName)
	        final Map<String, Object> properties = entity.getProperties();
	        
	    	final String[] propertyNames = properties.keySet().toArray(
	            new String[properties.size()]);
	        for(final String propertyName : propertyNames) {
	           System.out.println("-> " + propertyName + ": " + entity.getProperty(propertyName));
	        }
	    }
		//Entity employee = datastore.get();
		
	}
}
