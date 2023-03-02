package view;

import java.util.List;
import java.util.Optional;

import controller.IAppControllerMToV;
import kide.KideAppEvent;
import model.EventDetailed;

public interface IGui {
	void handleEvents(Optional<List<KideAppEvent>> events);

	void handleEventDetails(EventDetailed event);

	IAppControllerMToV getController();
}
