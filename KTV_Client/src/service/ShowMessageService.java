package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;


//��ʾ���ɹ�����
public class ShowMessageService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String kind = object.getString("kind");
		if (kind.equals("Insert_Suc")){
			JOptionPane.showMessageDialog(PlayMusicListFrame.getPlayMusicListFrame(),"���ɹ�");
		}
	}
}
