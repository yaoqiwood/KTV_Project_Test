package view;

import javax.swing.*;
import java.awt.*;

//���ַ���ѡ��
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
		this.setTitle("����ѡ���Զ�ˢ�� 5��һ�Σ�");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.add(roomMusicJPanel);
	}

	public RoomMusicJPanel getRoomMusicJPanel() {
		return roomMusicJPanel;
	}
}
