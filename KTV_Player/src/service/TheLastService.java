package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.swing.*;

//提示服务
public class TheLastService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(),"播放列表已空，没歌儿了~");
	}
}
