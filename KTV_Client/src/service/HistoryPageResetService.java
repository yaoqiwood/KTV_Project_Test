package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

//��ʷҳ�����÷���
public class HistoryPageResetService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		int Count = object.getInt("count");
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().PageHistoryReset(Count);
	}
}
