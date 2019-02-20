package view;

import ctrl.PlayerActionListener;
import ctrl.SyncMusic;
import message.MusicBufRequest;
import music.MusicUtil;
import net.Client;
import net.sf.json.JSONObject;

import javax.media.Player;
import javax.media.Time;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//ÒôÀÖ²¥·ÅÆ÷Ãæ°å
public class MusicPlayerJPanel extends JPanel {
	private JButton btn_licences = new JButton("¾²Òô");// ²¥·Å
	private JButton btn_pause = new JButton("ÔÝÍ£");// ÔÝÍ£
	private JButton btn_NextMus = new JButton("ÏÂÒ»Ê×");// ÏÂÒ»Ê×
	private JButton btn_RepeatMus = new JButton("ÖØ³ª");

	private JLabel MusicLabelInfo = new JLabel("ÕýÔÚ²¥·Å");
	private JLabel MusicLabelNextInfo = new JLabel("ÏÂÒ»Ê×");

	private JLabel RoomIDLable = new JLabel("·¿¼äºÅ£º");

	private JLabel TimeLabel = new JLabel("0:0");
	private JLabel EndTimeLabel = new JLabel("0:0");

	private int Min = 0;
	private int Sec = 0;

//	private JSlider jSliderMus = new JSlider();
	private JProgressBar jProgressMus = new JProgressBar();
	private String Room_ID = null;
	private JSlider jSliderVol = new JSlider();
	private PlayerActionListener playerActionListener = new PlayerActionListener();



	public MusicPlayerJPanel() {
		this.setBounds(0,0,800,500);
		this.setLayout(null);

		this.MusicLabelInfo.setBounds(55,30,200,30);
		this.MusicLabelInfo.setFont(new Font("Î¢ÈíÑÅºÚ",0,15));
		this.MusicLabelNextInfo.setBounds(55,80,200,30);
		this.MusicLabelNextInfo.setFont(new Font("Î¢ÈíÑÅºÚ",1,15));



//		jSliderMus.setBounds(55, 330, 690, 30);
		jProgressMus.setBounds(55, 330, 690, 15);
		jSliderVol.setBounds(410, 10, 50, 100);
		btn_licences.setBounds(360,440,80,30);
		btn_licences.setFont(new Font("Î¢ÈíÑÅºÚ",1,15));
		btn_pause.setBounds(210,400,80,30);
		btn_pause.setFont(new Font("Î¢ÈíÑÅºÚ",1,15));
		btn_NextMus.setBounds(360,400,80,30);
		btn_NextMus.setFont(new Font("Î¢ÈíÑÅºÚ",1,15));
		btn_RepeatMus.setBounds(510,400,80,30);
		btn_RepeatMus.setFont(new Font("Î¢ÈíÑÅºÚ",1,15));

		RoomIDLable.setBounds(670,30,100,30);
		RoomIDLable.setFont(new Font("Î¢ÈíÑÅºÚ",0,15));

		TimeLabel.setBounds(60,350,80,30);
		TimeLabel.setFont(new Font("Î¢ÈíÑÅºÚ",0,14));

		EndTimeLabel.setBounds(705,350,80,30);
		EndTimeLabel.setFont(new Font("Î¢ÈíÑÅºÚ",0,14));

		jSliderVol.setBounds(650,300,100,30);
		jSliderVol.setOrientation(JSlider.HORIZONTAL);
		jSliderVol.setMaximum(50);

//		jSliderMus.setEnabled(false);

		btn_licences.setActionCommand("btn_license");
		btn_licences.addActionListener(playerActionListener);

		btn_pause.setActionCommand("btn_pause");
		btn_pause.addActionListener(playerActionListener);

		btn_RepeatMus.setActionCommand("btn_RepeatMus");
		btn_RepeatMus.addActionListener(playerActionListener);

		btn_NextMus.setActionCommand("btn_NextMus");
		btn_NextMus.addActionListener(playerActionListener);

		this.add(btn_licences);
		this.add(btn_pause);
		this.add(btn_NextMus);
		this.add(btn_RepeatMus);
//		this.add(jSliderMus);
		this.add(jProgressMus);
		this.add(jSliderVol);
		this.add(RoomIDLable);
		this.add(jSliderVol);
		this.add(MusicLabelInfo);
		this.add(MusicLabelNextInfo);
		this.add(TimeLabel);
		this.add(EndTimeLabel);

		FirstPlayMusRequest();  //³õ´Î²¥·ÅÇëÇó
		new Thread(new SyncMusic()).start();

//		jSliderMus.addMouseListener(new MouseAdapter() {
//			public void mouseReleased(MouseEvent e) {
//				Player player = MusicUtil.getMusicUtil().getPlayer();
//				// TODO Auto-generated method stub
//				super.mouseReleased(e);
//				int sec = jSliderMus.getValue();
//				if (player !=null && player.getState() == Player.Started) {
//					Time time = new Time((double)sec);
//					player.setMediaTime(time);
//					jSliderMus.setValue(sec);
//				}
//			}
//		});
//		jProgressMus.addMouseListener(new MouseAdapter() {
//			public void mouseReleased(MouseEvent e) {
//				Player player = MusicUtil.getMusicUtil().getPlayer();
//				// TODO Auto-generated method stub
//				super.mouseReleased(e);
//				int sec = jProgressMus.getValue();
//				if (player !=null && player.getState() == Player.Started) {
//					Time time = new Time((double)sec);
//					player.setMediaTime(time);
//					jProgressMus.setValue(sec);
//				}
//			}
//		});

		jSliderVol.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				Player player = MusicUtil.getMusicUtil().getPlayer();
				// TODO Auto-generated method stub
				super.mouseReleased(arg0);
				int value = jSliderVol.getValue();
				if (player != null) {
					float level = (float)value / 100;
					player.getGainControl().setLevel(level);
				}
			}
		});

	}


	public void FirstPlayMusRequest(){
		String msg = JSONObject.fromObject(new MusicBufRequest()).toString();
		Client.getClient().sendMsg(msg);
	}

	public String getRoom_ID() {
		return Room_ID;
	}

	public void setRoom_ID(String room_ID) {
		Room_ID = room_ID;
	}

	public JLabel getRoomIDLable() {
		return RoomIDLable;
	}

//	public JSlider getjSliderMus() {
//		return jSliderMus;
//	}

	public JProgressBar getjProgressMus() {
		return jProgressMus;
	}

	public JLabel getMusicLabelInfo() {
		return MusicLabelInfo;
	}

	public JButton getBtn_pause() {
		return btn_pause;
	}

	public JSlider getjSliderVol() {
		return jSliderVol;
	}

	public JLabel getMusicLabelNextInfo() {
		return MusicLabelNextInfo;
	}

	public JLabel getTimeLabel() {
		return TimeLabel;
	}

	public JLabel getEndTimeLabel() {
		return EndTimeLabel;
	}

	public int getMin() {
		return Min;
	}

	public void setMin(int min) {
		Min = min;
	}

	public int getSec() {
		return Sec;
	}

	public void setSec(int sec) {
		Sec = sec;
	}
}
