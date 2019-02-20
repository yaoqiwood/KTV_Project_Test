package message;


//通用消息传输 （偷懒专用 JSon
public class NormalMessage {
	private String type;
	private String MusicLength;
	private String Port;
	private String Time;
	private String Count;
	private String DeleteID;
	private String TopID;
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

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getCount() {
		return Count;
	}

	public void setCount(String count) {
		Count = count;
	}

	public String getDeleteID() {
		return DeleteID;
	}

	public void setDeleteID(String deleteID) {
		DeleteID = deleteID;
	}

	public String getTopID() {
		return TopID;
	}

	public void setTopID(String topID) {
		TopID = topID;
	}
}
