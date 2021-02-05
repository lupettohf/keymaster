package application;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hf.keymaster.database.ConnectionManager;
import user.User;
import utils.Utils;

public class ApplicationDAO {
	
	public static boolean createApplication(User user, String name, String description, String website, int version)
	{
		
		String QUERY = "INSERT INTO applications (ownerid, name, description, website, version) VALUES (?,?,?,?,?)";
		
		PreparedStatement preparedStatement; 
		
		int OwnerID = user.getID();
			
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setInt(1, OwnerID);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, description);
			preparedStatement.setString(4, website);
			preparedStatement.setInt(5, version);
			preparedStatement.setString(6, Utils.generateSecureString(32));
			
			if(preparedStatement.executeUpdate() == 1) { return true; } 
					
		}catch(Exception e) { e.printStackTrace(); }
		
		return false;
	}
	
	public static List<Application> getApplications(User user)
	{
		String QUERY = "SELECT * FROM applications WHERE ownerid = ?";
		
		PreparedStatement preparedStatement; 
		List<Application> applicationList = new ArrayList<Application>();
		int OwnerID = user.getID();
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setInt(1, OwnerID);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				 applicationList.add(new Application(
						rs.getInt("id"), 
						rs.getInt("ownerid"), 
						rs.getString("name"), 
						rs.getString("description"), 
						rs.getString("website"), 
						rs.getInt("version"), 
						rs.getString("apikey")
				));
			} 
					
		}catch(Exception e) { e.printStackTrace(); }
		
		if(applicationList.isEmpty()) { return null; }
		else { return applicationList; }
	}
	
	public static boolean updateApplication(Application Old, Application New)
	{
		String QUERY = "UPDATE application SET (name, description, website, version) VALUES (?,?,?,?) WHERE id = ?";
		
		PreparedStatement preparedStatement; 
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setString(1, New.getName());
			preparedStatement.setString(2, New.getDescription());
			preparedStatement.setString(3, New.getWebsite());
			preparedStatement.setInt(4, New.getVersion());
			preparedStatement.setInt(5, Old.getID());
			
			if(preparedStatement.executeUpdate() == 1) { return true; }
		}catch(Exception e) { e.printStackTrace(); }
		
		return false;
	}
	
	public static String regenerateAPIKey(Application app)
	{
		String QUERY = "UPDATE application SET (apikey) VALUES (?) WHERE id = ?";
		
		PreparedStatement preparedStatement; 
		String NewAPI = Utils.generateSecureString(32);
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setString(1, NewAPI);
			preparedStatement.setInt(2, app.getID());
			
			if(preparedStatement.executeUpdate() == 1) { return NewAPI; }
		}catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
	
	public static Application getApplication(int id)
	{
		String QUERY = "SELECT * FROM applications WHERE id = ?";
		
		PreparedStatement preparedStatement; 
		
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);
			
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next())
			{
				return new Application(
						rs.getInt("id"), 
						rs.getInt("ownerid"), 
						rs.getString("name"), 
						rs.getString("description"), 
						rs.getString("website"), 
						rs.getInt("version"), 
						rs.getString("apikey")
				);
			} 
					
		}catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
}
