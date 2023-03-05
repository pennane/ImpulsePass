package view.layout.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.database.sql.LoginDatabase;
import model.database.sql.User;
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
	@FXML
	private Button registerButton;

	public void loginButtonAction(ActionEvent e) throws Exception {
		LoginDatabase database = new LoginDatabase();
		if (usernameInput.getText().isBlank() == false && passwordInput.getText().isBlank() == false) {
			User u = new User(usernameInput.getText(), passwordInput.getText());
			if (database.validate(u)) {
				loginMessage.setText("login ok");
			}
		} else {
			loginMessage.setText("Insert username and password");
		}
	}

	public void registerButtonAction(ActionEvent e) {
		loginMessage.setText("Registerbutton");
	}

	@Override
	public ILayoutController initialize(Gui gui) {
		// TODO Auto-generated method stub
		return null;
	}

}
