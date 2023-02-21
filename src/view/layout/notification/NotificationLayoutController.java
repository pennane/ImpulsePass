package view.layout.notification;

import view.Gui;
import view.ILayoutController;

public class NotificationLayoutController implements ILayoutController {
	Gui gui;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		return this;
	}
}