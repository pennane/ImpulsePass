package database;

import java.util.Date;
import java.util.List;

import kide.KideAppEvent;

public class EventsDataPoint {
	List<KideAppEvent> events;
	Date date;

	public EventsDataPoint(List<KideAppEvent> events) {
		date = new Date();
		this.events = events;
	}
}
