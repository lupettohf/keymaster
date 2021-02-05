package hf.keymaster.license.key;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hf.keymaster.application.Application;
import hf.keymaster.database.ConnectionManager;
import hf.keymaster.license.License;
import hf.keymaster.utils.Utils;

public class KeyDAO {

	public static boolean createKey(License license) {
		String QUERY = "INSERT INTO keys (licenseid, licensekey, redeemed) VALUES (?,?,?)";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, license.getID());
			preparedStatement.setString(2, Utils.generateSecureString(16));
			preparedStatement.setBoolean(3, false);

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static Key getKey(int id) {
		String QUERY = "SELECT * FROM keys WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new Key(rs.getInt("id"), rs.getInt("licenseid"), rs.getString("licensekey"),
						rs.getBoolean("redeemed"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static Key getKey(String key) {
		String QUERY = "SELECT * FROM keys WHERE licensekey = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, key);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new Key(rs.getInt("id"), rs.getInt("licenseid"), rs.getString("licensekey"),
						rs.getBoolean("redeemed"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static List<Key> getKeys(License license)
	{
		String QUERY = "SELECT * FROM key WHERE licenseid = ?";

		PreparedStatement preparedStatement; 
		List<Key> keyList = new ArrayList<Key>();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, license.getID());

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
			{
				keyList.add(
						new Key(
								rs.getInt("id"), 
								rs.getInt("licenseid"), 
								rs.getString("licensekey"), 
								rs.getBoolean("redeemed")
								));
			} 
		} catch (Exception e) { e.printStackTrace(); }
		
		return keyList;
	}

	public static boolean activateKey(int id)
	{/*TODO da finire*/ return false;}
	
}
 