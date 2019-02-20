package service;

import dao.daoServiceImpl;
import message.Message;
import message.NormalMessage;
import net.Client;
import net.RecvThread;
import net.sf.json.JSONObject;

//���ε�¼�������
public class SecondUpdataService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
//		-------------------------------------------------------------
		String time = new daoServiceImpl().SelectMaxTimeMusic();
		NormalMessage normalMessage = new NormalMessage();
		normalMessage.setType(Message.SECONDUPDATA);
		normalMessage.setTime(time);
		String msg = JSONObject.fromObject(normalMessage).toString();
		Client.getClient().sendMsg(msg);
	}
}
