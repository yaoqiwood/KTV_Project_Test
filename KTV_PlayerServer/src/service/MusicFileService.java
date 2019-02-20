package service;

//import message.FileResponse;
import message.PlayMusicResponse;
import net.PlayMusicThread;
import net.RecvThread;
import net.sf.json.JSONObject;
import util.FileUtil;

import java.io.*;
import java.net.Socket;

//音乐文件服务
public class MusicFileService implements IService {

	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String SPath = object.getString("SPath");
		String Sname = object.getString("sname");
		String length = object.getString("length");
		String OPath = "wav/" + Sname;
		int Port = 10088;
		Socket socket = null;
//		FileUtil.copyFile(SPath,OPath);
		String IP = "192.168.43.10";

		new Thread(new PlayMusicThread(IP,Port,SPath)).start();

		String msg = JSONObject.fromObject(new PlayMusicResponse(null,Port,OPath,length)).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();

	}
}
