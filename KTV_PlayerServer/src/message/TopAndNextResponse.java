package message;


//K歌消息处理回应
public class TopAndNextResponse {
	private String type = Message.TOPNANEXTANDRE;
	private String room_id;

	public TopAndNextResponse() {
	}

	public TopAndNextResponse(String room_id) {
		this.room_id = room_id;
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
}
