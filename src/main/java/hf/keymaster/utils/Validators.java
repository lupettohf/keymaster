package hf.keymaster.utils;

public class Validators {

	public static boolean ValidateUsername(String Username)
	{
		if(Username.length() > 30 || Username.length() < 3) { return false; }
		
		/* Dalla A alla Z (maiuscole e minuscole), numeri 0-9, e underscore. */
		if(!Username.matches("^[a-zA-Z0-9_]*$")) { return false; }
		
		return true;
	}
	
	public static boolean ValidatePassword(String Password, String Password_Confirm)
	{
		if(Password.length() > 30 || Password.length() < 5) { return false; }
		if(!Password.equals(Password_Confirm)) { return false; }
		
		return true;
	}
	
	public static boolean ValidateEmail(String Email)
	{
		return Email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
	}

}
