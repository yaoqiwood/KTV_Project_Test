package view;

import javax.swing.*;
import java.awt.*;

//音乐房间选择
public class RoomMusicJFrame extends JFrame {
	private static RoomMusicJFrame roomMusicJFrame;
	public static RoomMusicJFrame getRoomMusicJFrame(){
		if (roomMusicJFrame == null){
			roomMusicJFrame = new RoomMusicJFrame();
		}
		return roomMusicJFrame;
	}

	private RoomMusicJPanel roomMusicJPanel = new RoomMusicJPanel();
	public RoomMusicJFrame()  {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("房间选择（自动刷新 5秒一次）");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.add(roomMusicJPanel);
	}

	public RoomMusicJPanel getRoomMusicJPanel() {
		return roomMusicJPanel;
	}
}
