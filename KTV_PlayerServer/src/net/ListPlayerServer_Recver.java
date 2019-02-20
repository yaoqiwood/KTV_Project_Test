package net;


import bean.MusicFile;
import bean.RoomID;
import dao.FileDaoImpl;
import message.*;
import net.sf.json.JSONObject;
import view.PlayerServerFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//  服务端与服务端消息之间的传输线程接受
public class ListPlayerServer_Recver implements Runnable {
	private Socket socket = null;
	public static Map<String, Socket> map = new HashMap<>();
	private BufferedReader reader;
	private PrintWriter writer;
	String rMsg;
	private JSONObject object;

	public ListPlayerServer_Recver(Socket socket) {
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
			try {
				rMsg = reader.readLine();
				System.out.println(rMsg);
				if (rMsg == null){
					continue;
				}

				object = JSONObject.fromObject(rMsg);
				String room_id = object.getString("room_id");
				String type = object.getString("type");
				Socket socketTemp = RecvThread.map.get(room_id);
				map.put(room_id, socket);
				System.out.println(room_id);
				System.out.println(socketTemp);
				try {
					writer = new PrintWriter(socketTemp.getOutputStream());
				} catch (Exception e){
					e.printStackTrace();
				}

				if (type.equals(Message.TOPMUSIC)) {
					ArrayList<MusicFile> musicFileArrayList = new FileDaoImpl().SelectPlaylist(room_id);
					String music_name = musicFileArrayList.get(0).getMusic_name();
					String msg = JSONObject.fromObject(new TopNameChangeMessage(music_name)).toString();
					SendMsg(msg);
				} else if (type.equals(Message.TOPNANEXT)) {
					String msg = JSONObject.fromObject(new TopAndNextMessage()).toString();
					SendMsg(msg);
				} else if (type.equals(Message.LOGOUT)) {
					String roomID = object.getString("room_id");
					String msg = JSONObject.fromObject(new LogOutMessage(roomID)).toString();
					boolean ret = new FileDaoImpl().UpdateLogServiceState(roomID);
					PlayerServerFrame.getPlayerServerFrame().getPlayerServerJPanel().setLastPlay_listID(null);
					SendMsg(msg);
				} else if (type.equals(Message.NEWMUSICPLAY)) {
					ArrayList<MusicFile> musicFileArrayList = new FileDaoImpl().SelectPlaylist(room_id);
					if (musicFileArrayList.size() == 1) {
						String music_name = musicFileArrayList.get(0).getMusic_name();
						System.out.println(music_name);
						String roomID = object.getString("room_id");
						String msg = JSONObject.fromObject(new NewPlayMessage(roomID, music_name)).toString();
						SendMsg(msg);
					}
				} else if (type.equals(Message.CONNECTREQUEST)){
//					String roomID = object.getString("room_id");
				} else if (type.equals(Message.DELETEID)){
					String roomID = object.getString("room_id");
					ArrayList<MusicFile> musicFileArrayList = new FileDaoImpl().SelectDelectName(roomID);
					if (musicFileArrayList.size()>0){
//						System.out.println(musicFileArrayList.get(0).getMusic_name());
						String music_name = musicFileArrayList.get(0).getMusic_name();
						String msg = JSONObject.fromObject(new TopNameChangeMessage(music_name)).toString();
						SendMsg(msg);
					}else {
						String music_name = "";
						String msg = JSONObject.fromObject(new TopNameChangeMessage(music_name)).toString();
						SendMsg(msg);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

	}

	public void SendMsg(String msg) {
		if (writer != null) {
			writer.println(msg);
			writer.flush();
		}
	}

	public Map<String, Socket> getMap() {
		return map;
	}

	public JSONObject getObject() {
		return object;
	}
}
