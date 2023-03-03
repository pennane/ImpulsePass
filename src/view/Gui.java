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
import model.KideAppEventDetails;
import view.layout.base.BaseLayoutController;
import view.layout.notification.NotificationLayoutController;
import view.layout.upsert.UpsertLayoutController;

public class Gui extends Application implements IGui {
	IAppControllerMToV controller;

	BorderPane layoutBase;
	BaseLayoutController layoutBaseController;

	AnchorPane statisticsLayout;
	ILayoutController statisticsLayoutController;

	AnchorPane upsertLayout;
	ILayoutController upsertLayoutController;

	AnchorPane notificationLayout;
	ILayoutController notificationLayoutController;

	@Override
	public void start(Stage primaryStage) throws IOException {
		controller = new AppController(this);

		// Load base layout
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("layout/base/BaseLayout.fxml"));
		layoutBase = (BorderPane) loader.load();
		layoutBaseController = loader.getController();
		layoutBaseController.initialize(this);

		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("layout/statistics/StatisticsLayout.fxml"));
		statisticsLayout = (AnchorPane) loader.load();
		statisticsLayoutController = loader.getController();
		statisticsLayoutController.initialize(this);

		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("layout/upsert/UpsertLayout.fxml"));
		upsertLayout = (AnchorPane) loader.load();
		upsertLayoutController = loader.getController();
		upsertLayoutController.initialize(this);

		loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("layout/notification/NotificationLayout.fxml"));
		notificationLayout = (AnchorPane) loader.load();
		notificationLayoutController = loader.getController();
		notificationLayoutController.initialize(this);

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

	public void showUpsertLayout() {
		this.layoutBase.setCenter(upsertLayout);
	}

	public void showStatisticsLayout() {
		this.layoutBase.setCenter(statisticsLayout);
	}

	public void showNotificationLayout() {
		this.layoutBase.setCenter(notificationLayout);
	}

	public NotificationLayoutController getNotificationLayoutController() {
		return (NotificationLayoutController) notificationLayoutController;
	}

	@Override
	public IAppControllerMToV getController() {
		return controller;
	}

	@Override
	public void handleEvents(Optional<List<KideAppEvent>> events) {
		((UpsertLayoutController) upsertLayoutController).receiveFetchedEvents(events);
	}

	@Override
	public void handleEventDetails(Optional<KideAppEventDetails> event) {
		((NotificationLayoutController) notificationLayoutController).receiveEventDetails(event);
	}
}
