package com.kic.hrm.server;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.Employee;

public class DataStoreControl {
	static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public static Entity CreateEntity(Class<?> entiryClassName) {
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity m_entiry = new Entity(entiryClassName.getSimpleName());
		return m_entiry;
	}
		
	public static void Edit(Entity entity,String propertyName,int value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void Edit(Entity entity,String propertyName,float value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void Edit(Entity entity,String propertyName,String value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void Edit(Entity entity,String propertyName,Date value) {
		entity.setProperty(propertyName, value);
	}
	
	public static void DeleteProprety(Entity entity, String propertyName) {
		entity.removeProperty(propertyName);
	}
	
	public static void Save(Entity entity) {
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(entity);
	}
	
	public static List<Entity> Query(Class<?> entiryClassName,SortDirection sortdirection) {
		
		Query query = new Query(entiryClassName.getSimpleName());
		//Query query = new Query(entiryName, KeyFactory.stringToKey(""));
	    query.addSort(Entity.KEY_RESERVED_PROPERTY, sortdirection);
	    //query.s
	    return datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
	}
	
	public static void DeleteEntity(String entiry) {
		//datastore.d
		//DeleteAllData
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//datastore.
	}
	
	/*
	public static <T> List<T> Filter() {
		List<T> temps = new List<T>();
		return  temps;
	}
	*/

}
