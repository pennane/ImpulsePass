package view.layout.settings;

import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import view.Gui;
import view.ILayoutController;
import view.Intl;

public class SettingsLayoutController implements ILayoutController {
	private Gui gui;

	@FXML
	private ComboBox<Locale> languageComboBox;

	private ObservableList<Locale> languageList;

	@Override
	public ILayoutController initialize(Gui gui) {
		this.gui = gui;

		// Initialize the language list
		languageList = FXCollections.observableArrayList(Gui.EN_LOCALE, Gui.FR_LOCALE, Gui.FI_LOCALE);

		// Set the language list in the ComboBox
		languageComboBox.setItems(languageList);

		// Set the default selection to the current locale
		languageComboBox.getSelectionModel().select(Intl.INSTANCE.getLocale());

		languageComboBox.setConverter(new StringConverter<Locale>() {
			@Override
			public String toString(Locale locale) {
				if (locale.equals(Gui.EN_LOCALE)) {
					return "English";
				} else if (locale.equals(Gui.FR_LOCALE)) {
					return "Français";
				} else if (locale.equals(Gui.FI_LOCALE)) {
					return "Suomi";
				}
				return null;
			}

			@Override
			public Locale fromString(String s) {
				// We don't need to implement this method
				return null;
			}
		});

		// Add a listener to change the locale when the user selects a language
		languageComboBox.setOnAction(event -> {
			Locale selectedLocale = languageComboBox.getSelectionModel().getSelectedItem();
			gui.changeLocale(selectedLocale);
		});

		return this;
	}
}