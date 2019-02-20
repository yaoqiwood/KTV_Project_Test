package net;

import message.RoomSelectRequest;
import net.sf.json.JSONObject;
import view.RoomMusicJFrame;

//重连发送重连
public class ReConnectSenderThread implements Runnable {
	@Override
	public void run() {
		while (true){
			String msg = JSONObject.fromObject(new RoomSelectRequest()).toString();
			Client.getClient().sendMsg(msg);

			if (RoomMusicJFrame.getRoomMusicJFrame().getRoomMusicJPanel().isSenderLocker()){
				break;
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}



	}
}
