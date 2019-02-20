package service;

import dao.FileDaoImpl;
import message.Message;
import message.RoomBindResponse;
import net.RecvThread;
import net.sf.json.JSONObject;

//绑定房间服务
public class BindRoomService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String roomID = object.getString("roomID");
		boolean stateRet = new FileDaoImpl().SelectRoomIDConnSt(roomID);
		if (stateRet){
			handle.setServerToID(roomID);
			handle.map.put(roomID,handle.getSocket());
			boolean ret = new FileDaoImpl().BindRoomID(roomID);
			if (ret){
				String msg = JSONObject.fromObject(new RoomBindResponse(roomID, Message.SUC)).toString();
				handle.getWriter().println(msg);
				handle.getWriter().flush();
			}
		}else {
			String msg = JSONObject.fromObject(new RoomBindResponse(roomID,Message.FAIL)).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();
		}


	}
}
