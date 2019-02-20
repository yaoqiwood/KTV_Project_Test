package service;

import net.RecvFileThread;
import net.RecvThread;
import net.sf.json.JSONObject;

//文件保存服务
public class FileSaveService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String IP = object.getString("IP");
		int Port = object.getInt("port");
		String OPath = object.getString("OPath");
		String file_length = object.getString("file_length");

		new Thread(new RecvFileThread(Port,OPath,file_length)).start();
	}
}
