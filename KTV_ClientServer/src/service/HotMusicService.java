package service;

import bean.MusicBean;
import dao.ServiceDaoImpl;
import message.HotMusicResponse;
import net.RecvThread;
import net.sf.json.JSONObject;

import java.util.ArrayList;


//热门音乐服务
public class HotMusicService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String RoomID = handle.getServerToID();
		int CurrentPage = object.getInt("currentPage");
		int Offset = object.getInt("offset");
		ArrayList<MusicBean> musicBeanArrayList = new ServiceDaoImpl().SelectHotMusic(CurrentPage,Offset);
		for(MusicBean i :musicBeanArrayList){
			System.out.println(i.getUpdate_time());
		}
		String msg = JSONObject.fromObject(new HotMusicResponse(musicBeanArrayList)).toString();
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}
}
