package bean;

//  音乐路径JSon数据包

public class FileBean {
	private String music_name;
	private String music_path;
	private String music_length;

	public FileBean() {
	}

	public FileBean(String music_name, String music_path, String music_length) {
		this.music_name = music_name;
		this.music_path = music_path;
		this.music_length = music_length;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public String getMusic_path() {
		return music_path;
	}

	public void setMusic_path(String music_path) {
		this.music_path = music_path;
	}

	public String getMusic_length() {
		return music_length;
	}

	public void setMusic_length(String music_length) {
		this.music_length = music_length;
	}
}
