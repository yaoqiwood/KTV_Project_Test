package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

//热门页数总数计算服务
public class HotPageCountService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		int Count = object.getInt("count");
		System.out.println(Count);
		if (Count <= 5) {
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setHotPageCount(1);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().getPageNumLabel().setText("1/1");
			if (Count == 0) {
				PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setHotPageCount(0);
				PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().getPageNumLabel().setText("0/0");
			}
		} else {
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setHotPageCount(2);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().getPageNumLabel().setText("1/2");
		}
	}
}
