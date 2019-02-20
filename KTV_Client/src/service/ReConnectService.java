package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;


//��������
public class ReConnectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String room_id = object.getString("room_id");
		handle.setClient_RoomID(room_id);
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setEnabled(false);
		int key = JOptionPane.showConfirmDialog(PlayMusicListFrame.getPlayMusicListFrame(),"���䣺 "+room_id+" ������������ɹ�","��ʾ",JOptionPane.OK_OPTION);
		if (key == JOptionPane.OK_OPTION){
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setEnabled(true);
		}
	}
}
