package view;

import message.Message;
import message.NormalMessage;
import net.ListServer_Sender;
import net.RecvThread;
import net.Server;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;


//·þÎñÃæ°å
public class ServerPanel extends JPanel {
	private JTextArea jTextArea = new JTextArea();
	private JScrollPane jScrollPane = new JScrollPane(jTextArea);
	private ServerFrame serverFrame = null;
	public ServerPanel(ServerFrame serverFrame) {
		this.setLayout(null);
		this.setBounds(0,0,500,500);
//		this.jTextArea.setBounds(0,0,500,500);
		this.jScrollPane.setBounds(0,0,500,500);
		this.jTextArea.setFont(new Font("Î¢ÈíÑÅºÚ",0,16));
		this.jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		this.add(jTextArea);
		this.add(jScrollPane);
		this.serverFrame = serverFrame;
	}


	public JTextArea getjTextArea() {
		return jTextArea;
	}
}
