package com.kic.hrm.client.view;

import java.util.Date;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.kic.hrm.client.presenter.LeavePresenter;
import com.google.gwt.user.client.ui.TextBox;

public class LeaveView extends Composite implements LeavePresenter.Display{
	
	private final Button leaveButton;
	private final ListBox typeleaveListBox;
	private final Label   quotaleaveLabel;
	private final DateBox startDateBox;
	private final DateBox endDateBox;
	private final Button fullformleaveButton;
	private final Button backButton;
	
	public LeaveView() {
		// TODO Auto-generated constructor stub
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		Label lblLeave = new Label("Leave");
		verticalPanel.add(lblLeave);
		
		Button btnBack = new Button("Back");
		verticalPanel.add(btnBack);
		btnBack.setWidth("100%");
		
		//startDateBox.a
		//quotaleaveLabel.
		backButton = btnBack;
		
		Grid grid = new Grid(2, 1);
		grid.setBorderWidth(2);
		grid.setCellPadding(1);
		verticalPanel.add(grid);
		grid.setWidth("100%");
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		grid.setWidget(0, 0, verticalPanel_1);
		verticalPanel_1.setWidth("100%");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel_1);
		horizontalPanel_1.setWidth("100%");
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("Leave");
		comboBox.addItem("Holiday");
		horizontalPanel_1.add(comboBox);
		comboBox.setWidth("95%");
		
		Label lblQuta = new Label("Quota :");
		horizontalPanel_1.add(lblQuta);
		lblQuta.setWidth("50");
		
		Label lblXxx = new Label("x");
		horizontalPanel_1.add(lblXxx);
		
		Label lblDay = new Label("Day");
		horizontalPanel_1.add(lblDay);
		lblDay.setWidth("50");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Label lblStart = new Label("Start :");
		horizontalPanel.add(lblStart);
		lblStart.setWidth("45");
		
		DateBox dateBox = new DateBox();
		horizontalPanel.add(dateBox);
		horizontalPanel.setCellHorizontalAlignment(dateBox, HasHorizontalAlignment.ALIGN_CENTER);
		dateBox.setWidth("95%");
		
		Label lblTo = new Label("To");
		horizontalPanel.add(lblTo);
		
		DateBox dateBox_1 = new DateBox();
		horizontalPanel.add(dateBox_1);
		dateBox_1.setWidth("95%");
		
		Label lblNewLabel = new Label("Discription");
		verticalPanel_1.add(lblNewLabel);
		
		TextBox textBox = new TextBox();
		verticalPanel_1.add(textBox);
		textBox.setWidth("98%");
		
		
	    //  DateBox dateBox = new DateBox();
	     // dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));

		
		Button btnNewButton = new Button("Leave");
		verticalPanel_1.add(btnNewButton);
		verticalPanel_1.setCellHorizontalAlignment(btnNewButton, HasHorizontalAlignment.ALIGN_CENTER);
		btnNewButton.setWidth("100%");
		
		Button btnFullLeaveForm = new Button("Full Leave Form");
		btnFullLeaveForm.setEnabled(false);
		grid.setWidget(1, 0, btnFullLeaveForm);
		btnFullLeaveForm.setWidth("100%");
		grid.getCellFormatter().setWidth(1, 0, "");
		grid.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		grid.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");

		dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateBox_1.setFormat(new DateBox.DefaultFormat(dateFormat));
		//dateBox_1.setFormat(dateBox);
		
		typeleaveListBox = comboBox;
		quotaleaveLabel = lblXxx;
		leaveButton = btnNewButton;
		startDateBox = dateBox;
		endDateBox = dateBox_1;
		//startDateBox.getDatePicker().getLastDate()
		fullformleaveButton = btnFullLeaveForm;
		
		//dateBox_1.set
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}

	@Override
	public HasClickHandlers getLeaveButton() {
		// TODO Auto-generated method stub
		return leaveButton;
	}

	@Override
	public HasClickHandlers getFullFormLeaveButton() {
		// TODO Auto-generated method stub
		return fullformleaveButton;
	}

	@Override
	public HasClickHandlers getBackButton() {
		// TODO Auto-generated method stub
		return backButton;
	}

	@Override
	public Label getQuotaLabel() {
		// TODO Auto-generated method stub
		return quotaleaveLabel;
	}

	@Override
	public HasValue<Date> getStartDateLeave() {
		// TODO Auto-generated method stub
		return startDateBox;
	}
	
	@Override
	public HasValue<Date> getEndDateLeave() {
		// TODO Auto-generated method stub
		return endDateBox;
	}

	@Override
	public ListBox getTypeLeaveListBox() {
		// TODO Auto-generated method stub
		return typeleaveListBox;
	}

	@Override
	public Button getbtnLeaveButton() {
		// TODO Auto-generated method stub
		return leaveButton;
	}


	
	
}
