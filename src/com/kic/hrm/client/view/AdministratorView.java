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
		
		Grid grid_2 = new Grid(1, 4);
		decoratedStackPanel.add(grid_2, "Start Time", false);
		grid_2.setSize("100%", "100%");
		
		Label label = new Label("FolderID ");
		grid_2.setWidget(0, 0, label);
		
		TextBox textBox_1 = new TextBox();
		textBox_1.setText("0BxCzuY_jk0HhQlNNRXJEdVJmRVU");
		grid_2.setWidget(0, 1, textBox_1);
		
		Button button = new Button("Save CSV to Drive");
		grid_2.setWidget(0, 2, button);
		
		Button btnFlashDatastore = new Button("Flash Datastore");
		grid_2.setWidget(0, 3, btnFlashDatastore);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		decoratedStackPanel.add(verticalPanel_1, "Admin Config", false);
		verticalPanel_1.setSize("100%", "100%");
		
		Grid grid_3 = new Grid(9, 5);
		verticalPanel_1.add(grid_3);
		
		Label label_5 = new Label("Admin Config");
		grid_3.setWidget(0, 0, label_5);
		
		Label label_6 = new Label("Admin Type");
		grid_3.setWidget(1, 0, label_6);
		
		Label label_7 = new Label("On duty");
		grid_3.setWidget(2, 0, label_7);
		
		Label label_4 = new Label("Hours");
		grid_3.setWidget(2, 1, label_4);
		label_4.setSize("50", "18");
		
		ListBox listBox_1 = new ListBox();
		grid_3.setWidget(2, 2, listBox_1);
		
		Label label_12 = new Label("Minutes");
		grid_3.setWidget(2, 3, label_12);
		
		ListBox listBox_2 = new ListBox();
		grid_3.setWidget(2, 4, listBox_2);
		
		Label label_8 = new Label("Off duty");
		grid_3.setWidget(3, 0, label_8);
		
		Label label_10 = new Label("Hours");
		grid_3.setWidget(3, 1, label_10);
		label_10.setSize("50", "18");
		
		Label label_9 = new Label("Early");
		grid_3.setWidget(4, 0, label_9);
		
		Label label_11 = new Label("Hours");
		grid_3.setWidget(4, 1, label_11);
		label_11.setSize("50", "18");
		
		Label lblProjectType = new Label("Project Type");
		grid_3.setWidget(5, 0, lblProjectType);
		
		Label label_13 = new Label("On duty");
		grid_3.setWidget(6, 0, label_13);
		
		Label label_16 = new Label("Hours");
		grid_3.setWidget(6, 1, label_16);
		label_16.setSize("50", "18");
		
		ListBox listBox_3 = new ListBox();
		grid_3.setWidget(6, 2, listBox_3);
		
		Label label_19 = new Label("Minutes");
		grid_3.setWidget(6, 3, label_19);
		
		ListBox listBox_4 = new ListBox();
		grid_3.setWidget(6, 4, listBox_4);
		
		Label label_14 = new Label("Off duty");
		grid_3.setWidget(7, 0, label_14);
		
		Label label_17 = new Label("Hours");
		grid_3.setWidget(7, 1, label_17);
		label_17.setSize("50", "18");
		
		Label label_15 = new Label("Early");
		grid_3.setWidget(8, 0, label_15);
		
		Label label_18 = new Label("Hours");
		grid_3.setWidget(8, 1, label_18);
		label_18.setSize("50", "18");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		
		Label label_3 = new Label("E-mail");
		horizontalPanel.add(label_3);
		
		TextBox textBox_2 = new TextBox();
		horizontalPanel.add(textBox_2);
		
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
		
		
		folderIDTextBox = textBox_1;
		saveToDriveButton = button;
		flashDataStoreButton = btnFlashDatastore;
		
		emailReceiverTextBox = textBox_2;
		
		addProfileButton = button_5;
		listProfileListBox = listBox_5;
		refreshListProfileButton = button_6;
		editProfileButton = button_7;
		deleteProfileButton = button_8;
		
		backButton = btnNewButton_1;
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
