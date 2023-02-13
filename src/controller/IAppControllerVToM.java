package controller;

import java.util.List;
import java.util.Optional;

import kide.KideAppEvent;

public interface IAppControllerVToM {
	void receiveEvents(Optional<List<KideAppEvent>> events);
}
