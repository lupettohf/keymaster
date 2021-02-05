package hf.keymaster.application;

public class Application {
	private int ID;
	private int OwnerID;
	private String Name;
	private String Description;
	private String Website;
	private int Version;
	private String APIKey;
	
	public Application(int ID, int OwnerID, String Name, String Description, String Website, int Version, String APIKey)
	{
		this.ID = ID;
		this.OwnerID = OwnerID;
		this.Name = Name;
		this.Description = Description;
		this.Website = Website;
		this.Version = Version;
		this.APIKey = APIKey;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getOwnerID() {
		return OwnerID;
	}
	public void setOwnerID(int ownerID) {
		OwnerID = ownerID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getWebsite() {
		return Website;
	}
	public void setWebsite(String website) {
		Website = website;
	}
	public int getVersion() {
		return Version;
	}
	public void setVersion(int version) {
		this.Version = version;
	}
	public String getAPIKey() {
		return APIKey;
	}
	public void setAPIKey(String aPIKey) {
		APIKey = aPIKey;
	}
}
