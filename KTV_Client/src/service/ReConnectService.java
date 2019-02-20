package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;


//重连服务
public class ReConnectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String room_id = object.getString("room_id");
		handle.setClient_RoomID(room_id);
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setEnabled(false);
		int key = JOptionPane.showConfirmDialog(PlayMusicListFrame.getPlayMusicListFrame(),"房间： "+room_id+" 与服务器重连成功","提示",JOptionPane.OK_OPTION);
		if (key == JOptionPane.OK_OPTION){
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setEnabled(true);
		}
	}
}
