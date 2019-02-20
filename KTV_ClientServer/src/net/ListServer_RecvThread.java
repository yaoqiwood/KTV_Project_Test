package net;

import message.Message;
import message.NormalMessage;
import net.sf.json.JSONObject;
import service.Controllor;
import view.ServerFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


// 接受线程 服务端与服务端
public class ListServer_RecvThread implements Runnable {
	private BufferedReader reader;
	private Socket socket;
	public ListServer_RecvThread(Socket socket) {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true){
			try {
				String msg = reader.readLine();
				System.out.println(msg);
				JSONObject object = JSONObject.fromObject(msg);
				String type = object.getString("type");
				String room_id = object.getString("room_id");
				if(type.equals(Message.TOPNANEXTANDRE)){
					socket = RecvThread.map.get(room_id);
					PrintWriter writer = new PrintWriter(socket.getOutputStream());
					NormalMessage normalMessage = new NormalMessage();
					normalMessage.setType(Message.TOPNANEXTANDRE);
					String msgTemp = JSONObject.fromObject(normalMessage).toString();
					writer.println(msgTemp);
					writer.flush();
				}

			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(ServerFrame.getServerFrame(),"与播放服务端失去连接，5秒钟后将尝试重新连接");
				new Thread(new ReConnectPServerThread(socket,this)).start();
				break;
			}
		}
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
