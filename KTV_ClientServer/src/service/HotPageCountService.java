package service;

import dao.ServiceDaoImpl;
import message.Message;
import message.NormalMessage;
import net.RecvThread;
import net.sf.json.JSONObject;


//热门页数总数服务
public class HotPageCountService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		int Count = new ServiceDaoImpl().SelectHotCountNumPage();
		NormalMessage normalMessage = new NormalMessage();
		normalMessage.setType(Message.HOTPAGECOUNT);
		normalMessage.setCount(String.valueOf(Count));
		String msg = JSONObject.fromObject(normalMessage).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
