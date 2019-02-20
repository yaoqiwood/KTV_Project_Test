package view;

import ctrl.PlayMusicActionListener;

import javax.swing.*;
import java.awt.*;


//����Combox�б�
public class PlayMusicListComboxPanel extends JPanel {
	private PlayMusicActionListener playMusicActionListener = new PlayMusicActionListener();

//	private JButton btnPlay = new JButton("�㲥");// ����
//	private JButton btnExit = new JButton("�˳�");// �˳�
//
//	private JButton btnSpell = new JButton("ƴ�����");
//	private JButton btnHotMusic = new JButton("����");
//	private JButton btnClassify = new JButton("������");
//	private JButton btnSinger = new JButton("��ͨ���");
//
//	private JButton btnPrevious = new JButton("��һҳ");
//	private JButton btnNext = new JButton("��һҳ");
//
	private JComboBox ListJCombox = new JComboBox();
	private JButton BtnConfirm = new JButton("ȷ��");

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
//			i.setFont(new Font("΢���ź�", 1, 16));
//			this.add(i);
//		}
//
//		temp = 110;
//		for (JButton i : GroupBtnPlay_Exit) {
//			i.setBounds(temp += 100, 380, 80, 30);
//			i.setFont(new Font("΢���ź�", 1, 16));
//			this.add(i);
//			i.addActionListener(playMusicActionListener);
//		}
//
//		this.btnExit.setActionCommand("btnExit");

//
//		temp = -30;
//		for (JButton i : GroupBtnPrevious_Next) {
//			i.setBounds(temp += 200, 340, 80, 30);
//			i.setFont(new Font("΢���ź�", 1, 14));
//			this.add(i);
//		}

		ListJCombox.setFont(new Font("΢���ź�", 1, 14));
		ListJCombox.setBounds(90,100,120,30);

		ListJCombox.setModel(model);

		BtnConfirm.setFont(new Font("΢���ź�", 1, 14));
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
