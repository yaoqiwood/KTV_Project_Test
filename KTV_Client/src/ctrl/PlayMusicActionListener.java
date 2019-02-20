package ctrl;

import bean.MusicBean;
import bean.TypeBean;
import dao.daoServiceImpl;
import message.*;
import net.Client;
import net.sf.json.JSONObject;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


//点播窗口专用监听按钮命令 专用管理
public class PlayMusicActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		String key = e.getActionCommand();
//		-------------------------------------------------------
		PlayMusicListPanel playMusicListPanel = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel();
		PlayMusicListSpellPanel playMusicListSpellPanel = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel();
		JTable jTable = playMusicListPanel.getPlayMusicJTable().getTable();
		PlayMusicListComboxPanel playMusicListComboxPanel = PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListComboxPanel();
		ArrayList<TypeBean> typeBeanArrayList = new daoServiceImpl().TypeComboxfill();
//		------------------------------------------------------

		if (key.equals("btnExit")) {
			int TempKey = JOptionPane.showConfirmDialog(PlayMusicListFrame.getPlayMusicListFrame(), "请问您确认退出吗？","提示",JOptionPane.OK_CANCEL_OPTION);
			if (TempKey == JOptionPane.OK_OPTION) {
				String RoomID = PlayMusicListFrame.getPlayMusicListFrame().getRoomID();
				String msg = JSONObject.fromObject(new RoomLogOutRequest(RoomID)).toString();
				Client.getClient().sendMsg(msg);
				System.exit(0);
			}
		}
		if (key.equals("btnSpell")) {
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().setVisible(true);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListComboxPanel().setVisible(false);
			playMusicListPanel.getBtnTopMusic().setVisible(false);
			playMusicListPanel.getBtnDelete().setVisible(false);
			playMusicListPanel.getBtnTop_Next().setVisible(false);
			PlayMusicListFrame.getPlayMusicListFrame().setSize(1000, 450);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().RefreshTableSpell("", 0, 5);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setSelectMode("SpellMode");
		}

		if (key.equals("btnPlay")) {
			int index = jTable.getSelectedRow();
			String MusicID = jTable.getValueAt(index, 0).toString();
			String msg = JSONObject.fromObject(new PlayMusicListMessage(MusicID)).toString();
			Client.getClient().sendMsg(msg);
			System.out.println(msg);
		}

		if (key.equals("btnHotMusic")) {
			String msg = JSONObject.fromObject(new HotMusicMessage(0, 5)).toString();
			Client.getClient().sendMsg(msg);

			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().setPageCurrent(0);
			PlayMusicListFrame.getPlayMusicListFrame().setSize(600, 450);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().setVisible(false);
			playMusicListPanel.getBtnTopMusic().setVisible(false);
			playMusicListPanel.getBtnDelete().setVisible(false);
			playMusicListPanel.getBtnTop_Next().setVisible(false);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().PageHotReset();
		}

		int CharTemp = 65;
		for (int i = 0; i < 26; i++) {
			String newChar = String.valueOf((char) (CharTemp + i));
			JTextField tempTextField = playMusicListSpellPanel.getInputTextField();
			if (key.equals("btnAction" + newChar)) {
				tempTextField.setText(tempTextField.getText() + newChar);
			}
		}

		if (key.equals("InputConfirm")) {
			JTextField tempTextField = playMusicListSpellPanel.getInputTextField();
			playMusicListPanel.RefreshTableSpell(tempTextField.getText(), 0, 5);
			playMusicListPanel.PageSpellReset(tempTextField.getText());
			playMusicListPanel.setSelectMode("SpellMode");
		}

		if (key.equals("Confirm")) {
			DefaultComboBoxModel model = playMusicListComboxPanel.getModel();
			int Index = model.getIndexOf(model.getSelectedItem());
			String type_id = typeBeanArrayList.get(Index).getType_id();
			ArrayList<MusicBean> musicBeanArrayList = new daoServiceImpl().TypeSelectID(type_id);
			playMusicListPanel.RefreshArraylist(musicBeanArrayList);
			playMusicListPanel.setVisible(true);
		}

		if (key.equals("btnClassify")) {
			typeBeanArrayList = new daoServiceImpl().TypeComboxfill();
			playMusicListComboxPanel.getModel().removeAllElements();
			for (TypeBean i : typeBeanArrayList) {
				String type_id = i.getType_id();
				String type_name = i.getType_name();
				playMusicListComboxPanel.getModel().addElement(i.getType_name());
			}
			playMusicListPanel.setVisible(true);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListSpellPanel().setVisible(false);
			PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListComboxPanel().setVisible(true);
			PlayMusicListFrame.getPlayMusicListFrame().setSize(900, 450);
			playMusicListPanel.getBtnDelete().setVisible(false);
			playMusicListPanel.getBtnTopMusic().setVisible(false);
			playMusicListPanel.getBtnTop_Next().setVisible(false);
		}

		if (key.equals("btnHistoryPlay")) {
			String msg = JSONObject.fromObject(new HistoryPlayMessage(0, 5)).toString();
			Client.getClient().sendMsg(msg);

			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.HISTORYPAGERESET);
			String TempNew = JSONObject.fromObject(normalMessage).toString();
			Client.getClient().sendMsg(TempNew);
			playMusicListSpellPanel.setVisible(false);
			playMusicListPanel.getBtnDelete().setVisible(true);
			playMusicListPanel.getBtnTopMusic().setVisible(true);
			playMusicListPanel.getBtnTop_Next().setVisible(true);
			PlayMusicListFrame.getPlayMusicListFrame().setSize(600, 450);
		}

		if (key.equals("btnNext")) {
			playMusicListPanel.btnPageNext();
		}

		if (key.equals("btnPrevious")) {
			playMusicListPanel.btnPagePrevious();
		}

		if (key.equals("btnDelete")) {
			int Row = playMusicListPanel.getPlayMusicJTable().getTable().getSelectedRow();
			String ID = playMusicListPanel.getPlayMusicJTable().getTable().getValueAt(Row, 0).toString();

			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.DELETEID);
			normalMessage.setDeleteID(ID);
			String msg = JSONObject.fromObject(normalMessage).toString();
			Client.getClient().sendMsg(msg);
		}

		if (key.equals("btnTopMusic")) {
			int Row = playMusicListPanel.getPlayMusicJTable().getTable().getSelectedRow();
			String ID = playMusicListPanel.getPlayMusicJTable().getTable().getValueAt(Row, 0).toString();

			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.TOPMUSIC);
			normalMessage.setTopID(ID);
			String msg = JSONObject.fromObject(normalMessage).toString();
			Client.getClient().sendMsg(msg);
		}

		if (key.equals("btnTop_Next")) {
			int Row = playMusicListPanel.getPlayMusicJTable().getTable().getSelectedRow();
			String ID = playMusicListPanel.getPlayMusicJTable().getTable().getValueAt(Row, 0).toString();

			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.TOPNANEXT);
			normalMessage.setTopID(ID);
			String msg = JSONObject.fromObject(normalMessage).toString();
			Client.getClient().sendMsg(msg);
		}

		if (key.equals("ClearConfirm")) {
			playMusicListSpellPanel.getInputTextField().setText("");
		}

	}
}
