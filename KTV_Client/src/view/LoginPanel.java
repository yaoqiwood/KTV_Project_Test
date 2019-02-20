package view;

import ctrl.LoginActionListener;

import javax.swing.*;
import java.awt.*;


//µÇÂ¼Ãæ°å
public class LoginPanel extends JPanel {
	private JLabel RoomLoginLabel = new JLabel("ÕËºÅ£º");
	private JLabel PWDLoginLabel = new JLabel("ÃÜÂë£º");
	private JTextField RoomLoginTextField = new JTextField("1");
	private JTextField PWDLoginTextField = new JTextField("admin");
	private JButton LoginJButton = new JButton("µÇÂ¼");
	private JButton QuitJButton = new JButton("ÍË³ö");

	private LoginActionListener loginActionListener = new LoginActionListener();

	public LoginPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, 400, 300);

		this.RoomLoginLabel.setBounds(75,50,100,30);
		this.RoomLoginLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));

		this.RoomLoginTextField.setBounds(175,50,150,30);
		this.RoomLoginTextField.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));

		this.PWDLoginLabel.setBounds(75,100,100,30);
		this.PWDLoginLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));

		this.PWDLoginTextField.setBounds(175,100,150,30);
		this.PWDLoginTextField.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));

		this.LoginJButton.setBounds(110,150,80,30);
		this.LoginJButton.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		this.QuitJButton.setBounds(210,150,80,30);
		this.QuitJButton.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));


		this.add(RoomLoginLabel);
		this.add(PWDLoginLabel);
		this.add(RoomLoginTextField);
		this.add(PWDLoginTextField);
		this.add(LoginJButton);
		this.add(QuitJButton);

		LoginJButton.setActionCommand("LoginJButton");
		QuitJButton.setActionCommand("QuitJButton");
		LoginJButton.addActionListener(loginActionListener);
		QuitJButton.addActionListener(loginActionListener);
	}

	public JLabel getRoomLoginLabel() {
		return RoomLoginLabel;
	}

	public JLabel getPWDLoginLabel() {
		return PWDLoginLabel;
	}

	public JTextField getRoomLoginTextField() {
		return RoomLoginTextField;
	}

	public JTextField getPWDLoginTextField() {
		return PWDLoginTextField;
	}

	public JButton getLoginJButton() {
		return LoginJButton;
	}

	public JButton getQuitJButton() {
		return QuitJButton;
	}

	public LoginActionListener getLoginActionListener() {
		return loginActionListener;
	}
}
