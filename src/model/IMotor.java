package model;

/**
 * Facade malli Moottorille
 */
public interface IMotor {
	void handleEventsRequest();

	void handleEventDetailsRequest(String id);
}
