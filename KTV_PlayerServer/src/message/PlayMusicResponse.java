package message;


//播放音乐传输必要信息Json信息
public class PlayMusicResponse {
	private String type = Message.PLAYMUSIC;
	private String IP;
	private int Port;
	private String OPath;
	private String file_length;
	private String NextMusicName;

	public PlayMusicResponse() {
	}

	public PlayMusicResponse(String IP, int port, String OPath, String file_length) {
		this.IP = IP;
		Port = port;
		this.OPath = OPath;
		this.file_length = file_length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public String getOPath() {
		return OPath;
	}

	public void setOPath(String OPath) {
		this.OPath = OPath;
	}

	public String getFile_length() {
		return file_length;
	}

	public void setFile_length(String file_length) {
		this.file_length = file_length;
	}

	public String getNextMusicName() {
		return NextMusicName;
	}

	public void setNextMusicName(String nextMusicName) {
		NextMusicName = nextMusicName;
	}
}
