package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.swing.*;

//登出服务
public class LogOutService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		System.out.println(object);
		String room_id = object.getString("room_id");
		JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(),"房间: ："+room_id+" 播放窗口正在退出...");
		System.exit(0);
	}
}
