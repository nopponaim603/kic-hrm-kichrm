package com.kic.hrm.client.view;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.resources.css.ast.HasSelectors;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.presenter.ProfilePresenter;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.IntegerBox;

public class ProfileView  extends Composite implements ProfilePresenter.Display{
	
	private final Button SubmitButton;
	private final Button CancelButton;
	private final TextBox WorkIDTextBox;
	private final ListBox SexTextBox;
	private final TextBox NameTextBox;
	private final TextBox SurnameTextBox;
	private final TextBox NameTTextBox;
	private final TextBox SurnameTTextBox;
	private final TextBox ShortNameTextBox;
	private final ListBox RoleTextBox;
	private final ListBox SegmentTextBox;
	private final TextBox EmailTextBox;
	private final TextBox PhoneTextBox;
	
	public ProfileView() {
		// TODO Auto-generated constructor stub
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		Grid grid = new Grid(12, 2);
		verticalPanel.add(grid);
		
		Label lblWorkid = new Label("WorkID");
		grid.setWidget(0, 0, lblWorkid);
		
		TextBox textBox = new TextBox();
		grid.setWidget(0, 1, textBox);
		
		Label lblSec = new Label("sex");
		grid.setWidget(1, 0, lblSec);
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("Mr");
		comboBox.addItem("Mrs");
		comboBox.addItem("Ms");
		grid.setWidget(1, 1, comboBox);
		
		Label lblName = new Label("Name");
		grid.setWidget(2, 0, lblName);
		
		TextBox textBox_1 = new TextBox();
		grid.setWidget(2, 1, textBox_1);
		
		Label lblSurname = new Label("Surname");
		grid.setWidget(3, 0, lblSurname);
		
		TextBox textBox_2 = new TextBox();
		grid.setWidget(3, 1, textBox_2);
		
		Label lblNamet = new Label("Name Thai");
		grid.setWidget(4, 0, lblNamet);
		
		TextBox textBox_3 = new TextBox();
		grid.setWidget(4, 1, textBox_3);
		
		Label lblSurnameThai = new Label("Surname Thai");
		grid.setWidget(5, 0, lblSurnameThai);
		
		TextBox textBox_4 = new TextBox();
		grid.setWidget(5, 1, textBox_4);
		
		Label lblShortname = new Label("Shortname");
		grid.setWidget(6, 0, lblShortname);
		
		TextBox textBox_5 = new TextBox();
		grid.setWidget(6, 1, textBox_5);
		
		Label lblRole = new Label("Role");
		grid.setWidget(7, 0, lblRole);
		
		ListBox comboBox_1 = new ListBox();
		comboBox_1.addItem("Director");
		comboBox_1.addItem("ProjectManager");
		comboBox_1.addItem("Secretary");
		comboBox_1.addItem("Administration");
		comboBox_1.addItem("ChiefFinancialOfficer");
		comboBox_1.addItem("ProcurementOfficer");
		comboBox_1.addItem("ITOffice");
		comboBox_1.addItem("ProjectOfficer");
		comboBox_1.addItem("InformationOfficer");
		comboBox_1.addItem("Advisor");
		comboBox_1.addItem("Designer");
		comboBox_1.addItem("ProjectCoordinator");
		comboBox_1.addItem("ResearchAssistant");
		comboBox_1.addItem("Researchers");
		grid.setWidget(7, 1, comboBox_1);
		
		Label lblSegment = new Label("Segment");
		grid.setWidget(8, 0, lblSegment);
		
		ListBox comboBox_2 = new ListBox();
		comboBox_2.addItem("Director");
		comboBox_2.addItem("Project");
		comboBox_2.addItem("Office");
		comboBox_2.addItem("Researchers");
		comboBox_2.addItem("Advisor");
		grid.setWidget(8, 1, comboBox_2);
		
		Label lblEmail = new Label("E-mail");
		grid.setWidget(9, 0, lblEmail);
		
		TextBox textBox_7 = new TextBox();
		grid.setWidget(9, 1, textBox_7);
		
		Label lblPhone = new Label("Phone");
		grid.setWidget(10, 0, lblPhone);
		
		TextBox textBox_6 = new TextBox();
		grid.setWidget(10, 1, textBox_6);
		
		Button btnBack = new Button("Back");
		grid.setWidget(11, 0, btnBack);
		
		Button btnNewButton = new Button("Submit");
		grid.setWidget(11, 1, btnNewButton);
		//verticalPanelLeavingForm.add(verticalPanel);
		
		SubmitButton = btnNewButton;
		CancelButton = btnBack;
		WorkIDTextBox = textBox;
		SexTextBox = comboBox;
		NameTextBox = textBox_1;
		SurnameTextBox = textBox_2;
		NameTTextBox = textBox_3;
		SurnameTTextBox = textBox_4;
		ShortNameTextBox = textBox_5;
		
		RoleTextBox = comboBox_1;
		SegmentTextBox = comboBox_2;
		
		EmailTextBox = textBox_7;
		PhoneTextBox = textBox_6;
		
		//SegmentTextBox.getS
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}

	@Override
	public HasClickHandlers getSubmit() {
		// TODO Auto-generated method stub
		return SubmitButton;
	}

	@Override
	public HasClickHandlers getCancel() {
		// TODO Auto-generated method stub
		return CancelButton;
	}

	@Override
	public HasValue<String> getWorkID() {
		// TODO Auto-generated method stub
		return WorkIDTextBox;
	}

	@Override
	public HasValue<String> getName() {
		// TODO Auto-generated method stub
		return NameTextBox;
	}

	@Override
	public HasValue<String> getSurname() {
		// TODO Auto-generated method stub
		return SurnameTextBox;
	}

	@Override
	public HasValue<String> getNameT() {
		// TODO Auto-generated method stub
		return NameTTextBox;
	}

	@Override
	public HasValue<String> getSurnameT() {
		// TODO Auto-generated method stub
		return SurnameTTextBox;
	}

	@Override
	public HasValue<String> getShortname() {
		// TODO Auto-generated method stub
		return ShortNameTextBox;
	}

	@Override
	public HasValue<String> getEmail() {
		// TODO Auto-generated method stub
		return EmailTextBox;
	}

	@Override
	public HasValue<String> getPhone() {
		// TODO Auto-generated method stub
		return PhoneTextBox;
	}

	@Override
	public String getSex() {
		// TODO Auto-generated method stub
		return SexTextBox.getItemText(SexTextBox.getSelectedIndex());
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return RoleTextBox.getItemText(RoleTextBox.getSelectedIndex());
	}
	
	
	
	@Override
	public String getSegment() {
		// TODO Auto-generated method stub
		return SegmentTextBox.getItemText(SegmentTextBox.getSelectedIndex());
	}

	@Override
	public ListBox setSex() {
		// TODO Auto-generated method stub
		return SexTextBox;
	}

	@Override
	public ListBox setRole() {
		// TODO Auto-generated method stub
		return RoleTextBox;
	}

	@Override
	public ListBox setSegment() {
		// TODO Auto-generated method stub
		return SegmentTextBox;
	}


}
