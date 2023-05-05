package view;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Lokalisointi Singleton
 */
public enum Intl {
	INSTANCE;

	private Locale locale;
	private ResourceBundle bundle;

	private Intl() {
		setLocale(Gui.EN_LOCALE);
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		bundle = ResourceBundle.getBundle("bundles.LangBundle", locale);
	}

	public String get(String key) {
		return bundle.getString(key);
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public Locale getLocale() {
		return locale;
	}
}
