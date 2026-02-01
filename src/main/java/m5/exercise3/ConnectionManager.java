package m5.exercise3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
	private static final String URL = "jdbc:postgresql://127.0.0.1:5432/training_db";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";

	private ConnectionManager() {
	} // prevent instantiation

	public static Connection getConnection() throws SQLException {
		// Optional: Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
