package net;

//import bean.Chat;
//import bean.Friends;
//import bean.User;
//import dao.ChatDaoImpl;
//import dao.UserDaoImpl;
import dao.FileDaoImpl;
import net.sf.json.JSONObject;
import service.Controllor;
import view.PlayerServerFrame;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//�����߳�
public class RecvThread implements Runnable {
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private String rMsg;
	private int num;
	private String ServerToID;
	private String[] data;
	private JSONObject object;
	public static Map<String, Socket> map = new HashMap<>();

	public RecvThread(Socket socket, int num) {
		this.socket = socket;
		this.num = num;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("��" + num + "�ͻ���Socket" + socket);
			try {
				rMsg = reader.readLine();
				System.out.println("����˽�������" + rMsg);
				PlayerServerFrame.getPlayerServerFrame().getPlayerServerJPanel().getjTextArea().append("����˽�������" + rMsg+'\n');
				object = JSONObject.fromObject(rMsg);
				String type = object.getString("type");
				System.out.println(type);
				Controllor.getService(type).doRun(this);
			} catch (IOException e) {
				boolean ret = new FileDaoImpl().recoveryRoomIDState(ServerToID);
				String list_id = PlayerServerFrame.getPlayerServerFrame().getPlayerServerJPanel().getLastPlay_listID();
				boolean last = new FileDaoImpl().AbnormalPlayList(list_id);
//				System.out.println(map.remove(ServerToID));
//				System.out.println(map.get(ServerToID));
				e.printStackTrace();
				break;
			}
		}
	}

	public void SendsMsgs(String sMsg) {
		writer.println(sMsg);
		writer.flush();
	}


	public Socket getSocket() {
		return socket;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public String getServerToID() {
		return ServerToID;
	}

	public void setServerToID(String serverToID) {
		ServerToID = serverToID;
	}

	public JSONObject getObject() {
		return object;
	}

	public String getrMsg() {
		return rMsg;
	}

	public String[] getData() {
		return data;
	}
}
