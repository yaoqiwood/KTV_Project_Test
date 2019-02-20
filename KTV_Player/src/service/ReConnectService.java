package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.swing.*;

//����
public class ReConnectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String room_id = object.getString("room_id");
		handle.setClient_RoomID(room_id);
		JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(),"���䣺 "+room_id+" ������������ɹ�");
	}
}
