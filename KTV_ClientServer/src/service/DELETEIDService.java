package service;

import dao.ServiceDaoImpl;
import message.Message;
import message.NormalMessage;
import net.ListServer_Sender;
import net.RecvThread;
import net.sf.json.JSONObject;


//É¾³ý·þÎñ
public class DELETEIDService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String deleteID = object.getString("deleteID");
		boolean ret = new ServiceDaoImpl().DeleteIDFromHistory(Integer.parseInt(deleteID));
		if (ret){
			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.DELETEID);
			String msg = JSONObject.fromObject(normalMessage).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();

			normalMessage.setType(Message.DELETEID);
			normalMessage.setRoom_id(handle.getServerToID());
			String TempMsg = JSONObject.fromObject(normalMessage).toString();

			ListServer_Sender.getListServer_sender().SendMsg(TempMsg);
		}

	}
}
