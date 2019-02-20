package service;

import net.RecvThread;
import net.sf.json.JSONObject;

//连接服务
public class ConnectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
//		handle.getWriter().println(object);
//		handle.getWriter().flush();
	}
}
