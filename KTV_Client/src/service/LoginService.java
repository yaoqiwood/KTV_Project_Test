package service;

import dao.daoServiceImpl;
import message.Message;
import message.NormalMessage;
import net.Client;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.LoginFrame;
import view.PlayMusicListFrame;

import javax.swing.*;


//登录服务
public class LoginService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String suc = object.getString("SUC");
		String RoomID = object.getString("roomID");
		if (suc.equals(Message.SUC)){
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"房间登录成功："+RoomID);
			PlayMusicListFrame.getPlayMusicListFrame().setRoomID(RoomID);
			LoginFrame.getLoginFrame().setVisible(false);
			PlayMusicListFrame.getPlayMusicListFrame().setVisible(true);
			handle.setClient_RoomID(RoomID);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().getRoomLabel().setText("房间号："+RoomID);

		}else if (suc.equals(Message.OCCUPY)){
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"当前房间已登录");
		}else if (suc.equals(Message.FAIL)){
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"登录失败，请检查您的房号密码");
		}

	}
}
