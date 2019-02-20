package service;

import dao.ServiceDaoImpl;
import message.Message;
import message.NormalMessage;
import message.ShowMessage;
import net.ListServer_Sender;
import net.RecvThread;
import net.sf.json.JSONObject;

// 音乐列表服务
public class PlayMusicListService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		String musicID = object.getString("musicID");
		String RoomID = handle.getServerToID();

		boolean ret = new ServiceDaoImpl().InserIntoPlayList(musicID, RoomID);
		if (ret) {
			String msg = JSONObject.fromObject(new ShowMessage("Insert_Suc")).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();
		}

//		查询歌曲总数
		int count = new ServiceDaoImpl().CountListUnplay(RoomID);
		if (count == 1) {
			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.NEWMUSICPLAY);
			normalMessage.setRoom_id(RoomID);
			String msg = JSONObject.fromObject(normalMessage).toString();
			ListServer_Sender.getListServer_sender().SendMsg(msg);
		}


	}
}
