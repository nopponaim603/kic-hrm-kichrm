package com.kic.hrm.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.kic.hrm.client.presenter.HumanResourcesManagementPresenter;
import com.google.gwt.user.client.ui.ToggleButton;


public class HumanResourcesManagementView extends Composite implements HumanResourcesManagementPresenter.Display {
	private final Button applyLeavingButton;
	private final ToggleButton m_tglbtnOauth;
	public HumanResourcesManagementView() {
		// TODO Auto-generated constructor stub
		TabPanel tabPanel = new TabPanel();
		tabPanel.setAnimationEnabled(true);
		initWidget(tabPanel);
		tabPanel.setSize("780px", "580px");
		
		VerticalPanel verticalPanel_3 = new VerticalPanel();
		tabPanel.add(verticalPanel_3, "Main Page", false);
		verticalPanel_3.setSize("780px", "550px");
		
		ToggleButton tglbtnOauth = new ToggleButton("Oauth 2.0");
		verticalPanel_3.add(tglbtnOauth);
		m_tglbtnOauth = tglbtnOauth;
		
		VerticalPanel verticalPanelLeavingForm = new VerticalPanel();
		verticalPanelLeavingForm.setVisible(false);
		tabPanel.add(verticalPanelLeavingForm, "Leaving Form", false);
		verticalPanelLeavingForm.setSize("780px", "550px");	
		
		Button Testbuttom = new Button("New button");
		verticalPanelLeavingForm.add(Testbuttom);

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
		
		
		
		System.out.println("Create GWT");
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
	
}
