package ctrl;

import message.RoomLogOutRequest;
import net.Client;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListener extends WindowAdapter {

//	由于处理异常退出需要，关闭提示
	@Override
	public void windowClosing(WindowEvent e) {
//		super.windowClosing(e);
//		int key = JOptionPane.showConfirmDialog(null,"请确认是否要关闭？","提示",JOptionPane.OK_CANCEL_OPTION);
//
//		if (key == JOptionPane.OK_OPTION){
//			String RoomID = PlayMusicListFrame.getPlayMusicListFrame().getRoomID();
//			String msg = JSONObject.fromObject(new RoomLogOutRequest(RoomID)).toString();
//			Client.getClient().sendMsg(msg);
//			System.exit(0);
//		}
	}
}
