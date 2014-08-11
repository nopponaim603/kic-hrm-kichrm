package com.kic.hrm.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.GreetingServiceAsync;
import com.kic.hrm.client.presenter.NewPresenter.Display;
import com.kic.hrm.client.presenter.NewPresenter.taskRole;
import com.kic.hrm.client.view.event.NewViewLeadApprove;
import com.kic.hrm.client.view.event.NewViewLeadEject;
import com.kic.hrm.data.model.LeaveTask;
import com.kic.hrm.data.model.LeaveTask.progress;


public class NewView extends Composite implements Display{
	
	private final Button backButton;
	private final FlexTable flexTable;
	public NewView() {
		// TODO Auto-generated constructor stub
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		ScrollPanel scrollPanel = new ScrollPanel();
		verticalPanel.add(scrollPanel);
		
		FlexTable flexTable = new FlexTable();
		flexTable.setCellSpacing(3);
		scrollPanel.setWidget(flexTable);
		flexTable.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("News");
		flexTable.setWidget(0, 0, lblNewLabel);
		
		Button btnBack = new Button("Back");
		flexTable.setWidget(1, 0, btnBack);
		btnBack.setWidth("100%");
		
		Label lblTaskList = new Label("Task List :");
		flexTable.setWidget(2, 0, lblTaskList);
		
		
		/*
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		flexTable.setWidget(3, 0, verticalPanel_1);
		
		Label lblLeave = new Label("Leave");
		verticalPanel_1.add(lblLeave);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		
		Label lblNameDate = new Label("Name : Date");
		horizontalPanel.add(lblNameDate);
		
		Button btnNewButton = new Button("New button");
		horizontalPanel.add(btnNewButton);
		
		Button btnNewButton_1 = new Button("New button");
		horizontalPanel.add(btnNewButton_1);
		
		*/
		
		backButton = btnBack;
		this.flexTable = flexTable;
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}
	
	@Override
	public HasClickHandlers getBackButton() {
		// TODO Auto-generated method stub
		return backButton;
	}
	
	@Override
	public void createTake(GreetingServiceAsync rpcService,
			LeaveTask leavetask, taskRole Owner) {
		// TODO Auto-generated method stub
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		Label lblLeave = new Label(leavetask.getM_leavetype().toString() + " : Owner" + Owner.toString());
		verticalPanel_1.add(lblLeave);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		
		Label lblNameDate = new Label("Start : " + leavetask.getM_start().toString() + " to " + leavetask.getM_end().toString()
							+ " \n Message : " + leavetask.getM_sendmessage()
							+ " \n Progress : " + leavetask.getM_leaveprogress().toString());
		horizontalPanel.add(lblNameDate);
			
		if(Owner == taskRole.Leader || Owner == taskRole.HR) {
			Button ApproveButton = new Button("Approve");
			horizontalPanel.add(ApproveButton);
			ApproveButton.addClickHandler(new NewViewLeadApprove(rpcService,leavetask));
		}
		
		if(leavetask.getM_leaveprogress() != progress.Complete) {
			Button EjectButton = new Button("Eject");
			horizontalPanel.add(EjectButton);
			EjectButton.addClickHandler(new NewViewLeadEject(rpcService,leavetask));
		}
		
		
		
		
		
		
		
		this.flexTable.setWidget(this.flexTable.getRowCount(), 0, verticalPanel_1);
	}
	
}
