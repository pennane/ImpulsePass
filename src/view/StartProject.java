package view;

import config.Config;

/**
 * The main class of the project
 */
public class StartProject {
	public static void main(final String[] args) {
		System.out.println(Config.get("OOGA"));
		Gui.main(args);
	}
}
