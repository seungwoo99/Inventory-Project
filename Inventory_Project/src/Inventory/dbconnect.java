package Inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnect {
	Connection connection = null;
	
	public static Connection connectDB() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnector", "root", "password");
			System.out.println("Connected with the database successfully");
			
			return connection;
		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			
			return null;
		} 
	}
}
