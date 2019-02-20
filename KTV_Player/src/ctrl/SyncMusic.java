package ctrl;

import music.MusicUtil;
import view.MusicPlayerJFrame;

import javax.media.Player;
import javax.swing.*;


//同步进度调用
public class SyncMusic implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		int Minute = 0;
		int Second = 0;
		while (true) {
			Player player = MusicUtil.getMusicUtil().getPlayer();
//			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjSliderMus().setMaximum(100);
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjProgressMus().setMaximum(100);
			JLabel textLabel = MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getEndTimeLabel();
			String time = textLabel.getText();
			String[] newTemp = time.split(":");
			int Min = Integer.parseInt(newTemp[0]);
			int Sec = Integer.parseInt(newTemp[1]);
			Min *= 60;
			Min += Sec;
//			System.out.println(Min);
//			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjSliderMus().setMaximum(Min - 1);
			MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjProgressMus().setMaximum(Min - 1);
//			int temp = MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjSliderMus().getMaximum();
			int temp = MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjProgressMus().getMaximum();
//			System.out.println("te" + temp);
			if (player != null && player.getState() == Player.Started) {
				int sec = (int) player.getMediaTime().getSeconds();//获取当前播放的位置(时间)
//				System.out.println("sec = " + sec);
				Minute = sec / 60;
				Second = sec % 60;
//				MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjSliderMus().setValue(sec);
				MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getjProgressMus().setValue(sec);
				JLabel jLabel = MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getTimeLabel();
				jLabel.setText(Minute + ":" + Second);
//				System.out.println(sec + "/" + temp);
				if (sec >= temp) {
					MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().FirstPlayMusRequest();
				}
			}


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
