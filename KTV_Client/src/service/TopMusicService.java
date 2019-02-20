package service;

import message.HistoryPlayMessage;
import net.Client;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;
import view.PlayMusicListPanel;

//¸èÇúÖÃ¶¥·þÎñ
public class TopMusicService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String msg = JSONObject.fromObject(new HistoryPlayMessage(0, 5)).toString();
		Client.getClient().sendMsg(msg);
		PlayMusicListPanel playMusicListPanel = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel();
		playMusicListPanel.setPageCurrent(0);
		playMusicListPanel.getPageNumLabel().setText(playMusicListPanel.getPageCurrent()+1 +"/"+ playMusicListPanel.getPageTotal());
	}
}
