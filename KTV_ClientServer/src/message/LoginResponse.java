package message;

import message.Message;

//JSon µÇÂ¼»ØÓ¦
public class LoginResponse {
	private String type = Message.LOGIN;
	private String RoomID;
	private String PWD;
	private String SUC;

	public LoginResponse() {
	}

	public LoginResponse(String roomID, String PWD) {
		RoomID = roomID;
		this.PWD = PWD;
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

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String PWD) {
		this.PWD = PWD;
	}

	public String getSUC() {
		return SUC;
	}

	public void setSUC(String SUC) {
		this.SUC = SUC;
	}
}
