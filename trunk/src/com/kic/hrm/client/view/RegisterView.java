package com.kic.hrm.client.view;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;

public class RegisterView  extends Composite {
	
	public RegisterView() {
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
		comboBox_2.addItem("Director,");
		comboBox_2.addItem("Project,");
		comboBox_2.addItem("Office,");
		comboBox_2.addItem("Researchers,");
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
		
	}
}
