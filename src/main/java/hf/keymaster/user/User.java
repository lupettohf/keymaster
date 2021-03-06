package hf.keymaster.user;

/**
 * 
 * Classe pubblica dell'oggetto User
 *
 */

public class User {
	private int ID;
	private String Username;
	private String Password;
	private String EMail;
	private String FirstName;
	private String LastName;
	private boolean isDeveloper;

	/**
	 * Costruttore della classe ser
	 * @param ID			identificativo dell'utente
	 * @param Username		username dell'utente
	 * @param Password		password dell'utente
	 * @param EMail			email dell'utente
	 * @param FirstName		nome dell'utente
	 * @param LastName		cognome dell'utente
	 * @param isDeveloper	stato dell'utente
	 */
	
	public User(int ID, String Username, String Password, String EMail, String FirstName, String LastName, boolean isDeveloper) {
		this.ID = ID;
		this.Username = Username;
		this.Password = Password;
		this.EMail = EMail;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.isDeveloper = isDeveloper;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public boolean isDeveloper() {
		return isDeveloper;
	}

	public void setDeveloper(boolean isDeveloper) {
		this.isDeveloper = isDeveloper;
	}

}
