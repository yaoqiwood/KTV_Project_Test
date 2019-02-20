package service;

import bean.MusicBean;
import message.Message;
import net.RecvThread;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import java.util.ArrayList;

public class HistoryPlayService implements IService {


//	历史记录服务
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		JSONArray jsonArray = object.getJSONArray("musicBeanArrayList");
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		for (Object i : jsonArray){
			JSONObject tempObject = JSONObject.fromObject(i);
			String list_id = tempObject.getString("list_id");
			String music_id = tempObject.getString("music_id");
			String music_name = tempObject.getString("music_name");
			String music_path = tempObject.getString("music_path");
			String singer_name = tempObject.getString("singer_name");
			String spell = tempObject.getString("spell");
			String singer_id = tempObject.getString("singer");
			String type_id = tempObject.getString("type_id");
			String update_time = tempObject.getString("update_time");
//			String[] colname = { "歌曲ID","歌名", "歌手", "歌曲类型", "歌曲添加时间" };
			musicBeanArrayList.add(new MusicBean(list_id, music_name, null, null, null, spell, singer_id, type_id, update_time));
		}
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setSelectMode(Message.HISTORY);
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().RefreshArraylist(musicBeanArrayList);
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setMusicBeanArrayList(musicBeanArrayList);
	}

}
