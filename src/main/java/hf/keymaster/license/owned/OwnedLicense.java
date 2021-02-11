package hf.keymaster.license.owned;

public class OwnedLicense {
	private int ID;
	private int userID;
	private int licenseID;
	private int keyID;
	private long activationEpoch;
	private String HardwareID;

	public OwnedLicense(int ID, int userID, int licenseID, int keyID, long activationEpoch, String HardwareID) {
		this.ID = ID;
		this.userID = userID;
		this.licenseID = licenseID;
		this.keyID = keyID;
		this.activationEpoch = activationEpoch;
		this.HardwareID = HardwareID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) { 
		ID = iD;
	}

	public int getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(int licenseID) {
		this.licenseID = licenseID;
	}

	public int getKeyID() {
		return keyID;
	}

	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}

	public long getActivationEpoch() {
		return activationEpoch;
	}

	public void setActivationEpoch(long activationEpoch) {
		this.activationEpoch = activationEpoch;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getHardwareID() {
		return HardwareID;
	}

	public void setHardwareID(String hardwareID) {
		HardwareID = hardwareID;
	}

}
