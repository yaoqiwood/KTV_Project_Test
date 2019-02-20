package view;

import javax.swing.*;

//µÇÂ¼´°¿Ú
public class LoginFrame extends JFrame {
	private static LoginFrame loginFrame;
	public static LoginFrame getLoginFrame(){
		if (loginFrame==null){
			loginFrame = new LoginFrame();
		}
		return loginFrame;
	}

	private LoginPanel loginPanel = new LoginPanel();

	public LoginFrame(){
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("·¿¼äµÇÂ¼¿ò");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		this.add(loginPanel);

	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}


}
