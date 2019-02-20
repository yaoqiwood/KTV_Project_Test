package service;

import bean.RoomID;
import dao.FileDaoImpl;
import message.RoomIDResponse;
import net.RecvThread;
import net.sf.json.JSONObject;

import java.util.ArrayList;

//房间选择服务
public class RoomSelectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		ArrayList<RoomID> roomIDArrayList = new FileDaoImpl().SelectRoomID();
		String msg = JSONObject.fromObject(new RoomIDResponse(roomIDArrayList)).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
