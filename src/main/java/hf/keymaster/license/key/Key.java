package hf.keymaster.license.key;

/**
 * 
 * Classe pubblica dell'oggetto Key
 *
 */

public class Key {
	private int ID;
	private int LicenseID;
	private String LicenseKey;
	private boolean Redeemed;

	/**
	 * Costruttore della classe Key
	 * @param ID			identificativo della chiave
	 * @param LicenseID		identificativo della licenza
	 * @param LicenseKey	chiave della licenza 
	 * @param Redeemed		stato della licenza
	 */
	
	public Key(int ID, int LicenseID, String LicenseKey, boolean Redeemed) {
		this.ID = ID;
		this.LicenseID = LicenseID;
		this.LicenseKey = LicenseKey;
		this.Redeemed = Redeemed;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getLicenseID() {
		return LicenseID;
	}

	public void setLicenseID(int licenseID) {
		LicenseID = licenseID;
	}

	public String getLicenseKey() {
		return LicenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		LicenseKey = licenseKey;
	}

	public boolean isRedeemed() {
		return Redeemed;
	}

	public void setRedeemed(boolean redeemed) {
		Redeemed = redeemed;
	}
}
