package controller;

import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import model.IMotor;
import model.Motor;
import model.kide.KideAppEvent;
import model.kide.KideAppEventDetails;
import view.IGui;

public class AppController implements IAppControllerMToV, IAppControllerVToM {
	IMotor motor;
	IGui gui;

	public AppController(IGui gui) {
		this.motor = new Motor(this);
		this.gui = gui;
		((Thread) motor).start(); // Might not do anything atm, stuff feels very synchronous
	}

	@Override
	public void requestEvents() {
		new Thread(() -> motor.handleEventsRequest()).start();
	}

	@Override
	public void requestEventDetails(String id) {
		new Thread(() -> motor.handleEventDetailsRequest(id)).start();
	}

	@Override
	public void receiveEventDetails(Optional<KideAppEventDetails> e) {
		Platform.runLater(() -> gui.handleEventDetails(e));
	}

	@Override
	public void receiveEvents(Optional<List<KideAppEvent>> events) {
		Platform.runLater(() -> gui.handleEvents(events));
	}

}