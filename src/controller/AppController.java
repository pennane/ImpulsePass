package controller;

import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
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
		((Thread) motor).start(); // Might not do anything atm, stuff feels very synchronous
	}

	@Override
	public void requestEvents() {
		new Thread(() -> motor.handleEventsRequest()).start();
	}

	@Override
	public void receiveEvents(Optional<List<KideAppEvent>> events) {
		Platform.runLater(() -> gui.handleEvents(events));
	}

}