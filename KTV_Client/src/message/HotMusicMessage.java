package message;

//ÈÈÃÅÒôÀÖÒ³Êı
public class HotMusicMessage {
	private String type = Message.HOTMUSIC;
	private int CurrentPage;
	private int Offset;

	public HotMusicMessage() {
	}

	public HotMusicMessage(int currentPage, int offset) {
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
