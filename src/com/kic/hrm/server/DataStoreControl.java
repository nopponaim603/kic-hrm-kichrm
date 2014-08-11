package com.kic.hrm.server;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;

import com.kic.hrm.data.model.Employee;

public class DataStoreControl {
	static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity CreateEntity(Class<?> entiryClassName) {
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity m_entiry = new Entity(entiryClassName.getSimpleName());
		
		return m_entiry;
	}
	
	public static Entity EditEntity(String inputKey , Long inputID) throws EntityNotFoundException {
		Key newkey = KeyFactory.createKey(inputKey, inputID);
		return datastore.get(newkey);
	}
	
	public static void DeleteEntity(String inputKey , Long inputID) {
		Key newkey = KeyFactory.createKey(inputKey, inputID);
		datastore.delete(newkey);
	}
	
	public static void SaveEntity(Entity entity) {
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//datastore.
		datastore.put(entity);
	}
	
	public static List<Entity> Query(Class<?> entiryClassName,SortDirection sortdirection) {
		
		Query query = new Query(entiryClassName.getSimpleName());
		//Query query = new Query(entiryName, KeyFactory.stringToKey(""));
	    query.addSort(Entity.KEY_RESERVED_PROPERTY, sortdirection);
	    //query.s
	    List<Entity> temp = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
	    /*
	    com.google.appengine.api.datastore.Key mykey = null ;
	    
	    for(Entity em : temp) {
	    	 mykey = em.getKey();
	    	 System.out.println(em.getKey().toString());
	    }
	    Long mykeyID = mykey.getId();
	    String mykeyString = mykey.getKind();
	    
	    com.google.appengine.api.datastore.Key createNewKey = KeyFactory.createKey(mykeyString, mykeyID);
	    try {
			Entity newEntity = datastore.get(createNewKey);
			System.out.println(newEntity.getKey().toString());
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	    return temp;
	}
	
	public static List<Entity> Query(Class<?> entiryClassName,SortDirection sortdirection,Filter filter) {
		
		Query query = new Query(entiryClassName.getSimpleName());
		query.setFilter(filter);
	    query.addSort(Entity.KEY_RESERVED_PROPERTY, sortdirection);
	    List<Entity> temp = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

	    return temp;
	}
	
	public static List<Entity> Query(Class<?> entiryClassName,SortDirection sortdirection,List<Filter> filters) {
		
		Query query = new Query(entiryClassName.getSimpleName());
		for(Filter filter : filters)
			query.setFilter(filter);
		
	    query.addSort(Entity.KEY_RESERVED_PROPERTY, sortdirection);
	    List<Entity> temp = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

	    return temp;
	}
				
	public static void EditProperty(Entity entity,String propertyName,int value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void EditProperty(Entity entity,String propertyName,float value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void EditProperty(Entity entity,String propertyName,String value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void EditProperty(Entity entity,String propertyName,Date value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void DeleteProprety(Entity entity, String propertyName) {
		entity.removeProperty(propertyName);
	}
	
}
