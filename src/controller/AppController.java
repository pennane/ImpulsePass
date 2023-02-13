package controller;

import java.util.List;
import java.util.Optional;

import kide.KideAppEvent;
import model.IMotor;
import model.Motor;
import view.IGui;

public class AppController implements IAppControllerMToV, IAppControllerVToM {
	IMotor motor;
	IGui gui;

	public AppController(IGui gui) {
		this.motor = new Motor(this);
		this.gui = gui;
	}

	@Override
	public void requestEvents() {
		this.motor.handleEventsRequest();
	}

	@Override
	public void receiveEvents(Optional<List<KideAppEvent>> events) {
		this.gui.handleEvents(events);
	}

}