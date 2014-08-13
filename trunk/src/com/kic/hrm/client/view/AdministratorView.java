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
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;

public class AdministratorView extends Composite implements AdministratorPresenter.Display{

	private final TextBox folderIDTextBox;
	private final Button saveToDriveButton;
	private final Button flashDataStoreButton;
	private final TextBox emailReceiverTextBox;
	
	
	
	private final ListBox listProfileListBox;
	private final Button refreshListProfileButton;
	private final Button addProfileButton;
	private final Button editProfileButton;
	private final Button deleteProfileButton;
	
	private final Button backButton;
	
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
		
		Grid grid_5 = new Grid(1, 4);
		verticalPanel_3.add(grid_5);
		grid_5.setSize("100%", "100%");
		
		Label label_23 = new Label("FolderID ");
		grid_5.setWidget(0, 0, label_23);
		
		TextBox textBox_2 = new TextBox();
		textBox_2.setText("0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
		grid_5.setWidget(0, 1, textBox_2);
		
		Button button_1 = new Button("Save CSV to Drive");
		grid_5.setWidget(0, 2, button_1);
		
		Button button_2 = new Button("Flash Datastore");
		grid_5.setWidget(0, 3, button_2);
		
		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		verticalPanel_3.add(horizontalPanel_3);
		
		Label lblReportEmail = new Label("Report E-mail");
		horizontalPanel_3.add(lblReportEmail);
		
		TextBox textBox_3 = new TextBox();
		horizontalPanel_3.add(textBox_3);
		
		Button btnNewButton = new Button("Send Report");
		horizontalPanel_3.add(btnNewButton);
		
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
		
		ListBox listBox = new ListBox();
		listBox.addItem("0");
		listBox.addItem("1");
		listBox.addItem("2");
		listBox.addItem("3");
		listBox.addItem("4");
		listBox.addItem("5");
		listBox.addItem("6");
		listBox.addItem("7");
		listBox.addItem("8");
		listBox.addItem("9");
		listBox.addItem("10");
		listBox.addItem("11");
		listBox.addItem("12");
		listBox.addItem("13");
		listBox.addItem("14");
		listBox.addItem("15");
		listBox.addItem("16");
		listBox.addItem("17");
		listBox.addItem("18");
		listBox.addItem("19");
		listBox.addItem("20");
		listBox.addItem("21");
		listBox.addItem("22");
		listBox.addItem("23");
		grid_1.setWidget(2, 2, listBox);
		
		Label label_2 = new Label("Minutes");
		grid_1.setWidget(2, 3, label_2);
		
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
		listBox_6.addItem("24");
		listBox_6.addItem("25");
		listBox_6.addItem("26");
		listBox_6.addItem("27");
		listBox_6.addItem("28");
		listBox_6.addItem("29");
		listBox_6.addItem("30");
		listBox_6.addItem("31");
		listBox_6.addItem("32");
		listBox_6.addItem("33");
		listBox_6.addItem("34");
		listBox_6.addItem("35");
		listBox_6.addItem("36");
		listBox_6.addItem("37");
		listBox_6.addItem("38");
		listBox_6.addItem("39");
		listBox_6.addItem("40");
		listBox_6.addItem("41");
		listBox_6.addItem("42");
		listBox_6.addItem("43");
		listBox_6.addItem("44");
		listBox_6.addItem("45");
		listBox_6.addItem("46");
		listBox_6.addItem("47");
		listBox_6.addItem("48");
		listBox_6.addItem("49");
		listBox_6.addItem("50");
		listBox_6.addItem("51");
		listBox_6.addItem("52");
		listBox_6.addItem("53");
		listBox_6.addItem("54");
		listBox_6.addItem("55");
		listBox_6.addItem("56");
		listBox_6.addItem("57");
		listBox_6.addItem("58");
		listBox_6.addItem("59");
		grid_1.setWidget(2, 4, listBox_6);
		
		Label label_5 = new Label("Early");
		grid_1.setWidget(3, 0, label_5);
		
		Label label_10 = new Label("Hours");
		grid_1.setWidget(3, 1, label_10);
		label_10.setSize("50", "18");
		
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
		grid_1.setWidget(3, 2, listBox_7);
		
		Label label_22 = new Label("Minutes");
		grid_1.setWidget(3, 3, label_22);
		
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
		listBox_8.addItem("24");
		listBox_8.addItem("25");
		listBox_8.addItem("26");
		listBox_8.addItem("27");
		listBox_8.addItem("28");
		listBox_8.addItem("29");
		listBox_8.addItem("30");
		listBox_8.addItem("31");
		listBox_8.addItem("32");
		listBox_8.addItem("33");
		listBox_8.addItem("34");
		listBox_8.addItem("35");
		listBox_8.addItem("36");
		listBox_8.addItem("37");
		listBox_8.addItem("38");
		listBox_8.addItem("39");
		listBox_8.addItem("40");
		listBox_8.addItem("41");
		listBox_8.addItem("42");
		listBox_8.addItem("43");
		listBox_8.addItem("44");
		listBox_8.addItem("45");
		listBox_8.addItem("46");
		listBox_8.addItem("47");
		listBox_8.addItem("48");
		listBox_8.addItem("49");
		listBox_8.addItem("50");
		listBox_8.addItem("51");
		listBox_8.addItem("52");
		listBox_8.addItem("53");
		listBox_8.addItem("54");
		listBox_8.addItem("55");
		listBox_8.addItem("56");
		listBox_8.addItem("57");
		listBox_8.addItem("58");
		listBox_8.addItem("59");
		grid_1.setWidget(3, 4, listBox_8);
		
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
		
		ListBox listBox_3 = new ListBox();
		listBox_3 = addHours(listBox_3);
		grid_3.setWidget(1, 2, listBox_3);
		
		Label label_19 = new Label("Minutes");
		grid_3.setWidget(1, 3, label_19);
		
		ListBox listBox_4 = new ListBox();
		addMinutes(listBox_4);
		grid_3.setWidget(1, 4, listBox_4);
		
		Label label_14 = new Label("Off duty");
		grid_3.setWidget(2, 0, label_14);
		
		Label label_17 = new Label("Hours");
		grid_3.setWidget(2, 1, label_17);
		label_17.setSize("50", "18");
		
		ListBox listBox_9 = new ListBox();
		addHours(listBox_9);
		grid_3.setWidget(2, 2, listBox_9);
		
		Label label_9 = new Label("Minutes");
		grid_3.setWidget(2, 3, label_9);
		
		ListBox listBox_11 = new ListBox();
		addMinutes(listBox_11);
		grid_3.setWidget(2, 4, listBox_11);
		
		Label label_15 = new Label("Early");
		grid_3.setWidget(3, 0, label_15);
		
		Label label_18 = new Label("Hours");
		grid_3.setWidget(3, 1, label_18);
		label_18.setSize("50", "18");
		
		ListBox listBox_10 = new ListBox();
		addHours(listBox_10);
		grid_3.setWidget(3, 2, listBox_10);
		
		Label label_11 = new Label("Minutes");
		grid_3.setWidget(3, 3, label_11);
		
		ListBox listBox_12 = new ListBox();
		addMinutes(listBox_12);
		grid_3.setWidget(3, 4, listBox_12);
		
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
		
		ListBox listBox_5 = new ListBox();
		horizontalPanel_2.add(listBox_5);
		listBox_5.setWidth("107px");
		
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
		flashDataStoreButton = button_2;
		
		emailReceiverTextBox = textBox_3;
		
		addProfileButton = button_5;
		listProfileListBox = listBox_5;
		refreshListProfileButton = button_6;
		editProfileButton = button_7;
		deleteProfileButton = button_8;
		
		backButton = btnNewButton_1;
	}
	
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
	public HasClickHandlers getFlashDataStoreButton() {
		// TODO Auto-generated method stub
		return flashDataStoreButton;
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
}
