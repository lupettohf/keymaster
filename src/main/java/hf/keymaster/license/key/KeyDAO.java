package hf.keymaster.license.key;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hf.keymaster.database.ConnectionManager;
import hf.keymaster.license.License;
import hf.keymaster.utils.Generated;
import hf.keymaster.utils.Utils;

@Generated
public class KeyDAO {

	/**
	 * Metodo per la creazione di un chiave, nel database:
	 * @param license oggetto della classe License, rappresenta la licenza 
	 * @pre license è un oggetto License valido non NULL
	 * @post viene resa persistente la chiave nel database
	 * @return Il sistema restituisce vero se la creazione della chiave è avvenuta con successo altrimenti restituisce un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean createKey(License license) {
		String QUERY = "INSERT INTO `keys` (licenseid, licensekey, redeemed) VALUES (?,?,?)";

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

	/**
	 * Metodo getter di una chiave, presente nel database:
	 * @param id rappresenta l'identificativo in formato int
	 * @pre id è un int valido non NULL
	 * @post se la query trova corrispondenze l'int non è vuoto
	 * @return Il sistema ritorna una chiave associata al parametro id passato altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
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

	/**
	 * Metodo getter di una chiave,presente nel database:
	 * @param key rapprenta il valore della chiave in formato Stringa
	 * @pre key è una String valida non NULL
	 * @post se la query trova corrispondenze Key non è vuota
	 * @return Il sistema ritorna la chiave associata al parametro key passato altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static Key getKey(String key) {
		String QUERY = "SELECT * FROM `keys` WHERE licensekey = ?";

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

	/**
	 * Metodo che restituisce il numero di chiavi associate ad una licenza, presente all'interno del database:
	 * @param license oggetto della classe License, rappresenta la licenza
	 * @pre license è un oggetto License valido non NULL
	 * @post se la query trova corrispondenze l'int non è vuoto
	 * @return Il sistema restituisce un valore intero se vi sono occorrenze di chiavi della licenza passata come parametro altrimenti restituisce un valore non intero;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static int countKeys(License license) {
		String QUERY = "SELECT COUNT(*) FROM `keys` WHERE licenseid = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, license.getID());

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * Metodo che restituisce il numero di chiavi revocate associate ad una licenza, presente all'interno del database:
	 * @param license oggetto della classe License, rappresenta la licenza
	 * @pre license è un oggetto License valido non NULL
	 * @post se la query trova corrispondenze l'int non è vuoto
	 * @return Il sistema ritorna un valore intero se vi sono occorrenze di chiavi revocarte della licenza passata come parametro altrimenti restituisce un valore non intero;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static int countRedemedKeys(License license) {
		String QUERY = "SELECT COUNT(*) FROM `keys` WHERE licenseid = ? AND redeemed = 1";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, license.getID());

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * Metodo getter che restituisce le chiavi associate ad una licenza ancora non revocata, presente all'interno del database:
	 * @param license oggetto della classe License, rappresenta la licenza
	 * @param OnlyNonRedeemed valore booleano, rappresenta lo stato di una licenza (0)
	 * @pre license è un oggetto License non NULL, OnlyNonredeemed è un valore booleano valido non NULL
	 * @post se la query trova corrispondenze la lista di Key non è vuota
	 * @return Il sistema restituisce una lista di chiavi non revocate associate alla licenza passata come parametro altrimenti restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static List<Key> getKeys(License license, boolean OnlyNonRedeemed) {
		String QUERY = "SELECT * FROM `keys` WHERE licenseid = ?";

		if (OnlyNonRedeemed) {
			QUERY = "SELECT * FROM `keys` WHERE licenseid = ? AND redeemed = 0";
		}

		PreparedStatement preparedStatement;
		List<Key> keyList = new ArrayList<Key>();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, license.getID());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				keyList.add(new Key(rs.getInt("id"), rs.getInt("licenseid"), rs.getString("licensekey"),
						rs.getBoolean("redeemed")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return keyList;
	}

	/**
	 * Metodo getter che restituisce le chiavi associate ad una licenza ancora revocata in un range definito,presente all'interno del database:
	 * @param license oggetto della classe License, rappresenta la licenza
	 * @param OnlyNonRedeemed valore booleano, rappresenta lo stato di revoca di una licenza (0)
	 * @param offset rappresenta il range di chiavi 
	 * @pre license è un oggetto License valido non NULL, OnlyNonRedeemed è un booleano valido non NULL, offset è un int valido non NULL
	 * @post se la query trova corrispondenze la lista di Key non è vuota
	 * @return Il sistema ritorna una lista di chiavi non revocate associate alla licenza passato come parametro nell'offset definito; altrimenti lancia un eccezione e restituisce un valore nullo;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static List<Key> getKeys(License license, boolean OnlyNonRedeemed, int offset) {
		String QUERY = "SELECT * FROM `keys` WHERE licenseid = ? LIMIT ?,10";

		if (OnlyNonRedeemed) {
			QUERY = "SELECT * FROM `keys` WHERE licenseid = ? AND redeemed = 0 LIMIT ?,10";
		}

		PreparedStatement preparedStatement;
		List<Key> keyList = new ArrayList<Key>();

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setInt(1, license.getID());
			preparedStatement.setInt(2, offset);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				keyList.add(new Key(rs.getInt("id"), rs.getInt("licenseid"), rs.getString("licensekey"),
						rs.getBoolean("redeemed")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return keyList;
	}

	/**
	 * Metodo che permette l'attivazione di una chiave, presente all'interno del database:
	 *
	 * @param id rappresenta l'identificativo in valore int
	 * @pre id è un int valido non NULL
	 * @post vengono modificati i dati persistenti nel database
	 * @return Il sistema restituisce il valore vero se la chiave relativa all'id passato è stata attivata con successo; altrimenti lancia un eccezione e restituisce un valore falso;
	 * @throws Exception lancia un eccezione e la stampa
	 */
	
	public static boolean activateKey(int id) {
		String QUERY = "UPDATE `keys` SET redeemed = ? WHERE id = ?";

		PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionManager.getDBConnection().prepareStatement(QUERY);

			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, id);

			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}
