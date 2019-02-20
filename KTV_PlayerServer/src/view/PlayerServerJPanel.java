package view;

import javax.swing.*;

//服务器播放面板
public class PlayerServerJPanel extends JPanel {
	private JTextArea jTextArea = new JTextArea();
	private JScrollPane jScrollPane = new JScrollPane(jTextArea);
	private String LastPlay_listID;


	public PlayerServerJPanel() {
		this.setLayout(null);
		this.setBounds(0,0,500,500);
//		this.jTextArea.setBounds(0,0,500,500);
		this.jScrollPane.setBounds(0,0,500,500);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//		this.add(jTextArea);
		this.add(jScrollPane);
	}

	public JTextArea getjTextArea() {
		return jTextArea;
	}

	public void setjTextArea(JTextArea jTextArea) {
		this.jTextArea = jTextArea;
	}

	public String getLastPlay_listID() {
		return LastPlay_listID;
	}

	public void setLastPlay_listID(String lastPlay_listID) {
		LastPlay_listID = lastPlay_listID;
	}
}
