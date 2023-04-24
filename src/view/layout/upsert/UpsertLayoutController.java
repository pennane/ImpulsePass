package view.layout.upsert;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import config.Config;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.database.mongo.EventsDataPoint;
import model.database.mongo.Mongo;
import model.kide.KideAppEvent;
import view.Gui;
import view.ILayoutController;

public class UpsertLayoutController implements ILayoutController {
	Gui gui;
	@FXML
	private Button buttonFetchEvents;
	@FXML
	private Button buttonSaveEvent;
	@FXML
	private ListView<EventsDataPoint> listDataPoints;
	@FXML
	private ListView<KideAppEvent> listEvents;
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

	private Predicate<KideAppEvent> predicate;
	private FilteredList<KideAppEvent> kideAppEvents;

	private Boolean availableFilter;
	private Boolean upcomingFilter;
	private Boolean salesOngoingFilter;
	private Boolean salesEndedFilter;

	@Override
	public ILayoutController initialize(Gui gui) {
		availableFilter = false;
		upcomingFilter = false;
		salesOngoingFilter = false;
		salesEndedFilter = false;

		predicate = createPredicate();
		showEventsList(Mongo.INSTANCE.fetchLatest().get().getEvents());
		buttonFetchEvents.setDisable(false);
		this.gui = gui;

		listEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				showEventInfo(newValue);
			} else {
				listEvents.getSelectionModel().clearSelection();
			}
		});

		return this;

	}

	public void handleFetchEvents() {
		buttonFetchEvents.setDisable(true);
		gui.getController().requestEvents();
	}

	public void receiveFetchedEvents(Optional<List<KideAppEvent>> events) {
		buttonFetchEvents.setDisable(false);
		if (events.isPresent()) {
			showEventsList(events.get());
		}
	}

	public void showEventsList(List<KideAppEvent> e) {
		kideAppEvents = new FilteredList<KideAppEvent>(FXCollections.observableArrayList(e), predicate);
		listEvents.getItems().clear();
		listEvents.getItems().addAll(kideAppEvents);
	}

	public void showEventInfo(KideAppEvent e) {
		infoLayoutBox.setVisible(true);
		if (Mongo.INSTANCE.savedEventExists(e)) {
			buttonSaveEvent.setDisable(true);
			buttonSaveEvent.setText(gui.getIntl("findNew.addedToMyEvents"));
		} else {
			buttonSaveEvent.setDisable(false);
			buttonSaveEvent.setText(gui.getIntl("findNew.saveToMyEvents"));
		}
		Image logo = new Image(Config.get("IMG_URL_PREFIX",
				"https://portalvhdsp62n0yt356llm.blob.core.windows.net/bailataan-mediaitems/") + e.getMediaFilename());
		int saleTimeInDays = Integer.parseInt(e.getTimeUntilSalesStart()) / 86400;
		int startTimeInDays = Integer.parseInt(e.getTimeUntilActual()) / 86400;
		imgViewLogo.setImage(logo);
		textEventName.setText(e.getName());
		textCompanyName.setText(e.getCompanyName());
		if (e.getAvailability() == 0 || e.getSalesEnded())
			textSaleStart.setText(gui.getIntl("event.soldOut"));
		else if (e.getSalesStarted())
			textSaleStart.setText(gui.getIntl("event.salesStarted"));
		else
			textSaleStart.setText(saleTimeInDays + " " + gui.getIntl("daysUntilSaleStart"));

		textEventStart.setText(startTimeInDays + " " + gui.getIntl("event.daysUntilEventStart"));

	}

	public void saveEvent() {
		buttonSaveEvent.setDisable(true);
		buttonSaveEvent.setText(gui.getIntl("findNew.addedToMyEvents"));
		if (!Mongo.INSTANCE.savedEventExists(listEvents.getSelectionModel().getSelectedItem())) {
			Mongo.INSTANCE.insertUserSavedEvent(listEvents.getSelectionModel().getSelectedItem());
			gui.getNotificationLayoutController().insertEventToList(listEvents.getSelectionModel().getSelectedItem());
		}
	}

	public void toggleAvailableFilter() {
		availableFilter = !availableFilter;
		updatePredicate();
	}

	public void toggleUpcomingFilter() {
		upcomingFilter = !upcomingFilter;
		updatePredicate();
	}

	public void toggleSalesOngoingFilter() {
		salesOngoingFilter = !salesOngoingFilter;
		updatePredicate();
	}

	public void togglSalesEndedFilter() {
		salesEndedFilter = !salesEndedFilter;
		updatePredicate();
	}

	public Predicate<KideAppEvent> createPredicate() {
		List<Predicate<KideAppEvent>> predicates = new ArrayList<>();

		if (availableFilter) {
			predicates.add(e -> e.getAvailability() > 0);
		}
		if (upcomingFilter) {
			predicates.add(e -> {
				var now = ZonedDateTime.now();
				return now.compareTo(e.getDateSalesFrom()) < 0;
			});
		}
		if (salesOngoingFilter) {
			predicates.add(e -> {
				if (e.getSalesEnded()) {
					return false;
				}
				var now = ZonedDateTime.now();
				return now.compareTo(e.getDateSalesFrom()) > 0;
			});
		}
		if (salesEndedFilter) {
			predicates.add(e -> e.getSalesEnded());
		}

		return predicates.stream().reduce(x -> true, Predicate::and);
	}

	public void updatePredicate() {
		var predicate = createPredicate();
		if (kideAppEvents != null) {
			kideAppEvents.setPredicate(predicate);
			listEvents.getItems().clear();
			listEvents.getItems().addAll(kideAppEvents);
		}

		this.predicate = predicate;

	}

}