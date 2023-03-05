package view.layout.register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.database.sql.LoginDatabase;
import model.database.sql.User;

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
			User u = new User(usernameInput.getText(), passwordInput.getText());
			if (database.validateNewUser(u)) {
				loginMessage.setText("Username taken!");
			}

			else if (isValidPassword(u.getPassword()) && isValidUsername(u.getUsername()) != true) {
				loginMessage.setText("Registerbutton");
				database.writeToDatabase(u);
			} else {
				loginMessage.setText("Insert valid password or username");
			}
		} else {
			loginMessage.setText("Insert username and password");
		}
	}

	public static boolean isValidPassword(String password) {
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean isValidUsername(String u) {
		String regex = "[^a-z0-9 ]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(u);
		return matcher.matches();
	}
}
