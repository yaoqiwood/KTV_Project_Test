package message;


//K歌消息回应
public class TopAndNextMessage {
	private String type = Message.TOPNANEXT;

	public TopAndNextMessage() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
