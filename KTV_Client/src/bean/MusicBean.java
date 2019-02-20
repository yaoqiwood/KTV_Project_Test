package bean;

//  音乐文件数据类 JSon传输
public class MusicBean {
	private String music_id;
	private String music_name;
	private String music_time;
	private String music_path;
	private String music_length;
	private String spell;
	private String singer;
	private String type_id;
	private String update_time;
	private String singer_name;
	private String type_name;
	private String Hot;
	private String list_id;

	public MusicBean() {
	}

	public MusicBean(String music_id, String music_name, String music_time, String music_path, String music_length, String spell, String singer, String type_id, String update_time) {
		this.music_id = music_id;
		this.music_name = music_name;
		this.music_time = music_time;
		this.music_path = music_path;
		this.music_length = music_length;
		this.spell = spell;
		this.singer = singer;
		this.type_id = type_id;
		this.update_time = update_time;
	}



	public String getMusic_id() {
		return music_id;
	}

	public void setMusic_id(String music_id) {
		this.music_id = music_id;
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

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getSinger_name() {
		return singer_name;
	}

	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getHot() {
		return Hot;
	}

	public void setHot(String hot) {
		Hot = hot;
	}

	public String getList_id() {
		return list_id;
	}

	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
}
