package net;

import view.PlayerServerFrame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


//服务文件信息框架
public class Server {
	ServerSocket server;
	private LinkedList<RecvThread> recvThreadLinkedList = new LinkedList<RecvThread>();
	{
		try {
			int count = 1;
			System.out.println("服务器等待数据...");
			PlayerServerFrame.getPlayerServerFrame().getPlayerServerJPanel().getjTextArea().append("服务端接受数据:"+'\n');
			server = new ServerSocket(10089);
//			启动第二通信线程
			new Thread(new TempServerThread()).start();
			while (true){
				Socket socket = server.accept();
				System.out.println("服务器接收到"+socket);
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
