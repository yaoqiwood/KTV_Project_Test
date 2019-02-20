package service;

import message.Message;

import java.util.HashMap;
import java.util.Map;


//¿ØÖÆÆ÷
public class Controllor {
	private static Map<String,IService> serviceMap = new HashMap<>();

	static {
		serviceMap.put(Message.LOGIN,new LoginService());
		serviceMap.put(Message.MUSICTXTTABLE,new MusicTxtTranService());
		serviceMap.put(Message.REMUSICTXTSEND,new ReMusicTxtEndService());
		serviceMap.put(Message.SECONDUPDATA,new SecondUpdataService());
		serviceMap.put(Message.HOTMUSIC,new HotMusicService());
		serviceMap.put(Message.HISTORY,new HistoryPlayService());
		serviceMap.put(Message.HISTORYNEXTPAGE,new HistoryPageNextService());
		serviceMap.put(Message.HISTORYPREVIOUSPAGE,new HistoryPagePreviousService());
		serviceMap.put(Message.HISTORYPAGERESET,new HistoryPageResetService());
		serviceMap.put(Message.DELETEID,new HistoryDeleteService());
		serviceMap.put(Message.TOPMUSIC,new TopMusicService());
		serviceMap.put(Message.SHOWMESSAGE,new ShowMessageService());
		serviceMap.put(Message.TOPNANEXTANDRE,new TopRefreshNextService());
		serviceMap.put(Message.RECONNECT,new ReConnectService());
		serviceMap.put(Message.HOTPAGECOUNT,new HotPageCountService());

	}

	public static IService getService(String type){
		return serviceMap.get(type);
	}
}
