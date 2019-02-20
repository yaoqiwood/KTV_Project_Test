package net;

import view.PlayerServerFrame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


//�����ļ���Ϣ���
public class Server {
	ServerSocket server;
	private LinkedList<RecvThread> recvThreadLinkedList = new LinkedList<RecvThread>();
	{
		try {
			int count = 1;
			System.out.println("�������ȴ�����...");
			PlayerServerFrame.getPlayerServerFrame().getPlayerServerJPanel().getjTextArea().append("����˽�������:"+'\n');
			server = new ServerSocket(10089);
//			�����ڶ�ͨ���߳�
			new Thread(new TempServerThread()).start();
			while (true){
				Socket socket = server.accept();
				System.out.println("���������յ�"+socket);
				new Thread(new RecvThread(socket,count)).start();
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<RecvThread> getRecvThreadLinkedList() {
		return recvThreadLinkedList;
	}
}
