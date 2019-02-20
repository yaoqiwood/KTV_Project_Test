package service;

import message.ReConnectMessage;
import net.RecvThread;
import net.sf.json.JSONObject;

//重新连接
public class ReConnectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String room_id = object.getString("room_id");
		handle.setServerToID(room_id);
		String msg = JSONObject.fromObject(new ReConnectMessage(room_id)).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
