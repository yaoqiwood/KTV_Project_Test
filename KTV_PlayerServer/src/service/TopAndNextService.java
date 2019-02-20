package service;

import message.TopAndNextResponse;
import net.ListPlayerServer_Recver;
import net.RecvThread;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//K¸è·þÎñ
public class TopAndNextService implements IService {
	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		Socket socket = ListPlayerServer_Recver.map.get(handle.getServerToID());
		System.out.println(socket);
		String room_id = handle.getServerToID();
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			String msg = JSONObject.fromObject(new TopAndNextResponse(room_id)).toString();
			writer.println(msg);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
