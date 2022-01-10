package hf.keymaster.license.owned;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import hf.keymaster.database.ConnectionManager;
import hf.keymaster.license.License;
import hf.keymaster.license.LicenseDAO;
import hf.keymaster.license.key.Key;
import hf.keymaster.license.key.KeyDAO;
import hf.keymaster.user.User;

public class OwnedLicenseDAO {

	public static boolean activateLicense(User user, License license, Key key) {		
		Key _tf = null;
		if(key.getID() == -1)
		{
			_tf = key;
		} else {
			_tf = KeyDAO.getKey(key.getLicenseKey());
		}
		long now = Instant.now().toEpochMilli();

		if (_tf == null) {
			return false;
		}
		if (_tf.isRedeemed() && _tf.getID() != -1) {
			return false;
		}

		String QUERY = "INSERT INTO ownedlicenses (userid, licenseid, keyid, activationepoch) VALUES (?,?,?,?)";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, user.getID());
			preparedStatement.setInt(2, license.getID());
			preparedStatement.setInt(3, _tf.getID());
			preparedStatement.setLong(4, now);

			if (preparedStatement.executeUpdate() == 1) {
				if(_tf.getID() != -1)
				{
					KeyDAO.activateKey(key.getID());
				}
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public static boolean deleteLicense(User user, License license) {		
		
		String QUERY = "DELETE FROM ownedlicenses WHERE userid = ? AND id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, user.getID());
			preparedStatement.setInt(2, license.getID());

			if (preparedStatement.executeUpdate() == 1) {			
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static OwnedLicense getOwnedLicense(int id) {
		String QUERY = "SELECT * FROM ownedlicenses WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new OwnedLicense(rs.getInt("id"), rs.getInt("userid"), rs.getInt("licenseid"),
						rs.getInt("keyid"), rs.getLong("activationepoch"), rs.getString("hardwareid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<OwnedLicense> getOwnedLicenses(User user) {
		String QUERY = "SELECT * FROM ownedlicenses WHERE userid = ?";

		PreparedStatement preparedStatement;
		List<OwnedLicense> ownedLicense = new ArrayList<OwnedLicense>();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, user.getID());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ownedLicense.add(new OwnedLicense(rs.getInt("id"), rs.getInt("userid"), rs.getInt("licenseid"),
						rs.getInt("keyid"), rs.getLong("activationepoch"), rs.getString("hardwareid")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (ownedLicense.isEmpty()) {
			return null;
		} else {
			return ownedLicense;
		}
	}
	
	public static boolean setHardwareID(OwnedLicense owned, String HardwareID)
	{
		String QUERY = "UPDATE ownedlicenses SET hardwareid = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, HardwareID);
			preparedStatement.setInt(2, owned.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean renewLicense(OwnedLicense owned, Key key) {
		String QUERY = "UPDATE ownedlicenses SET activationepoch = ? WHERE id = ?";
		
		Key _tf = KeyDAO.getKey(key.getLicenseKey());
		OwnedLicense _ow = getOwnedLicense(owned.getID());
		long now = Instant.now().toEpochMilli();
		PreparedStatement preparedStatement;
		
		if (!isActive(owned)) {
			if (_tf.isRedeemed()) {
				return false;
			}
			if (_tf.getLicenseID() != _ow.getLicenseID()) {
				return false;
			}
			try {
				preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

				preparedStatement.setLong(1, now);
				preparedStatement.setInt(2, owned.getID());

				if (preparedStatement.executeUpdate() == 1) {
					KeyDAO.activateKey(key.getID());
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public static boolean isActive(OwnedLicense owned) {
		OwnedLicense _ow = getOwnedLicense(owned.getID());
		License _lic = LicenseDAO.GetLicense(_ow.getLicenseID());
		long now = Instant.now().toEpochMilli();
		int elapsed = (int) TimeUnit.MILLISECONDS.toDays(now - owned.getActivationEpoch());
		
		if(elapsed > _lic.getDuration())
		{
			return false;
		} else {
			return true;
		}
	}
}
