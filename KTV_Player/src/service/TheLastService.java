package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.swing.*;

//��ʾ����
public class TheLastService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(),"�����б��ѿգ�û�����~");
	}
}
