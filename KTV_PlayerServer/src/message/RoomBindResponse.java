package message;

//房间绑定必要
public class RoomBindResponse {
	private String type = Message.ROOMBINDACCESS;
	private String RoomID;
	private String Suc;

	public RoomBindResponse() {
	}

	public RoomBindResponse(String roomID, String suc) {
		RoomID = roomID;
		Suc = suc;
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

	public String getSuc() {
		return Suc;
	}

	public void setSuc(String suc) {
		Suc = suc;
	}
}
