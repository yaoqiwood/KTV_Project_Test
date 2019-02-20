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

//	���ڼ�������˵İ���
	@Override
	public void actionPerformed(ActionEvent e) {
		String key = e.getActionCommand();      //  ��ȡ������Ϣ
		if (key.equals("importSingle")) {       //����
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// �����ļ�ѡ��ģʽ
//			����Ĭ���ļ��� ���޸�
//			chooser.setCurrentDirectory(new File("H:\\׿Ծ��ѵ�ճ���ҵ\\2018\\11\\Nov 7th-KTV\\"));
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new WavFileFliter());
			int ret = chooser.showOpenDialog(null);// ���ļ�ѡ��������
			if (ret != chooser.APPROVE_OPTION) {// �û�ѡ�����ȡ����
				return;
			}
			// ��ȡ�û�ѡ���·��
			File file = chooser.getSelectedFile();
			FileBean fileBean = new FileBean(file.getName(), file.getPath(), String.valueOf(file.length()));
			boolean otc = new FileDaoImpl().importFile(fileBean);
			System.out.println("��������" + otc);
			JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"������ɣ�");
		}

		if (key.equals("importDictionary")) {       //�����ļ��а�
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// �����ļ�ѡ��ģʽ
//			chooser.setCurrentDirectory(new File("H:\\׿Ծ��ѵ�ճ���ҵ\\2018\\11\\Nov 7th-KTV\\"));
//			chooser.setFileFilter(new WavFileFliter());
			int ret = chooser.showOpenDialog(null);// ���ļ�ѡ��������
			if (ret != chooser.APPROVE_OPTION) {// �û�ѡ�����ȡ����
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
			JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"���ڵ���...���Ժ�(���ȷ����ʼ)");

			for (File i : files) {      //�������ϣ�д�����ݿ�
				FileBean fileBean = new FileBean(i.getName(), i.getPath(), String.valueOf(i.length()));
				boolean otc = new FileDaoImpl().importFile(fileBean);
				if (otc) {
					System.out.println("����ɹ�");
				} else {
					System.out.println("����ʧ��,·����ը���Ѵ���");
				}
			}
			JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"������ɣ�");
			KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().RefreshTabelData(0,5);
		}

		if (key.equals("importEdit")) {     //�༭����
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
					System.out.println("�޸ĳɹ�");
					JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(),"�޸ĳɹ���");
				}
				KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().RefreshTabelData(0, 5);
			} else {
				JOptionPane.showMessageDialog(KTV_ManagerFrame.getKtv_managerFrame(), "�밴�淶���루ֻ�����д��ĸ��");
			}
		}

		if (key.equals("nextPage")) {       //��һҳ
			KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().NextPage();
		}

		if (key.equals("previousPage")){    //��һҳ
			KTV_ManagerFrame.getKtv_managerFrame().getKtv_managerPanel().PreviousPage();
		}
	}

		public boolean StringToA (String str){      // ��������A-Z�ַ�
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
