package view.layout.upsert;

import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import kide.KideAppEvent;
import view.Gui;
import view.ILayoutController;

public class UpsertLayoutController implements ILayoutController {
	Gui gui;

	@FXML
	private Button buttonFetchEvents;

	@Override
	public ILayoutController initialize(Gui gui) {
		buttonFetchEvents.setText("Fetch and insert events from the web");
		buttonFetchEvents.setDisable(false);
		this.gui = gui;
		return this;

	}

	public void handleFetchEvents() {
		buttonFetchEvents.setText("fetching...");
		buttonFetchEvents.setDisable(true);
		gui.getController().requestEvents();
	}

	public void receiveFetchedEvents(Optional<List<KideAppEvent>> events) {
		buttonFetchEvents.setDisable(false);
		if (events.isPresent()) {
			buttonFetchEvents.setText(events.get().size() + " tapahtumaa viety kantaan");
		} else {
			buttonFetchEvents.setText(events.get().size() + " epäonnistui");
		}
	}
}