package com.kic.hrm.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Grid;
import com.kic.hrm.client.presenter.DailyReportPresenter;
import com.kic.hrm.data.model.StartTimeLog;

public class DailyReportView extends Composite implements HasText, DailyReportPresenter.Display {

	private static DailyReportViewUiBinder uiBinder = GWT
			.create(DailyReportViewUiBinder.class);
	@UiField Grid gGrid;
	@UiField Button gBack;

	interface DailyReportViewUiBinder extends UiBinder<Widget, DailyReportView> {
		
	}

	public DailyReportView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void LoadState(StartTimeLog m_log) {
		
		Label lbl_employeeName = new Label(m_log.getM_name());
		Label lbl_employeeState = new Label(m_log.getM_type().toString());
		

		
		
		Label lbl_employeeTime = new Label(
				"Hour " + m_log.getM_clockIn().getHours() 
				+ " : " + m_log.getM_clockIn().getMinutes() 
				+ " : " + m_log.getM_clockIn().getSeconds());
		
		Label lbl_employeeAddress = new Label(m_log.getM_Note());
		
		//gGrid.add(lbl_employeeState);
		gGrid.insertRow(gGrid.getRowCount());
		
		gGrid.setWidget(gGrid.getRowCount() -1 , 0, lbl_employeeName);
		gGrid.setWidget(gGrid.getRowCount() -1 , 1, lbl_employeeState);
		gGrid.setWidget(gGrid.getRowCount() -1 , 2, lbl_employeeTime);
		gGrid.setWidget(gGrid.getRowCount() -1 , 3, lbl_employeeAddress);
		//gGrid.add
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HasClickHandlers getBack() {
		// TODO Auto-generated method stub
		return gBack;
	}

}
