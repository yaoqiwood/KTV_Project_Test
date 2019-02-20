package message;

import bean.MusicBean;

import java.util.ArrayList;
//JSon 热门音乐回应
public class HotMusicResponse {
	private String type = Message.HOTMUSIC;
	private ArrayList<MusicBean> musicBeanArrayList;

	public HotMusicResponse(ArrayList<MusicBean> musicBeanArrayList) {
		this.musicBeanArrayList = musicBeanArrayList;
	}

	public HotMusicResponse() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<MusicBean> getMusicBeanArrayList() {
		return musicBeanArrayList;
	}

	public void setMusicBeanArrayList(ArrayList<MusicBean> musicBeanArrayList) {
		this.musicBeanArrayList = musicBeanArrayList;
	}

}
