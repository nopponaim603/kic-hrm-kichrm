/**
 * 
 */
package com.kic.hrm.client.view;

import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;

import com.google.gwt.user.client.ui.Widget;

import com.kic.hrm.client.presenter.QuotaPresenter;

import com.google.gwt.user.client.ui.Label;

/**
 * @author Noppon
 *
 */
public class QuotaView extends Composite implements HasText ,QuotaPresenter.Display {

	private static QuotaViewUiBinder uiBinder = GWT
			.create(QuotaViewUiBinder.class);
	
	@UiField Button Backbtn;
	@UiField Label OnTimeText;
	@UiField Label OnSiteText;
	@UiField Label LateText;
	@UiField Label AbsenceText;
	@UiField Label LeaveQuotaText;
	@UiField Label HolidayQuotaText;
	@UiField Button DailyReportbtn;

	interface QuotaViewUiBinder extends UiBinder<Widget, QuotaView> {
		
	}

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public QuotaView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public QuotaView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		// Can access @UiField after calling createAndBindUi
		
	}
	
	/*
	public Widget asWidget() {
		return this;
	}*/

	@Override
	public HasClickHandlers getBack() {
		// TODO Auto-generated method stub
		return Backbtn;
	}

	@Override
	public HasClickHandlers getDailyReport() {
		// TODO Auto-generated method stub
		return DailyReportbtn;
	}
	
	@Override
	public void setOnTimeText(int ontime) {
		// TODO Auto-generated method stub
		OnTimeText.setText(String.valueOf(ontime));
	}

	@Override
	public void setOnSiteText(int onsite) {
		// TODO Auto-generated method stub
		OnSiteText.setText(String.valueOf(onsite));
	}

	@Override
	public void setLate(int late) {
		// TODO Auto-generated method stub
		LateText.setText(String.valueOf(late));
	}

	@Override
	public void setAbsence(int absence) {
		// TODO Auto-generated method stub
		AbsenceText.setText(String.valueOf(absence));
	}

	@Override
	public void setLeaveQuota(int leaveQuota) {
		// TODO Auto-generated method stub
		LeaveQuotaText.setText(String.valueOf(leaveQuota));
	}

	@Override
	public void setHolidayQuota(int holidayQuota) {
		// TODO Auto-generated method stub
		HolidayQuotaText.setText(String.valueOf(holidayQuota));
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

}
