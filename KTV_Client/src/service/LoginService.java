package service;

import dao.daoServiceImpl;
import message.Message;
import message.NormalMessage;
import net.Client;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.LoginFrame;
import view.PlayMusicListFrame;

import javax.swing.*;


//��¼����
public class LoginService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		String suc = object.getString("SUC");
		String RoomID = object.getString("roomID");
		if (suc.equals(Message.SUC)){
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"�����¼�ɹ���"+RoomID);
			PlayMusicListFrame.getPlayMusicListFrame().setRoomID(RoomID);
			LoginFrame.getLoginFrame().setVisible(false);
			PlayMusicListFrame.getPlayMusicListFrame().setVisible(true);
			handle.setClient_RoomID(RoomID);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().getRoomLabel().setText("����ţ�"+RoomID);

		}else if (suc.equals(Message.OCCUPY)){
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"��ǰ�����ѵ�¼");
		}else if (suc.equals(Message.FAIL)){
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"��¼ʧ�ܣ��������ķ�������");
		}

	}
}
