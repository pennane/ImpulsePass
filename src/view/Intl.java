package view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Intl {
	INSTANCE;

	private ResourceBundle bundle;

	private Intl() {
		bundle = ResourceBundle.getBundle("bundles.LangBundle", Gui.EN_LOCALE);
	}

	public void setLocale(Locale locale) {
		bundle = ResourceBundle.getBundle("bundles.LangBundle", locale);
	}

	public String get(String key) {
		return bundle.getString(key);
	}

	public ResourceBundle getBundle() {
		return bundle;
	}
}
