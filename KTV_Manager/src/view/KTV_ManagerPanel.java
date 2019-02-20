package view;

import bean.ClassifyBean;
import bean.MusicBean;
import ctrl.ManagerBtnListener;
import dao.FileDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//  ���
public class KTV_ManagerPanel extends JPanel {
	private PlayMusicManagerTable playMusicManagerTable = new PlayMusicManagerTable();      //���
	private DefaultComboBoxModel comboBoxModel_Char = new DefaultComboBoxModel();       //�������ַ�model
	private DefaultComboBoxModel comboBoxModel_Type = new DefaultComboBoxModel();       //����������model


	private JButton importSingle = new JButton("����");       //���밴ť
	private JButton importDictionary = new JButton("��������");     //�������밴ť
	private JButton importEdit = new JButton("�����޸�");       //��Ϣ�޸�

	private JTextField jSpellField = new JTextField();      //ƴд��
	private JLabel SpellLabel = new JLabel("ƴд");       //ƴдlabel

	private JComboBox MusicTypeCombox = new JComboBox();        //����������
	private JLabel MusicTypeLabel = new JLabel("��������");     //��������label

	private JLabel jPageLabel = new JLabel("0/0");      //ҳ��label
	private JButton nextPage = new JButton("��һҳ");
	private JButton previousPage = new JButton("��һҳ");

	private int CurrentPage = 0;        //��ǰҳ��
	private int TotalPage = 0;          //��ҳ��

	private JButton[] btnTemp = new JButton[]{importSingle, importDictionary, importEdit};          //��ť��
	private JButton[] PageBtnGroup = new JButton[]{previousPage, nextPage};         //��ť����ҳ��

	private JComboBox[] FristLineComboxGroup = new JComboBox[]{MusicTypeCombox};        //��������������
	private JLabel[] FirstLineLabelGroup = new JLabel[]{SpellLabel, MusicTypeLabel};        //ƴд��ʾ��

	private ManagerBtnListener managerBtnListener = new ManagerBtnListener();       //����

	private ArrayList<MusicBean> musicBeanArrayList;        //����

	public KTV_ManagerPanel() {
		this.setLayout(null);           //�趨ȫ��
		this.setBounds(0, 0, 800, 600);     //��С


		int width = 60;
		for (JButton i : btnTemp) {         //��������
			i.setBounds(width += 140, 500, 120, 30);
			i.setFont(new Font("΢���ź�", 0, 15));
		}

		width = 90;
		for (JButton i : PageBtnGroup) {        //��������
			i.setBounds(width += 180, 418, 80, 25);
			i.setFont(new Font("΢���ź�", 0, 15));
			this.add(i);
			i.addActionListener(managerBtnListener);
		}


		this.SpellLabel.setBounds(190, 35, 80, 30);
		this.jSpellField.setBounds(270, 35, 100, 30);
		this.MusicTypeLabel.setBounds(410, 35, 80, 30);
		this.MusicTypeCombox.setBounds(510, 35, 100, 30);
		this.jPageLabel.setBounds(380, 418, 100, 30);
		this.jPageLabel.setFont(new Font("΢���ź�", 0, 15));

		for (JLabel i : FirstLineLabelGroup) {
			i.setFont(new Font("΢���ź�", 0, 15));
			this.add(i);
		}

		for (JComboBox i : FristLineComboxGroup) {
			i.setFont(new Font("΢���ź�", 0, 15));
			this.add(i);
		}

		MusicTypeCombox.setModel(comboBoxModel_Type);       //����model
		RefreshTabelData(0, 5);     //ˢ�±�
//		RefreshCharList();      //ˢ������ĸlist
		RefreshClassifyList();  //ˢ������
		PageReset();        //ҳ������
//		���
		this.add(playMusicManagerTable.getPane());
		this.add(jSpellField);
		this.add(importSingle);
		this.add(importDictionary);
		this.add(importEdit);
		this.add(jPageLabel);


//      ���밴�������趨
		importSingle.setActionCommand("importSingle");
		importDictionary.setActionCommand("importDictionary");
		importEdit.setActionCommand("importEdit");

		nextPage.setActionCommand("nextPage");
		previousPage.setActionCommand("previousPage");

		this.importSingle.addActionListener(managerBtnListener);
		this.importDictionary.addActionListener(managerBtnListener);
		this.importEdit.addActionListener(managerBtnListener);

//      ��Ӽ���
		playMusicManagerTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int Row = playMusicManagerTable.getTable().getSelectedRow();
				Object OBspell = playMusicManagerTable.getTable().getValueAt(Row, 5);
				Object OBtype_name = playMusicManagerTable.getTable().getValueAt(Row, 7);


				if (OBspell != null) {
					String spell = OBspell.toString();
//					comboBoxModel_Char.setSelectedItem(spell);
					jSpellField.setText(spell);
				} else {
					jSpellField.setText("");
				}

				if (OBtype_name != null) {
					String type_name = OBtype_name.toString();
					comboBoxModel_Type.setSelectedItem(type_name);
				} else {
					comboBoxModel_Type.setSelectedItem("");
				}
			}
		});
	}


//	ˢ�±�
	public void RefreshTabelData(int CurrentPage, int Offset) {
		musicBeanArrayList = new FileDaoImpl().GetTabelArraylist(Offset, CurrentPage * Offset);
		playMusicManagerTable.getModel().setRowCount(0);
		for (MusicBean i : musicBeanArrayList) {
			String music_id = i.getMusic_id();
			String music_name = i.getMusic_name();
			String music_time = i.getMusic_time();
			String music_path = i.getMusic_path();
			String music_length = i.getMusic_length();
			String spell = i.getSpell();
			String singer_id = i.getSinger();
			String type_id = i.getType_id();
			String type_name = new FileDaoImpl().SelectIDFromType(type_id);
			String[] temp = new String[]{music_id, music_name, music_time, music_path, music_length, spell, singer_id, type_name};
			playMusicManagerTable.getModel().addRow(temp);
		}
	}

//	ˢ��list
	public void RefreshCharList() {
		int temp = 65;
		for (int i = 0; i <= 25; i++) {
			comboBoxModel_Char.addElement((char) (temp + i));
		}
	}

//	ˢ���б�list
	public void RefreshClassifyList() {
		ArrayList<ClassifyBean> classifyBeans = new FileDaoImpl().SelectClassifyList();
		for (ClassifyBean i : classifyBeans) {
			comboBoxModel_Type.addElement(i.getClassifyName());
		}
	}


//	��һҳ
	public void NextPage() {
		int Count = new FileDaoImpl().SelectCount_page();
		if (Count % 5 == 0) {
			TotalPage = Count / 5;
		} else if (Count % 5 != 0) {
			TotalPage = (Count / 5) + 1;
		}
		if (CurrentPage < TotalPage - 1) {
			CurrentPage += 1;
			this.jPageLabel.setText(CurrentPage + 1 + "/" + TotalPage);
			RefreshTabelData(CurrentPage, 5);
		}
	}

//	��һҳ
	public void PreviousPage() {
		int Count = new FileDaoImpl().SelectCount_page();
		if (Count % 5 == 0) {
			TotalPage = Count / 5;
		} else if (Count % 5 != 0) {
			TotalPage = (Count / 5) + 1;
		}
		if (CurrentPage > 0) {
			CurrentPage -= 1;
			this.jPageLabel.setText(CurrentPage + 1 + "/" + TotalPage);
			RefreshTabelData(CurrentPage, 5);
		}
	}

//	ҳ������
	public void PageReset() {
		int Count = new FileDaoImpl().SelectCount_page();
		if (Count % 5 == 0) {
			TotalPage = Count / 5;
		} else if (Count % 5 != 0) {
			TotalPage = (Count / 5) + 1;
		}
		this.jPageLabel.setText(1 + "/" + TotalPage);
		if (TotalPage == 0) {
			this.jPageLabel.setText(0 + "/" + 0);
		}

	}

	public DefaultComboBoxModel getComboBoxModel_Char() {
		return comboBoxModel_Char;
	}

	public void setComboBoxModel_Char(DefaultComboBoxModel comboBoxModel_Char) {
		this.comboBoxModel_Char = comboBoxModel_Char;
	}

	public PlayMusicManagerTable getPlayMusicManagerTable() {
		return playMusicManagerTable;
	}

	public DefaultComboBoxModel getComboBoxModel_Type() {
		return comboBoxModel_Type;
	}

	public JButton getImportSingle() {
		return importSingle;
	}

	public JButton getImportDictionary() {
		return importDictionary;
	}

	public JButton getImportEdit() {
		return importEdit;
	}

	public JTextField getjSpellField() {
		return jSpellField;
	}

	public JLabel getSpellLabel() {
		return SpellLabel;
	}

	public JComboBox getMusicTypeCombox() {
		return MusicTypeCombox;
	}

	public JLabel getMusicTypeLabel() {
		return MusicTypeLabel;
	}

	public JButton getNextPage() {
		return nextPage;
	}

	public JButton getPreviousPage() {
		return previousPage;
	}

	public JButton[] getBtnTemp() {
		return btnTemp;
	}

	public JButton[] getPageBtnGroup() {
		return PageBtnGroup;
	}

	public JComboBox[] getFristLineComboxGroup() {
		return FristLineComboxGroup;
	}

	public JLabel[] getFirstLineLabelGroup() {
		return FirstLineLabelGroup;
	}

	public ManagerBtnListener getManagerBtnListener() {
		return managerBtnListener;
	}

	public ArrayList<MusicBean> getMusicBeanArrayList() {
		return musicBeanArrayList;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getTotalPage() {
		return TotalPage;
	}

	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}
}
