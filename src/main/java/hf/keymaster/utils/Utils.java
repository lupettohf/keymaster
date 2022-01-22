package hf.keymaster.utils;

import java.security.SecureRandom;

import  jakarta.servlet.http.HttpSession;

/**
 * 
 * Classe pubblica dell'oggetto Utils
 *
 */

/**
 * 
 * Metodo per la realizzazione di una key:
 * @params lenght rappresenta la lunghezza della chiave
 * @pre lenght è un intero valido non NULL
 * @return il sistema ritorna una String di lunghezza lenght utilizzando il secureRandom() di java
 */

public class Utils {
	public static String generateSecureString(int lenght) {
		StringBuilder sb = new StringBuilder(lenght);
		SecureRandom random = new SecureRandom();
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String upperAlphabet = lowerAlphabet.toUpperCase();
		String alphabet = lowerAlphabet + upperAlphabet;

		for (int i = 0; i < lenght; i++) {
			int rndCharAt = random.nextInt(alphabet.length());
			char rndChar = alphabet.charAt(rndCharAt);
			sb.append(rndChar);
		}

		return sb.toString();
	}
	
	public static void setAlert(Alert alert, HttpSession session)
	{
		session.setAttribute("alert", alert);
	}

}
