package bean;

//音乐文件JSon传输类
public class MusicFile {
	private String list_id;
	private String music_id;
	private String room_id;
	private String play_state;
	private String sort;
	private String music_name;
	private String music_time;
	private String music_path;
	private String music_length;

	public MusicFile(String list_id, String music_id, String room_id, String play_state, String sort, String music_name, String music_time, String music_path, String music_length) {
		this.list_id = list_id;
		this.music_id = music_id;
		this.room_id = room_id;
		this.play_state = play_state;
		this.sort = sort;
		this.music_name = music_name;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_length = music_length;
	}

	public MusicFile() {
	}

	public String getList_id() {
		return list_id;
	}

	public void setList_id(String list_id) {
		this.list_id = list_id;
	}

	public String getMusic_id() {
		return music_id;
	}

	public void setMusic_id(String music_id) {
		this.music_id = music_id;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getPlay_state() {
		return play_state;
	}

	public void setPlay_state(String play_state) {
		this.play_state = play_state;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public String getMusic_time() {
		return music_time;
	}

	public void setMusic_time(String music_time) {
		this.music_time = music_time;
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
