package com.kic.hrm.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.kic.hrm.client.presenter.TestPresenter;

public class TestView extends Composite implements TestPresenter.Display{

	private static TestViewUiBinder uiBinder = GWT
			.create(TestViewUiBinder.class);

	interface TestViewUiBinder extends UiBinder<Widget, TestView> {
	}

	public TestView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
