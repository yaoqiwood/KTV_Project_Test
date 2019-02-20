package view;

import ctrl.PlayerActionListener;
import message.RoomSelectRequest;
import net.Client;
import net.ReConnectSenderThread;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;

//房间选择面板
public class RoomMusicJPanel extends JPanel {

	private JList RoomChoserList = new JList();
	private JLabel RoomLabel = new JLabel("房间选择（自动刷新 5秒一次）");
	private JButton BtnConfirm = new JButton("确定");
	private DefaultListModel model = new DefaultListModel();
	private PlayerActionListener playerActionListener = new PlayerActionListener();
	private boolean SenderLocker = false;

	public RoomMusicJPanel() {
		this.setLayout(null);
		this.setBounds(0,0,400,400);
		this.RoomChoserList.setModel(model);
		this.RoomChoserList.setBounds(30,30,200,290);
		this.BtnConfirm.setBounds(160,325,80,30);
		this.BtnConfirm.setFont(new Font("微软雅黑",0,14));
		this.BtnConfirm.setActionCommand("Confirm");
		this.BtnConfirm.addActionListener(playerActionListener);
		this.add(RoomChoserList);
		this.add(RoomLabel);
		this.add(BtnConfirm);

//		服务器请求房间选择
		RoomVaacumSelect();
	}

	public void RoomVaacumSelect(){
//		String msg = JSONObject.fromObject(new RoomSelectRequest()).toString();
//		Client.getClient().sendMsg(msg);

//		实时更新表
		new Thread(new ReConnectSenderThread()).start();

	}

	public JButton getBtnConfirm() {
		return BtnConfirm;
	}

	public JList getRoomChoserList() {
		return RoomChoserList;
	}

	public JLabel getRoomLabel() {
		return RoomLabel;
	}

	public DefaultListModel getModel() {
		return model;
	}

	public boolean isSenderLocker() {
		return SenderLocker;
	}

	public void setSenderLocker(boolean senderLocker) {
		SenderLocker = senderLocker;
	}
}
