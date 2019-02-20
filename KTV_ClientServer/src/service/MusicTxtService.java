package service;

import message.Message;
import message.NormalMessage;
import net.RecvThread;
import net.SendFileThread;
import net.sf.json.JSONObject;

import java.io.File;


//音乐文本服务
public class MusicTxtService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String IP = object.getString("IP");
		int Port = object.getInt("port");
		new Thread(new SendFileThread(IP,Port,handle)).start();
		String Path = "./Util/JSon.txt";
		File file = new File(Path);
		long legnth = file.length();
		NormalMessage normalMessage = new NormalMessage();
		normalMessage.setMusicLength(String.valueOf(legnth));
		normalMessage.setPort(String.valueOf(Port));
		normalMessage.setType(Message.REMUSICTXTSEND);
		String msg = JSONObject.fromObject(normalMessage).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
