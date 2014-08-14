package com.kic.hrm.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Grid;

import com.google.gwt.user.client.ui.TextBox;


public class HumanResourcesManagementView extends Composite implements HumanResourcesManagementPresenter.Display {
	private final Button applyLeavingButton;
	private final ToggleButton m_tglbtnOauth;
	private final Button addProfileButton;
	private final Button editProfileButton;
	private final Button refreshButton;
	private final Button deleteButton;
	private final ListBox userList;
	
	private final TextBox idFile;
	private final Button getFileButton;
	
	public HumanResourcesManagementView() {
		// TODO Auto-generated constructor stub
		TabPanel tabPanel = new TabPanel();
		tabPanel.setAnimationEnabled(true);
		initWidget(tabPanel);
		tabPanel.setSize("100%", "100%");
		
		VerticalPanel verticalPanel_3 = new VerticalPanel();
		tabPanel.add(verticalPanel_3, "Main Page", false);
		verticalPanel_3.setSize("auto", "auto");
		
		ToggleButton tglbtnOauth = new ToggleButton("Oauth 2.0");
		verticalPanel_3.add(tglbtnOauth);
		tglbtnOauth.setWidth("");
		m_tglbtnOauth = tglbtnOauth;
		
		VerticalPanel verticalPanelLeavingForm = new VerticalPanel();
		verticalPanelLeavingForm.setVisible(false);
		tabPanel.add(verticalPanelLeavingForm, "Leaving Form", false);
		verticalPanelLeavingForm.setSize("100%", "100%");
		
		Button Testbuttom = new Button("New button");
		verticalPanelLeavingForm.add(Testbuttom);
		Testbuttom.setWidth("100%");

		VerticalPanel verticalPanel_4 = new VerticalPanel();
		tabPanel.add(verticalPanel_4, "Leaving Report", false);
		verticalPanel_4.setSize("780px", "550px");

		FlexTable flexTable_1 = new FlexTable();
		verticalPanel_4.add(flexTable_1);

		Label lblNewLabel_1 = new Label("New label");
		flexTable_1.setWidget(0, 0, lblNewLabel_1);

		ListBox comboBox_2 = new ListBox();
		flexTable_1.setWidget(1, 0, comboBox_2);
		comboBox_2
				.addItem("\u0E25\u0E32\u0E1B\u0E23\u0E30\u0E08\u0E33\u0E40\u0E14\u0E37\u0E2D\u0E19");
		comboBox_2
				.addItem("\u0E25\u0E32\u0E1B\u0E23\u0E30\u0E08\u0E33\u0E1B\u0E35");
		comboBox_2.setWidth("161px");

		CellTable<Object> cellTable_1 = new CellTable<Object>();
		flexTable_1.setWidget(2, 0, cellTable_1);
		cellTable_1.setSize("692px", "364px");

		FlowPanel flowPanel_3 = new FlowPanel();
		tabPanel.add(flowPanel_3, "Leaving Calendar", true);
		flowPanel_3.setSize("780px", "580px");

		DatePicker datePicker = new DatePicker();
		flowPanel_3.add(datePicker);
		datePicker.setSize("625px", "325px");
		
		tabPanel.selectTab(0);
		applyLeavingButton = Testbuttom;
		
		
		Grid grid = new Grid(5, 5);
		tabPanel.add(grid, "Admin Control", false);
		grid.setSize("auto", "auto");
		
		Label lblAdminControl = new Label("Admin Control");
		grid.setWidget(0, 0, lblAdminControl);
		
		Label lblEmailReport = new Label("E-mail Report");
		grid.setWidget(1, 0, lblEmailReport);
		
		TextBox textBox = new TextBox();
		grid.setWidget(1, 1, textBox);
		textBox.setWidth("102px");
		
		Label lblAddEmployee = new Label("Add Employee");
		grid.setWidget(2, 0, lblAddEmployee);
		
		Button btnAdd = new Button("add");
		grid.setWidget(2, 2, btnAdd);
		
		addProfileButton = btnAdd;
		
		Label lblEditProfileEmployee = new Label("Edit Profile Employee");
		grid.setWidget(3, 0, lblEditProfileEmployee);
		
		ListBox comboBox = new ListBox();
		grid.setWidget(3, 1, comboBox);
		comboBox.setWidth("107px");
		
		Button btnReface = new Button("Refresh");
		grid.setWidget(3, 2, btnReface);
		
		Button btnEdit = new Button("Edit");
		grid.setWidget(3, 3, btnEdit);
		refreshButton = btnReface;
		editProfileButton = btnEdit;
		userList = comboBox;
		
		Button btnDelete = new Button("Delete");
		grid.setWidget(3, 4, btnDelete);
		deleteButton = btnDelete;
		
		Label lblGetFileForm = new Label("FolderID ");
		grid.setWidget(4, 0, lblGetFileForm);
		
		TextBox txtbxbxczuyjkhhuenoalliwhdqc = new TextBox();
		txtbxbxczuyjkhhuenoalliwhdqc.setText("0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
		grid.setWidget(4, 1, txtbxbxczuyjkhhuenoalliwhdqc);
		
		Button btnGetfile = new Button("Save CSV to Drive");
		grid.setWidget(4, 2, btnGetfile);
		
		idFile = txtbxbxczuyjkhhuenoalliwhdqc;
		getFileButton = btnGetfile;
		
		Button btnNewButton = new Button("New button");
		grid.setWidget(4, 3, btnNewButton);
		
		System.out.println("Create View on : " + HumanResourcesManagementView.class.getSimpleName());
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}
	
	@Override
	public HasClickHandlers getApplyLeavingButton() {
		// TODO Auto-generated method stub
		return applyLeavingButton;
	}

	@Override
	public HasClickHandlers getToggleOauth() {
		// TODO Auto-generated method stub
		return m_tglbtnOauth;
	}

	public HasClickHandlers getAddProfileButton() {
		return addProfileButton;
	}

	public HasClickHandlers getEditProfileButton() {
		return editProfileButton;
	}

	public HasClickHandlers getRefreshButton() {
		return refreshButton;
	}

	@Override
	public ListBox getUsersListBox() {
		// TODO Auto-generated method stub
		return userList;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		// TODO Auto-generated method stub
		return deleteButton;
	}

	@Override
	public HasClickHandlers getFileButton() {
		// TODO Auto-generated method stub
		return getFileButton;
	}

	@Override
	public HasValue<String> getIDFile() {
		// TODO Auto-generated method stub
		return idFile;
	}
	
}
