package view;

import java.util.List;
import java.util.Optional;

import kide.KideAppEvent;

public interface IGui {
	void handleEvents(Optional<List<KideAppEvent>> events);
}
