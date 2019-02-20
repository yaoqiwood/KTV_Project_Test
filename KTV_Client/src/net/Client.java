package net;

import view.LoginFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

//客户端socket请求发送

public class Client {
	private PrintWriter writer;
	private BufferedReader reader;

	private static Client client;
	private static Socket socket;

	public static Client getClient() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	private Client() {
		try {
			socket = new Socket("127.0.0.1", 10001);
			writer = new PrintWriter(socket.getOutputStream());
			new Thread(new RecvThread(socket)).start();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(LoginFrame.getLoginFrame(),"当前服务器正在维护或已关闭，请稍后重试。");
			try {
				socket.close();
			} catch (IOException e1) {
//				e1.printStackTrace();
			}
//			e.printStackTrace();
		}

	}

	public void sendMsg(String sMsg) {
		if (writer != null) {
			writer.println(sMsg);
			writer.flush();
		}

	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public BufferedReader getReader() {
		return reader;
	}
}
