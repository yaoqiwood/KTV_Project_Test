package ctrl;

import bean.FileBean;
import bean.MusicBean;
import dao.FileDaoImpl;
import util.WavFileFliter;
import view.KTV_ManagerFrame;
import view.KTV_ManagerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ManagerBtnListener implements ActionListener {

//	用于监听管理端的按键
	@Override
	public void actionPerformed(ActionEvent e) {
		String key = e.getActionCommand();      //  获取命令信息
		if (key.equals("importSingle")) {       //导入
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 设置文件选择模式
//			设置默认文件夹 可修改
//			chooser.setCurrentDirectory(new File("H:\\卓跃培训日常作业\\2018\\11\\Nov 7th-KTV\\"));
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new WavFileFliter());
			int ret = chooser.showOpenDialog(null);// 打开文件选择器窗口
			if (ret != chooser.APPROVE_OPTION) {// 用户选择的是取消键
				return;
			}
			// 获取用户选择的路径
			File file = chooser.getSelectedFile();
			FileBean fileBean = new FileBean(file.getName(), file.getPath(), String.valueOf(file.length()));
			boolean otc = new FileDaoImpl().importFile(fileBean);
			System.out.println("插入结果：" + otc);
			JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"导入完成！");
		}

		if (key.equals("importDictionary")) {       //导入文件夹包
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 设置文件选择模式
//			chooser.setCurrentDirectory(new File("H:\\卓跃培训日常作业\\2018\\11\\Nov 7th-KTV\\"));
//			chooser.setFileFilter(new WavFileFliter());
			int ret = chooser.showOpenDialog(null);// 打开文件选择器窗口
			if (ret != chooser.APPROVE_OPTION) {// 用户选择的是取消键
				return;
			}

			File file = chooser.getSelectedFile();
			File[] files = file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String name = pathname.getName();
					if (name.endsWith(".wav")) {
						return true;
					}
					return false;
				}
			});
			JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"正在导入...请稍后(点击确定后开始)");

			for (File i : files) {      //遍历集合，写入数据库
				FileBean fileBean = new FileBean(i.getName(), i.getPath(), String.valueOf(i.length()));
				boolean otc = new FileDaoImpl().importFile(fileBean);
				if (otc) {
					System.out.println("插入成功");
				} else {
					System.out.println("插入失败,路径爆炸或已存在");
				}
			}
			JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"导入完成！");
			KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().RefreshTabelData(0,5);
		}

		if (key.equals("importEdit")) {     //编辑导入
//			ComboBoxModel comboBoxModel_char = KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().getComboBoxModel_Char();
			JTextField jSpellText = KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().getjSpellField();
			ComboBoxModel comboBoxModel_type = KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().getComboBoxModel_Type();
//			String spell = comboBoxModel_char.getSelectedItem().toString();
			String spell = jSpellText.getText();
			String type_name = comboBoxModel_type.getSelectedItem().toString();

			JTable table = KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().getPlayMusicManagerTable().getTable();
			int row = table.getSelectedRow();
			String music_id = table.getValueAt(row, 0).toString();
			String type_id = new FileDaoImpl().SelectNameFromType(type_name);

			if (StringToA(spell)) {
				int res = new FileDaoImpl().UpdateEditor(spell, type_id, music_id);
				if (res > 0) {
					System.out.println("修改成功");
					JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"修改成功！");
				}
				KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().RefreshTabelData(0, 5);
			} else {
				JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(), "请按规范输入（只允许大写字母）");
			}
		}

		if (key.equals("nextPage")) {       //下一页
			KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().NextPage();
		}

		if (key.equals("previousPage")){    //上一页
			KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().PreviousPage();
		}
	}

		public boolean StringToA (String str){      // 快速生成A-Z字符
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				int b = (int) c;
				if (!(b >= 65 && c <= 90)) {
					return false;
				}
			}
			return true;
		}
	}
