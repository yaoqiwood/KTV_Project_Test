package test;

import net.Server;
import view.ServerFrame;

//��main
public class Run {
	public static void main(String[] args) {
		ServerFrame.getServerFrame().setVisible(true);
		new Server();
	}
}
