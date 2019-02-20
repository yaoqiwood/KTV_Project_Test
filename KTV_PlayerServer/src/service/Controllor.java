package service;

import message.Message;

import java.util.HashMap;
import java.util.Map;

//¿ØÖÆÆ÷
public class Controllor {
	private static Map<String,IService> serviceMap = new HashMap<>();

	static {
		serviceMap.put(Message.ROOMSELECT,new RoomSelectService());
		serviceMap.put(Message.BINDROOM,new BindRoomService());
		serviceMap.put(Message.MUSICBUF,new MusicPlayService());
		serviceMap.put(Message.TOPNANEXT,new TopAndNextService());
		serviceMap.put(Message.RECONNECT,new ReConnectService());
	}

	public static IService getService(String type){
		return serviceMap.get(type);
	}
}
