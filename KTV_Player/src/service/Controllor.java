package service;

import message.Message;

import java.util.HashMap;
import java.util.Map;

//¿ØÖÆÆ÷
public class Controllor {
	private static Map<String,IService> serviceMap = new HashMap<>();

	static {
		serviceMap.put(Message.ROOMSELECT,new RoomSelectService());
		serviceMap.put(Message.ROOMBINDACCESS,new RoomBindReService());
		serviceMap.put(Message.PLAYMUSIC,new PlayMusicService());
		serviceMap.put(Message.TOPMUSIC,new TopMusicService());
		serviceMap.put(Message.TOPNANEXT,new TopAndNextService());
		serviceMap.put(Message.LOGOUT,new LogOutService());
		serviceMap.put(Message.NEWMUSICPLAY,new NewMusicPlayService());
		serviceMap.put(Message.RECONNECT,new ReConnectService());
		serviceMap.put(Message.CONNECTMESSAGE,new ConnectService());
		serviceMap.put(Message.THELASTMUSIC,new TheLastService());
	}

	public static IService getService(String type){
		return serviceMap.get(type);
	}
}
