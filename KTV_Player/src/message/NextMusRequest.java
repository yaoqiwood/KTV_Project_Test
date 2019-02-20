package message;


//下一首请求
public class NextMusRequest {
	private String type = Message.MUSICNEXT;
	private String MusicID;

	public NextMusRequest() {
	}

	public NextMusRequest(String musicID) {
		MusicID = musicID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMusicID() {
		return MusicID;
	}

	public void setMusicID(String musicID) {
		MusicID = musicID;
	}
}
