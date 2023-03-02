package view;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import controller.AppController;
import controller.IAppControllerMToV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kide.KideAppEvent;
import model.EventDetailed;
import view.layout.base.BaseLayoutController;
import view.layout.notification.NotificationLayoutController;
import view.layout.upsert.UpsertLayoutController;

public class Gui extends Application implements IGui {
	IAppControllerMToV controller;

	BorderPane layoutBase;
	BaseLayoutController layoutBaseController;

	AnchorPane currentLayout;
	ILayoutController currentLayoutController;

	@Override
	public void start(Stage primaryStage) throws IOException {
		controller = new AppController(this);

		// Load base layout
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("layout/base/BaseLayout.fxml"));
		layoutBase = (BorderPane) loader.load();
		layoutBaseController = loader.getController();
		layoutBaseController.initialize(this);

		// Show some default layout
		showStatisticsLayout();

		// Finish up initialization
		Scene scene = new Scene(layoutBase);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public IAppControllerMToV getController() {
		return controller;
	}

	public void showLayout(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(fxml));

			currentLayout = (AnchorPane) loader.load();
			currentLayoutController = loader.getController();
			currentLayoutController.initialize(this);

			this.layoutBase.setCenter(currentLayout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUpsertLayout() {
		showLayout("layout/upsert/UpsertLayout.fxml");
	}

	public void showStatisticsLayout() {
		showLayout("layout/statistics/StatisticsLayout.fxml");
	}

	public void showNotificationLayout() {
		showLayout("layout/notification/NotificationLayout.fxml");
	}

	@Override
	public void handleEvents(Optional<List<KideAppEvent>> events) {
		if (currentLayoutController instanceof UpsertLayoutController) {
			((UpsertLayoutController) currentLayoutController).receiveFetchedEvents(events);
		}

	}

	@Override
	public void handleEventDetails(EventDetailed event) {
		if (currentLayoutController instanceof NotificationLayoutController) {
			((NotificationLayoutController) currentLayoutController).receiveEventDetails(event);
		}
	}
}
