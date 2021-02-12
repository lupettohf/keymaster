package hf.keymaster.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection databaseConnection;

	public static Connection getDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (databaseConnection == null) {
				databaseConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/keymaster?useSSL=false",
						"keymaster", "HkGVvELgKztkTBPS");
				//HkGVvELgKztkTBPS
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return databaseConnection;
	}

	public static void closeConnection() throws SQLException {
		databaseConnection.commit();
		databaseConnection.close();
	}
}
