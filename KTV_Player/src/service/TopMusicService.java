package service;

import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;
import view.RoomMusicJFrame;


//置顶服务
public class TopMusicService implements IService{
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String music_name = object.getString("music_name");
		MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getMusicLabelNextInfo().setText("下一首："+music_name);
	}
}
