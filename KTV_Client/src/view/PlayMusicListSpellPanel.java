package view;

import ctrl.PlayMusicActionListener;

import javax.swing.*;
import java.awt.*;


//µ„∏Ë√Ê∞Â ∆¥“Ùµ„∏Ë
public class PlayMusicListSpellPanel extends JPanel {
	private PlayMusicActionListener playMusicActionListener = new PlayMusicActionListener();


	private JTextField InputTextField = new JTextField();
	private JButton InputConfirm = new JButton("»∑∂®");
	private JButton ClearConfirm = new JButton("«Âø’");

	private JButton btnSpellA = new JButton();
	private JButton btnSpellB = new JButton();
	private JButton btnSpellC = new JButton();
	private JButton btnSpellD = new JButton();
	private JButton btnSpellE = new JButton();
	private JButton btnSpellF = new JButton();
	private JButton btnSpellG = new JButton();
	private JButton btnSpellH = new JButton();
	private JButton btnSpellI = new JButton();
	private JButton btnSpellJ = new JButton();
	private JButton btnSpellK = new JButton();
	private JButton btnSpellL = new JButton();
	private JButton btnSpellM = new JButton();
	private JButton btnSpellN = new JButton();
	private JButton btnSpellO = new JButton();
	private JButton btnSpellP = new JButton();
	private JButton btnSpellQ = new JButton();
	private JButton btnSpellR = new JButton();
	private JButton btnSpellS = new JButton();
	private JButton btnSpellT = new JButton();
	private JButton btnSpellU = new JButton();
	private JButton btnSpellV = new JButton();
	private JButton btnSpellW = new JButton();
	private JButton btnSpellX = new JButton();
	private JButton btnSpellY = new JButton();
	private JButton btnSpellZ = new JButton();

//	private JButton[] GroupBtnPlay_Exit = new JButton[]{btnPlay, btnExit};
//	private JButton[] GroupBtnSpell_Singer = new JButton[]{btnSpell, btnHotMusic, btnClassify, btnSinger};
//	private JButton[] GroupBtnPrevious_Next = new JButton[]{btnPrevious, btnNext};
	private JButton[] GroupBtnA_Z = new JButton[]{btnSpellA, btnSpellB, btnSpellC, btnSpellD, btnSpellE,
			btnSpellF, btnSpellG, btnSpellH, btnSpellI, btnSpellJ, btnSpellK, btnSpellL, btnSpellM, btnSpellN,
			btnSpellO, btnSpellP, btnSpellQ, btnSpellR, btnSpellS, btnSpellT, btnSpellU, btnSpellV, btnSpellW,
			btnSpellX, btnSpellY, btnSpellZ};

	public PlayMusicListSpellPanel() {
		this.setLayout(null);
		this.setBounds(560, 0, 420, 450);
		this.setVisible(false);

//		-----------------------------------------------------------
		InputTextField.setBounds(135,320,150,30);
		InputTextField.setEditable(false);
		this.add(InputTextField);
		InputConfirm.setBounds(135+150+20,320,70,30);
		InputConfirm.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 14));
		InputConfirm.setActionCommand("InputConfirm");
		InputConfirm.addActionListener(playMusicActionListener);

		ClearConfirm.setBounds(135-20-70,320,70,30);
		ClearConfirm.setFont(new Font("Œ¢»Ì—≈∫⁄", 1, 14));
		ClearConfirm.setActionCommand("ClearConfirm");
		ClearConfirm.addActionListener(playMusicActionListener);
		this.add(InputConfirm);
		this.add(ClearConfirm);
//		-----------------------------------------------------------


		int CharTemp = 65;
		int TempX = 20;
		int TempY = 40;
		int j = 0;
		for (int i = 0; i < 26; i++) {
			String newChar = String.valueOf((char) (CharTemp + i));
			GroupBtnA_Z[i].setText(newChar);
			GroupBtnA_Z[i].setBounds((TempX + j * 50) + (20 * j), TempY, 50, 30);
			GroupBtnA_Z[i].setActionCommand("btnAction" + newChar);
			GroupBtnA_Z[i].addActionListener(playMusicActionListener);
			this.add(GroupBtnA_Z[i]);
			if (i % 5 == 0 && i != 0) {
				TempX = 20;
				TempY += 50;
				j = 0;
			}else {
				j++;
			}
		}
	}

	public JTextField getInputTextField() {
		return InputTextField;
	}
}
