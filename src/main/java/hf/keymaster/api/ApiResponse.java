package hf.keymaster.api;

/**
 * 
 * Classe pubblica dell'oggetto ApiResponse
 *
 */

public class ApiResponse {

	private String ApplicationName;
	private String LicenseName;
	private String LicenseDescription;
	private int Type;
	private long ActivationEpoch;
	private String HardwareID;

	/**
	 * Costruttore della classe ApiResponse
	 * @param ApplicationName		nome dell'applicazione
	 * @param LicenseName			nome della licenza
	 * @param LicenseDescription	descrizione della licenza
	 * @param Type					tipo di licenza
	 * @param ActivationEpoch		Unix Timestrap 
	 * @param HardwareID			identificativo dell'hardware
	 */
	
	public ApiResponse(String ApplicationName, String LicenseName, String LicenseDescription, int Type,
			long ActivationEpoch, String HardwareID) {
		this.ApplicationName = ApplicationName;
		this.LicenseName = LicenseName;
		this.LicenseDescription = LicenseDescription;
		this.Type = Type;
		this.ActivationEpoch = ActivationEpoch;
		this.HardwareID = HardwareID;
	}

	public String getApplicationName() {
		return ApplicationName;
	}

	public void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}

	public String getLicenseName() {
		return LicenseName;
	}

	public void setLicenseName(String licenseName) {
		LicenseName = licenseName;
	}

	public String getLicenseDescription() {
		return LicenseDescription;
	}

	public void setLicenseDescription(String licenseDescription) {
		LicenseDescription = licenseDescription;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public long getActivationEpoch() {
		return ActivationEpoch;
	}

	public void setActivationEpoch(long activationEpoch) {
		ActivationEpoch = activationEpoch;
	}

	public String getHardwareID() {
		return HardwareID;
	}

	public void setHardwareID(String hardwareID) {
		HardwareID = hardwareID;
	}

}
