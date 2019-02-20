package message;


//音乐传输ip需求 请求 Json
public class MusicTableTranMessage {
	private String type = Message.MUSICTXTTABLE;
	private String IP;
	private String Port;
	public MusicTableTranMessage() {
	}

	public MusicTableTranMessage(String IP, String port) {
		this.IP = IP;
		Port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}
}
