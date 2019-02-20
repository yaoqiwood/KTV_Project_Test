package view;

import javax.swing.*;

//服务器窗口
public class ServerFrame extends JFrame {
	private static ServerFrame serverFrame;
	public static ServerFrame getServerFrame(){
		if (serverFrame == null){
			serverFrame = new ServerFrame();
		}
		return serverFrame;
	}

	private ServerPanel serverPanel = new ServerPanel(this);
	private int room_id = 0;

	public ServerFrame()  {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("客户服务端");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.add(serverPanel);
	}

	public ServerPanel getServerPanel() {
		return serverPanel;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
}
