package view;

import javax.swing.*;

//����������������
public class PlayerServerFrame extends JFrame {
	private static PlayerServerFrame playerServerFrame;
	public static PlayerServerFrame getPlayerServerFrame(){
		if (playerServerFrame==null){
			playerServerFrame = new PlayerServerFrame();
		}
		return playerServerFrame;
	}

	private PlayerServerJPanel playerServerJPanel = new PlayerServerJPanel();
	public PlayerServerFrame() {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("���Ŷ˷����");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.add(playerServerJPanel);
	}

	public PlayerServerJPanel getPlayerServerJPanel() {
		return playerServerJPanel;
	}

	public void setPlayerServerJPanel(PlayerServerJPanel playerServerJPanel) {
		this.playerServerJPanel = playerServerJPanel;
	}
}
