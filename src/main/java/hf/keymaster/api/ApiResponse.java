package hf.keymaster.api;

public class ApiResponse {

	private String ApplicationName;
	private String LicenseName;
	private String LicenseDescription;
	private int Type;
	private long ActivationEpoch;

	public ApiResponse(String ApplicationName, String LicenseName, String LicenseDescription, int Type,
			long ActivationEpoch) {
		this.ApplicationName = ApplicationName;
		this.LicenseName = LicenseName;
		this.LicenseDescription = LicenseDescription;
		this.Type = Type;
		this.ActivationEpoch = ActivationEpoch;
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

}
