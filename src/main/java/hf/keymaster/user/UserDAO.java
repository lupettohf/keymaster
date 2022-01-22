package hf.keymaster.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.digest.DigestUtils;

import hf.keymaster.database.ConnectionManager;

public class UserDAO {

	/**
	 * Metodo hash che permette di formattare la password in chiave hash256:
	 * @param Password rappresenta la password da codificare
	 * @pre   Password è una stringa valida non NULL
	 * @return Il sistema restituisce la password codificata in sha256
	 */
	
	private static String hashPassword(String Password) {
		return DigestUtils.sha256Hex(Password);
	}

	/**
	 * Metodo getter che restituisce un utente presente presente nel database:
	 * @param ID valore intero, rappresenta l'identificativo 
	 * @pre ID è un Int valido e non NULL
	 * @return Il sistema restituisce l'utente associato al parametro id passato altrimenti restiruisce un valore nullo;
	 * @throws Exception lancia un eccezione 
	 */
	
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
						rs.getBoolean("isdeveloper"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo getter che restituisce un utente presente nel database:
	 * 
	 * @param username valore Stringa, rappresenta il nome dell'utente
	 * @pre username è una String valida e non NULL
	 * @post se la query trova corrispondenze l'User non è vuoto 
	 * @return Il sistema restituisce l'utente associato al parametro username passato altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione 
	 */
	
	public static User getUserByName(String username) {
		String QUERY = "SELECT * FROM users WHERE username = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("email"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getBoolean("isdeveloper"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Metodo CRUD per la creazione di un nuovo utente nel database:
	 * @param Username valore Stringa, rappresenta il nome dell'utente
	 * @param Password valore Stringa, rappresenta la password dell'utente
	 * @param EMail valore Stringa, rappresenta l'e-mail dell'utente
	 * @pre   Username, Password ed Email sono String valide e non NULL
	 * @post  dati resi persistenti nel database
	 * @return Il sistema restituisce vero se la creazione dell'utente è avvenuta con successo altrimenti restituisce un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
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
	
	/**
	 * Metodo che permette ad un utente presente nel database di diventare un utente sviluppatore:
	 * @param user oggetto della classe User, rappresenta l'utente
	 * @pre user è un User valido e non NULL
	 * @post dati resi persistenti nel database
	 * @return Il sistema restituisce vero se l'upgrade a utente sviluppatore è avvenuto con successo altrimenti restituisce un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean UpgradeUser(User user)
	{
		String QUERY = "UPDATE users SET isdeveloper = 1 WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, user.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Metodo setter per la modifica del nome e del cognome relativo ad un utente presente nel database:
	 * @param user oggetto della classe User, rappresenta l'utente
	 * @pre user è un oggetto User valido non NULL
	 * @post dati resi persistenti nel database
	 * @return Il sistema restituisce vero se la modifica dei campi nome e cognome è avvenuta correttamente altrimenti restituisce un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
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
		return false;

	}

	/**
	 * Metodo che permette la modifica della password di un utente presente nel database:
	 * @param user oggetto della classe User, rappresenta l'utente
	 * @return Il sistema restituisce vero se la modifica del campo password è avvenuta con successo altrimenti restituisce un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
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

	/**
	 * Metodo che permette la verifica del login da parte di utente presente nel database:
	 * @param Username valore Stringa, rappresenta l'username dell'utente
	 * @param Password valore Stringa, rappresenta la password dell'utente
	 * @pre   Username e Password sono String valide e non NULL
	 * @post  se la query trova delle corrispondenze l'interno non è vuoto
	 * @return Il sistema restituisce un valore intero associato all'identificativo dell'utente in caso di successo altrimenti restituisce un valore non intero;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
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
	
	
	public static void DestroyTestUser()
	{
		String QUERY = "DELETE FROM users WHERE username = 'testuser02'";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
