package ctrl;

import message.RoomLogOutRequest;
import net.Client;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListener extends WindowAdapter {

//	���ڴ����쳣�˳���Ҫ���ر���ʾ
	@Override
	public void windowClosing(WindowEvent e) {
//		super.windowClosing(e);
//		int key = JOptionPane.showConfirmDialog(null,"��ȷ���Ƿ�Ҫ�رգ�","��ʾ",JOptionPane.OK_CANCEL_OPTION);
//
//		if (key == JOptionPane.OK_OPTION){
//			String RoomID = PlayMusicListFrame.getPlayMusicListFrame().getRoomID();
//			String msg = JSONObject.fromObject(new RoomLogOutRequest(RoomID)).toString();
//			Client.getClient().sendMsg(msg);
//			System.exit(0);
//		}
	}
}
