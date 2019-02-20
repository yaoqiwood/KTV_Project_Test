package service;

import message.Message;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;
import view.RoomMusicJFrame;

import javax.swing.*;

//����󶨷���
public class RoomBindReService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String roomID = object.getString("roomID");
		String suc = object.getString("suc");
		if (suc.equals(Message.SUC)){
			JOptionPane.showMessageDialog(RoomMusicJFrame.getRoomMusicJFrame(), "�����" + roomID + "�ɹ�");
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getRoomIDLable().setText("����ţ�" + roomID);
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().setRoom_ID(roomID);
			MusicPlayerJFrame.getMusicPlayerJFrame().setVisible(true);
			RoomMusicJFrame.getRoomMusicJFrame().setVisible(false);
			handle.setClient_RoomID(roomID);
		}else if (suc.equals(Message.FAIL)){
			JOptionPane.showMessageDialog(RoomMusicJFrame.getRoomMusicJFrame(),"�����ʧ��");
		}

	}
}
