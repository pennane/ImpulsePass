package view.layout.notification;

import java.util.List;

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
	static final String imgUrlPrefix = "https://portalvhdsp62n0yt356llm.blob.core.windows.net/bailataan-mediaitems/";
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
		Image logo = new Image(imgUrlPrefix + e.getMediaFilename());
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
}