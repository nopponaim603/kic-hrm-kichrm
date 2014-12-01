package com.kic.hrm.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Frame;
import com.kic.hrm.client.presenter.CalendarReportPresenter;

public class CalendarReportView extends Composite implements HasText, CalendarReportPresenter.Display {

	private static CalendarReportViewUiBinder uiBinder = GWT
			.create(CalendarReportViewUiBinder.class);
	@UiField Frame uiFrame;
	@UiField Button uiBack;
	
	String urlCalender = "https://www.google.com/calendar/embed?src=camtedu.net_ffupfdej93dc7td5rop26gvp1s%40group.calendar.google.com&ctz=Asia/Bangkok";
	
	interface CalendarReportViewUiBinder extends
			UiBinder<Widget, CalendarReportView> {
	}

	public CalendarReportView() {
		initWidget(uiBinder.createAndBindUi(this));
		//uiHTMLBody.
		//uiHTMLBody.set
		uiFrame.setUrl(urlCalender);
	}

	public CalendarReportView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	public void setText(String text) {
		
	}

	public String getText() {
		return null;
	}

	@Override
	public HasClickHandlers getBack() {
		// TODO Auto-generated method stub
		return uiBack;
	}

}
