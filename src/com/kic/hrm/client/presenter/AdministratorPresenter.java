package com.kic.hrm.client.presenter;

import java.util.ArrayList;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.AppController;
import com.kic.hrm.client.CloudHRM;
import com.kic.hrm.client.GreetingServiceAsync;

import com.kic.hrm.client.event.gotoDashBoardEvent;
import com.kic.hrm.client.event.gotoDashBoardEventHandler;
import com.kic.hrm.client.event.gotoProfileAndEditEvent;
import com.kic.hrm.client.event.gotoProfileEvent;
import com.kic.hrm.client.event.gotoProfileEventHandler;
import com.kic.hrm.data.model.Employee;


public class AdministratorPresenter implements Presenter{

	public interface Display {
		HasValue<String> getFolderID();
		HasClickHandlers getSaveCSVtoDriveButton();
		HasClickHandlers getFlashDataStoreButton();
		
		HasValue<String> getEmailReceiver();
		
		ListBox getUsersListBox();
		HasClickHandlers getAddProfileButton();
		HasClickHandlers getRefreshListsProfileButton();
		HasClickHandlers getDeleteProfileButton();
		HasClickHandlers getEditProfileButton();
		
		HasClickHandlers getBackButton();
		
		Widget asWidget();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
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
				eventBus.fireEvent(new gotoDashBoardEvent());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		 container.clear();
		 container.add(display.asWidget());
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

	private void DisplayHandler() {
		
		display.getSaveCSVtoDriveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(), CloudHRM.getGOOGLE_CLIENT_ID())
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
				System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
				eventBus.fireEvent(new gotoProfileEvent());
				System.out.println("HRM_P| After call eventBus fireEvent");
				//this.e
				
			}
		});
		
		display.getEditProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("code is go to AppController.java.");
				eventBus.fireEvent(new gotoProfileAndEditEvent(Integer.parseInt(display.getUsersListBox().getItemText(display.getUsersListBox().getSelectedIndex()))));
			}
		});
		
		display.getRefreshListsProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
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
						UpdateList();
					}
					
				});
			}
		});
	}
	
}
