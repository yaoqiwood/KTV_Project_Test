package bean;

//房间类 数据 存储ID 名称
public class Roomer {
	private String RoomID;
	private String RoomName;

	public Roomer() {
	}

	public Roomer(String roomID, String roomName) {
		RoomID = roomID;
		RoomName = roomName;
	}

	public String getRoomID() {
		return RoomID;
	}

	public void setRoomID(String roomID) {
		RoomID = roomID;
	}

	public String getRoomName() {
		return RoomName;
	}

	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
}
