package message;


//Json K�����
public class TopAndNextResponse {
	private String type = Message.TOPNANEXT;

	public TopAndNextResponse() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
