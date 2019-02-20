package service;

import net.RecvFileThread;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.ProgressBarFrame;

import java.io.IOException;
import java.net.ServerSocket;


//再次登录更新请求服务
public class ReMusicTxtEndService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		ServerSocket serverSocket = null;
		JSONObject object = handle.getJsonObject();
		int musicLength = object.getInt("musicLength");
		int port = object.getInt("port");
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProgressBarFrame.getProgressBarFrame().setVisible(true);
		new Thread(new RecvFileThread(serverSocket,musicLength)).start();
	}

}
