package view;

import javax.swing.*;
import java.awt.*;

//������ʾ��
public class ReConnectFrame extends JFrame {
	private static ReConnectFrame reConnectFrame;
	public static ReConnectFrame getReConnectFrame(){
		if (reConnectFrame == null){
			reConnectFrame = new ReConnectFrame();
		}
		return reConnectFrame;
	}

	private JPanel ReConJPanel = new JPanel();
	private JLabel ReConLabel = new JLabel("��ǰ������δ������ά����2��󽫳�����������...");

	public ReConnectFrame() {
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("��������");
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		ReConJPanel.setBounds(0,0,500,200);
		ReConJPanel.setLayout(null);
		ReConLabel.setBounds(85,75,330,30);
		ReConLabel.setFont(new Font("΢���ź�",0,14));
		ReConJPanel.add(ReConLabel);
		this.add(ReConJPanel);

	}


}
