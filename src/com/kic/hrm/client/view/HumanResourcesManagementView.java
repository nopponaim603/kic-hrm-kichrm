package com.kic.hrm.client.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter;

public class HumanResourcesManagementView extends Composite implements HumanResourcesManagementPresenter.Display {
	private final Button applyLeavingButton;
	
	public HumanResourcesManagementView() {
		// TODO Auto-generated constructor stub
		TabPanel tabPanel = new TabPanel();
		tabPanel.setAnimationEnabled(true);
		initWidget(tabPanel);
		tabPanel.setSize("780px", "580px");
		
		VerticalPanel verticalPanel_3 = new VerticalPanel();
		tabPanel.add(verticalPanel_3, "Main Page", false);
		verticalPanel_3.setSize("780px", "550px");

		CellTable<Object> cellTable = new CellTable<Object>();
		verticalPanel_3.add(cellTable);
		cellTable.setWidth("521px");

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVisible(false);
		tabPanel.add(verticalPanel, "Leaving Form", false);
		verticalPanel.setSize("780px", "550px");

		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		flexTable.setSize("760px", "57px");

		Label label = new Label(
				"\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48 17 \u0E2A\u0E34\u0E07\u0E2B\u0E32\u0E04\u0E21 2556 ");
		flexTable.setWidget(0, 0, label);
		label.setStyleName("gwt-panal");
		label.setDirectionEstimator(true);
		label.setSize("151px", "24px");

		Label lblNewLabel = new Label(
				"\u0E08\u0E33\u0E19\u0E27\u0E19\u0E27\u0E31\u0E19\u0E25\u0E32\u0E2A\u0E30\u0E2A\u0E21");
		flexTable.setWidget(0, 2, lblNewLabel);
		lblNewLabel.setStyleName("gwt-panal");
		lblNewLabel.setDirectionEstimator(true);
		lblNewLabel.setSize("106px", "24px");

		Label label_1 = new Label(
				"\u0E0A\u0E37\u0E48\u0E2D-\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		flexTable.setWidget(1, 0, label_1);
		label_1.setStyleName("gwt-panal");
		label_1.setWidth("128px");

		Label label_2 = new Label(
				"\u0E19\u0E32\u0E22 \u0E23\u0E27\u0E22\u0E17\u0E23\u0E31\u0E1E\u0E22\u0E4C \u0E2A\u0E27\u0E07\u0E2A\u0E27\u0E23\u0E23\u0E04\u0E4C");
		flexTable.setWidget(1, 1, label_2);
		label_2.setStyleName("gwt-panal");

		Label label_9 = new Label("\u0E25\u0E32\u0E01\u0E34\u0E08");
		flexTable.setWidget(1, 2, label_9);
		label_9.setStyleName("gwt-panal");
		label_9.setWidth("46px");

		Label label_10 = new Label("10");
		flexTable.setWidget(1, 3, label_10);
		label_10.setStyleName("gwt-panal");

		Label label_3 = new Label(
				"\u0E23\u0E2B\u0E31\u0E2A\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19");
		flexTable.setWidget(2, 0, label_3);
		label_3.setWidth("99px");
		label_3.setStyleName("gwt-panal");

		Label label_4 = new Label("5032165");
		flexTable.setWidget(2, 1, label_4);
		label_4.setStyleName("gwt-panal");

		Label label_11 = new Label("\u0E25\u0E32\u0E1B\u0E48\u0E27\u0E22");
		flexTable.setWidget(2, 2, label_11);
		label_11.setWidth("55px");
		label_11.setStyleName("gwt-panal");

		Label label_12 = new Label("10");
		flexTable.setWidget(2, 3, label_12);
		label_12.setStyleName("gwt-panal");

		Label label_5 = new Label("\u0E15\u0E33\u0E41\u0E2B\u0E19\u0E48\u0E07");
		flexTable.setWidget(3, 0, label_5);
		label_5.setWidth("115px");
		label_5.setStyleName("gwt-panal");

		Label label_6 = new Label(
				"\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19\u0E1B\u0E23\u0E30\u0E08\u0E33 (\u0E2A\u0E48\u0E27\u0E19\u0E07\u0E32\u0E19)");
		flexTable.setWidget(3, 1, label_6);
		label_6.setStyleName("gwt-panal");

		Label label_13 = new Label(
				"\u0E25\u0E32\u0E1E\u0E31\u0E01\u0E1C\u0E48\u0E2D\u0E19\u0E1B\u0E23\u0E30\u0E08\u0E33\u0E1B\u0E35");
		flexTable.setWidget(3, 2, label_13);
		label_13.setWidth("110px");
		label_13.setStyleName("gwt-panal");

		Label label_14 = new Label("10");
		flexTable.setWidget(3, 3, label_14);
		label_14.setStyleName("gwt-panal");

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel.add(verticalPanel_1);
		verticalPanel_1.setSize("504px", "62px");

		Label lblNewLabel_7 = new Label(
				"\u0E21\u0E35\u0E04\u0E27\u0E32\u0E21\u0E1B\u0E23\u0E30\u0E2A\u0E07\u0E04\u0E4C\u0E17\u0E35\u0E48\u0E08\u0E30\u0E25\u0E32");
		verticalPanel_1.add(lblNewLabel_7);
		lblNewLabel_7.setWidth("472px");

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel_1);

		RadioButton rdbtnNewRadioButton = new RadioButton("new name",
				"\u0E25\u0E32\u0E01\u0E34\u0E08");
		horizontalPanel_1.add(rdbtnNewRadioButton);

		RadioButton rdbtnNewRadioButton_1 = new RadioButton("new name",
				"\u0E25\u0E32\u0E1B\u0E48\u0E27\u0E22");
		horizontalPanel_1.add(rdbtnNewRadioButton_1);

		RadioButton rdbtnNewRadioButton_2 = new RadioButton("new name",
				"\u0E25\u0E32\u0E1E\u0E31\u0E01\u0E1C\u0E48\u0E2D\u0E19");
		horizontalPanel_1.add(rdbtnNewRadioButton_2);

		RadioButton radioButton = new RadioButton("new name",
				"\u0E2D\u0E37\u0E48\u0E19 \u0E46");
		horizontalPanel_1.add(radioButton);

		VerticalPanel verticalPanel_2 = new VerticalPanel();
		verticalPanel.add(verticalPanel_2);
		verticalPanel_2.setSize("504px", "62px");

		Label label_7 = new Label(
				"\u0E2A\u0E32\u0E40\u0E2B\u0E15\u0E38\u0E02\u0E2D\u0E07\u0E01\u0E32\u0E23\u0E25\u0E32");
		verticalPanel_2.add(label_7);
		label_7.setWidth("472px");

		TextBox textBox = new TextBox();
		textBox.setText("\u0E40\u0E19\u0E37\u0E48\u0E2D\u0E07\u0E21\u0E32\u0E08\u0E32\u0E01");
		verticalPanel_2.add(textBox);
		textBox.setSize("504px", "55px");

		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_3);
		horizontalPanel_3.setSize("212px", "33px");

		Label label_8 = new Label(
				"\u0E25\u0E32\u0E15\u0E31\u0E49\u0E07\u0E41\u0E15\u0E48\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48");
		horizontalPanel_3.add(label_8);
		label_8.setSize("107px", "27px");

		DateBox dateBox = new DateBox();
		horizontalPanel_3.add(dateBox);
		dateBox.setWidth("189px");

		Label lblNewLabel_8 = new Label(
				"\u0E08\u0E33\u0E19\u0E27\u0E19\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48\u0E15\u0E49\u0E2D\u0E07\u0E01\u0E32\u0E23\u0E25\u0E32");
		horizontalPanel_3.add(lblNewLabel_8);
		lblNewLabel_8.setWidth("141px");

		ListBox comboBox = new ListBox();
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		comboBox.addItem("6");
		comboBox.addItem("7");
		comboBox.addItem("8");
		comboBox.addItem("9");
		comboBox.addItem("10");
		horizontalPanel_3.add(comboBox);

		Label lblNewLabel_9 = new Label(
				"\u0E08\u0E19\u0E16\u0E36\u0E07\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48 11  \u0E01.\u0E04.  2556");
		horizontalPanel_3.add(lblNewLabel_9);
		lblNewLabel_9.setWidth("169px");

		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_4);
		horizontalPanel_4.setSize("501px", "45px");

		Label lblNewLabel_10 = new Label(
				"\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E15\u0E34\u0E14\u0E15\u0E48\u0E2D\u0E40\u0E23\u0E48\u0E07\u0E14\u0E48\u0E27\u0E19");
		horizontalPanel_4.add(lblNewLabel_10);

		ListBox listBox = new ListBox();
		listBox.addItem("084-0417543");
		listBox.addItem("086-6700339");
		horizontalPanel_4.add(listBox);
		listBox.setSize("212px", "42px");
		listBox.setVisibleItemCount(5);

		Button btnNewButton_4 = new Button("New button");
		btnNewButton_4
				.setText("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E17\u0E31\u0E1E\u0E17\u0E4C\u0E15\u0E34\u0E14\u0E15\u0E48\u0E2D");
		horizontalPanel_4.add(btnNewButton_4);

		HorizontalPanel horizontalPanel_5 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_5);
		horizontalPanel_5.setSize("581px", "35px");

		Label lblNewLabel_11 = new Label("New label");
		horizontalPanel_5.add(lblNewLabel_11);
		lblNewLabel_11.setWidth("165px");

		ListBox comboBox_1 = new ListBox();
		comboBox_1
				.addItem("\u0E19\u0E32\u0E22 \u0E2A\u0E21\u0E08\u0E34\u0E15\u0E23 \u0E21\u0E32\u0E01\u0E31\u0E19\u0E41\u0E25\u0E49\u0E27");
		horizontalPanel_5.add(comboBox_1);
		comboBox_1.setWidth("186px");

		HorizontalPanel horizontalPanel_6 = new HorizontalPanel();
		horizontalPanel_6
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanel_6);
		horizontalPanel_6.setWidth("780px");

		Button btnNewButton_5 = new Button("\u0E15\u0E01\u0E25\u0E07");
		horizontalPanel_6.add(btnNewButton_5);
		btnNewButton_5.setWidth("70px");

		Button btnNewButton_6 = new Button("New button");
		btnNewButton_6.setText("\u0E22\u0E01\u0E40\u0E25\u0E34\u0E01");
		horizontalPanel_6.add(btnNewButton_6);
		btnNewButton_6.setWidth("70px");

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
		applyLeavingButton = btnNewButton_5;
		
		System.out.println("Create GWT");
	}
	
	@Override
	public HasClickHandlers getApplyLeavingButton() {
		// TODO Auto-generated method stub
		return applyLeavingButton;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		System.out.println("return Widget : " +this	);
		return this;
	}
	
}
