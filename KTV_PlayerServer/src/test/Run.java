package test;

import net.Server;
import net.TempServerThread;
import net.TempServiceServer;
import view.PlayerServerFrame;


//Ö÷Mian
public class Run {
	public static void main(String[] args) {
		PlayerServerFrame.getPlayerServerFrame().setVisible(true);
		new Server();
	}
}
