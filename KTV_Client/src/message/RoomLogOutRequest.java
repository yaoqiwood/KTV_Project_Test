package message;

//����ǳ�����
public class RoomLogOutRequest {
	private String type = Message.LOGOUT;
	private String RoomID;

	public RoomLogOutRequest() {
	}

	public RoomLogOutRequest(String roomID) {
		RoomID = roomID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoomID() {
		return RoomID;
	}

	public void setRoomID(String roomID) {
		RoomID = roomID;
	}
}

