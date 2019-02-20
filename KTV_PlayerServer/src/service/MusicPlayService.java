package service;


import bean.MusicFile;
import dao.FileDaoImpl;
import message.ConnectMessage;
import message.PlayMusicResponse;
import net.MusicSpathThread;
import net.RecvThread;
import net.SendFileTread;
import net.sf.json.JSONObject;
import view.PlayerServerFrame;

import java.io.File;
import java.util.ArrayList;


//�����ļ����ŷ���
public class MusicPlayService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();

		new Thread(new MusicSpathThread(handle)).start();

	}
}
