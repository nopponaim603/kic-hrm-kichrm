package com.kic.hrm.data.model;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.kic.hrm.data.model.SystemConfig.property;
import com.kic.hrm.server.DataStoreControl;

public class SystemConfigService {

	// Service
	public static SystemConfig AddDataSystemConfig(Entity entity) {
		SystemConfig m_sysConfig = new SystemConfig();
		
		m_sysConfig.setKind(entity.getKind());
		m_sysConfig.setKeyID(entity.getKey().getId());
		
		//String tempLong = Long.toString((Long) entity.getProperty(property.employeeID.toString()));
		m_sysConfig.setM_Drive_folderID( (String) entity.getProperty(property.Drive_folderID.toString()));
		m_sysConfig.setM_Report_email( (String) entity.getProperty(property.Report_email.toString()));
		m_sysConfig.setM_calendarID( (String) entity.getProperty(property.calendarID.toString()));
		
		m_sysConfig.setAdminOndutyTime((Date) entity.getProperty(property.AdminOndutyTime.toString()));
		m_sysConfig.setAdminOffdutyTime((Date) entity.getProperty(property.AdminOffdutyTime.toString()));
		m_sysConfig.setAdminEarly((Date) entity.getProperty(property.AdminEarly.toString()));
		
		m_sysConfig.setProjectOndutyTime((Date) entity.getProperty(property.ProjectOndutyTime.toString()));
		m_sysConfig.setProjectOffdutyTime((Date) entity.getProperty(property.ProjectOffdutyTime.toString()));
		m_sysConfig.setProjectEarly((Date) entity.getProperty(property.ProjectEarly.toString()));
		
		return m_sysConfig;	
	}
	
	public static Entity FlashData(Entity entity, SystemConfig sysConfig) {

		// entity.getProperty("test")

		entity.setProperty(property.Drive_folderID.toString(), sysConfig.getM_Drive_folderID());
		entity.setProperty(property.Report_email.toString()  , sysConfig.getM_Report_email());
		entity.setProperty(property.calendarID.toString()  , sysConfig.getM_calendarID());
		
		entity.setProperty(property.AdminOndutyTime.toString(), sysConfig.getAdminOndutyTime());
		entity.setProperty(property.AdminOffdutyTime.toString(), sysConfig.getAdminOffdutyTime());
		entity.setProperty(property.AdminEarly.toString(), sysConfig.getAdminEarly());

		entity.setProperty(property.ProjectOndutyTime.toString(), sysConfig.getProjectOndutyTime());
		entity.setProperty(property.ProjectOffdutyTime.toString(), sysConfig.getProjectOffdutyTime());
		entity.setProperty(property.ProjectEarly.toString(), sysConfig.getProjectEarly());

		return entity;
	}
	
	public static SystemConfig getSystemConfig() {
		// TODO Auto-generated method stub
		SystemConfig sysconfig;
		List<Entity> entities = DataStoreControl.Query(SystemConfig.class,
				SortDirection.ASCENDING);
		System.out.println("System Config Data . " + entities.size());
		if(entities.size() > 0 )
		{
			System.out.println("Have Data.");
			sysconfig = SystemConfigService.AddDataSystemConfig(entities.get(0));
		}else {
			System.out.println("No Data.");
			sysconfig = new SystemConfig();
			Entity m_entity = DataStoreControl.CreateEntity(SystemConfig.class);
			m_entity = SystemConfigService.FlashData(m_entity, sysconfig);
			DataStoreControl.SaveEntity(m_entity);
			//sysconfig = SystemConfigService.AddDataSystemConfig(m_entity);
		}
		
		return sysconfig;
	}
	
	public static void ApplySystemConfig(SystemConfig sysConfig) {
		// TODO Auto-generated method stub
		Entity d_systemConfig = null;
		
		try {
			d_systemConfig = DataStoreControl.EditEntity(sysConfig.getKind(), sysConfig.getKeyID());
			d_systemConfig = SystemConfigService.FlashData(d_systemConfig, sysConfig);
			DataStoreControl.SaveEntity(d_systemConfig);
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
