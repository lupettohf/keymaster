package hf.keymaster.utils;

public class Alert {
	private String Message;
	private String Type;
	
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
