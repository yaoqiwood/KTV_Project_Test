package service;


import message.Message;

import java.util.HashMap;
import java.util.Map;


//¿ØÖÆÆ÷
public class Controllor {
	private static Map<String,IService> serviceMap = new HashMap<>();

	static {
		serviceMap.put(Message.LOGIN,new LoginService());
		serviceMap.put(Message.LOGOUT,new LogOutService());
		serviceMap.put(Message.MUSICTXTTABLE,new MusicTxtService());
		serviceMap.put(Message.SECONDUPDATA,new SecondUpdataService());
		serviceMap.put(Message.PLAYMUSICLISTMESSAGE,new PlayMusicListService());
		serviceMap.put(Message.HOTMUSIC,new HotMusicService());
		serviceMap.put(Message.HISTORY,new HistoryPlayService());
		serviceMap.put(Message.HISTORYNEXTPAGE,new HistoryNextPageService());
		serviceMap.put(Message.HISTORYPREVIOUSPAGE,new HistoryPreviousPageService());
		serviceMap.put(Message.HISTORYPAGERESET,new HistoryPageResetService());
		serviceMap.put(Message.DELETEID,new DELETEIDService());
		serviceMap.put(Message.TOPMUSIC,new TopMusicService());
		serviceMap.put(Message.TOPNANEXT,new TopANDNext());
		serviceMap.put(Message.RECONNECT,new ReConnectService());
		serviceMap.put(Message.HOTPAGECOUNT,new HotPageCountService());
	}

	public static IService getService(String type){
		return serviceMap.get(type);
	}
}
