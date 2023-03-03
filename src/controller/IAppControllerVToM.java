package controller;

import java.util.List;
import java.util.Optional;

import kide.KideAppEvent;
import model.KideAppEventDetails;

public interface IAppControllerVToM {
	void receiveEvents(Optional<List<KideAppEvent>> events);

	void receiveEventDetails(Optional<KideAppEventDetails> e);
}
