package service;

import dao.ServiceDaoImpl;
import message.Message;
import message.NormalMessage;
import net.RecvThread;
import net.sf.json.JSONObject;


//历史上一页
public class HistoryPreviousPageService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String room_id = handle.getServerToID();
		int Count = new ServiceDaoImpl().HistoryPageCount(room_id);
		NormalMessage normalMessage = new NormalMessage();
		normalMessage.setType(Message.HISTORYPREVIOUSPAGE);
		normalMessage.setCount(String.valueOf(Count));
		String msg = JSONObject.fromObject(normalMessage).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
