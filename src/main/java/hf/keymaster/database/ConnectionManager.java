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
	            cpds.setMinPoolSize(5);
	            cpds.setMaxPoolSize(10);
	            cpds.setMaxStatements(100);
	        } catch (PropertyVetoException e) {

	        }
	    }
	    
	    public static Connection getDBConnection() throws SQLException {
	        return cpds.getConnection();
	    }
	    
	public static void closeConnection() throws SQLException {
		databaseConnection.commit();
		databaseConnection.close();
	}
}
