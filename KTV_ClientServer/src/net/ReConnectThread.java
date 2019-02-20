package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


//接受线程
public class ReConnectThread implements Runnable {
	private ListServer_Sender listServer_sender;

	public ReConnectThread(ListServer_Sender listServer_sender) {
		this.listServer_sender = listServer_sender;
	}

	@Override
	public void run() {
		while (true){
			try {
				Socket socket = new Socket("127.0.0.1",10002);
				listServer_sender.setWriter(new PrintWriter(socket.getOutputStream()));
				new Thread(new ListServer_RecvThread(socket)).start();
				break;
			} catch (IOException e) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}

	}
}
