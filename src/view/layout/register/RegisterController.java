package view.layout.register;

import database.LoginDatabase;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {
	@FXML
	private TextField usernameInput;
	@FXML
	private TextField passwordInput;
	@FXML
	private Label loginMessage;

	public void registerButtonAction(ActionEvent e) throws Exception {
		LoginDatabase database = new LoginDatabase();

		if (usernameInput.getText().isBlank() == false && passwordInput.getText().isBlank() == false) {
			loginMessage.setText("Registerbutton");
			User u = new User(usernameInput.getText(), passwordInput.getText());
			database.writeToDatabase(u);
		} else {
			loginMessage.setText("Insert username and password");
		}
	}
}
