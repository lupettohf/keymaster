package hf.keymaster.license;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hf.keymaster.application.Application;
import hf.keymaster.database.ConnectionManager;

public class LicenseDAO {

	/**
	 * Metodo per la creazione di una licenza, all'interno del database:
	 * @param app oggetto della classe Application, rappresenta l'applicativo
	 * @param Name rappresenta il nome della licenza
	 * @param Description rappresenta la descrizione della licenza
	 * @param Duration rappresenta la durata della licenza
	 * @param Type rappresenta il tipo di licenza
	 * @pre	  app è un oggetto Application valido, Name, Description sono String valide, Durata è un intero valido, Type è un booleano valido non NULL
	 * @post  viene resa persistente la licenza nel database 
	 * @return Il sistema ritorna vero se la licenza è stata creata correttamente altrimenti restituisce un valore falso;
	 */
	
	public static boolean createLicense(Application app, String Name, String Description, int Duration, int Type) {
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

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Metodo getter di una licenza, presente all'interno del database:
	 * @param id rappresenta l'identificativo della licenza
	 * @pre id è un int valido non NULL 
	 * @post se la query trova delle corrispondenze l'oggetto License di ritorno non è NULL
	 * @return Il sistema ritorna la licenza associata al parametro id passato altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione
	 */
	
	public static License GetLicense(int id) {
		String QUERY = "SELECT * FROM licenses WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return new License(rs.getInt("id"), rs.getInt("appid"), rs.getString("name"),
						rs.getString("description"), rs.getInt("duration"), rs.getInt("type"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Metodo getter di una lista di licenze, presenti all'interno del database:
	 * @param app oggetto della classe Application, rappresenta l'applicativo;
	 * @pre app è oggetto Application valido non NULL
	 * @post se la query trove corrispondeze la liste delle licenze è piena
	 * @return Il sistema ritorna una lista di licenze associate all'applicativo passato come parametro; altrimenti lancia un eccezione e restituisce un valore nullo;
	 */
	
	public static List<License> getLicenses(Application app) {
		String QUERY = "SELECT * FROM licenses WHERE appid = ?";

		PreparedStatement preparedStatement;
		List<License> licenseList = new ArrayList<License>();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, app.getID());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				licenseList.add(new License(rs.getInt("id"), rs.getInt("appid"), rs.getString("name"),
						rs.getString("description"), rs.getInt("duration"), rs.getInt("type")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (licenseList.isEmpty()) {
			return null;
		} else {
			return licenseList;
		}
	}

	/**
	 * Metodo per la modifica di una licenza, all'interno del database:
	 *
	 * @param Old oggetto della classe License, rappresenta la licenza da modificare
	 * @param New oggetto della classe License, rappresenta la licenza modificata
	 * @pre Old e New sono licenze valide non NULL
	 * @return Il sistema ritorna vero se la modifica della licenza è avvenuta con successo; altrimenti lancia un eccezione e restituisce un valore falso;
	 */
	
	public static boolean updateLicense(License Old, License New) {
		String QUERY = "UPDATE licenses SET name = ?, description = ?, duration = ?, type = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setString(1, New.getName());
			preparedStatement.setString(2, New.getDescription());
			preparedStatement.setInt(3, New.getDuration());
			preparedStatement.setInt(4, New.getType());
			preparedStatement.setInt(5, Old.getID());

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
