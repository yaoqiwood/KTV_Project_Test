package message;

import bean.MusicBean;

import java.util.ArrayList;


//历史消息JSon
public class HistoryPlayMessage {
	private String type = Message.HISTORY;
	private ArrayList<MusicBean> musicBeanArrayList;

	public HistoryPlayMessage() {
	}

	public HistoryPlayMessage(ArrayList<MusicBean> musicBeanArrayList) {
		this.musicBeanArrayList = musicBeanArrayList;
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
