package service;

import bean.FileBean;
import net.RecvThread;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import view.RoomMusicJFrame;

import javax.swing.*;

//房间选择服务刷新
public class RoomSelectService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		JSONArray jsonArray = object.getJSONArray("roomIDArrayList");
		DefaultListModel model = RoomMusicJFrame.getRoomMusicJFrame().getRoomMusicJPanel().getModel();
		model.removeAllElements();
		for (Object i : jsonArray) {
			JSONObject temp = JSONObject.fromObject(i);
			model.addElement(temp.getString("roomID"));
		}
	}
}
