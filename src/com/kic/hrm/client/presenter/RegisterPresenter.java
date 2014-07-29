package com.kic.hrm.client.presenter;

import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.css.ast.HasSelectors;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.ProfileUpdateEvent;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter.Display;
import com.kic.hrm.data.model.Employee;

public class RegisterPresenter implements Presenter{

	public interface Display {
		Widget asWidget();

		HasClickHandlers getSubmit();
		HasClickHandlers getCancel();
		
		HasValue<Integer> getWorkID();
		String getSex();
		HasValue<String> getName();
		HasValue<String> getSurname();
		HasValue<String> getNameT();
		HasValue<String> getSurnameT();
		HasValue<String> getShortname();
		
		String  getRole();
		String  getSegment();
		HasValue<String> getEmail();
		HasValue<String> getPhone();
		
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	public Employee m_employee;
	public RegisterPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		m_employee = new Employee();
		//display.getWorkID().getValue()
		//display.getSex().getName()
		bind();
		
	}

	private void bind() {
		// TODO Auto-generated method stub
		display.getSubmit().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	  System.out.println("getSubmit  : on Click");
		    	  doSave();
		      }
		    });
		
		display.getCancel().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		    	  System.out.println("getCancel  : on Click");
		    	  //History.fireCurrentHistoryState();
		    	  //eventBus.fireEvent(new ProfileUpdateEvent());
		      }
		    });
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
			container.clear();
			container.add(display.asWidget());
	}
	
	private void doSave() {
		

		m_employee.setM_employeeID(display.getWorkID().getValue());
		m_employee.setM_sex(Employee.sex.valueOf(display.getSex()));
		m_employee.setM_name(display.getName().getValue());
		m_employee.setM_surname(display.getSurname().getValue());
		m_employee.setM_nameT(display.getNameT().getValue());
		m_employee.setM_surnameT(display.getSurnameT().getValue());
		m_employee.setM_shortName(display.getShortname().getValue());
		m_employee.setM_role(Employee.role.valueOf(display.getRole()));
		m_employee.setM_segment(Employee.segment.valueOf(display.getSegment()));
		m_employee.setM_email(display.getEmail().getValue());
		m_employee.setM_phone(display.getPhone().getValue());
		
		System.out.println("Test Value");
		System.out.println(m_employee.getM_name() + " : " + m_employee.getM_employeeID() + " : " + m_employee.getM_sex()
				+ " : " +m_employee.getM_nameT());
		
		
		//eventBus.fireEvent(new ProfileUpdateEvent());
	}
}
