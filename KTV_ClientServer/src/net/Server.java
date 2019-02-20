package net;

import bean.MusicBean;
import dao.ServiceDaoImpl;
import net.sf.json.JSONObject;
import service.FileWriter;
import view.ServerFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


//服务框架
public class Server {
	ServerSocket server;
	private LinkedList<RecvThread> recvThreadLinkedList = new LinkedList<RecvThread>();
	{
		JTextArea area = ServerFrame.getServerFrame().getServerPanel().getjTextArea();

		try {
			int count = 1;
			System.out.println("服务器等待数据...");
			initialization();
			area.append("服务器等待数据\n");
			server = new ServerSocket(10001);
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

	private void initialization() {
//			-----------------------------------------
		ArrayList<MusicBean> musicBeanArrayList = new ServiceDaoImpl().SelectMusicArraylist();
		String str = "";
		for (MusicBean i : musicBeanArrayList) {
			str += JSONObject.fromObject(i).toString();
			str += '\n';
		}
		String path = "./Util/JSon.txt";
		FileWriter.getFileWriter().FileWriter(path, str);
//			-----------------------------------------
	}


	public LinkedList<RecvThread> getRecvThreadLinkedList() {
		return recvThreadLinkedList;
	}
}
