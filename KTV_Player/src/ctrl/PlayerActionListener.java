package ctrl;

import message.BindRequest;
import music.MusicUtil;
import net.Client;
import net.sf.json.JSONObject;
import view.MusicPlayerJFrame;
import view.RoomMusicJFrame;
import view.RoomMusicJPanel;

import javax.media.Player;
import javax.media.Time;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// ´°¿Ú¼àÌýÀà
public class PlayerActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultListModel model = RoomMusicJFrame.getRoomMusicJFrame().getRoomMusicJPanel().getModel();
		RoomMusicJPanel roomMusicJPanel = RoomMusicJFrame.getRoomMusicJFrame().getRoomMusicJPanel();
		String key = e.getActionCommand();

		if (key.equals("Confirm")){
			String value = roomMusicJPanel.getRoomChoserList().getSelectedValue().toString();
			System.out.println(value);
			String msg = JSONObject.fromObject(new BindRequest(value)).toString();
			Client.getClient().sendMsg(msg);

			RoomMusicJFrame.getRoomMusicJFrame().getRoomMusicJPanel().setSenderLocker(true);
		}

		if (key.equals("btn_pause")){
			Player player = MusicUtil.getMusicUtil().getPlayer();
			JButton btnPause = MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getBtn_pause();
			if (player != null) {
				if (player.getState() == Player.Started) {
					player.stop();
					btnPause.setText("¼ÌÐø");
				}else {
					player.start();
					btnPause.setText("ÔÝÍ£");
				}
			}
		}

		if (key.equals("btn_RepeatMus")){
			Player player = MusicUtil.getMusicUtil().getPlayer();
			player.setMediaTime(new Time(0));
		}

		if (key.equals("btn_NextMus")){
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().FirstPlayMusRequest();
		}

		if (key.equals("btn_license")){
			Player player = MusicUtil.getMusicUtil().getPlayer();
			if (player != null){
				player.getGainControl().setLevel(0);
			}
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjSliderVol().setValue(0);
		}


	}
}
