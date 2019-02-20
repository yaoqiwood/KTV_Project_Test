package service;

import dao.ServiceDaoImpl;
import message.Message;
import message.NormalMessage;
import net.ListServer_Sender;
import net.RecvThread;
import net.sf.json.JSONObject;

import java.lang.reflect.Member;


//µÇ³ö·þÎñ
public class LogOutService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String RoomID = object.getString("roomID");
		boolean res = new ServiceDaoImpl().UpdateRoomState("5",RoomID);
		boolean oct = new ServiceDaoImpl().UpdataNewPlayStateOnClose(RoomID);
		if (res){
			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.LOGOUT);
			normalMessage.setRoom_id(RoomID);
			String msg = JSONObject.fromObject(normalMessage).toString();
			ListServer_Sender.getListServer_sender().SendMsg(msg);
		}

	}
}
