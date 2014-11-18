/**
 * 
 */
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
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.IntegerBox;
import com.kic.hrm.client.presenter.QuotaPresenter.Display;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Noppon
 *
 */
public class QuotaView extends Composite implements HasText,Display {

	private static QuotaViewUiBinder uiBinder = GWT
			.create(QuotaViewUiBinder.class);
	@UiField Button Applybtn;
	@UiField Button Backbtn;
	@UiField TextBox LeaveQuotaText;
	@UiField TextBox HolidayQuotaText;

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
		Applybtn.setText(firstName);
	}

	public void setText(String text) {
		Applybtn.setText(text);
	}

	/**
	 * Gets invoked when the default constructor is called
	 * and a string is provided in the ui.xml file.
	 */
	public String getText() {
		return Applybtn.getText();
	}

	@UiHandler("Applybtn")
	void onApplybtnClick(ClickEvent event) {
	}

	@Override
	public HasValue<String> getLeaveQuota() {
		// TODO Auto-generated method stub
		
		return LeaveQuotaText;
	}

	@Override
	public HasValue<String> getHolidayQuota() {
		// TODO Auto-generated method stub
		return HolidayQuotaText;
	}

	@Override
	public HasClickHandlers getApply() {
		// TODO Auto-generated method stub
		return Applybtn;
	}

	@Override
	public HasClickHandlers getBack() {
		// TODO Auto-generated method stub
		return Backbtn;
	}
}
