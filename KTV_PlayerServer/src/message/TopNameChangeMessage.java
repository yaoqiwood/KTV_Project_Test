package message;


//置顶消息音乐名称修改
public class TopNameChangeMessage {
	private String type = Message.TOPMUSIC;
	private String music_name;

	public TopNameChangeMessage() {
	}

	public TopNameChangeMessage(String music_name) {
		this.music_name = music_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
}
