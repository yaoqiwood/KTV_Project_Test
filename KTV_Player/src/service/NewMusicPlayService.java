package service;

import message.MusicBufRequest;
import music.MusicUtil;
import net.Client;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import javax.media.Player;

//���ļ������������
public class NewMusicPlayService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		Player player = MusicUtil.getMusicUtil().getPlayer();
		if (player != null) {
			String music_name = object.getString("music_name");
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getMusicLabelNextInfo().setText("��һ�ף�"+music_name);
		}

	}
}
