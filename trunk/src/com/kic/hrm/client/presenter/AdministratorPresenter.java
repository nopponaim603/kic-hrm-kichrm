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
		
		bind();
	}
	
	private void bind() {
		// TODO Auto-generated method stub
		DisplayHandler();
		
		display.getBackButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("test Back to Dashboard.");
				eventBus.fireEvent(new gotoDashBoardEvent());
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
		
		//Setup Time Config
		//m_SysConfig
		
		//If Data have not on Data store
		//setupDefultTime();
		
	}
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		 container.clear();
		 container.add(display.asWidget());
	}
	
	void setupDefultTime() {
		TimeStartConfig.setDefult();
		
		SetupTimeHoureAndMinutes(TimeStartConfig.getAdminOndutyTime(),display.AdminOnDutyHoure() , display.AdminOnDutyMinutes());
		SetupTimeHoureAndMinutes(TimeStartConfig.getAdminOffdutyTime(),display.AdminOffDutyHoure() , display.AdminOffDutyMinutes());
		SetupEarlyMinutes(TimeStartConfig.getAdminEarly(),display.AdminEarlyMinutes());
		
		SetupTimeHoureAndMinutes(TimeStartConfig.getProjectOndutyTime(),display.ProjectOnDutyHoure() , display.ProjectOnDutyMinutes());
		SetupTimeHoureAndMinutes(TimeStartConfig.getProjectOffdutyTime(),display.ProjectOffDutyHoure() , display.ProjectOffDutyMinutes());
		SetupEarlyMinutes(TimeStartConfig.getProjectEarly(),display.ProjectEarlyMinutes());
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

	private void DisplayHandler() {
		
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
