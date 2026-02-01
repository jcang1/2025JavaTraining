package m5.exercise3;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentsDB extends Exception {

	// private static final String URL =
	// "jdbc:postgresql://127.0.0.1:5432/training_db";
	// private static final String USER = "postgres";
	// private static final String PASSWORD = "postgres";
	private static String sql = null;

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static int rows = 0;

	public void inqStudent() {
		try {
			// 1. Create connection
			connection = ConnectionManager.getConnection();
			System.out.println("Connected Successfully");
			// 2. Create statement
			statement = connection.createStatement();

			// 3. Execute query
			sql = "Select * from students;";

			resultSet = statement.executeQuery(sql);

			ResultSetMetaData meta = resultSet.getMetaData();
			int cols = meta.getColumnCount();

//print header
			for (int i = 1; i <= cols; i++) {
				System.out.print(meta.getColumnLabel(i));
				if (i < cols)
					System.out.print(" | ");
			}
			System.out.println("\n" + "-".repeat(80));

//print rows

			while (resultSet.next()) {
				for (int i = 1; i <= cols; i++) {
					System.out.print(resultSet.getString(i));
					if (i < cols)
						System.out.print(" | ");
				}
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources
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

	public void addStudent(String name, int age, String email) {
		try {
			Connection connection = ConnectionManager.getConnection();
			sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?);";
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, email);
			
			rows = pstmt.executeUpdate(); // not executeQuery()
			//System.out.println("Inserted rows: " + rows);
			if (rows == 1) {
				System.out.println("Student added successfully!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources
			if (rows == 0) {
				System.out.println("Error inserting. Possible duplicate email address.");
			}
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

//	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		inqStudent();
//		addStudent("John Doe", 30, "john_Doe@email.com");
//		inqStudent();
//	}

}
