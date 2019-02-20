package service;

import message.HistoryPlayMessage;
import net.Client;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;

//删除历史服务
public class HistoryDeleteService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		int CurrentPage = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().getPageCurrent();
		String msg = JSONObject.fromObject(new HistoryPlayMessage(CurrentPage,5)).toString();
		Client.getClient().sendMsg(msg);

		JOptionPane.showMessageDialog(PlayMusicListFrame.getPlayMusicListFrame(),"删除成功");
	}
}
