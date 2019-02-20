package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

//历史下一页服务
public class HistoryPageNextService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		int Count = object.getInt("count");
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().NextHistoryPage(Count);


	}
}
