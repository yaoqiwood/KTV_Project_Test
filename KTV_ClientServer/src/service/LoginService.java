package service;

import bean.MusicBean;
import bean.Roomer;
import dao.ServiceDaoImpl;
import message.LoginResponse;
import message.Message;
import message.MusicTableTranMessage;
import message.NormalMessage;
import net.ListServer_Sender;
import net.RecvThread;
import net.sf.json.JSONObject;
import view.ServerFrame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//登录服务
public class LoginService implements IService {

	@Override
	public void doRun(RecvThread handle) {
		JSONObject object = handle.getObject();
		System.out.println(object);
		String RoomID = object.getString("loginRoomID");
		String RoomPsw = object.getString("loginPWD");
		int Status = new ServiceDaoImpl().LoginAccount(RoomID, RoomPsw);
		LoginResponse loginResponse = null;
		if (Status == 0) {
			loginResponse = new LoginResponse(RoomID, RoomPsw);
			boolean otc = new ServiceDaoImpl().UpdateRoomState("1", RoomID); // 0代表首次登录状态 5代表第非首次 1与5都是被占用 3为密码错误
			loginResponse.setSUC(Message.SUC);
			String msg = JSONObject.fromObject(new MusicTableTranMessage()).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();
			SetRoomIDToMap(handle,RoomID);
//			设定当前ID标识
			ServerFrame.getServerFrame().setRoom_id(Integer.parseInt(RoomID));
			ConnectRequest(RoomID);
//			handle.setServerToID(RoomID);
		} else if (Status == 1) {
			loginResponse = new LoginResponse();
			loginResponse.setSUC(Message.OCCUPY);
		} else if (Status == 3) {
			loginResponse = new LoginResponse();
			loginResponse.setSUC(Message.FAIL);
		} else if (Status == 5) {
			loginResponse = new LoginResponse(RoomID, RoomPsw);
			boolean otc = new ServiceDaoImpl().UpdateRoomState("1", RoomID); // 0代表首次登录状态 5代表第非首次 1与5都是被占用 3为密码错误
			loginResponse.setSUC(Message.SUC);

			NormalMessage normalMessage = new NormalMessage();
			normalMessage.setType(Message.SECONDUPDATA);
			String msg = JSONObject.fromObject(normalMessage).toString();
			handle.getWriter().println(msg);
			handle.getWriter().flush();
			SetRoomIDToMap(handle,RoomID);
//			设定当前ID标识
			ServerFrame.getServerFrame().setRoom_id(Integer.parseInt(RoomID));
//			handle.setServerToID(RoomID);
			ConnectRequest(RoomID);
		}

		JSONObject msg = JSONObject.fromObject(loginResponse);
		handle.getWriter().println(msg);
		handle.getWriter().flush();
	}

	public void SetRoomIDToMap(RecvThread handle,String RoomID){
		Map<String, Socket> map = handle.map;
		handle.setServerToID(RoomID);
		map.put(RoomID,handle.getSocket());
	}


	public void ConnectRequest(String RoomID){
		NormalMessage normalMessage = new NormalMessage();
		normalMessage.setType(Message.CONNECTREQUEST);
		normalMessage.setRoom_id(String.valueOf(RoomID));
		String msg = JSONObject.fromObject(normalMessage).toString();
		ListServer_Sender.getListServer_sender().SendMsg(msg);
	}

}
