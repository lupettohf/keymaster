package hf.keymaster.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * Driver Manager Connection Pool 
 *
 */

public class ConnectionManager {
	private static Connection databaseConnection;
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	
	 static {
	        try {
	            cpds.setDriverClass("com.mysql.jdbc.Driver");
	            cpds.setJdbcUrl("jdbc:mysql://192.168.1.200:3306/keymaster?useSSL=false");
	            cpds.setUser("external");
	            cpds.setPassword("external271166");
	        } catch (PropertyVetoException e) {
	            // handle the exception
	        }
	    }
	    
	    public static Connection getDBConnection() throws SQLException {
	        return cpds.getConnection();
	    }
	    
	
	public static Connection xgetDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (databaseConnection == null) {
				databaseConnection = DriverManager.getConnection("jdbc:mysql://192.168.1.200:3306/keymaster?useSSL=false",
						"external", "external271166");
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
