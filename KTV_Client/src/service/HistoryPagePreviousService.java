package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;


//历史上一页服务
public class HistoryPagePreviousService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		int Count = object.getInt("count");
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().PreviousHistoryPage(Count);
	}
}
