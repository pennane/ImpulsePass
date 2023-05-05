package controller;

/**
 * Rajapinta, jolla View keskustelee Modelin kanssa
 */
public interface IAppControllerMToV {
	void requestEvents();

	public void requestEventDetails(String id);
}
