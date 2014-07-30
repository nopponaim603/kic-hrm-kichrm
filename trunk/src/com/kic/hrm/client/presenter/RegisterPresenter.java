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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.AddProfileEvent;
import com.kic.hrm.client.event.ProfileUpdateEvent;
import com.kic.hrm.client.event.RegisterEvent;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter.Display;
import com.kic.hrm.client.view.RegisterView;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.Employee.sex;

public class RegisterPresenter implements Presenter{
	public enum state {
		add,
		edit,
		NONE
	}
	public interface Display {
		Widget asWidget();

		HasClickHandlers getSubmit();
		HasClickHandlers getCancel();
		
		HasValue<String> getWorkID();
		String getSex();
		ListBox setSex();
		HasValue<String> getName();
		HasValue<String> getSurname();
		HasValue<String> getNameT();
		HasValue<String> getSurnameT();
		HasValue<String> getShortname();
		
		String  getRole();
		ListBox setRole();
		
		String  getSegment();
		ListBox setSegment();
		
		HasValue<String> getEmail();
		HasValue<String> getPhone();
		
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	public Employee m_employee;
	private state redisterState;
	public RegisterPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		bind();
		redisterState = state.add;
		m_employee = new Employee();
		//display.getWorkID().getValue()
		//display.getSex().getName()
		
		
	}

	public RegisterPresenter(GreetingServiceAsync rpcService,
			HandlerManager eventBus, Display view, int employeeID) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		bind();
		redisterState = state.edit;
		
		//get employee data form rpc server
		rpcService.getProfile(employeeID, new AsyncCallback<Employee>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Employee result) {
				m_employee = result;
				int test = m_employee.getM_sex().ordinal();
				
				//System.out.println("Key :" + m_employee.getKey() + " : " + test);
				
				// TODO Auto-generated method stub
				//Integer.
				//System.out.println(m_employee.getM_employeeID());
				
				display.getWorkID().setValue(String.valueOf(m_employee.getM_employeeID()));
				
				//System.out.println(display.getWorkID().getValue());
				//Integer.				
				display.setSex().setSelectedIndex(m_employee.getM_sex().ordinal());
				display.getName().setValue(m_employee.getM_name());
				display.getSurname().setValue(m_employee.getM_surname());
				display.getNameT().setValue(m_employee.getM_nameT());
				display.getSurnameT().setValue(m_employee.getM_surnameT());
				display.getShortname().setValue(m_employee.getM_shortName());
				display.setRole().setSelectedIndex(m_employee.getM_role().ordinal());
				display.setSegment().setSelectedIndex(m_employee.getM_segment().ordinal());
				display.getEmail().setValue(m_employee.getM_email());
				display.getPhone().setValue(m_employee.getM_phone());
			}
		});
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
		    	  eventBus.fireEvent(new AddProfileEvent());
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
		

		m_employee.setM_employeeID(Integer.parseInt(display.getWorkID().getValue()));
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
		
		/*
		System.out.println("Test Value");
		System.out.println(m_employee.getM_name() + " : " + m_employee.getM_employeeID() + " : " + m_employee.getM_sex()
				+ " : " +m_employee.getM_nameT());
		*/
		
		rpcService.addProfile(m_employee ,this.redisterState, new AsyncCallback<Employee>() {
			
			@Override
			public void onSuccess(Employee result) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new AddProfileEvent());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		//eventBus.fireEvent(new ProfileUpdateEvent());
	}

}
