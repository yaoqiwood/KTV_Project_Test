package message;

//JSon ��������
public class ReConnectMessage {
	private String type = Message.RECONNECT;
	private String room_id;


	public ReConnectMessage() {
	}

	public ReConnectMessage(String room_id) {
		this.room_id = room_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
}
