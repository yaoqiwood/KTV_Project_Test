package net;

import message.ReConnectRequest;
import net.sf.json.JSONObject;
import service.Controllor;
import view.PlayMusicListFrame;

import javax.swing.*;
import java.io.*;
import java.net.Socket;


//接受线程
public class RecvThread implements Runnable {
	private Socket socket;
	private BufferedReader reader;
	private String[] data;
	private String Client_RoomID;
	private JSONObject jsonObject;

	public RecvThread(Socket socket) {
		this.socket = socket;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		while (true) {
			System.out.println("客户端Socket" + socket);
			try {
				String rMsg = reader.readLine();
				System.out.println("客户端接受：" + rMsg);

				jsonObject = JSONObject.fromObject(rMsg);
				String type = jsonObject.getString("type");
				Controllor.getService(type).doRun(this);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(PlayMusicListFrame.getPlayMusicListFrame(),"与服务器失去连接，5秒后将尝试重新连接");
				LineDown(socket,reader);
				break;
			}
		}

	}

	public void LineDown(Socket socketTemp,BufferedReader readerTemp){
		while (true){
			try {
				Socket socket = new Socket("127.0.0.1",10001);
				socketTemp.close();
				readerTemp.close();
				Client.getClient().setWriter(new PrintWriter(socket.getOutputStream()));
				String msg = JSONObject.fromObject(new ReConnectRequest(Client_RoomID)).toString();
				Client.getClient().sendMsg(msg);
				new Thread(new RecvThread(socket)).start();
				break;
			} catch (IOException e1) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				e1.printStackTrace();
			}
		}
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public String[] getData() {
		return data;
	}

	public String getClient_RoomID() {
		return Client_RoomID;
	}

	public void setClient_RoomID(String client_RoomID) {
		Client_RoomID = client_RoomID;
	}
}
