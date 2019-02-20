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

//  面板
public class KTV_ManagerPanel extends JPanel {
	private PlayMusicManagerTable playMusicManagerTable = new PlayMusicManagerTable();      //表格
	private DefaultComboBoxModel comboBoxModel_Char = new DefaultComboBoxModel();       //下拉框字符model
	private DefaultComboBoxModel comboBoxModel_Type = new DefaultComboBoxModel();       //类型下拉框model


	private JButton importSingle = new JButton("导入");       //导入按钮
	private JButton importDictionary = new JButton("批量导入");     //批量导入按钮
	private JButton importEdit = new JButton("歌曲修改");       //信息修改

	private JTextField jSpellField = new JTextField();      //拼写框
	private JLabel SpellLabel = new JLabel("拼写");       //拼写label

	private JComboBox MusicTypeCombox = new JComboBox();        //下拉框类型
	private JLabel MusicTypeLabel = new JLabel("音乐类型");     //音乐类型label

	private JLabel jPageLabel = new JLabel("0/0");      //页数label
	private JButton nextPage = new JButton("下一页");
	private JButton previousPage = new JButton("上一页");

	private int CurrentPage = 0;        //当前页数
	private int TotalPage = 0;          //总页数

	private JButton[] btnTemp = new JButton[]{importSingle, importDictionary, importEdit};          //按钮组
	private JButton[] PageBtnGroup = new JButton[]{previousPage, nextPage};         //按钮上下页组

	private JComboBox[] FristLineComboxGroup = new JComboBox[]{MusicTypeCombox};        //音乐类型下拉框
	private JLabel[] FirstLineLabelGroup = new JLabel[]{SpellLabel, MusicTypeLabel};        //拼写提示框

	private ManagerBtnListener managerBtnListener = new ManagerBtnListener();       //监听

	private ArrayList<MusicBean> musicBeanArrayList;        //集合

	public KTV_ManagerPanel() {
		this.setLayout(null);           //设定全局
		this.setBounds(0, 0, 800, 600);     //大小


		int width = 60;
		for (JButton i : btnTemp) {         //批量设置
			i.setBounds(width += 140, 500, 120, 30);
			i.setFont(new Font("微软雅黑", 0, 15));
		}

		width = 90;
		for (JButton i : PageBtnGroup) {        //批量设置
			i.setBounds(width += 180, 418, 80, 25);
			i.setFont(new Font("微软雅黑", 0, 15));
			this.add(i);
			i.addActionListener(managerBtnListener);
		}


		this.SpellLabel.setBounds(190, 35, 80, 30);
		this.jSpellField.setBounds(270, 35, 100, 30);
		this.MusicTypeLabel.setBounds(410, 35, 80, 30);
		this.MusicTypeCombox.setBounds(510, 35, 100, 30);
		this.jPageLabel.setBounds(380, 418, 100, 30);
		this.jPageLabel.setFont(new Font("微软雅黑", 0, 15));

		for (JLabel i : FirstLineLabelGroup) {
			i.setFont(new Font("微软雅黑", 0, 15));
			this.add(i);
		}

		for (JComboBox i : FristLineComboxGroup) {
			i.setFont(new Font("微软雅黑", 0, 15));
			this.add(i);
		}

		MusicTypeCombox.setModel(comboBoxModel_Type);       //设置model
		RefreshTabelData(0, 5);     //刷新表
//		RefreshCharList();      //刷新首字母list
		RefreshClassifyList();  //刷新类型
		PageReset();        //页数重置
//		添加
		this.add(playMusicManagerTable.getPane());
		this.add(jSpellField);
		this.add(importSingle);
		this.add(importDictionary);
		this.add(importEdit);
		this.add(jPageLabel);


//      导入按键命令设定
		importSingle.setActionCommand("importSingle");
		importDictionary.setActionCommand("importDictionary");
		importEdit.setActionCommand("importEdit");

		nextPage.setActionCommand("nextPage");
		previousPage.setActionCommand("previousPage");

		this.importSingle.addActionListener(managerBtnListener);
		this.importDictionary.addActionListener(managerBtnListener);
		this.importEdit.addActionListener(managerBtnListener);

//      添加监听
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


//	刷新表
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

//	刷新list
	public void RefreshCharList() {
		int temp = 65;
		for (int i = 0; i <= 25; i++) {
			comboBoxModel_Char.addElement((char) (temp + i));
		}
	}

//	刷新列表list
	public void RefreshClassifyList() {
		ArrayList<ClassifyBean> classifyBeans = new FileDaoImpl().SelectClassifyList();
		for (ClassifyBean i : classifyBeans) {
			comboBoxModel_Type.addElement(i.getClassifyName());
		}
	}


//	下一页
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

//	上一页
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

//	页码重置
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
