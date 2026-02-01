package m5.activity2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection extends Exception {
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/training_db";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";

	public static void main(String[] args) {
		System.out.println("Connecting to PostgreSQL...");

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// 1. Create connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected Successfully");

			// 2. Create statement
			statement = connection.createStatement();

			// 3. Execute query
			//String sql = "SELECT id, name, email from students";

			//resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. Close resources
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
