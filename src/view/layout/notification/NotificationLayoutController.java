package view.layout.notification;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import config.Config;
import database.Mongo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kide.KideAppEvent;
import model.EventListCell;
import model.KideAppEventDetails;
import view.Gui;
import view.ILayoutController;

public class NotificationLayoutController implements ILayoutController {
	Gui gui;
	private KideAppEvent latestEvent;
	@FXML
	private ListView<KideAppEvent> listViewEvents;
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
	@FXML
	private Button btnRefresh;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		showEventsList();

		listViewEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				getEventInfo(newValue);
			} else {
				listViewEvents.getSelectionModel().clearSelection();
			}
		});
		return this;
	}

	public void showEventsList() {
		List<KideAppEvent> events = Mongo.INSTANCE.fetchUserSavedEvents();
		listViewEvents.getItems().clear();
		listViewEvents.getItems().addAll(events);
		listViewEvents.setCellFactory(param -> new EventListCell());
	}

	public void insertEventToList(KideAppEvent e) {
		listViewEvents.getItems().add(e);
	}

	public void receiveEventDetails(Optional<KideAppEventDetails> e) {
		displayEventInfo(e);
	}

	public void displayEventInfo(Optional<KideAppEventDetails> e) {
		latestEvent.updateData(e);
		Mongo.INSTANCE.updateEvent(latestEvent);
		Image logo = new Image(Config.get("IMG_URL_PREFIX",
				"https://portalvhdsp62n0yt356llm.blob.core.windows.net/bailataan-mediaitems/")
				+ latestEvent.getMediaFilename());
		imgViewLogo.setImage(logo);
		textEventName.setText(latestEvent.getName());
		textCompanyName.setText(latestEvent.getCompanyName());

		ZonedDateTime nowDate = ZonedDateTime.now();

		long daysUntilStart = ChronoUnit.DAYS.between(nowDate, latestEvent.getDateActualUntil());
		long daysUntilSaleStart = ChronoUnit.DAYS.between(nowDate, latestEvent.getDateSalesFrom());
		long daysUntilSaleEnd = ChronoUnit.DAYS.between(nowDate, latestEvent.getDateSalesUntil());

		if (latestEvent.getAvailability() == 0)
			textSaleStart.setText("Sold out!");
		else if (latestEvent.getSalesEnded())
			textSaleStart.setText("Sales ended!");
		else if (latestEvent.getSalesStarted())
			textSaleStart.setText("Ticket sale started! " + daysUntilSaleEnd + " days left!");
		else
			textSaleStart.setText(daysUntilSaleStart + " days until ticket sale");

		if (daysUntilStart == 0) {
			textEventStart.setText("Event starts today!");
		} else if (daysUntilStart < 0) {
			textEventStart.setText("Event ended already!");
		} else {
			textEventStart.setText(daysUntilStart + " days until start of event");
		}
	}

	public void displayLoading() {
		Image loadGif = new Image("res/loading.gif");
		imgViewLogo.setImage(loadGif);
		textEventName.setText(null);
		textCompanyName.setText(null);
		textSaleStart.setText(null);
		textEventStart.setText(null);
	}

	public void getEventInfo(KideAppEvent e) {
		infoLayoutBox.setVisible(true);
		displayLoading();
		gui.getController().requestEventDetails(e.getId());
		latestEvent = e;

	}
}