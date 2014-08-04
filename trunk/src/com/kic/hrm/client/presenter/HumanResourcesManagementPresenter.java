package com.kic.hrm.client.presenter;


import java.util.ArrayList;

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
import com.kic.hrm.client.event.ApplyLeavingEvent;
import com.kic.hrm.client.event.EditProfileEvent;
import com.kic.hrm.client.event.RegisterEvent;
import com.kic.hrm.client.presenter.Presenter;
import com.kic.hrm.data.model.Employee;
import com.kic.hrm.shared.LoginInfo;

public class HumanResourcesManagementPresenter implements Presenter {

	public interface Display {
		
		//Apply Leaving
		HasClickHandlers getApplyLeavingButton();
		
		//Toggle
		HasClickHandlers getToggleOauth();
		
		/*
		void setData(List<String> data);
		int getClickedRow(ClickEvent event);
		List<Integer> getSelectedRows();
		*/
		
		HasClickHandlers getAddProfileButton();
		HasClickHandlers getEditProfileButton();
		HasClickHandlers getRefreshButton();
		HasClickHandlers getDeleteButton();
		
		HasClickHandlers getFileButton();
		HasValue<String> getIDFile();
		
		Widget asWidget();
		
		ListBox getUsersListBox();
	}
	
	private final GreetingServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	
	final Auth AUTH = Auth.get();
	public HumanResourcesManagementPresenter(GreetingServiceAsync rpcService,HandlerManager eventBus, Display view) {
		// TODO Auto-generated constructor stub
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		
		bind();
		
		 //	    
		System.out.println(HumanResourcesManagementPresenter.class.getSimpleName() + " : constructor" );
	}
		
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	    container.clear();
	    container.add(display.asWidget());
	}

	public void bind() {
		// TODO Auto-generated method stub
		
		DisplayTabOne();
		
		display.getApplyLeavingButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		       System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
		        //eventBus.fireEvent(new ApplyLeavingEvent());
		       //eventBus
		       final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(), CloudHRM.getGOOGLE_CLIENT_ID())
	            .withScopes(CloudHRM.getDRIVE_SCOPESArry());
		       
		       AUTH.login(req, new Callback<String, Throwable>() {
				
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						rpcService.ApplyLeaving(result, new AsyncCallback<Boolean>() {
							
							@Override
							public void onSuccess(Boolean result) {
								// TODO Auto-generated method stub
								 eventBus.fireEvent(new ApplyLeavingEvent());
					    	 System.out.println("HRM_P| After call eventBus fireEvent");
					    	  //History.fireCurrentHistoryState();
					    	  //ApplyLeaving();
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
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

		DisplayTabAdmin();
		/*
		display.getAddButton().addClickHandler(new ClickHandler() {   
		      public void onClick(ClickEvent event) {
		        
		      }
		    });
		*/
		//
	}
	
	private void DisplayTabOne() {
		display.getToggleOauth().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
				// eventBus.fireEvent(new RegisterEvent());
				//ToggleOauth();
			}
			
		});
	}
	
	private void DisplayTabAdmin() {
		display.getAddProfileButton().addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("HRM_P| " + event.toDebugString() + " is on Click");
				eventBus.fireEvent(new RegisterEvent());
				System.out.println("HRM_P| After call eventBus fireEvent");
				//this.e
			}
		});

		display.getEditProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new EditProfileEvent(Integer.parseInt(display.getUsersListBox().getItemText(display.getUsersListBox().getSelectedIndex()))));
			}
		});
		
		display.getRefreshButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				UpdateList();
			}
		});
		
		display.getDeleteButton().addClickHandler(new ClickHandler() {
			
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
		
		display.getFileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				final AuthRequest req = new AuthRequest(CloudHRM.getGOOGLE_AUTH_URL(), CloudHRM.getGOOGLE_CLIENT_ID())
	            .withScopes(CloudHRM.getDRIVE_SCOPESArry());
				
				AUTH.login(req, new Callback<String, Throwable>() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						rpcService.saveCSVtoDrive(result, display.getIDFile().getValue(),new AsyncCallback<String>() {
							
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
		
	}
	
	private void UpdateList() {
		rpcService.UpdateList(Employee.class.getSimpleName(), new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				// TODO Auto-generated method stub
				display.getUsersListBox().clear();
				for(String nameUser : result) {
					display.getUsersListBox().addItem(nameUser);
				}
			}
		});
	}
	
	private void ApplyLeaving(){
		System.out.println("HRM Presenter Before call rpcService");
		
		rpcService.ApplyLeaving("test", new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onSuccess");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onFailure");
			}
		});
	}
	
	private void ToggleOauth() {
		System.out.println("HRM Presenter in ToggleOauth : Before call rpcService : By press True Only");
		rpcService.EnableOauth(true, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onFailure");
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				System.out.println("HRM Presenter on Click onSuccess");
				//eventBus.fireEvent(new RegisterEvent());
				//eventBus.
			}
		});
	}
	
	
}
