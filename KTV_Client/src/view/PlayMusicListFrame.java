package view;

import bean.MusicBean;
import ctrl.WindowListener;

import javax.swing.*;
import java.util.ArrayList;

//歌曲窗口
public class PlayMusicListFrame extends JFrame {

	private static PlayMusicListFrame playMusicListFrame;
	public static PlayMusicListFrame getPlayMusicListFrame(){
		if (playMusicListFrame == null){
			playMusicListFrame = new PlayMusicListFrame();
		}
		return playMusicListFrame;
	}


	private String RoomID;

	private PlayMusicListPanel playMusicListPanel = new PlayMusicListPanel();
	private PlayMusicListSpellPanel playMusicListSpellPanel = new PlayMusicListSpellPanel();
	private PlayMusicListComboxPanel playMusicListComboxPanel = new PlayMusicListComboxPanel();


	public PlayMusicListFrame() {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("点播音乐列表客户窗口");
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setVisible(false);


		this.add(playMusicListPanel);
		this.add(playMusicListSpellPanel);
		this.add(playMusicListComboxPanel);
	}

	public String getRoomID() {
		return RoomID;
	}

	public void setRoomID(String roomID) {
		RoomID = roomID;
	}

	public PlayMusicListPanel getPlayMusicListPanel() {
		return playMusicListPanel;
	}

	public PlayMusicListSpellPanel getPlayMusicListSpellPanel() {
		return playMusicListSpellPanel;
	}

	public PlayMusicListComboxPanel getPlayMusicListComboxPanel() {
		return playMusicListComboxPanel;
	}


}
