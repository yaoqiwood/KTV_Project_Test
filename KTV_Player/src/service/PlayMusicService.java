package service;

import net.MusicPlayThread;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//�������ַ���
public class PlayMusicService implements IService {
	ServerSocket serverSocket = null;
	Socket socket = null;

	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		int Port = object.getInt("port");
		String Sname = object.getString("OPath");
		String OPath = "./music_Player/" + Sname;
		String NextMusicName = object.getString("nextMusicName");
		System.out.println(OPath + " OPath");
		int file_length = object.getInt("file_length");
		MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getMusicLabelInfo().setText("���ڲ��ţ� " + Sname);
		MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getMusicLabelNextInfo().setText("��һ�ף�"+ NextMusicName);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(Port);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getBtn_pause().getText());
		MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getBtn_pause().setText("��ͣ");

		new Thread(new MusicPlayThread(serverSocket, OPath, file_length)).start();

	}
}
