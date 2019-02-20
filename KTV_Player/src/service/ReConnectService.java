package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.swing.*;

//重连
public class ReConnectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String room_id = object.getString("room_id");
		handle.setClient_RoomID(room_id);
		JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(),"房间： "+room_id+" 与服务器重连成功");
	}
}
