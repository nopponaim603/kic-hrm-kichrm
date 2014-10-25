package com.kic.hrm.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.presenter.DashBoardPresenter;

public class DashBoardView extends Composite implements DashBoardPresenter.Display{
	
	private final Button btnNaws;
	private final Button btnLeave;
	private final Button btnReport;
	private final Button btnAdmin;
	
	private final Button btnCheckIN;
	private final Button btnOnSite;
	
	public DashBoardView() {
		// TODO Auto-generated constructor stub
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("DashBoard");
		verticalPanel.add(lblNewLabel);
		
		Button btnNewButton_CheckIN = new Button("Check in");
		verticalPanel.add(btnNewButton_CheckIN);
		btnNewButton_CheckIN.setWidth("100%");
		
		Button btnNewButton_OnSite = new Button("On Site");
		verticalPanel.add(btnNewButton_OnSite);
		btnNewButton_OnSite.setWidth("100%");
		
		Button btnNewButton = new Button("News");
		verticalPanel.add(btnNewButton);
		btnNewButton.setWidth("100%");
		
		Button btnNewButton_1 = new Button("Leave");
		verticalPanel.add(btnNewButton_1);
		btnNewButton_1.setWidth("100%");
		
		Button btnNewButton_2 = new Button("Report");
		verticalPanel.add(btnNewButton_2);
		btnNewButton_2.setWidth("100%");
		
		Button btnNewButton_3 = new Button("Admin");
		verticalPanel.add(btnNewButton_3);
		btnNewButton_3.setWidth("100%");
		
		btnNaws = btnNewButton;
		btnLeave = btnNewButton_1;
		btnReport = btnNewButton_2;
		btnAdmin = btnNewButton_3;
		
		btnCheckIN = btnNewButton_CheckIN;
		btnOnSite = btnNewButton_OnSite;
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		
		//System.out.println("return Widget : " +this	);
		return this;
	}

	@Override
	public HasClickHandlers getNewsButton() {
		// TODO Auto-generated method stub
		return btnNaws;
	}

	@Override
	public HasClickHandlers getLeaveButton() {
		// TODO Auto-generated method stub
		return btnLeave;
	}

	@Override
	public HasClickHandlers getReportButton() {
		// TODO Auto-generated method stub
		return btnReport;
	}

	@Override
	public HasClickHandlers getAdminButton() {
		// TODO Auto-generated method stub
		return btnAdmin;
	}

	@Override
	public HasClickHandlers getCheckInButton() {
		// TODO Auto-generated method stub
		return btnCheckIN;
	}

	@Override
	public HasClickHandlers getOnSiteButton() {
		// TODO Auto-generated method stub
		return btnOnSite;
	}

	@Override
	public Button getButtonCheckIn() {
		// TODO Auto-generated method stub
		return btnCheckIN;
	}

	@Override
	public Button getButtonOnSite() {
		// TODO Auto-generated method stub
		return btnOnSite;
	}

	@Override
	public Button getButtonNew() {
		// TODO Auto-generated method stub
		return btnNaws;
	}

	@Override
	public Button getButtonLeave() {
		// TODO Auto-generated method stub
		return btnLeave;
	}

	@Override
	public Button getButtonReport() {
		// TODO Auto-generated method stub
		return btnReport;
	}

	@Override
	public Button getButtonAdmin() {
		// TODO Auto-generated method stub
		return btnAdmin;
	}
}
