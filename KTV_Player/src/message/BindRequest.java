package message;


//Json∞Û∂®“Ù¿÷∑øº‰
public class BindRequest {
	private String type = Message.BINDROOM;
	private String roomID;

	public BindRequest() {
	}

	public BindRequest(String roomID) {
		this.roomID = roomID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
}
