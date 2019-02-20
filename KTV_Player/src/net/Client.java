package net;

import view.ReConnectFrame;
import view.RoomMusicJFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

//客户端发送
public class Client {
	private static PrintWriter writer;
	private static BufferedReader reader;
	private static Socket socket;

	private static Client client;

	public static Client getClient() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	private Client() {
		while (true) {
			try {
				socket = new Socket("127.0.0.1", 10089);
				writer = new PrintWriter(socket.getOutputStream());
				new Thread(new RecvThread(socket)).start();
				ReConnectFrame.getReConnectFrame().setVisible(false);
				break;
			} catch (IOException e) {
				System.out.println(socket);
				try {
					if (socket == null) {
						ReConnectFrame.getReConnectFrame().setVisible(true);
					}
					Thread.sleep(2000);
					System.out.println("服务器未开启或维护，2秒后重新连接");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
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

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
}
