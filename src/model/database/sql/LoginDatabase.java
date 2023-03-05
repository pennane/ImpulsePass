package model.database.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import config.Config;

public class LoginDatabase {

	public LoginDatabase() {
		// TODO Auto-generated constructor stub
	}

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rS = null;

	public void writeToDatabase(User u) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName(Config.get("SQL_DATABASE_DRIVER"));
			// Setup the connection with the DB
			connect = DriverManager.getConnection(Config.get("SQL_DATABASE_ADDRESS"),
					Config.get("SQL_DATABASE_USERNAME"), Config.get("SQL_DATABASE_PASSWORD"));

			preparedStatement = connect.prepareStatement("insert into  Users values (default, ?,?)");

			// Parameters start with 1

			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public User[] getAllFromDatabase() throws Exception {
		try {
			// This will load the Mariadb driver, each DB has its own driver
			Class.forName(Config.get("SQL_DATABASE_DRIVER"));
			// Setup the connection with the DB
			connect = DriverManager.getConnection(Config.get("SQL_DATABASE_ADDRESS"),
					Config.get("SQL_DATABASE_USERNAME"), Config.get("SQL_DATABASE_PASSWORD"));

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			rS = statement.executeQuery("select * from Users");
			ArrayList<User> dataArray = new ArrayList<>();
			while (rS.next()) {
				dataArray.add(new User(rS.getString("Username"), rS.getString("Password")));
			}
			User[] returnArray = new User[dataArray.size()];
			for (int i = 0; i < dataArray.size(); i++) {
				returnArray[i] = dataArray.get(i);
			}
			return returnArray;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public Boolean validate(User u) throws Exception {

		User[] dataArray = getAllFromDatabase();
		var username = u.getUsername();
		var password = u.getPassword();
		for (int i = 0; i < dataArray.length; i++) {
			if (dataArray[i].getUsername().equals(username) && dataArray[i].getPassword().equals(password)) {
				return true;
			}
		}
		return false;

	}

	public Boolean validateNewUser(User u) throws Exception {

		User[] dataArray = getAllFromDatabase();
		var username = u.getUsername();
		for (int i = 0; i < dataArray.length; i++) {
			if (dataArray[i].getUsername().equals(username)) {
				return true;
			}
		}
		return false;

	}

	private void close() {
		try {
			if (rS != null) {
				rS.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
