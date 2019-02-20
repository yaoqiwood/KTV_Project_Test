package net;

import bean.MusicFile;
import dao.FileDaoImpl;
import message.PlayMusicResponse;
import message.TheLastMessage;
import net.sf.json.JSONObject;
import view.PlayerServerFrame;

import java.io.File;
import java.util.ArrayList;


//����·�������߳�
public class MusicSpathThread implements Runnable {
	private String SPath = null;
	private String list_id = null;
	private ArrayList<MusicFile> musicFileArrayList = null;
	private RecvThread handle;
	private String room_id;

	public MusicSpathThread(RecvThread handle) {
		this.handle = handle;
	}

	@Override
	public void run() {
		room_id = handle.getServerToID();
		ArrayList<MusicFile> TempArray = new FileDaoImpl().SelectPlaylist(room_id);
		if (TempArray.size() == 1) {
			if (TempArray.get(0).getMusic_name() == null){
				String tempMSG = JSONObject.fromObject(new TheLastMessage()).toString();
				handle.getWriter().println(tempMSG);
				handle.getWriter().flush();
			}
		}

		while (true) {
			musicFileArrayList = new FileDaoImpl().SelectPlaylist(room_id);
			SPath = musicFileArrayList.get(0).getMusic_path();
			list_id = musicFileArrayList.get(0).getList_id();
			PlayerServerFrame.getPlayerServerFrame().getPlayerServerJPanel().setLastPlay_listID(list_id);

			if (SPath != null) {
				break;
			}
//		    ����������·��֮ǰ����һ�����ݰ���ȥ��������
//			String connectPackage = JSONObject.fromObject(new ConnectMessage()).toString();
//			handle.getWriter().println(connectPackage);
//			handle.getWriter().flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

//		���²���״̬
		boolean ret = new FileDaoImpl().PlayerUpdate(list_id);
//		ֱ�ӻ�ȡ��һ�׸���
		ArrayList<MusicFile> tempArraylist = new FileDaoImpl().SelectPlaylist(room_id);
		String Music_NextName = tempArraylist.get(0).getMusic_name();
		int Port = 10050;
		File file = new File(SPath);
		long length = file.length();
		String fileName = file.getName();
		new Thread(new SendFileTread("127.0.0.1", Port, SPath)).start();
//		���͸������ݰ�
		PlayMusicResponse playMusicResponse = new PlayMusicResponse("127.0.0.1", Port, fileName, String.valueOf(length));
		playMusicResponse.setNextMusicName(Music_NextName);
		String msg = JSONObject.fromObject(playMusicResponse).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
