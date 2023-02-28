package view.layout.notification;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import config.Config;
import database.Mongo;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kide.KideAppEvent;
import view.Gui;
import view.ILayoutController;

public class NotificationLayoutController implements ILayoutController {
	Gui gui;
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

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		showEventsList();

		listViewEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				showEventInfo(newValue);
			} else {
				listViewEvents.getSelectionModel().clearSelection();
			}
		});
		return this;
	}

	public void showEventsList() {
		List<KideAppEvent> events = Mongo.INSTANCE.fetchUserSavedEvents();
		for (int i = 0; i < events.size(); i++) {
			listViewEvents.getItems().add(events.get(i));
		}
	}

	public void showEventInfo(KideAppEvent e) {
		infoLayoutBox.setVisible(true);
		Image logo = new Image(Config.get("IMG_URL_PREFIX",
				"https://portalvhdsp62n0yt356llm.blob.core.windows.net/bailataan-mediaitems/") + e.getMediaFilename());
		imgViewLogo.setImage(logo);
		textEventName.setText(e.getName());
		textCompanyName.setText(e.getCompanyName());

		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		ZonedDateTime eventStartDate = ZonedDateTime.parse(e.getDateActualUntil(), formatter);
		ZonedDateTime saleStartDate = ZonedDateTime.parse(e.getDateSalesFrom(), formatter);
		ZonedDateTime saleEndDate = ZonedDateTime.parse(e.getDateSalesUntil(), formatter);
		ZonedDateTime dateTimeNow = ZonedDateTime.now();
		long daysUntilStart = ChronoUnit.DAYS.between(dateTimeNow, eventStartDate);
		long daysUntilSaleStart = ChronoUnit.DAYS.between(dateTimeNow, saleStartDate);
		long daysUntilSaleEnd = ChronoUnit.DAYS.between(dateTimeNow, saleEndDate);
		if (e.getAvailability() == 0 || e.getSalesEnded())
			textSaleStart.setText("Sold out!");
		else if (e.getSalesStarted())
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
}