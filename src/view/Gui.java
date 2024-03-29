package view;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import controller.AppController;
import controller.IAppControllerMToV;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.kide.KideAppEvent;
import model.kide.KideAppEventDetails;
import view.layout.base.BaseLayoutController;
import view.layout.notification.NotificationLayoutController;
import view.layout.upsert.UpsertLayoutController;

/**
 * Käyttöliittymä
 */
public class Gui extends Application implements IGui {
	public static final Locale FI_LOCALE = new Locale("fi", "FI");
	public static final Locale EN_LOCALE = new Locale("en", "EN");
	public static final Locale FR_LOCALE = new Locale("fr", "FR");

	Stage primaryStage;

	IAppControllerMToV controller;

	BorderPane layoutBase;
	BaseLayoutController layoutBaseController;

	HBox viewContainer;

	AnchorPane statisticsLayout;
	ILayoutController statisticsLayoutController;

	AnchorPane upsertLayout;
	ILayoutController upsertLayoutController;

	AnchorPane notificationLayout;
	ILayoutController notificationLayoutController;

	AnchorPane settingsLayout;
	ILayoutController settingsLayoutController;

	@Override
	public void start(Stage primaryStage) throws IOException {

		controller = new AppController(this);
		this.primaryStage = primaryStage;

		initalizeViews();

		showStatisticsLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showUpsertLayout() {
		viewContainer.getChildren().clear();
		viewContainer.getChildren().add(upsertLayout);
	}

	public void showStatisticsLayout() {
		viewContainer.getChildren().clear();
		viewContainer.getChildren().add(statisticsLayout);
	}

	public void showNotificationLayout() {
		viewContainer.getChildren().clear();
		viewContainer.getChildren().add(notificationLayout);
	}

	public void showSettingsLayout() {
		viewContainer.getChildren().clear();
		viewContainer.getChildren().add(settingsLayout);
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

	private void initalizeViews() {
		try {

			// Load base layout

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("layout/base/BaseLayout.fxml"));
			loader.setResources(Intl.INSTANCE.getBundle());

			layoutBase = (BorderPane) loader.load();

			layoutBaseController = loader.getController();
			layoutBaseController.initialize(this);

			viewContainer = (HBox) layoutBase.getCenter();

			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("layout/statistics/StatisticsLayout.fxml"));
			loader.setResources(Intl.INSTANCE.getBundle());
			statisticsLayout = (AnchorPane) loader.load();
			statisticsLayoutController = loader.getController();
			statisticsLayoutController.initialize(this);

			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("layout/upsert/UpsertLayout.fxml"));
			loader.setResources(Intl.INSTANCE.getBundle());
			upsertLayout = (AnchorPane) loader.load();
			upsertLayoutController = loader.getController();
			upsertLayoutController.initialize(this);

			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("layout/notification/NotificationLayout.fxml"));
			loader.setResources(Intl.INSTANCE.getBundle());
			notificationLayout = (AnchorPane) loader.load();
			notificationLayoutController = loader.getController();
			notificationLayoutController.initialize(this);

			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("layout/settings/SettingsLayout.fxml"));
			loader.setResources(Intl.INSTANCE.getBundle());
			settingsLayout = (AnchorPane) loader.load();
			settingsLayoutController = loader.getController();
			settingsLayoutController.initialize(this);

			Scene scene = new Scene(layoutBase);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeLocale(Locale locale) {
		Intl.INSTANCE.setLocale(locale);
		initalizeViews();
		showSettingsLayout();
	}

}
