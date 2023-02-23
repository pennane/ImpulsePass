package view.layout.upsert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import database.EventsDataPoint;
import database.Mongo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import kide.KideAppEvent;
import view.Gui;
import view.ILayoutController;

public class UpsertLayoutController implements ILayoutController {
	Gui gui;

	@FXML
	private Button buttonFetchEvents;
	@FXML
	private Button buttonShowResults;
	@FXML
	private ListView<EventsDataPoint> listDataPoints;
	@FXML
	private ListView<KideAppEvent> listEvents;
	@FXML
	private DatePicker pickerEndDate;
	@FXML
	private DatePicker pickerStartDate;

	@Override
	public ILayoutController initialize(Gui gui) {
		buttonFetchEvents.setText("Fetch and insert events from the web");
		buttonFetchEvents.setDisable(false);
		this.gui = gui;
		listDataPoints.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null) {
				showEventsList(newValue);
			}else {
				listDataPoints.getSelectionModel().clearSelection();
			}
		});
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
	
	public void showEventsList(EventsDataPoint e) {
		for(int i = 0; i < e.getEvents().size(); i++) {
			listEvents.getItems().add(e.getEvents().get(i));
		}
	}
	
	public void showEventsDataPoints() {
		listDataPoints.getItems().clear();
		 LocalDate endDate = pickerEndDate.getValue().plusDays(1);
         LocalDate startDate = pickerStartDate.getValue();

         Date startDateObj = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
         Date endDateObj = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchDataPoints(startDateObj, endDateObj);
		List<KideAppEvent> events = eventDataPoints.get(0).getEvents();
		for(int i = 0; i < eventDataPoints.size(); i++) {
			listDataPoints.getItems().add(eventDataPoints.get(i));
		}
		
	}
}