package message;


//JSon 普通消息 偷懒用
public class NormalMessage {
	private String type;
	private String MusicLength;
	private String Port;
	private String Count;
	private String room_id;
	public NormalMessage() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMusicLength() {
		return MusicLength;
	}

	public void setMusicLength(String musicLength) {
		MusicLength = musicLength;
	}

	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}

	public String getCount() {
		return Count;
	}

	public void setCount(String count) {
		Count = count;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
}
