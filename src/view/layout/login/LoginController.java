package view.layout.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.Gui;
import view.ILayoutController;

public class LoginController implements ILayoutController {
	@FXML
	private Button loginButton;
	@FXML
	private Label loginMessage;
	@FXML
	private TextField usernameInput;
	@FXML
	private PasswordField passwordInput;

	public void loginButtonAction(ActionEvent e) {

		if (usernameInput.getText().isBlank() == false && passwordInput.getText().isBlank() == false) {
			loginMessage.setText("Login clicked");
		} else {
			loginMessage.setText("Insert username and password");
		}
	}

	@Override
	public ILayoutController initialize(Gui gui) {
		// TODO Auto-generated method stub
		return null;
	}

}
