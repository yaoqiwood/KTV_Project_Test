package service;

import message.Message;
import net.RecvThread;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;
import view.PlayMusicListPanel;
import view.PlayMusicListSpellPanel;

import javax.swing.*;


//���Ÿ�������
public class HotMusicService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		JSONArray jsonArray = object.getJSONArray("musicBeanArrayList");

//      ---------------------------------------
		PlayMusicListPanel playMusicListPanel = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel();
		PlayMusicListSpellPanel playMusicListSpellPanel = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel();
		JTable jTable = playMusicListPanel.getPlayMusicJTable().getTable();
//      ---------------------------------------
		PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setSelectMode(Message.HOTMUSIC);
		String[] colname = {"����ID", "����", "�ȶ�", "�������ʱ��"};
		playMusicListPanel.getPlayMusicJTable().getModel().setColumnIdentifiers(colname);
		playMusicListPanel.getPlayMusicJTable().getModel().setRowCount(0);
		try {
			for (Object i : jsonArray) {
				JSONObject TempObject = JSONObject.fromObject(i);
				String music_id = TempObject.getString("music_id");
				String music_length = TempObject.getString("music_length");
				String music_name = TempObject.getString("music_name");
				String update_time = TempObject.getString("update_time");
				String Hot = TempObject.getString("hot");
				String[] temp = new String[]{music_id, music_name, Hot, update_time};
				playMusicListPanel.getPlayMusicJTable().getModel().addRow(temp);
			}
		} catch (Exception e) {

		}

	}
}
