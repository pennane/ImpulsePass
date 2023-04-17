package view.layout.settings;

import view.Gui;
import view.ILayoutController;

public class SettingsLayoutController implements ILayoutController {
	Gui gui;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;

		return this;
	}

	public void handleSetFrench() {
		gui.changeLocale(Gui.FR_LOCALE);
	}

	public void handleSetFinnish() {
		gui.changeLocale(Gui.FI_LOCALE);
	}

	public void handleSetEnglish() {
		gui.changeLocale(Gui.EN_LOCALE);
	}

}