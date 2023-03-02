package model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import controller.IAppControllerVToM;
import database.Mongo;
import kide.KideAppApi;
import kide.KideAppEvent;

public class Motor extends Thread implements IMotor {
	IAppControllerVToM controller;
	KideAppApi api;

	public Motor(IAppControllerVToM controller) {
		this.controller = controller;
		api = new KideAppApi();

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
		Date yesterday = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
		Date now = new Date();
		Mongo.INSTANCE.fetchDataPoints(yesterday, now);
	}
}
