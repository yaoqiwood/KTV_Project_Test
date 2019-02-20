package view;

import ctrl.PlayMusicActionListener;

import javax.swing.*;
import java.awt.*;


//ÒôÀÖComboxÁÐ±í
public class PlayMusicListComboxPanel extends JPanel {
	private PlayMusicActionListener playMusicActionListener = new PlayMusicActionListener();

//	private JButton btnPlay = new JButton("µã²¥");// ²¥·Å
//	private JButton btnExit = new JButton("ÍË³ö");// ÍË³ö
//
//	private JButton btnSpell = new JButton("Æ´Òôµã¸è");
//	private JButton btnHotMusic = new JButton("ÈÈÃÅ");
//	private JButton btnClassify = new JButton("·ÖÀàµã¸è");
//	private JButton btnSinger = new JButton("ÆÕÍ¨µã¸è");
//
//	private JButton btnPrevious = new JButton("ÉÏÒ»Ò³");
//	private JButton btnNext = new JButton("ÏÂÒ»Ò³");
//
	private JComboBox ListJCombox = new JComboBox();
	private JButton BtnConfirm = new JButton("È·¶¨");

	private DefaultComboBoxModel model = new DefaultComboBoxModel();

//	private JButton[] GroupBtnPlay_Exit = new JButton[]{btnPlay, btnExit};
//	private JButton[] GroupBtnSpell_Singer = new JButton[]{btnSpell, btnHotMusic, btnClassify, btnSinger};
//	private JButton[] GroupBtnPrevious_Next = new JButton[]{btnPrevious, btnNext};

	public PlayMusicListComboxPanel() {
		this.setLayout(null);
		this.setBounds(600, 0, 300, 450);
		this.setVisible(false);

		int temp = -10;
//		for (JButton i : GroupBtnSpell_Singer) {
//			i.setBounds(20, temp += 50, 100, 30);
//			i.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 16));
//			this.add(i);
//		}
//
//		temp = 110;
//		for (JButton i : GroupBtnPlay_Exit) {
//			i.setBounds(temp += 100, 380, 80, 30);
//			i.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 16));
//			this.add(i);
//			i.addActionListener(playMusicActionListener);
//		}
//
//		this.btnExit.setActionCommand("btnExit");

//
//		temp = -30;
//		for (JButton i : GroupBtnPrevious_Next) {
//			i.setBounds(temp += 200, 340, 80, 30);
//			i.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 14));
//			this.add(i);
//		}

		ListJCombox.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 14));
		ListJCombox.setBounds(90,100,120,30);

		ListJCombox.setModel(model);

		BtnConfirm.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 14));
		BtnConfirm.setBounds(110,200,80,30);
		BtnConfirm.setActionCommand("Confirm");
		BtnConfirm.addActionListener(playMusicActionListener);
		this.add(ListJCombox);
		this.add(BtnConfirm);
	}

	public JComboBox getListJCombox() {
		return ListJCombox;
	}

	public DefaultComboBoxModel getModel() {
		return model;
	}
}
