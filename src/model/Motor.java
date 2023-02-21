package model;

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
		controller.receiveEvents(events);

		if (events.isPresent()) {
			Mongo.INSTANCE.insertEvents(events.get());
		}

	}
}
