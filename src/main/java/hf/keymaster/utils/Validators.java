package hf.keymaster.utils;

import org.apache.commons.validator.UrlValidator;

/**
 * 
 * Classe pubblica dell'oggetto Validators
 *
 */

public class Validators {

	/**
	 * Metodo per la verifica di un username:
	 * @param Username rappresenta l'username 
	 * @pre Username è una String valida non NULL
	 * @return Il sistema ritorna vero se rispetta le condizioni di lunghezza e di caratteri, altrimenti ritorna falso
	 */
	
	public static boolean ValidateUsername(String Username) {
		if (Username.length() > 30 || Username.length() < 3) {
			return false;
		}

		/* Dalla A alla Z (maiuscole e minuscole), numeri 0-9, e underscore. */
		if (!Username.matches("^[a-zA-Z0-9_]*$")) {
			return false;
		}

		return true;
	}

	public static boolean ValidatePassword(String Password, String Password_Confirm) {
		if (Password.length() > 30 || Password.length() < 5) {
			return false;
		}
		if (!Password.equals(Password_Confirm)) {
			return false;
		}

		return true;
	}

	public static boolean ValidateEmail(String Email) {
		return Email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
	}

	public static boolean ValidateURL(String URL) {
		String[] schemes = {"http","https"}; 
		UrlValidator urlValidator = new UrlValidator(schemes);
		if (urlValidator.isValid(URL)) {
		   return true;
		} 
		return false;
	}
}
