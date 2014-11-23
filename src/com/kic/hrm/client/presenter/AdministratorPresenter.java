package com.kic.hrm.client.presenter;

import java.util.ArrayList;
import java.util.Date;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.client.event.gotoProfileAndEditEvent;
import com.kic.hrm.client.event.gotoProfileEvent;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.data.model.SystemConfig;
import com.kic.hrm.shared.TimeStartConfig;


public class AdministratorPresenter implements Presenter{

	public interface Display {
		HasValue<String> getFolderID();
		HasClickHandlers getSaveCSVtoDriveButton();
		
		HasClickHandlers getSendEmailButton();
		HasValue<String> getEmailReceiver();
		
		HasValue<String> getCalendarID();
		
		ListBox getUsersListBox();
		HasClickHandlers getAddProfileButton();
		HasClickHandlers getRefreshListsProfileButton();
		HasClickHandlers getDeleteProfileButton();
		HasClickHandlers getEditProfileButton();
		
		HasClickHandlers getBackButton();
		
		ListBox AdminOnDutyHoure();
		ListBox AdminOnDutyMinutes();
		ListBox AdminOffDutyHoure();
		ListBox AdminOffDutyMinutes();
		ListBox AdminEarlyMinutes();
		
		ListBox ProjectOnDutyHoure();
		ListBox ProjectOnDutyMinutes();
		ListBox ProjectOffDutyHoure();
		ListBox ProjectOffDutyMinutes();
		ListBox ProjectEarlyMinutes();
		
		HasClickHandlers getDefaultButton();
		
		Widget asWidget();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	private SystemConfig m_SysConfig;

	final Auth AUTH = Auth.get();
	
	public AdministratorPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		m_SysConfig = new SystemConfig();

		
		bind();
	}
	
	private void bind() {
		// TODO Auto-generated method stub
		DisplayProfileHandler();
		
		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ApplyDisplaySystemConfig();
				rpcService.ApplySystemConfig(m_SysConfig, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						System.out.println("Back to Dashboard.");
						eventBus.fireEvent(new gotoDashBoardEvent());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
		
		display.getSendEmailButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Send Report Dairy to Email.");
				String emailSendTo = "noppon_aim@hotmail.com";
				rpcService.sendReportDairyToEmail(emailSendTo, new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						System.out.println("Success Send Email : " + result);
						Window.alert("Call Server Success To Send Email : Result is " + result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
		display.getDefaultButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				SystemConfig temp = m_SysConfig;
				m_SysConfig = new SystemConfig();
				
				m_SysConfig.setKind(temp.getKind());
				m_SysConfig.setKeyID(temp.getKeyID());
				m_SysConfig.setM_Drive_folderID(temp.getM_Drive_folderID());
				m_SysConfig.setM_Report_email(temp.getM_Report_email());
				SetupDisplay(m_SysConfig);
			}
		});
		//Setup Time Config
		//m_SysConfig
		rpcService.getSystemConfig(new AsyncCallback<SystemConfig>() {
			
			@Override
			public void onSuccess(SystemConfig result) {
				// TODO Auto-generated method stub
				m_SysConfig = result;
				SetupDisplay(m_SysConfig);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
		
		//If Data have not on Data store
		//
		
	}
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		 container.clear();
		 container.add(display.asWidget());
	}
	private void SetupDisplay(SystemConfig sysConfig) {
		display.getFolderID().setValue(sysConfig.getM_Drive_folderID());
		display.getEmailReceiver().setValue(sysConfig.getM_Report_email());
		display.getCalendarID().setValue(sysConfig.getM_calendarID());
		setupDefultTime(sysConfig);
	}
	
	void setupDefultTime( SystemConfig sysConfig) {
		//TimeStartConfig.setDefult();
		
		SetupTimeHoureAndMinutes(sysConfig.getAdminOndutyTime(),display.AdminOnDutyHoure() , display.AdminOnDutyMinutes());
		SetupTimeHoureAndMinutes(sysConfig.getAdminOffdutyTime(),display.AdminOffDutyHoure() , display.AdminOffDutyMinutes());
		SetupEarlyMinutes(sysConfig.getAdminEarly(),display.AdminEarlyMinutes());
		
		SetupTimeHoureAndMinutes(sysConfig.getProjectOndutyTime(),display.ProjectOnDutyHoure() , display.ProjectOnDutyMinutes());
		SetupTimeHoureAndMinutes(sysConfig.getProjectOffdutyTime(),display.ProjectOffDutyHoure() , display.ProjectOffDutyMinutes());
		SetupEarlyMinutes(sysConfig.getProjectEarly(),display.ProjectEarlyMinutes());
	}
	
	void SetupTimeHoureAndMinutes(Date Time,ListBox m_listboxHoure ,ListBox m_listboxMinutes ) {
		m_listboxHoure.setItemSelected(Time.getHours(), true);
		m_listboxMinutes.setItemSelected(Time.getMinutes(), true);			
	}
	
	void SetupEarlyMinutes(Date Time,ListBox m_listbox) {
		m_listbox.setItemSelected(converterEarlyMinutes(Time.getMinutes()), true);
	}
	
	int converterEarlyMinutes(int input_minutes) {
		switch(input_minutes) {
		case 5: return 0;
		case 10: return 1;
		case 15: return 2;
		case 30: return 3;
		case 45: return 4;
		case 60: return 5;
		}
		
		return 0;
	}

	Date converterToDate(Date OriginDate,ListBox m_listboxHoure ,ListBox m_listboxMinutes) {
		Date temp = OriginDate;
		
		if(m_listboxHoure != null)
		temp.setHours(Integer.parseInt(m_listboxHoure.getValue(m_listboxHoure.getSelectedIndex())));
		else temp.setHours(0);
		
		temp.setMinutes(Integer.parseInt(m_listboxMinutes.getValue(m_listboxMinutes.getSelectedIndex())));
		//System.out.println("Print Time : " + temp);
		return temp;
	}
	
	private void ApplyDisplaySystemConfig() {
		
		m_SysConfig.setM_Drive_folderID(display.getFolderID().getValue());
		m_SysConfig.setM_Report_email(display.getEmailReceiver().getValue());
		m_SysConfig.setM_calendarID(display.getCalendarID().getValue());
		
		m_SysConfig.setAdminOndutyTime(converterToDate(m_SysConfig.getAdminOndutyTime() 
														, display.AdminOnDutyHoure()
														, display.AdminOnDutyMinutes()));
		
		m_SysConfig.setAdminOffdutyTime(converterToDate(m_SysConfig.getAdminOffdutyTime() 
														, display.AdminOffDutyHoure()
														, display.AdminOffDutyMinutes()));
		
		m_SysConfig.setAdminEarly(converterToDate(m_SysConfig.getAdminEarly() 
														, null
														, display.AdminEarlyMinutes()));
		
		m_SysConfig.setProjectOndutyTime(converterToDate(m_SysConfig.getProjectOndutyTime()
														, display.ProjectOnDutyHoure()
														, display.ProjectOnDutyMinutes()));

		m_SysConfig.setProjectOffdutyTime(converterToDate(m_SysConfig.getProjectOffdutyTime()
														, display.ProjectOffDutyHoure()
														, display.ProjectOffDutyMinutes()));

		m_SysConfig.setProjectEarly(converterToDate(m_SysConfig.getProjectEarly()
														, null
														, display.ProjectEarlyMinutes()));
		//m_SysConfig.setAdminOndutyTime(adminOndutyTime);

		//;
	}
	
	
	private void DisplayProfileHandler() {
		
		display.getSaveCSVtoDriveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(), CloudHRM.getCLIENT_ID())
	            .withScopes(CloudHRM.getDRIVE_SCOPESArry());
				
				AUTH.login(req, new Callback<String, Throwable>() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						rpcService.saveCSVtoDrive(result, display.getFolderID().getValue(),new AsyncCallback<String>() {
							
							@Override
							public void onSuccess(String result) {
								// TODO Auto-generated method stub
								System.out.println("onSuccess");
								Window.alert("Call Server Success : Problem is " + result);
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								System.out.println("onFailure");
								Window.alert("onFailure");
							}
						});
					}
					
					@Override
					public void onFailure(Throwable reason) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
		display.getAddProfileButton().addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("AddProfile");
				eventBus.fireEvent(new gotoProfileEvent());
			}
		});
		
		display.getEditProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("EditProfile.");
				eventBus.fireEvent(new gotoProfileAndEditEvent(Integer.parseInt(display.getUsersListBox().getItemText(display.getUsersListBox().getSelectedIndex()))));
			}
		});
		
		display.getRefreshListsProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("Refresh Complete.");
				UpdateList();
			}
		});
		
		display.getDeleteProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				rpcService.deleteProfile(Integer.parseInt(display.getUsersListBox().getItemText(display.getUsersListBox().getSelectedIndex())), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						System.out.println("Delete Complete.");
						Window.alert("Delete Complete.");
						UpdateList();
					}
					
				});
			}
		});
	}
	
	private void UpdateList() {
		rpcService.UpdateList(Employee.class.getSimpleName(), new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onSuccess(ArrayList<String> result) {
				// TODO Auto-generated method stub
				display.getUsersListBox().clear();
				for(String nameUser : result) {
					display.getUsersListBox().addItem(nameUser);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

		});
	}
}
