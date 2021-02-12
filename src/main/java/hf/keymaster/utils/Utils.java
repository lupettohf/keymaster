package hf.keymaster.utils;

import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

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
		session.removeAttribute("alert");
		session.setAttribute("alert", alert);
	}

}
