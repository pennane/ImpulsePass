package view;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import controller.IAppControllerMToV;
import model.kide.KideAppEvent;
import model.kide.KideAppEventDetails;

/**
 * Facade käyttöliittymälle
 */
public interface IGui {
	void handleEvents(Optional<List<KideAppEvent>> events);

	void handleEventDetails(Optional<KideAppEventDetails> event);

	IAppControllerMToV getController();

	void changeLocale(Locale locale);
}
