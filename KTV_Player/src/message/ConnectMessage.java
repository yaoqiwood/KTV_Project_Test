package message;


//������Ϣ
public class ConnectMessage {
	private String type = Message.CONNECTMESSAGE;

	public ConnectMessage() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
