package view.layout.upsert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import config.Config;
import database.EventsDataPoint;
import database.Mongo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kide.KideAppEvent;
import view.Gui;
import view.ILayoutController;

public class UpsertLayoutController implements ILayoutController {
	Gui gui;
	@FXML
	private Button buttonFetchEvents;
	@FXML
	private Button buttonSaveEvent;
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
	@FXML
	private ImageView imgViewLogo;
	@FXML
	private Text textEventName;
	@FXML
	private Text textCompanyName;
	@FXML
	private Text textSaleStart;
	@FXML
	private Text textEventStart;
	@FXML
	private VBox infoLayoutBox;

	@Override
	public ILayoutController initialize(Gui gui) {
		buttonFetchEvents.setText("Fetch and insert events from the web");
		buttonFetchEvents.setDisable(false);
		this.gui = gui;
		listDataPoints.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				showEventsList(newValue);
			} else {
				listDataPoints.getSelectionModel().clearSelection();
			}
		});

		listEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				showEventInfo(newValue);
			} else {
				listEvents.getSelectionModel().clearSelection();
			}
		});

		var now = LocalDate.now();
		pickerEndDate.setValue(now.plusDays(1));
		pickerStartDate.setValue(now.minusDays(7));
		showEventsDataPoints();

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
		listEvents.getItems().clear();
		listEvents.getItems().addAll(e.getEvents());
	}

	public void showEventsDataPoints() {
		if (pickerEndDate.getValue() == null || pickerStartDate.getValue() == null) {
			return;
		}

		listDataPoints.getItems().clear();

		ZonedDateTime endDate = pickerEndDate.getValue().plusDays(1).atStartOfDay(ZoneId.systemDefault());
		ZonedDateTime startDate = pickerStartDate.getValue().atStartOfDay(ZoneId.systemDefault());

		List<EventsDataPoint> eventDataPoints = Mongo.INSTANCE.fetchDataPoints(startDate, endDate);
		listDataPoints.getItems().addAll(eventDataPoints);

	}

	public void showEventInfo(KideAppEvent e) {
		infoLayoutBox.setVisible(true);
		if (Mongo.INSTANCE.savedEventExists(e)) {
			buttonSaveEvent.setDisable(true);
			buttonSaveEvent.setText("Added to my events");
		} else {
			buttonSaveEvent.setDisable(false);
			buttonSaveEvent.setText("Save to my events");
		}
		Image logo = new Image(Config.get("IMG_URL_PREFIX",
				"https://portalvhdsp62n0yt356llm.blob.core.windows.net/bailataan-mediaitems/") + e.getMediaFilename());
		int saleTimeInDays = Integer.parseInt(e.getTimeUntilSalesStart()) / 86400;
		int startTimeInDays = Integer.parseInt(e.getTimeUntilActual()) / 86400;
		imgViewLogo.setImage(logo);
		textEventName.setText(e.getName());
		textCompanyName.setText(e.getCompanyName());
		if (e.getAvailability() == 0 || e.getSalesEnded())
			textSaleStart.setText("Sold out!");
		else if (e.getSalesStarted())
			textSaleStart.setText("Ticket sale started!");
		else
			textSaleStart.setText(saleTimeInDays + " days until ticket sale");

		textEventStart.setText(startTimeInDays + " days until start of event");

	}

	public void saveEvent() {
		buttonSaveEvent.setDisable(true);
		buttonSaveEvent.setText("Added to my events");
		if (!Mongo.INSTANCE.savedEventExists(listEvents.getSelectionModel().getSelectedItem())) {
			Mongo.INSTANCE.insertUserSavedEvent(listEvents.getSelectionModel().getSelectedItem());
			gui.getNotificationLayoutController().insertEventToList(listEvents.getSelectionModel().getSelectedItem());
		}
	}
}