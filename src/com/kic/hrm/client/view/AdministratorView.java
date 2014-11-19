package com.kic.hrm.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.presenter.AdministratorPresenter;


public class AdministratorView extends Composite implements AdministratorPresenter.Display{

	private final TextBox folderIDTextBox;
	private final Button saveToDriveButton;

	private final TextBox emailReceiverTextBox;
	private final Button sendEmailButton;

	private final ListBox listProfileListBox;
	private final Button refreshListProfileButton;
	private final Button addProfileButton;
	private final Button editProfileButton;
	private final Button deleteProfileButton;
	
	private final Button backButton;
	
	private final ListBox AdminOnduty_H;
	private final ListBox AdminOnduty_M;
	private final ListBox AdminOffduty_H;
	private final ListBox AdminOffduty_M;
	private final ListBox AdminEarlyduty_M;
	
	private final ListBox ProjectOnduty_H;
	private final ListBox ProjectOnduty_M;
	private final ListBox ProjectOffduty_H;
	private final ListBox ProjectOffduty_M;
	private final ListBox ProjectEarlyduty_M;
	
	public AdministratorView() {
		// TODO Auto-generated constructor stub
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		verticalPanel.add(decoratedStackPanel);
		decoratedStackPanel.setWidth("100%");
		
		VerticalPanel verticalPanel_3 = new VerticalPanel();
		decoratedStackPanel.add(verticalPanel_3, "Start Time", false);
		verticalPanel_3.setSize("100%", "100%");
		
		Grid grid_5 = new Grid(2, 3);
		verticalPanel_3.add(grid_5);
		grid_5.setSize("100%", "100%");
		
		Label label_23 = new Label("FolderID ");
		grid_5.setWidget(0, 0, label_23);
		
		TextBox textBox_2 = new TextBox();
		textBox_2.setText("0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
		grid_5.setWidget(0, 1, textBox_2);
		
		Button button_1 = new Button("Save CSV to Drive");
		grid_5.setWidget(0, 2, button_1);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		decoratedStackPanel.add(verticalPanel_1, "Time Config", false);
		verticalPanel_1.setSize("100%", "100%");
		
		Grid grid = new Grid(1, 1);
		grid.setBorderWidth(1);
		verticalPanel_1.add(grid);
		grid.setWidth("100%");
		
		Grid grid_1 = new Grid(4, 5);
		grid.setWidget(0, 0, grid_1);
		grid_1.setWidth("100%");
		
		Label label_6 = new Label("Admin Type");
		grid_1.setWidget(0, 0, label_6);
		
		Label label_7 = new Label("On duty");
		grid_1.setWidget(1, 0, label_7);
		
		Label label_4 = new Label("Hours");
		grid_1.setWidget(1, 1, label_4);
		label_4.setSize("50", "18");
		
		ListBox listBox_1 = new ListBox();
		listBox_1.addItem("0");
		listBox_1.addItem("1");
		listBox_1.addItem("2");
		listBox_1.addItem("3");
		listBox_1.addItem("4");
		listBox_1.addItem("5");
		listBox_1.addItem("6");
		listBox_1.addItem("7");
		listBox_1.addItem("8");
		listBox_1.addItem("9");
		listBox_1.addItem("10");
		listBox_1.addItem("11");
		listBox_1.addItem("12");
		listBox_1.addItem("13");
		listBox_1.addItem("14");
		listBox_1.addItem("15");
		listBox_1.addItem("16");
		listBox_1.addItem("17");
		listBox_1.addItem("18");
		listBox_1.addItem("19");
		listBox_1.addItem("20");
		listBox_1.addItem("21");
		listBox_1.addItem("22");
		listBox_1.addItem("23");
		grid_1.setWidget(1, 2, listBox_1);
		
		Label label_12 = new Label("Minutes");
		grid_1.setWidget(1, 3, label_12);
		
		ListBox listBox_2 = new ListBox();
		listBox_2.addItem("0");
		listBox_2.addItem("1");
		listBox_2.addItem("2");
		listBox_2.addItem("3");
		listBox_2.addItem("4");
		listBox_2.addItem("5");
		listBox_2.addItem("6");
		listBox_2.addItem("7");
		listBox_2.addItem("8");
		listBox_2.addItem("9");
		listBox_2.addItem("10");
		listBox_2.addItem("11");
		listBox_2.addItem("12");
		listBox_2.addItem("13");
		listBox_2.addItem("14");
		listBox_2.addItem("15");
		listBox_2.addItem("16");
		listBox_2.addItem("17");
		listBox_2.addItem("18");
		listBox_2.addItem("19");
		listBox_2.addItem("20");
		listBox_2.addItem("21");
		listBox_2.addItem("22");
		listBox_2.addItem("23");
		listBox_2.addItem("24");
		listBox_2.addItem("25");
		listBox_2.addItem("26");
		listBox_2.addItem("27");
		listBox_2.addItem("28");
		listBox_2.addItem("29");
		listBox_2.addItem("30");
		listBox_2.addItem("31");
		listBox_2.addItem("32");
		listBox_2.addItem("33");
		listBox_2.addItem("34");
		listBox_2.addItem("35");
		listBox_2.addItem("36");
		listBox_2.addItem("37");
		listBox_2.addItem("38");
		listBox_2.addItem("39");
		listBox_2.addItem("40");
		listBox_2.addItem("41");
		listBox_2.addItem("42");
		listBox_2.addItem("43");
		listBox_2.addItem("44");
		listBox_2.addItem("45");
		listBox_2.addItem("46");
		listBox_2.addItem("47");
		listBox_2.addItem("48");
		listBox_2.addItem("49");
		listBox_2.addItem("50");
		listBox_2.addItem("51");
		listBox_2.addItem("52");
		listBox_2.addItem("53");
		listBox_2.addItem("54");
		listBox_2.addItem("55");
		listBox_2.addItem("56");
		listBox_2.addItem("57");
		listBox_2.addItem("58");
		listBox_2.addItem("59");
		grid_1.setWidget(1, 4, listBox_2);
		
		Label label_8 = new Label("Off duty");
		grid_1.setWidget(2, 0, label_8);
		
		Label label_1 = new Label("Hours");
		grid_1.setWidget(2, 1, label_1);
		label_1.setSize("50", "18");
		
		ListBox listBox_3 = new ListBox();
		listBox_3.addItem("0");
		listBox_3.addItem("1");
		listBox_3.addItem("2");
		listBox_3.addItem("3");
		listBox_3.addItem("4");
		listBox_3.addItem("5");
		listBox_3.addItem("6");
		listBox_3.addItem("7");
		listBox_3.addItem("8");
		listBox_3.addItem("9");
		listBox_3.addItem("10");
		listBox_3.addItem("11");
		listBox_3.addItem("12");
		listBox_3.addItem("13");
		listBox_3.addItem("14");
		listBox_3.addItem("15");
		listBox_3.addItem("16");
		listBox_3.addItem("17");
		listBox_3.addItem("18");
		listBox_3.addItem("19");
		listBox_3.addItem("20");
		listBox_3.addItem("21");
		listBox_3.addItem("22");
		listBox_3.addItem("23");
		grid_1.setWidget(2, 2, listBox_3);
		
		Label label_2 = new Label("Minutes");
		grid_1.setWidget(2, 3, label_2);
		
		ListBox listBox_4 = new ListBox();
		listBox_4.addItem("0");
		listBox_4.addItem("1");
		listBox_4.addItem("2");
		listBox_4.addItem("3");
		listBox_4.addItem("4");
		listBox_4.addItem("5");
		listBox_4.addItem("6");
		listBox_4.addItem("7");
		listBox_4.addItem("8");
		listBox_4.addItem("9");
		listBox_4.addItem("10");
		listBox_4.addItem("11");
		listBox_4.addItem("12");
		listBox_4.addItem("13");
		listBox_4.addItem("14");
		listBox_4.addItem("15");
		listBox_4.addItem("16");
		listBox_4.addItem("17");
		listBox_4.addItem("18");
		listBox_4.addItem("19");
		listBox_4.addItem("20");
		listBox_4.addItem("21");
		listBox_4.addItem("22");
		listBox_4.addItem("23");
		listBox_4.addItem("24");
		listBox_4.addItem("25");
		listBox_4.addItem("26");
		listBox_4.addItem("27");
		listBox_4.addItem("28");
		listBox_4.addItem("29");
		listBox_4.addItem("30");
		listBox_4.addItem("31");
		listBox_4.addItem("32");
		listBox_4.addItem("33");
		listBox_4.addItem("34");
		listBox_4.addItem("35");
		listBox_4.addItem("36");
		listBox_4.addItem("37");
		listBox_4.addItem("38");
		listBox_4.addItem("39");
		listBox_4.addItem("40");
		listBox_4.addItem("41");
		listBox_4.addItem("42");
		listBox_4.addItem("43");
		listBox_4.addItem("44");
		listBox_4.addItem("45");
		listBox_4.addItem("46");
		listBox_4.addItem("47");
		listBox_4.addItem("48");
		listBox_4.addItem("49");
		listBox_4.addItem("50");
		listBox_4.addItem("51");
		listBox_4.addItem("52");
		listBox_4.addItem("53");
		listBox_4.addItem("54");
		listBox_4.addItem("55");
		listBox_4.addItem("56");
		listBox_4.addItem("57");
		listBox_4.addItem("58");
		listBox_4.addItem("59");
		grid_1.setWidget(2, 4, listBox_4);
		
		Label label_5 = new Label("Early");
		grid_1.setWidget(3, 0, label_5);
		
		Label label_3 = new Label("Minutes");
		grid_1.setWidget(3, 1, label_3);
		
		ListBox listBox_5 = new ListBox();
		listBox_5.addItem("5");
		listBox_5.addItem("10");
		listBox_5.addItem("15");
		listBox_5.addItem("30");
		listBox_5.addItem("45");
		listBox_5.addItem("60");
		grid_1.setWidget(3, 2, listBox_5);
		
		Grid grid_4 = new Grid(1, 1);
		grid_4.setBorderWidth(1);
		verticalPanel_1.add(grid_4);
		grid_4.setWidth("100%");
		
		Grid grid_3 = new Grid(4, 5);
		grid_4.setWidget(0, 0, grid_3);
		grid_3.setWidth("100%");
		
		Label lblProjectType = new Label("Project Type");
		grid_3.setWidget(0, 0, lblProjectType);
		
		Label label_13 = new Label("On duty");
		grid_3.setWidget(1, 0, label_13);
		
		Label label_16 = new Label("Hours");
		grid_3.setWidget(1, 1, label_16);
		label_16.setSize("50", "18");
		
		ListBox listBox_6 = new ListBox();
		listBox_6.addItem("0");
		listBox_6.addItem("1");
		listBox_6.addItem("2");
		listBox_6.addItem("3");
		listBox_6.addItem("4");
		listBox_6.addItem("5");
		listBox_6.addItem("6");
		listBox_6.addItem("7");
		listBox_6.addItem("8");
		listBox_6.addItem("9");
		listBox_6.addItem("10");
		listBox_6.addItem("11");
		listBox_6.addItem("12");
		listBox_6.addItem("13");
		listBox_6.addItem("14");
		listBox_6.addItem("15");
		listBox_6.addItem("16");
		listBox_6.addItem("17");
		listBox_6.addItem("18");
		listBox_6.addItem("19");
		listBox_6.addItem("20");
		listBox_6.addItem("21");
		listBox_6.addItem("22");
		listBox_6.addItem("23");

		grid_3.setWidget(1, 2, listBox_6);
		
		Label label_19 = new Label("Minutes");
		grid_3.setWidget(1, 3, label_19);
		
		ListBox listBox_7 = new ListBox();
		listBox_7.addItem("0");
		listBox_7.addItem("1");
		listBox_7.addItem("2");
		listBox_7.addItem("3");
		listBox_7.addItem("4");
		listBox_7.addItem("5");
		listBox_7.addItem("6");
		listBox_7.addItem("7");
		listBox_7.addItem("8");
		listBox_7.addItem("9");
		listBox_7.addItem("10");
		listBox_7.addItem("11");
		listBox_7.addItem("12");
		listBox_7.addItem("13");
		listBox_7.addItem("14");
		listBox_7.addItem("15");
		listBox_7.addItem("16");
		listBox_7.addItem("17");
		listBox_7.addItem("18");
		listBox_7.addItem("19");
		listBox_7.addItem("20");
		listBox_7.addItem("21");
		listBox_7.addItem("22");
		listBox_7.addItem("23");

		grid_3.setWidget(1, 4, listBox_7);
		
		Label label_14 = new Label("Off duty");
		grid_3.setWidget(2, 0, label_14);
		
		Label label_17 = new Label("Hours");
		grid_3.setWidget(2, 1, label_17);
		label_17.setSize("50", "18");
		
		ListBox listBox_8 = new ListBox();
		listBox_8.addItem("0");
		listBox_8.addItem("1");
		listBox_8.addItem("2");
		listBox_8.addItem("3");
		listBox_8.addItem("4");
		listBox_8.addItem("5");
		listBox_8.addItem("6");
		listBox_8.addItem("7");
		listBox_8.addItem("8");
		listBox_8.addItem("9");
		listBox_8.addItem("10");
		listBox_8.addItem("11");
		listBox_8.addItem("12");
		listBox_8.addItem("13");
		listBox_8.addItem("14");
		listBox_8.addItem("15");
		listBox_8.addItem("16");
		listBox_8.addItem("17");
		listBox_8.addItem("18");
		listBox_8.addItem("19");
		listBox_8.addItem("20");
		listBox_8.addItem("21");
		listBox_8.addItem("22");
		listBox_8.addItem("23");

		grid_3.setWidget(2, 2, listBox_8);
		
		Label label_9 = new Label("Minutes");
		grid_3.setWidget(2, 3, label_9);
		
		ListBox listBox_9 = new ListBox();
		listBox_9.addItem("0");
		listBox_9.addItem("1");
		listBox_9.addItem("2");
		listBox_9.addItem("3");
		listBox_9.addItem("4");
		listBox_9.addItem("5");
		listBox_9.addItem("6");
		listBox_9.addItem("7");
		listBox_9.addItem("8");
		listBox_9.addItem("9");
		listBox_9.addItem("10");
		listBox_9.addItem("11");
		listBox_9.addItem("12");
		listBox_9.addItem("13");
		listBox_9.addItem("14");
		listBox_9.addItem("15");
		listBox_9.addItem("16");
		listBox_9.addItem("17");
		listBox_9.addItem("18");
		listBox_9.addItem("19");
		listBox_9.addItem("20");
		listBox_9.addItem("21");
		listBox_9.addItem("22");
		listBox_9.addItem("23");

		grid_3.setWidget(2, 4, listBox_9);
		
		Label label_15 = new Label("Early");
		grid_3.setWidget(3, 0, label_15);
		
		Label label = new Label("Minutes");
		grid_3.setWidget(3, 1, label);
		
		ListBox listBox_10 = new ListBox();
		listBox_10.addItem("5");
		listBox_10.addItem("10");
		listBox_10.addItem("15");
		listBox_10.addItem("30");
		listBox_10.addItem("45");
		listBox_10.addItem("60");
		grid_3.setWidget(3, 2, listBox_10);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		horizontalPanel.setWidth("100%");
		
		Button btnApply = new Button("Apply");
		horizontalPanel.add(btnApply);
		btnApply.setWidth("100%");
		
		Button btnDefault = new Button("Default");
		horizontalPanel.add(btnDefault);
		btnDefault.setWidth("100%");
		
		VerticalPanel verticalPanel_2 = new VerticalPanel();
		decoratedStackPanel.add(verticalPanel_2, "Profile Management", false);
		verticalPanel_2.setSize("100%", "100%");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel_2.add(horizontalPanel_1);
		
		Label label_20 = new Label("Add Employee");
		horizontalPanel_1.add(label_20);
		
		Button button_5 = new Button("add");
		horizontalPanel_1.add(button_5);
		
		Label label_21 = new Label("Edit Profile Employee");
		verticalPanel_2.add(label_21);
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		verticalPanel_2.add(horizontalPanel_2);
		
		ListBox listBox_ProfileEmployee = new ListBox();
		horizontalPanel_2.add(listBox_ProfileEmployee);
		listBox_ProfileEmployee.setWidth("107px");
		
		Button button_6 = new Button("Refresh");
		horizontalPanel_2.add(button_6);
		
		Button button_7 = new Button("Edit");
		horizontalPanel_2.add(button_7);
		
		Button button_8 = new Button("Delete");
		horizontalPanel_2.add(button_8);
		
		Button btnNewButton_1 = new Button("Back");
		verticalPanel.add(btnNewButton_1);
		btnNewButton_1.setWidth("100%");
		
		folderIDTextBox = textBox_2;
		saveToDriveButton = button_1;
		
		Label lblReportEmail = new Label("Report E-mail");
		grid_5.setWidget(1, 0, lblReportEmail);
		lblReportEmail.setWidth("81px");
		
		TextBox textBox_3 = new TextBox();
		grid_5.setWidget(1, 1, textBox_3);
		
		emailReceiverTextBox = textBox_3;
		
		Button btnNewButton = new Button("Send Report");
		grid_5.setWidget(1, 2, btnNewButton);
		
		sendEmailButton = btnNewButton;
		addProfileButton = button_5;
		listProfileListBox = listBox_ProfileEmployee;
		refreshListProfileButton = button_6;
		editProfileButton = button_7;
		deleteProfileButton = button_8;
		
		backButton = btnNewButton_1;
		
		AdminOnduty_H = listBox_1;
		AdminOnduty_M = listBox_2;
		AdminOffduty_H = listBox_3;
		AdminOffduty_M = listBox_4;
		AdminEarlyduty_M = listBox_5;
		
		ProjectOnduty_H = listBox_6;
		ProjectOnduty_M = listBox_7;
		ProjectOffduty_H = listBox_8;
		ProjectOffduty_M = listBox_9;
		ProjectEarlyduty_M = listBox_10;
		
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}

	@Override
	public HasValue<String> getFolderID() {
		// TODO Auto-generated method stub
		return folderIDTextBox;
	}

	@Override
	public HasClickHandlers getSaveCSVtoDriveButton() {
		// TODO Auto-generated method stub
		return saveToDriveButton;
	}

	@Override
	public HasValue<String> getEmailReceiver() {
		// TODO Auto-generated method stub
		return emailReceiverTextBox;
	}

	@Override
	public HasClickHandlers getAddProfileButton() {
		// TODO Auto-generated method stub
		return addProfileButton;
	}

	@Override
	public HasClickHandlers getRefreshListsProfileButton() {
		// TODO Auto-generated method stub
		return refreshListProfileButton;
	}

	@Override
	public HasClickHandlers getDeleteProfileButton() {
		// TODO Auto-generated method stub
		return deleteProfileButton;
	}

	@Override
	public HasClickHandlers getEditProfileButton() {
		// TODO Auto-generated method stub
		return editProfileButton;
	}

	@Override
	public HasClickHandlers getBackButton() {
		// TODO Auto-generated method stub
		return backButton;
	}

	@Override
	public ListBox getUsersListBox() {
		// TODO Auto-generated method stub
		return listProfileListBox;
	}
	
	@Override
	public ListBox AdminOnDutyHoure() {
		return AdminOnduty_H;
	}

	@Override
	public ListBox AdminOnDutyMinutes() {
		// TODO Auto-generated method stub
		return AdminOnduty_M;
	}

	@Override
	public ListBox AdminOffDutyHoure() {
		// TODO Auto-generated method stub
		return AdminOffduty_H;
	}

	@Override
	public ListBox AdminOffDutyMinutes() {
		// TODO Auto-generated method stub
		return AdminOffduty_M;
	}

	@Override
	public ListBox AdminEarlyMinutes() {
		// TODO Auto-generated method stub
		return AdminEarlyduty_M;
	}

	@Override
	public ListBox ProjectOnDutyHoure() {
		// TODO Auto-generated method stub
		return ProjectOnduty_H;
	}

	@Override
	public ListBox ProjectOnDutyMinutes() {
		// TODO Auto-generated method stub
		return ProjectOnduty_M;
	}

	@Override
	public ListBox ProjectOffDutyHoure() {
		// TODO Auto-generated method stub
		return ProjectOffduty_H;
	}

	@Override
	public ListBox ProjectOffDutyMinutes() {
		// TODO Auto-generated method stub
		return ProjectOffduty_M;
	}

	@Override
	public ListBox ProjectEarlyMinutes() {
		// TODO Auto-generated method stub
		return ProjectEarlyduty_M;
	}

	@Override
	public HasClickHandlers getSendEmailButton() {
		// TODO Auto-generated method stub
		return sendEmailButton;
	}

	
}


/*
private ListBox addHours(ListBox inputlist) {
	inputlist.addItem("0");
	inputlist.addItem("1");
	inputlist.addItem("2");
	inputlist.addItem("3");
	inputlist.addItem("4");
	inputlist.addItem("5");
	inputlist.addItem("6");
	inputlist.addItem("7");
	inputlist.addItem("8");
	inputlist.addItem("9");
	inputlist.addItem("10");
	inputlist.addItem("11");
	inputlist.addItem("12");
	inputlist.addItem("13");
	inputlist.addItem("14");
	inputlist.addItem("15");
	inputlist.addItem("16");
	inputlist.addItem("17");
	inputlist.addItem("18");
	inputlist.addItem("19");
	inputlist.addItem("20");
	inputlist.addItem("21");
	inputlist.addItem("22");
	inputlist.addItem("23");
	
	return inputlist;
}
*/

/*
private void addMinutes(ListBox inputlist) {
	inputlist.addItem("0");
	inputlist.addItem("1");
	inputlist.addItem("2");
	inputlist.addItem("3");
	inputlist.addItem("4");
	inputlist.addItem("5");
	inputlist.addItem("6");
	inputlist.addItem("7");
	inputlist.addItem("8");
	inputlist.addItem("9");
	inputlist.addItem("10");
	inputlist.addItem("11");
	inputlist.addItem("12");
	inputlist.addItem("13");
	inputlist.addItem("14");
	inputlist.addItem("15");
	inputlist.addItem("16");
	inputlist.addItem("17");
	inputlist.addItem("18");
	inputlist.addItem("19");
	inputlist.addItem("20");
	inputlist.addItem("21");
	inputlist.addItem("22");
	inputlist.addItem("23");
	inputlist.addItem("24");
	inputlist.addItem("25");
	inputlist.addItem("26");
	inputlist.addItem("27");
	inputlist.addItem("28");
	inputlist.addItem("29");
	inputlist.addItem("30");
	inputlist.addItem("31");
	inputlist.addItem("32");
	inputlist.addItem("33");
	inputlist.addItem("34");
	inputlist.addItem("35");
	inputlist.addItem("36");
	inputlist.addItem("37");
	inputlist.addItem("38");
	inputlist.addItem("39");
	inputlist.addItem("40");
	inputlist.addItem("41");
	inputlist.addItem("42");
	inputlist.addItem("43");
	inputlist.addItem("44");
	inputlist.addItem("45");
	inputlist.addItem("46");
	inputlist.addItem("47");
	inputlist.addItem("48");
	inputlist.addItem("49");
	inputlist.addItem("50");
	inputlist.addItem("51");
	inputlist.addItem("52");
	inputlist.addItem("53");
	inputlist.addItem("54");
	inputlist.addItem("55");
	inputlist.addItem("56");
	inputlist.addItem("57");
	inputlist.addItem("58");
	inputlist.addItem("59");
}
*/
