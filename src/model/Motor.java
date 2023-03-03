package model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import controller.IAppControllerVToM;
import database.Mongo;
import kide.KideAppApi;
import kide.KideAppEvent;
import model.schedule.Scheduler;
import model.schedule.TaskConvertter;

public class Motor extends Thread implements IMotor {
	IAppControllerVToM controller;
	KideAppApi api;

	public Motor(IAppControllerVToM controller) {
		this.controller = controller;
		api = new KideAppApi();
		
		scheduleInitialTasks();
	}
	
	private void scheduleInitialTasks() {
		ZonedDateTime now = ZonedDateTime.now();

		var userSavedEvents = Mongo.INSTANCE.fetchUserSavedEvents();

		userSavedEvents
			.stream()
			.filter(e -> e.getDateSalesFrom().compareTo(now) > 0)
			.map(TaskConvertter::toSalesStartingTask)
			.forEach(Scheduler.INSTANCE::schedule);

		userSavedEvents
			.stream()
			.filter(e -> e.getDateActualFrom().compareTo(now) > 0)
			.map(TaskConvertter::toEventStartingTask)
			.forEach(Scheduler.INSTANCE::schedule);
	}

	@Override
	public void handleEventsRequest() {
		Optional<List<KideAppEvent>> events = api.fetchEvents();

		if (events.isPresent()) {
			Mongo.INSTANCE.insertEvents(events.get());
		}

		controller.receiveEvents(events);
	}

	@Override
	public void handleEventDetailsRequest(String id) {
		Optional<KideAppEventDetails> event = api.fetchEventDetails(id);
		controller.receiveEventDetails(event);
	}

	public void handleDatabaseRequest() {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime yesterday = now.minusDays(1);

		Mongo.INSTANCE.fetchDataPoints(yesterday, now);
	}
}
