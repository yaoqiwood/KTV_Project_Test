package service;

import message.MusicTableTranMessage;
import net.Client;
import net.RecvFileThread;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

//�ı��������
public class MusicTxtTranService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getJsonObject();
		boolean flag = object.getBoolean("updata");
		ServerSocket server = null;
		int Port  = 0;
		Port = 10087;
		String IP = "127.0.0.2";
		String msg = JSONObject.fromObject(new MusicTableTranMessage(IP,String.valueOf(Port))).toString();
		if (flag){
			int key = JOptionPane.showConfirmDialog(PlayMusicListFrame.getPlayMusicListFrame(),"���µĸ��£��Ƿ�Ҫ���У�","��ʾ",JOptionPane.OK_CANCEL_OPTION);
			if (key == JOptionPane.OK_OPTION){
				Client.getClient().sendMsg(msg);
			}
		}else {
			Client.getClient().sendMsg(msg);
		}
	}

}
