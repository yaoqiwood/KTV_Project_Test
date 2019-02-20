package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class KTV_ManagerFrame extends JFrame {
//	窗口管理单例
	private static KTV_ManagerFrame ktv_managerFrame;
	public static KTV_ManagerFrame getKtv_managerFrame(){
		if (ktv_managerFrame == null){
			ktv_managerFrame = new KTV_ManagerFrame();
		}
		return ktv_managerFrame;
	}

//	管理面板Panel
	KTV_ManagerPanel ktv_managerPanel = new KTV_ManagerPanel();
	public KTV_ManagerFrame() {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("后台管理");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.add(ktv_managerPanel);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int key = JOptionPane.showConfirmDialog(KTV_ManagerFrame.getKtv_managerFrame(), "请问是否要退出？","提示",JOptionPane.OK_CANCEL_OPTION);
				if (key == JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}

//	取得panel
	public KTV_ManagerPanel getKtv_managerPanel() {
		return ktv_managerPanel;
	}
}
