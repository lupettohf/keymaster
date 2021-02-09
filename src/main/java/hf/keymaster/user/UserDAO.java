package hf.keymaster.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.digest.DigestUtils;

import hf.keymaster.database.ConnectionManager;

public class UserDAO {

	private static String hashPassword(String Password) {
		return DigestUtils.sha256Hex(Password);
	}

	public static User getUser(int ID) {
		String QUERY = "SELECT * FROM users WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, ID);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("hardwareid"), rs.getBoolean("isdeveloper"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean registerUser(String Username, String Password, String EMail) {
		String QUERY = "INSERT INTO users (username, password, email) VALUES (?,?,?)";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, hashPassword(Password));
			preparedStatement.setString(3, EMail);
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		return false;

	}

	public static boolean SetFirstLastName(User user) {
		String QUERY = "UPDATE users SET firstname = ?, lastname = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setInt(3, user.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		return false;

	}

	public static boolean UpdatePassword(User user) {
		String QUERY = "UPDATE users SET password = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, hashPassword(user.getPassword()));
			preparedStatement.setInt(2, user.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		return false;

	}

	public static int loginUser(String Username, String Password) {
		String QUERY = "SELECT id FROM users WHERE username = ? AND password = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, hashPassword(Password));

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt("id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

}
