package com.kic.hrm.client;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kic.hrm.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.cellview.client.CellTable;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HumanResourcesManagement implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.setSize("800px", "600px");
		rootPanel.setStyleName("h1");

		TabPanel tabPanel = new TabPanel();
		tabPanel.setVisible(true);
		tabPanel.setAnimationEnabled(true);
		rootPanel.add(tabPanel, 10, 10);
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

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setHeight("123px");

		Grid grid_3 = new Grid(2, 1);
		horizontalPanel.add(grid_3);
		grid_3.setWidth("380px");

		Label label = new Label(
				"\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48 17 \u0E2A\u0E34\u0E07\u0E2B\u0E32\u0E04\u0E21 2556 ");
		label.setStyleName("gwt-panal");
		label.setDirectionEstimator(true);
		grid_3.setWidget(0, 0, label);
		label.setSize("151px", "24px");

		Grid grid_1 = new Grid(3, 2);
		grid_1.setStyleName("gwt-panal");
		grid_3.setWidget(1, 0, grid_1);

		Label label_1 = new Label(
				"\u0E0A\u0E37\u0E48\u0E2D-\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_1.setStyleName("gwt-panal");
		grid_1.setWidget(0, 0, label_1);
		label_1.setWidth("128px");

		Label label_2 = new Label(
				"\u0E19\u0E32\u0E22 \u0E23\u0E27\u0E22\u0E17\u0E23\u0E31\u0E1E\u0E22\u0E4C \u0E2A\u0E27\u0E07\u0E2A\u0E27\u0E23\u0E23\u0E04\u0E4C");
		label_2.setStyleName("gwt-panal");
		grid_1.setWidget(0, 1, label_2);

		Label label_3 = new Label(
				"\u0E23\u0E2B\u0E31\u0E2A\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19");
		label_3.setStyleName("gwt-panal");
		grid_1.setWidget(1, 0, label_3);

		Label label_4 = new Label("5032165");
		label_4.setStyleName("gwt-panal");
		grid_1.setWidget(1, 1, label_4);

		Label label_5 = new Label("\u0E15\u0E33\u0E41\u0E2B\u0E19\u0E48\u0E07");
		label_5.setStyleName("gwt-panal");
		grid_1.setWidget(2, 0, label_5);

		Label label_6 = new Label(
				"\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19\u0E1B\u0E23\u0E30\u0E08\u0E33 (\u0E2A\u0E48\u0E27\u0E19\u0E07\u0E32\u0E19)");
		label_6.setStyleName("gwt-panal");
		grid_1.setWidget(2, 1, label_6);

		Grid grid_2 = new Grid(2, 1);
		horizontalPanel.add(grid_2);
		grid_2.setSize("100%", "112px");

		Label lblNewLabel = new Label(
				"\u0E08\u0E33\u0E19\u0E27\u0E19\u0E27\u0E31\u0E19\u0E25\u0E32\u0E2A\u0E30\u0E2A\u0E21");
		grid_2.setWidget(0, 0, lblNewLabel);
		lblNewLabel.setStyleName("gwt-panal");
		lblNewLabel.setDirectionEstimator(true);
		lblNewLabel.setSize("205px", "24px");

		Grid grid = new Grid(3, 2);
		grid.setStyleName("gwt-panal");
		grid_2.setWidget(1, 0, grid);

		Label label_9 = new Label("\u0E25\u0E32\u0E01\u0E34\u0E08");
		label_9.setStyleName("gwt-panal");
		grid.setWidget(0, 0, label_9);
		label_9.setWidth("128px");

		Label label_10 = new Label("10");
		label_10.setStyleName("gwt-panal");
		grid.setWidget(0, 1, label_10);

		Label label_11 = new Label("\u0E25\u0E32\u0E1B\u0E48\u0E27\u0E22");
		label_11.setStyleName("gwt-panal");
		grid.setWidget(1, 0, label_11);

		Label label_12 = new Label("10");
		label_12.setStyleName("gwt-panal");
		grid.setWidget(1, 1, label_12);

		Label label_13 = new Label(
				"\u0E25\u0E32\u0E1E\u0E31\u0E01\u0E1C\u0E48\u0E2D\u0E19\u0E1B\u0E23\u0E30\u0E08\u0E33\u0E1B\u0E35");
		label_13.setStyleName("gwt-panal");
		grid.setWidget(2, 0, label_13);

		Label label_14 = new Label("10");
		label_14.setStyleName("gwt-panal");
		grid.setWidget(2, 1, label_14);

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
		tabPanel.add(verticalPanel_4, "Leaving Reports", false);
		verticalPanel_4.setSize("780px", "550px");

		Label lblNewLabel_1 = new Label("New label");
		verticalPanel_4.add(lblNewLabel_1);

		ListBox comboBox_2 = new ListBox();
		comboBox_2
				.addItem("\u0E25\u0E32\u0E1B\u0E23\u0E30\u0E08\u0E33\u0E40\u0E14\u0E37\u0E2D\u0E19");
		comboBox_2
				.addItem("\u0E25\u0E32\u0E1B\u0E23\u0E30\u0E08\u0E33\u0E1B\u0E35");
		verticalPanel_4.add(comboBox_2);
		comboBox_2.setWidth("161px");

		CellTable<Object> cellTable_1 = new CellTable<Object>();
		verticalPanel_4.add(cellTable_1);
		cellTable_1.setSize("558px", "242px");

		FlowPanel flowPanel_3 = new FlowPanel();
		tabPanel.add(flowPanel_3, "Leaving Calendar", true);
		flowPanel_3.setSize("780px", "580px");

		DatePicker datePicker = new DatePicker();
		flowPanel_3.add(datePicker);
		datePicker.setSize("625px", "325px");

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		tabPanel.selectTab(0);
		// Add a handler to close the DialogBox

		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				/*
				 * dialogBox.hide(); sendButton.setEnabled(true);
				 * sendButton.setFocus(true);
				 */
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				onSubmitLeaving();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			public void onSubmitLeaving(){
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);

				String msgBody = "...";
				
				try {
				    Message msg = new MimeMessage(session);
				    try {
						msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    try {
						 msg.addRecipient(Message.RecipientType.TO,
						 new InternetAddress("noppon.w@vr.camt.info", "Mr.Noppon"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    msg.setSubject("Your Example.com account has been activated");
				    msg.setText(msgBody);
				    Transport.send(msg);

				} catch (AddressException e) {
				    // ...
				} catch (MessagingException e) {
				    // ...
				}
			}
			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				/*
				 * errorLabel.setText(""); String textToServer =
				 * nameField.getText(); if
				 * (!FieldVerifier.isValidName(textToServer)) {
				 * errorLabel.setText("Please enter at least four characters");
				 * return; }
				 * 
				 * // Then, we send the input to the server.
				 * sendButton.setEnabled(false);
				 * textToServerLabel.setText(textToServer);
				 * serverResponseLabel.setText("");
				 * greetingService.greetServer(textToServer, new
				 * AsyncCallback<String>() { public void onFailure(Throwable
				 * caught) { // Show the RPC error message to the user dialogBox
				 * .setText("Remote Procedure Call - Failure");
				 * serverResponseLabel
				 * .addStyleName("serverResponseLabelError");
				 * serverResponseLabel.setHTML(SERVER_ERROR);
				 * dialogBox.center(); closeButton.setFocus(true); }
				 * 
				 * public void onSuccess(String result) {
				 * dialogBox.setText("Remote Procedure Call");
				 * serverResponseLabel
				 * .removeStyleName("serverResponseLabelError");
				 * serverResponseLabel.setHTML(result); dialogBox.center();
				 * closeButton.setFocus(true); }
				 * 
				 * });
				 */
			}
		}

		// Add a handler to send the name to the server
		//MyHandler handler = new MyHandler();
		//btnNewButton_5.addClickListener(handler.onSubmitLeaving(ClickEvent));
		//btnNewButton_5.addClickHandler(handler);
	}
}
