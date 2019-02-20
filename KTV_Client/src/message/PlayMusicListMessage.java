package message;


//音乐播放列表传输 JSon
public class PlayMusicListMessage {
	private String type = Message.PLAYMUSICLISTMESSAGE;
	private String MusicID;

	public PlayMusicListMessage(String musicID) {
		MusicID = musicID;
	}

	public PlayMusicListMessage() {
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
