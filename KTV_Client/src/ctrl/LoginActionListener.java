package ctrl;

import message.LoginJSonRequest;
import net.Client;
import net.sf.json.JSONObject;
import view.LoginFrame;
import view.PlayMusicListFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//登录窗口监听类，专用管理

public class LoginActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		String key = e.getActionCommand();
		if (key.equals("LoginJButton")){
			String RoomID = LoginFrame.getLoginFrame().getLoginPanel().getRoomLoginTextField().getText();
			String RoomPwd = LoginFrame.getLoginFrame().getLoginPanel().getPWDLoginTextField().getText();
			String msg = JSONObject.fromObject(new LoginJSonRequest(RoomID,RoomPwd)).toString();
			Client.getClient().sendMsg(msg);
		}

		if (key.equals("QuitJButton")){
			int TempKey = JOptionPane.showConfirmDialog(LoginFrame.getLoginFrame(), "请问您确认退出吗？","提示",JOptionPane.OK_CANCEL_OPTION);
			if (TempKey == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
		}


	}
}
