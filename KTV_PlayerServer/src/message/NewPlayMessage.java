package message;

//新播放消息
public class NewPlayMessage {
	private String type = Message.NEWMUSICPLAY;
	private String room_id;
	private String music_name;

	public NewPlayMessage(String room_id, String music_name) {
		this.room_id = room_id;
		this.music_name = music_name;
	}

	public NewPlayMessage() {
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

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
}
