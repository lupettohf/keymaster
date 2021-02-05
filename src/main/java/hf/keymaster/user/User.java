package hf.keymaster.user;

public class User {
	private int ID;
	private String Username;
	private String Password;
	private String EMail;
	private String FirstName;
	private String LastName;
	private String HardwareID;
	private boolean isDeveloper;
	
	public User(int ID, String Username, String Password, String EMail, String FirstName, String LastName, String HardwareID, boolean isDeveloper)
	{
		this.ID = ID;
		this.Username = Username;
		this.Password = Password; 
		this.EMail = EMail;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.HardwareID = HardwareID;
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

	public String getHardwareID() {
		return HardwareID;
	}

	public void setHardwareID(String hardwareID) {
		HardwareID = hardwareID;
	}

	public boolean isDeveloper() {
		return isDeveloper;
	}

	public void setDeveloper(boolean isDeveloper) {
		this.isDeveloper = isDeveloper;
	}
	
	
}
