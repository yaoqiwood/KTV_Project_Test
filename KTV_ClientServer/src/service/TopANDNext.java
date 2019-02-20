package service;

import dao.ServiceDaoImpl;
import message.Message;
import message.NormalMessage;
import net.ListServer_Sender;
import net.RecvThread;
import net.sf.json.JSONObject;

//K�����
public class TopANDNext implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String ID = object.getString("topID");
		String room_id = handle.getServerToID();
		boolean ret = new ServiceDaoImpl().TopMusicOperator(room_id,ID);
		if (ret){
			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.TOPMUSIC);
			String msg = JSONObject.fromObject(normalMessage).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();

			NormalMessage tempMessage = new NormalMessage();
			tempMessage.setType(Message.TOPNANEXT);
			tempMessage.setRoom_id(room_id);
			String tempMsg = JSONObject.fromObject(tempMessage).toString();

//			�������ӵ�PlayerServer
			ListServer_Sender.getListServer_sender().SendMsg(tempMsg);

		}
	}
}
