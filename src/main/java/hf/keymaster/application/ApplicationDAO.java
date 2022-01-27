package hf.keymaster.application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hf.keymaster.database.ConnectionManager;
import hf.keymaster.user.User;
import hf.keymaster.utils.Generated;
import hf.keymaster.utils.Utils;

@Generated
public class ApplicationDAO {

	/**
	 * Metodo per la creazione di un applicativo, all'interno del database:
	 * @param user l'oggetto della classe User, rappresenta l'utente
	 * @param name rappresenta la Stringa contenente il nome dell'applicazione
	 * @param description rappresenta la Stringa contente la descrizione dell'applicazione
	 * @param website rappresenta la Stringa contenente l'url del sito web
	 * @return Il sistema ritorna vero se ha eseguito con successo la creazione dell'applicazione altrimenti ritorna un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean createApplication(User user, String name, String description, String website) {

		String QUERY = "INSERT INTO applications (ownerid, name, description, website, version, apikey) VALUES (?,?,?,?,?,?)";

		PreparedStatement preparedStatement;

		int OwnerID = user.getID();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, OwnerID);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, description);
			preparedStatement.setString(4, website);
			preparedStatement.setInt(5, 0);
			preparedStatement.setString(6, Utils.generateSecureString(32));

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo getter di una lista di applicativi, di un determinato utente fornito come parametro, presente all'interno del database:
	 * @param user rappresenta l'oggetto della classe User, rappresenta l'utente
	 * @return Il sistema ritorna la lista di Applicazioni associate all'utente passato altrimenti ritorna un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static List<Application> getApplications(User user) {
		String QUERY = "SELECT * FROM applications WHERE ownerid = ?";

		PreparedStatement preparedStatement;
		List<Application> applicationList = new ArrayList<Application>();
		int OwnerID = user.getID();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, OwnerID);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				applicationList.add(new Application(rs.getInt("id"), rs.getInt("ownerid"), rs.getString("name"),
						rs.getString("description"), rs.getString("website"), rs.getInt("version"),
						rs.getString("apikey")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (applicationList.isEmpty()) {
			return null;
		} else {
			return applicationList;
		}
	}

	/**
	 * Metodo per l'eliminazione di un applicativo, presente all'interno del database:
	 * @param ToDelete oggetto della classe Application, rappresenta l'applicativo da rimuovere
	 * @return	Il sistema ritorna vero se l'operazione di eliminazione è avvenuta con successo altrimenti ritorna un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean deleteApplication(Application ToDelete) {
		String QUERY = "UPDATE applications SET name = ?, description = ?, website = ?, version = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(0, ToDelete.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Metodo per la revoca del possesso di un applicativo, presente all'interno del database:
	 * @param App oggetto della classe Application, rappresenta l'applicativo da revocare
	 * @return Il sistema ritorna vero se la revoca dell'applicazione è avvenuta con successo altrimenti ritorna un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean revokeApplication(Application App)
	{
		String QUERY = "UPDATE applications SET ownerid = -1 WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, App.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * Metodo per la modifica di un applicativo, presente all'interno del database:
	 * @param Old oggetto della classe Application, rappresenta l'applicativo da modificare
	 * @param New oggetto della classe Application, rappresenta l'applicativo modificato
	 * @return Il sistema ritorna vero se le modifiche all'applicativo sono state apportate altrimenti ritorna falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean updateApplication(Application Old, Application New) {
		String QUERY = "UPDATE applications SET name = ?, description = ?, website = ?, version = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, New.getName());
			preparedStatement.setString(2, New.getDescription());
			preparedStatement.setString(3, New.getWebsite());
			preparedStatement.setInt(4, New.getVersion());
			preparedStatement.setInt(5, Old.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo che permette di generare l' APIKey associata ad un Applicazione, presente all'interno del database:
	 * @param app oggetto della classe Application, rappresenta l'applicazione per cui generare l'apikey
	 * @return Il sistema ritorna una Stringa contente l'apikey associata all'applicativo altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static String regenerateAPIKey(Application app) {
		String QUERY = "UPDATE applications SET apikey = ? WHERE id = ?";

		PreparedStatement preparedStatement;
		String NewAPI = Utils.generateSecureString(32);
		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, NewAPI);
			preparedStatement.setInt(2, app.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return NewAPI;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo getter di un applicativo, presente all'interno del database:
	 * @param id rappresenta l'identificativo dell'applicazione da estrarre
	 * @return Il sistema ritorna l'applicazione associata al parametro id passato altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static Application getApplication(int id) {
		String QUERY = "SELECT * FROM applications WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new Application(rs.getInt("id"), rs.getInt("ownerid"), rs.getString("name"),
						rs.getString("description"), rs.getString("website"), rs.getInt("version"),
						rs.getString("apikey"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo getter di un applicativo, presente all'interno del database:
	 * @param apikey rappresenta la Stringa contenente l'apikey associata all'applicativo
	 * @return Il sistema restituisce l'applicazione associata al parametro apikey passato; altrimenti lancia un eccezione e restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static Application getApplication(String apikey) {
		String QUERY = "SELECT * FROM applications WHERE apikey = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, apikey);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new Application(rs.getInt("id"), rs.getInt("ownerid"), rs.getString("name"),
						rs.getString("description"), rs.getString("website"), rs.getInt("version"),
						rs.getString("apikey"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
