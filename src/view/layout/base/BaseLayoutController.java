package view.layout.base;

import view.Gui;
import view.ILayoutController;

public class BaseLayoutController implements ILayoutController {
	Gui gui;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		return this;
	}

	public void handleShowUpsertLayout() {
		gui.showUpsertLayout();
	}

	public void handleShowNotifationLayout() {
		gui.showNotificationLayout();
	}

	public void handleShowStatisticsLayout() {
		gui.showStatisticsLayout();
	}
}