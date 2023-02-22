package view;

import java.util.List;
import java.util.Optional;

import controller.IAppControllerMToV;
import kide.KideAppEvent;

public interface IGui {
	void handleEvents(Optional<List<KideAppEvent>> events);

	IAppControllerMToV getController();
}
