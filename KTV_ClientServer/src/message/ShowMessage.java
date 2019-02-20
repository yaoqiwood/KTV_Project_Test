package message;


//JSon у╧ож
public class ShowMessage {
	private String type = Message.SHOWMESSAGE;
	private String kind;

	public ShowMessage(String kind) {
		this.kind = kind;
	}

	public ShowMessage() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
}
