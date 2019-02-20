package message;

import message.Message;

// Json µÇÂ¼ÇëÇó
public class LoginJSonRequest {
	private String type = Message.LOGIN;
	private String LoginRoomID;
	private String LoginPWD;

	public LoginJSonRequest() {
	}

	public LoginJSonRequest(String loginRoomID, String loginPWD) {
		LoginRoomID = loginRoomID;
		LoginPWD = loginPWD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginRoomID() {
		return LoginRoomID;
	}

	public void setLoginRoomID(String loginRoomID) {
		LoginRoomID = loginRoomID;
	}

	public String getLoginPWD() {
		return LoginPWD;
	}

	public void setLoginPWD(String loginPWD) {
		LoginPWD = loginPWD;
	}
}
