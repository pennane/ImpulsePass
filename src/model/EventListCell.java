package model;

import database.Mongo;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import kide.KideAppEvent;

public class EventListCell extends ListCell<KideAppEvent> {
	HBox hbox = new HBox();
	Label label = new Label("");
	Pane pane = new Pane();
	Button button = new Button("");
	Image delete = new Image("res/delete.png");
	ImageView imgView = new ImageView(delete);

	public EventListCell() {
		super();
		button.setGraphic(imgView);
		hbox.getChildren().addAll(label, pane, button);
		hbox.setHgrow(pane, Priority.ALWAYS);
		button.setOnAction(event -> removeItem());
	}

	protected void removeItem() {
		Mongo.INSTANCE.deleteEvent(getItem());
		getListView().getItems().remove(getItem());
	}

	@Override
	protected void updateItem(KideAppEvent item, boolean empty) {
		super.updateItem(item, empty);
		setText(null);
		setGraphic(null);

		if (item != null && !empty) {
			label.setText(item.toString());
			setGraphic(hbox);
		}
	}
}
