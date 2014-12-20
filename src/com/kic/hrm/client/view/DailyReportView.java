package com.kic.hrm.client.view;

//import java.text.MessageFormat;
import sun.util.calendar.BaseCalendar.Date;
import sun.util.calendar.CalendarDate;

import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.kic.hrm.client.businesslogic.ConvertTimeZone;
import com.kic.hrm.client.presenter.DailyReportPresenter;
import com.kic.hrm.data.model.StartTimeLog;
import com.kic.hrm.data.model.StartTimeLogService;

public class DailyReportView extends Composite implements HasText, DailyReportPresenter.Display {

	private static DailyReportViewUiBinder uiBinder = GWT
			.create(DailyReportViewUiBinder.class);
	@UiField Grid gGrid;
	@UiField Button gBack;
	@UiField Button btnPrevious;
	@UiField Button btnToday;
	@UiField Button btnNext;


	interface DailyReportViewUiBinder extends UiBinder<Widget, DailyReportView> {
		
	}

	public DailyReportView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void LoadState(StartTimeLog m_log) {
		
		Label lbl_employeeName = new Label(m_log.getM_name());
		Label lbl_employeeState = new Label(m_log.getM_type().toString());
		
		//Convert Time
		Label lbl_employeeTime = new Label(DateTimeFormat.getShortTimeFormat().format(ConvertTimeZone.ClineTimeZoneToBangkok(m_log.getM_clockIn())));
		
		Label lbl_employeeAddress = new Label(m_log.getM_Note());
		
		//gGrid.add(lbl_employeeState);
		gGrid.insertRow(gGrid.getRowCount() );
		
		gGrid.setWidget(gGrid.getRowCount() -1 , 0, lbl_employeeName);
		gGrid.setWidget(gGrid.getRowCount() -1 , 1, lbl_employeeState);
		gGrid.setWidget(gGrid.getRowCount() -1 , 2, lbl_employeeTime);
		gGrid.setWidget(gGrid.getRowCount() -1 , 3, lbl_employeeAddress);
		//gGrid.add
	}
	
	public void ResetGridContend() {
		do {
		gGrid.removeRow(gGrid.getRowCount()-1);
		}while (gGrid.getRowCount() > 1);
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

	@Override
	public HasClickHandlers getPrevious() {
		// TODO Auto-generated method stub
		return btnPrevious;
	}

	@Override
	public HasClickHandlers getToday() {
		// TODO Auto-generated method stub
		return btnToday;
	}

	@Override
	public HasClickHandlers getNext() {
		// TODO Auto-generated method stub
		return btnNext;
	}

}
