package hf.keymaster.license;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hf.keymaster.application.Application;
import hf.keymaster.database.ConnectionManager;

public class LicenseDAO {
	
	public static boolean createLicense(Application app, String Name, String Description, int Duration, int Type)
	{
		String QUERY = "INSERT INTO licenses (appid, name, description, duration, type) VALUES (?,?,?,?,?)";
		
		PreparedStatement preparedStatement; 
		
		int AppID = app.getID();
			
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setInt(1, AppID);
			preparedStatement.setString(2, Name);
			preparedStatement.setString(3, Description);
			preparedStatement.setInt(4, Duration);
			preparedStatement.setInt(5, Type);
			
			if(preparedStatement.executeUpdate() == 1) { return true; } 
					
		}catch(Exception e) { e.printStackTrace(); }
		
		return false;	
	}
	
	public static License GetLicense(int id)
	{
		String QUERY = "SELECT * FROM licenses WHERE id = ?";
		
		PreparedStatement preparedStatement; 
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				return new License(
						rs.getInt("id"), 
						rs.getInt("appid"), 
						rs.getString("name"), 
						rs.getString("description"), 
						rs.getInt("duration"), 
						rs.getInt("type")
				);
			} 
					
		}catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
	
	public static List<License> getLicenses(Application app)
	{
		String QUERY = "SELECT * FROM licenses WHERE appid = ?";
		
		PreparedStatement preparedStatement; 
		List<License> licenseList = new ArrayList<License>();
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setInt(1, app.getID());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				 licenseList.add(new License(
							rs.getInt("id"), 
							rs.getInt("appid"), 
							rs.getString("name"), 
							rs.getString("description"), 
							rs.getInt("duration"), 
							rs.getInt("type")
				));
			} 
					
		}catch(Exception e) { e.printStackTrace(); }
		
		if(licenseList.isEmpty()) { return null; }
		else { return licenseList; }
	}

	public static boolean updateLicense(License Old, License New)
	{
		String QUERY = "UPDATE licenses SET name = ?, description = ?, duration = ?, type = ? WHERE id = ?";
		
		PreparedStatement preparedStatement; 
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setString(1, New.getName());
			preparedStatement.setString(2, New.getDescription());
			preparedStatement.setInt(3, New.getDuration());
			preparedStatement.setInt(4, New.getType());
			preparedStatement.setInt(5, Old.getID());
			
			if(preparedStatement.executeUpdate() == 1) { return true; }
		}catch(Exception e) { e.printStackTrace(); }
		
		return false;
	}
}
