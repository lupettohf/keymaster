package hf.keymaster.utils;

/**
 * 
 * Classe pubblica dell'oggetto Alert
 *
 */

public class Alert {
	private String Message;
	private String Type;
	
	/**
	 * Costruttore della classe Alert
	 * @param Message	rappresenta il messaggio
	 * @param Type	 	rappresenta il tipo 
	 */
	
	public Alert(String Message, String Type)
	{
		this.Message = Message;
		this.Type = Type;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
}
