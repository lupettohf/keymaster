package hf.keymaster.license;

/**
 * 
 * Classe pubblica dell'oggetto License
 *
 */

public class License {
	private int ID;
	private int AppID;
	private String Name;
	private String Description;
	private int Duration;
	private int Type;

	/**
	 * Costruttore della classe License
	 * @param ID			indetificatore della licenza
	 * @param AppID			indentificatore dell'applicativo
	 * @param Name			nome della licenza
	 * @param Description	descrizione della licenza
	 * @param Duration		durata della licenza
	 * @param Type			tipo della licenza
	 */
	
	public License(int ID, int AppID, String Name, String Description, int Duration, int Type) {
		this.ID = ID;
		this.AppID = AppID;
		this.Name = Name;
		this.Description = Description;
		this.Duration = Duration;
		this.Type = Type;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getAppID() {
		return AppID;
	}

	public void setAppID(int appID) {
		AppID = appID;
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

	public int getDuration() {
		return Duration;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

}
