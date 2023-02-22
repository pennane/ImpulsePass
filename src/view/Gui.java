package view;

import java.util.List;
import java.util.Optional;

import controller.AppController;
import controller.IAppControllerMToV;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kide.KideAppEvent;

public class Gui extends Application implements IGui {
	List<KideAppEvent> events;
	IAppControllerMToV controller;
	Button button;

	@Override
	public void start(Stage primaryStage) {
		controller = new AppController(this);

		button = new Button();
		button.setText("Hae tapahtumia");

		BorderPane root = new BorderPane(button);
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Pyydetään tapahtumia moottorilta..");
				controller.requestEvents();
			}
		});

	}

	@Override
	public void handleEvents(Optional<List<KideAppEvent>> events) {
		System.out.println("Sain vastauksen");
		if (events.isPresent()) {
			var foundEvents = events.get();
			System.out.println("Sain" + foundEvents.size() + " tapahtumaa");

			button.setText("Löytyi " + foundEvents.size() + " tapahtumaa");
			this.events = foundEvents;

			return;
		}
		System.out.println("Ei tapahatumia.");

		button.setText("Tapahtumien hakeminen epäonnistui;");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
