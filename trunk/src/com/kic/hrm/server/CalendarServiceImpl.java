package com.kic.hrm.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext.ListRequest.MinAccessRole;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Event.Reminders;
import com.google.gwt.core.client.GWT;

public class CalendarServiceImpl {

	private Calendar calendar = GWT.create(Calendar.class);
	
	
	public static Calendar BuildCalendarAPIbyTOKEN(String token,String APPLICATION_NAME) {
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credential = new GoogleCredential()
				.setAccessToken(token);
		Calendar service = new Calendar.Builder(httpTransport, jsonFactory,
				credential).setApplicationName(APPLICATION_NAME).build();
		return service;
	}
	
	/** Gets the calendar ID of some calendar that the user can write to. */
	private void getCalendarId(Calendar calender) {
		// We need to find an ID of a calendar that we have permission to write
		// events to. We'll just
		// pick the first one that gets returned, and we will delete the event
		// when we're done.
		/*
		 * calendar.calendarList().list().setMinAccessRole(MinAccessRole.OWNER)
		 * .fire(new Receiver<CalendarList>() {
		 * 
		 * @Override public void onSuccess(CalendarList list) { String
		 * calendarId = list.getItems().get(0).getId();
		 * 
		 * insertEvent(calendarId); } });
		 */
		try {
			com.google.api.services.calendar.Calendar.CalendarList.List m_list = calender
					.calendarList().list()
					.setMinAccessRole(MinAccessRole.OWNER.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Event createEvent(String titleEvent,String description,Date in_startDate,Date in_endDate) {
		Event event = new Event();
		// event.setColorId("red");
		event.setSummary(titleEvent);
		//event.setLocation("Somewhere");
		Reminders test = new Reminders();
		test.setUseDefault(false);
		event.setReminders(test);
		event.setDescription(description);

		Date startDate = in_startDate;
		Date endDate = in_endDate;
				//new Date(startDate.getTime() + 86400000);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDateStr = dateFormat.format(startDate);
		String endDateStr = dateFormat.format(endDate);

		// Out of the 6 methods for creating a DateTime object with no time
		// element, only the String version works
		DateTime startDateTime = new DateTime(startDateStr);
		DateTime endDateTime = new DateTime(endDateStr);

		// Must use the setDate() method for an all-day event (setDateTime()
		// is used for timed events)
		EventDateTime startEventDateTime = new EventDateTime()
				.setDate(startDateTime);
		EventDateTime endEventDateTime = new EventDateTime()
				.setDate(endDateTime);

		event.setStart(startEventDateTime);
		event.setEnd(endEventDateTime);
		return event;
	}
	
	
	
	/** Insert a new event for the given calendar ID. */
	/*
	 * private void insertEvent(Calendar calender, final String calendarId) {
	 * String today = DateTimeFormat.getFormat("yyyy-MM-dd") .format(new
	 * Date()); EventsContext ctx = calendar.events(); Event event =
	 * ctx.create(Event.class)
	 * .setSummary("Learn about the Google API GWT client library")
	 * .setStart(ctx.create(EventDateTime.class).setDateTime(today))
	 * .setEnd(ctx.create(EventDateTime.class).setDateTime(today));
	 * 
	 * // Note that the EventsContext used to insert the Event has to be the //
	 * same one used to create // it. ctx.insert(calendarId, event).fire(new
	 * Receiver<Event>() {
	 * 
	 * @Override public void onSuccess(Event inserted) { // The event has been
	 * inserted.
	 * 
	 * // Now we'll demonstrate retrieving it and updating it. String eventId =
	 * inserted.getId(); getEventForUpdate(calendarId, eventId); } }); }
	 */
	/** Get an event for the purposes of updating it. */
	/*
	 * private void getEventForUpdate(Calendar calender,final String calendarId,
	 * final String eventId) { final EventsContext ctx = calendar.events();
	 * ctx.get(calendarId, eventId).fire(new Receiver<Event>() {
	 * 
	 * @Override public void onSuccess(Event event) { // Note that the
	 * EventsContext used to update the event has to // be the same one that was
	 * // used to retrieve it. updateEvent(ctx, event, calendarId, eventId); }
	 * }); }
	 */
	/** Update an event that was previously retrieved. */
	/*
	 * private void updateEvent(Calendar calender,EventsContext ctx, Event
	 * event, final String calendarId, final String eventId) { String newSummary
	 * = ""; while (newSummary.isEmpty()) { newSummary =
	 * Window.prompt("Provide a new name for the event", ""); } Event
	 * editableEvent = ctx.edit(event); // Don't forget to call edit()
	 * editableEvent.setSummary(newSummary); ctx.update(calendarId, eventId,
	 * editableEvent).fire( new Receiver<Event>() {
	 * 
	 * @Override public void onSuccess(Event updated) { // The event has been
	 * updated. Now we'll delete it.
	 * 
	 * deleteEvent(calender,calendarId, eventId); } }); }
	 */
	/** Delete an event by its ID. */
	/*
	 * private void deleteEvent(Calendar calender,String calendarId, String
	 * eventId) { calendar.events().delete(calendarId, eventId) .fire(new
	 * Receiver<EmptyResponse>() {
	 * 
	 * @Override public void onSuccess(EmptyResponse r) { // The event has been
	 * deleted. And we're done! Window.alert("Event deleted! Demo complete!"); }
	 * }); }
	 */
}
