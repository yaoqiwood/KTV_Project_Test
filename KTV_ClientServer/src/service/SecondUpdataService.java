package service;

import bean.MusicBean;
import dao.ServiceDaoImpl;
import message.Message;
import message.MusicTableTranMessage;
import net.RecvThread;
import net.sf.json.JSONObject;

import java.util.ArrayList;


//二次登录检测更新
public class SecondUpdataService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String time = object.getString("time");
		System.out.println(time);
		ArrayList<MusicBean> musicBeanArrayList = new ServiceDaoImpl().SelectSecondUpdataTime(time);
//		System.out.println(musicBeanArrayList.size());
		if (musicBeanArrayList.size() != 0) {
			FileWriter.getFileWriter().initialization(musicBeanArrayList);
			MusicTableTranMessage musicTableTranMessage = new MusicTableTranMessage();
			musicTableTranMessage.setUpdata(true);
			String msg = JSONObject.fromObject(musicTableTranMessage).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();
		}
	}
}
