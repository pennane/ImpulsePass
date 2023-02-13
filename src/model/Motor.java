package model;

import controller.IAppControllerVToM;
import kide.KideAppApi;

public class Motor implements IMotor {
	IAppControllerVToM controller;
	KideAppApi api;

	public Motor(IAppControllerVToM controller) {
		this.controller = controller;
		api = new KideAppApi();
	}

	@Override
	public void handleEventsRequest() {
		controller.receiveEvents(api.fetchEvents());
	}

}
