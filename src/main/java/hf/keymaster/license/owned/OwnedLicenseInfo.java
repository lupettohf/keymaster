package hf.keymaster.license.owned;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import hf.keymaster.application.Application;
import hf.keymaster.license.License;

public class OwnedLicenseInfo {
	private License License;
	private Application Application;
	private OwnedLicense OwnedLicense;
	
	public OwnedLicenseInfo(License License, Application Application, OwnedLicense OwnedLicense) {
		this.Application = Application;
		this.License = License;
		this.OwnedLicense = OwnedLicense;
	}

	public License getLicense() {
		return License;
	}

	public void setLicense(License license) {
		License = license;
	}

	public Application getApplication() {
		return Application;
	}

	public void setApplication(Application application) {
		Application = application;
	}

	public OwnedLicense getOwnedLicense() {
		return OwnedLicense;
	}

	public void setOwnedLicense(OwnedLicense ownedLicense) {
		OwnedLicense = ownedLicense;
	}
	
	public int getRemaningDays()
	{
		long now = Instant.now().toEpochMilli();
		int elapsed = (int) TimeUnit.MILLISECONDS.toDays(now - OwnedLicense.getActivationEpoch());
		
		if(elapsed > License.getDuration())
		{
			return 0;
		} else {
			return License.getDuration() - elapsed;
		}
	}
}
