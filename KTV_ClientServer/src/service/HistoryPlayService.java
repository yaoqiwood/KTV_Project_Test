package service;

import bean.MusicBean;
import dao.ServiceDaoImpl;
import message.HistoryPlayMessage;
import net.RecvThread;
import net.sf.json.JSONObject;

import java.util.ArrayList;


//历史消息打开音乐服务
public class HistoryPlayService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		int CurrentPage = object.getInt("currentPage");
		int Offset = object.getInt("offset");
		String RoomID = handle.getServerToID();
		ArrayList<MusicBean> musicBeanArrayList = new ServiceDaoImpl().SelectHistoryMusic(CurrentPage,Offset,RoomID);
		String msg = JSONObject.fromObject(new HistoryPlayMessage(musicBeanArrayList)).toString();
		System.out.println(msg);
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
