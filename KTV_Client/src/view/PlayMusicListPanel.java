package view;

import bean.MusicBean;
import ctrl.PlayMusicActionListener;
import dao.daoServiceImpl;
import message.HistoryPlayMessage;
import message.HotMusicMessage;
import message.Message;
import message.NormalMessage;
import net.Client;
import net.sf.json.JSONObject;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;


//µ„∏Ë∏Ë«˙√Ê∞Â
public class PlayMusicListPanel extends JPanel {
	private JProgressBar jProgressBarMusic = new JProgressBar();

	private JButton btnPlay = new JButton("µ„≤•");// ≤•∑≈
	private JButton btnExit = new JButton("ÕÀ≥ˆ");// ÕÀ≥ˆ

	private JButton btnSpell = new JButton("∆¥“Ùµ„∏Ë");
	private JButton btnHotMusic = new JButton("»»√≈");
	private JButton btnClassify = new JButton("∑÷¿‡µ„∏Ë");
	private JButton btnSingle = new JButton("∆’Õ®µ„∏Ë");

	private JButton btnPrevious = new JButton("…œ“ª“≥");
	private JButton btnNext = new JButton("œ¬“ª“≥");
	private JButton btnHistoryPlay = new JButton("µ„∏Ë¿˙ ∑");

	private JButton btnDelete = new JButton("…æ≥˝");
	private JButton btnTopMusic = new JButton("÷√∂•");
	private JButton btnTop_Next = new JButton("K∏Ë");

	private JLabel RoomLabel = new JLabel("∑øº‰∫≈£∫");

	private int PageCurrent = 0;
	private int PageTotal = 0;
	private JLabel PageNumLabel = new JLabel(PageCurrent + "/" + PageTotal);
	private int HotPageCount = 0;

	private PlayMusicJTable playMusicJTable = new PlayMusicJTable();

	private String SelectMode = "Normal";

	private PlayMusicActionListener playMusicActionListener = new PlayMusicActionListener();

	private JButton[] btnPageGroup = new JButton[]{btnPrevious, btnNext};
	private JButton[] GroupBtnSpell_Singer = new JButton[]{btnSpell, btnHotMusic, btnClassify, btnHistoryPlay};
	private JButton[] GroupBtnPlay_Exit = new JButton[]{btnPlay, btnExit};

	private ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();


	public PlayMusicListPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, 580, 450);
//		--------------------------
		this.setVisible(true);

		int temp = -10;
		for (JButton i : GroupBtnSpell_Singer) {
			i.setBounds(20, temp += 50, 100, 30);
			i.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 16));
			this.add(i);
			i.addActionListener(playMusicActionListener);
		}


		temp = -30;
		for (JButton i : btnPageGroup) {
			i.setBounds(temp += 200, 340, 80, 30);
			i.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 14));
			this.add(i);
			i.addActionListener(playMusicActionListener);
		}


		temp = 110;
		for (JButton i : GroupBtnPlay_Exit) {
			i.setBounds(temp += 100, 380, 80, 30);
			i.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 16));
			this.add(i);
			i.addActionListener(playMusicActionListener);
		}

//		÷ÿ÷√“≥
		if (SelectMode.equals("Normal")) {
			PageNormalReset();
		}

		this.RoomLabel.setBounds(10,2,100,30);
		this.RoomLabel.setFont(new Font("Œ¢»Ì—≈∫⁄",1,14));

		this.PageNumLabel.setBounds(260, 340, 80, 30);
		this.PageNumLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 16));

		this.btnDelete.setBounds(470, 290, 80, 30);
		this.btnDelete.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 16));
		this.btnDelete.setVisible(false);

		this.btnTopMusic.setBounds(140, 290, 80, 30);
		this.btnTopMusic.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 16));
		this.btnTopMusic.setVisible(false);

		this.btnTop_Next.setBounds(305, 290, 80, 30);
		this.btnTop_Next.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 16));
		this.btnTop_Next.setVisible(false);


		this.add(RoomLabel);
		this.add(btnPlay);
		this.add(btnExit);

		this.add(btnSpell);
		this.add(btnHotMusic);
		this.add(btnClassify);
		this.add(btnSingle);
		this.add(btnTopMusic);
		this.add(btnDelete);
		this.add(btnTop_Next);

		this.add(PageNumLabel);
		btnExit.setActionCommand("btnExit");
		btnSpell.setActionCommand("btnSpell");
		btnPlay.setActionCommand("btnPlay");
		btnHotMusic.setActionCommand("btnHotMusic");
		btnClassify.setActionCommand("btnClassify");
		btnHistoryPlay.setActionCommand("btnHistoryPlay");
		btnSingle.setActionCommand("btnSingle");
		btnDelete.setActionCommand("btnDelete");
		btnTopMusic.setActionCommand("btnTopMusic");
		btnTop_Next.setActionCommand("btnTop_Next");

		btnNext.setActionCommand("btnNext");
		btnPrevious.setActionCommand("btnPrevious");

//		btnExit.addActionListener(playMusicActionListener);
		btnDelete.addActionListener(playMusicActionListener);
		btnTopMusic.addActionListener(playMusicActionListener);
		btnTop_Next.addActionListener(playMusicActionListener);
		this.add(playMusicJTable.getPane());
		RefreshTable(0, 5);
	}

	public void RefreshTable(int CurrentPage, int Offset) {
		ArrayList<MusicBean> musicBeanArrayList = new daoServiceImpl().SelectFromTMus(CurrentPage, Offset);
		RefreshArraylist(musicBeanArrayList);
	}

	public void RefreshTableSpell(String SpellWord, int CurrentPage, int Offset) {
		ArrayList<MusicBean> musicBeanArrayList = new daoServiceImpl().SelectFromTMusSpell(SpellWord, CurrentPage, Offset);
		RefreshArraylist(musicBeanArrayList);
	}

	public void RefreshArraylist(ArrayList<MusicBean> musicBeanArrayList) {
		this.playMusicJTable.getModel().setRowCount(0);
		String[] colname = {"∏Ë«˙ID", "∏Ë√˚", "∏Ë ÷", "∏Ë«˙¿‡–Õ", "∏Ë«˙ÃÌº” ±º‰"};
		this.playMusicJTable.getModel().setColumnIdentifiers(colname);

		for (MusicBean i : musicBeanArrayList) {
			String music_id = i.getMusic_id();
			String music_name = i.getMusic_name();
			String spell = i.getSpell();
			String singer_id = i.getSinger();
			String type_id = i.getType_id();
			String update_time = i.getUpdate_time();
			String[] temp = new String[]{music_id, music_name, singer_id, type_id, update_time};
			this.playMusicJTable.getModel().addRow(temp);
		}
//		“˛≤ÿID¡–
		TableColumnModel columnModel = playMusicJTable.getTable().getColumnModel();
		TableColumn column = columnModel.getColumn(0);
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setWidth(0);
		column.setPreferredWidth(0);

		if (musicBeanArrayList.size() == 0) {
			this.PageNumLabel.setText("0/0");
		}
	}

	public void PageSpellReset(String CharTemp) {
		PageTotal = new daoServiceImpl().PageSpellCount(CharTemp);
		if (PageTotal % 5 == 0) {
			PageTotal = PageTotal / 5;
		} else if (PageTotal % 5 != 0) {
			PageTotal = PageTotal / 5 + 1;
		}
		PageNumLabel.setText((1) + "/" + PageTotal);
		if (PageTotal == 0) {
			PageNumLabel.setText((0) + "/" + PageTotal);
		} else if (PageTotal == 1) {
			PageNumLabel.setText(1 + "/" + PageTotal);
		}
	}

	public void PageNormalReset() {
		PageTotal = new daoServiceImpl().SelectCount();
		if (PageTotal % 5 == 0) {
			PageTotal = PageTotal / 5;
		} else if (PageTotal % 5 != 0) {
			PageTotal = PageTotal / 5 + 1;
		}
		PageNumLabel.setText((PageCurrent + 1) + "/" + PageTotal);
	}

	public void PageHistoryReset(int Count) {
		PageTotal = Count;
		if (PageTotal % 5 == 0) {
			PageTotal = PageTotal / 5;
		} else if (PageTotal % 5 != 0) {
			PageTotal = PageTotal / 5 + 1;
		}
		PageNumLabel.setText((PageCurrent + 1) + "/" + PageTotal);
		if (PageTotal == 0) {
			PageNumLabel.setText((0) + "/" + PageTotal);
		}
	}

	public void PageHotReset() {
		NormalMessage normalMessage = new NormalMessage();
		normalMessage.setType(Message.HOTPAGECOUNT);
		String msg = JSONObject.fromObject(normalMessage).toString();
		Client.getClient().sendMsg(msg);
//		PageNumLabel.setText((1) + "/" + HotPageCount);
	}


	public void btnPageNext() {
		JTextField tempTextField = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().getInputTextField();
		int Count = 0;
		if (SelectMode.equals("Normal")) {
			Count = new daoServiceImpl().SelectCount();
			NextNormalPage(Count);
		} else if (SelectMode.equals("HISTORY")) {
			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.HISTORYNEXTPAGE);
			String msg = JSONObject.fromObject(normalMessage).toString();
			Client.getClient().sendMsg(msg);
		} else if (SelectMode.equals("SpellMode")) {
			Count = new daoServiceImpl().PageSpellCount(tempTextField.getText());
			NextSpellPage(Count);
		} else if (SelectMode.equals(Message.HOTMUSIC)) {
			if (PageCurrent < HotPageCount - 1) {
				PageCurrent += 1;
				String msg = JSONObject.fromObject(new HotMusicMessage(PageCurrent, 5)).toString();
				Client.getClient().sendMsg(msg);
				PageNumLabel.setText(PageCurrent + 1 + "/" + HotPageCount);
			}
		}
	}

	public void btnPagePrevious() {
		int Count = 0;
		if (SelectMode.equals("Normal")) {
			Count = new daoServiceImpl().SelectCount();
			PreviousNormalPage(Count);
		} else if (SelectMode.equals("HISTORY")) {
			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.HISTORYPREVIOUSPAGE);
			String msg = JSONObject.fromObject(normalMessage).toString();
			Client.getClient().sendMsg(msg);
		} else if (SelectMode.equals("SpellMode")) {
			JTextField tempTextField = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().getInputTextField();
			Count = new daoServiceImpl().PageSpellCount(tempTextField.getText());
			PreviousSpellPage(Count);
		} else if (SelectMode.equals(Message.HOTMUSIC)) {
			if (PageCurrent > 0) {
				PageCurrent -= 1;
				String msg = JSONObject.fromObject(new HotMusicMessage(PageCurrent, 5)).toString();
				Client.getClient().sendMsg(msg);
				PageNumLabel.setText(PageCurrent + 1 + "/" + HotPageCount);
			}
		}
	}

	private void PreviousNormalPage(int count) {
		if (count % 5 == 0) {
			PageTotal = count / 5;
		} else if (count % 5 != 0) {
			PageTotal = count / 5 + 1;
		}
		if (PageCurrent > 0) {
			PageCurrent -= 1;
			PageNumLabel.setText(PageCurrent + 1 + "/" + PageTotal);
			RefreshTable(PageCurrent, 5);
		}
	}

	public void NextNormalPage(int count) {
		if (count % 5 == 0) {
			PageTotal = count / 5;
		} else if (count % 5 != 0) {
			PageTotal = count / 5 + 1;
		}
		if (PageCurrent < PageTotal - 1) {
			PageCurrent += 1;
			PageNumLabel.setText(PageCurrent + 1 + "/" + PageTotal);
			RefreshTable(PageCurrent, 5);
		}
	}

	public void NextSpellPage(int count) {
		if (count % 5 == 0) {
			PageTotal = count / 5;
		} else if (count % 5 != 0) {
			PageTotal = count / 5 + 1;
			System.out.println(PageTotal);
		}
		if (PageCurrent < PageTotal - 1) {
			PageCurrent += 1;
			PageNumLabel.setText(PageCurrent + 1 + "/" + PageTotal);
			JTextField tempTextField = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().getInputTextField();
			RefreshTableSpell(tempTextField.getText(), PageCurrent, 5);
		}
	}


	public void NextHistoryPage(int count) {
		if (count % 5 == 0) {
			PageTotal = count / 5;
		} else if (count % 5 != 0) {
			PageTotal = count / 5 + 1;
		}
		if (PageCurrent < PageTotal - 1) {
			PageCurrent += 1;
			PageNumLabel.setText(PageCurrent + 1 + "/" + PageTotal);
			String msg = JSONObject.fromObject(new HistoryPlayMessage(PageCurrent, 5)).toString();
			Client.getClient().sendMsg(msg);
		}
	}

	public void PreviousHistoryPage(int count) {
		if (count % 5 == 0) {
			PageTotal = count / 5;
		} else if (count % 5 != 0) {
			PageTotal = count / 5 + 1;
		}

		if (PageCurrent > 0) {
			PageCurrent -= 1;
			PageNumLabel.setText(PageCurrent + 1 + "/" + PageTotal);
			String msg = JSONObject.fromObject(new HistoryPlayMessage(PageCurrent, 5)).toString();
			Client.getClient().sendMsg(msg);
		}
	}

	public void PreviousSpellPage(int count) {
		if (count % 5 == 0) {
			PageTotal = count / 5;
		} else if (count % 5 != 0) {
			PageTotal = count / 5 + 1;
		}

		if (PageCurrent > 0) {
			PageCurrent -= 1;
			PageNumLabel.setText(PageCurrent + 1 + "/" + PageTotal);
			JTextField tempTextField = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().getInputTextField();
			RefreshTableSpell(tempTextField.getText(), PageCurrent, 5);
		}
	}


	public JProgressBar getjProgressBarMusic() {
		return jProgressBarMusic;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnSpell() {
		return btnSpell;
	}

	public JButton getBtnHotMusic() {
		return btnHotMusic;
	}

	public JButton getBtnClassify() {
		return btnClassify;
	}

	public JButton getBtnSingle() {
		return btnSingle;
	}

	public String getSelectMode() {
		return SelectMode;
	}

	public void setSelectMode(String selectMode) {
		SelectMode = selectMode;
	}

	public PlayMusicJTable getPlayMusicJTable() {
		return playMusicJTable;
	}

	public JLabel getPageNumLabel() {
		return PageNumLabel;
	}

	public void setPageNumLabel(JLabel pageNumLabel) {
		PageNumLabel = pageNumLabel;
	}

	public int getPageCurrent() {
		return PageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		PageCurrent = pageCurrent;
	}

	public int getPageTotal() {
		return PageTotal;
	}

	public void setPageTotal(int pageTotal) {
		PageTotal = pageTotal;
	}

	public ArrayList<MusicBean> getMusicBeanArrayList() {
		return musicBeanArrayList;
	}

	public void setMusicBeanArrayList(ArrayList<MusicBean> musicBeanArrayList) {
		this.musicBeanArrayList = musicBeanArrayList;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnTopMusic() {
		return btnTopMusic;
	}

	public JButton getBtnTop_Next() {
		return btnTop_Next;
	}

	public int getHotPageCount() {
		return HotPageCount;
	}

	public void setHotPageCount(int hotPageCount) {
		HotPageCount = hotPageCount;
	}

	public JLabel getRoomLabel() {
		return RoomLabel;
	}
}
