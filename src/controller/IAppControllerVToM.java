package controller;

import java.util.List;
import java.util.Optional;

import model.kide.KideAppEvent;
import model.kide.KideAppEventDetails;

/**
 * Rajapinta, jolla Model keskustelee Viewin kanssa
 */
public interface IAppControllerVToM {
	void receiveEvents(Optional<List<KideAppEvent>> events);

	void receiveEventDetails(Optional<KideAppEventDetails> e);
}
