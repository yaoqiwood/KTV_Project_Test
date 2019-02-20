package message;

//消息处理  页数传输 JSon
public class HistoryPlayMessage {
	private String type = Message.HISTORY;
	private int CurrentPage;
	private int Offset;

	public HistoryPlayMessage() {
	}

	public HistoryPlayMessage(int currentPage, int offset) {
		CurrentPage = currentPage;
		Offset = offset;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getOffset() {
		return Offset;
	}

	public void setOffset(int offset) {
		Offset = offset;
	}
}
