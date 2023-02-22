package database;

import java.util.Date;
import java.util.List;

import kide.KideAppEvent;

public class EventsDataPoint {
	List<KideAppEvent> events;
	Date date;

	public EventsDataPoint(List<KideAppEvent> events) {
		this.date = new Date();
		this.events = events;
	}

	public EventsDataPoint() {
	}

	public List<KideAppEvent> getEvents() {
		return events;
	}

	public void setEvents(List<KideAppEvent> events) {
		this.events = events;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
