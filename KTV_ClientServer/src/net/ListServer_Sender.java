package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 服务端发送消息框架
public class ListServer_Sender {
	private PrintWriter writer = null;
	private BufferedReader reader = null;
	private Socket socket;
	private static ListServer_Sender listServer_sender;
	public static ListServer_Sender getListServer_sender(){
		if (listServer_sender == null){
			listServer_sender = new ListServer_Sender();
		}
		return listServer_sender;
	}

	public ListServer_Sender() {
		try {
			socket = new Socket("127.0.0.1",10002);
			writer = new PrintWriter(socket.getOutputStream());

			new Thread(new ListServer_RecvThread(socket)).start();

		} catch (IOException e) {
//			System.out.println(socket);
			new Thread(new ReConnectThread(this)).start();
//			listServer_sender = null;
			e.printStackTrace();
		}
	}

	public void SendMsg(String msg){
		System.out.println(msg);
//		System.out.println("Writer"+ writer);
		if (writer != null){
			writer.println(msg);
			writer.flush();
		}
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}


	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
