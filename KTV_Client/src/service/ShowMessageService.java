package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;


//提示点歌成功服务
public class ShowMessageService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String kind = object.getString("kind");
		if (kind.equals("Insert_Suc")){
			JOptionPane.showMessageDialog(PlayMusicListFrame.getPlayMusicListFrame(),"点歌成功");
		}
	}
}
