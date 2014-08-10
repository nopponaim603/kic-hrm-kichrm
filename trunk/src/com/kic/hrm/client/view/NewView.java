package com.kic.hrm.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.view.event.NewViewLeadApprove;
import com.kic.hrm.client.view.event.NewViewLeadEject;


public class NewView extends Composite{
	
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
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		flexTable.setWidget(0, 0, horizontalPanel_1);
		
		Button btnNewButton_2 = new Button("New button");
		horizontalPanel_1.add(btnNewButton_2);
		
		Button btnNewButton_3 = new Button("New button");
		horizontalPanel_1.add(btnNewButton_3);
		
		Label lblNewLabel = new Label("News");
		flexTable.setWidget(1, 0, lblNewLabel);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		flexTable.setWidget(2, 0, verticalPanel_1);
		
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
		
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}
	
	void createTake(FlexTable root,String TaskHader,String Taskcontent) {
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		Label lblLeave = new Label(TaskHader);
		verticalPanel_1.add(lblLeave);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		
		Label lblNameDate = new Label(Taskcontent);
		horizontalPanel.add(lblNameDate);
		
		Button ApproveButton = new Button("Approve");
		horizontalPanel.add(ApproveButton);
		
		Button EjectButton = new Button("Eject");
		horizontalPanel.add(EjectButton);
		
		ApproveButton.addClickHandler(new NewViewLeadApprove());
		EjectButton.addClickHandler(new NewViewLeadEject());
		
		root.setWidget(root.getRowCount(), 0, verticalPanel_1);
	}
}
