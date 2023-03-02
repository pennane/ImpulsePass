package controller;

import java.util.List;
import java.util.Optional;

import kide.KideAppEvent;
import model.EventDetailed;

public interface IAppControllerVToM {
	void receiveEvents(Optional<List<KideAppEvent>> events);

	void receiveEventDetails(EventDetailed e);
}
