package net;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReConnectPServerThread implements Runnable{
	private Socket tempSocket;
	private ListServer_RecvThread listServer_recvThread;

	public ReConnectPServerThread(Socket tempSocket, ListServer_RecvThread listServer_recvThread) {
		this.tempSocket = tempSocket;
		this.listServer_recvThread = listServer_recvThread;
	}

	@Override
	public void run() {
		while (true){
			try {
				Socket socket = new Socket("127.0.0.1",10002);
				listServer_recvThread.getReader().close();
				ListServer_Sender.getListServer_sender().setWriter(new PrintWriter(socket.getOutputStream()));
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
