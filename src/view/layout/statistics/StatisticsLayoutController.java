package view.layout.statistics;

import view.Gui;
import view.ILayoutController;

public class StatisticsLayoutController implements ILayoutController {
	Gui gui;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;
		return this;
	}
}