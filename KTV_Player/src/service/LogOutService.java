package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.swing.*;

//�ǳ�����
public class LogOutService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		System.out.println(object);
		String room_id = object.getString("room_id");
		JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(),"����: ��"+room_id+" ���Ŵ��������˳�...");
		System.exit(0);
	}
}
