package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginDatabase {
	public class Database {

		public Database() {
			// TODO Auto-generated constructor stub
		}

		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement preparedStatement = null;
		private ResultSet rS = null;
		private Secrets secrets = new Secrets();

		public void writeToDatabase(User u) throws Exception {
			try {
				// This will load the MySQL driver, each DB has its own driver
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("org.mariadb.jdbc.Driver");
				// Setup the connection with the DB
				connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);

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
				Class.forName("org.mariadb.jdbc.Driver");
				// Setup the connection with the DB
				connect = DriverManager.getConnection(secrets.DatabaseAdress, secrets.username, secrets.password);

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
}
