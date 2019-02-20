package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//零食重连线程服务
public class TempServiceServer {
	ServerSocket ListServerToPlayerServer;

	public TempServiceServer() {
		try {
//		    --------------------点播服务器与播放服务器连接------------------
			ListServerToPlayerServer = new ServerSocket(10002);
			while (true){
				System.out.println("零食服务器正在等待数据：");
				Socket ListSerToPlSocket = ListServerToPlayerServer.accept();
				System.out.println("零食服务器接收到"+ListSerToPlSocket);
				new Thread(new ListPlayerServer_Recver(ListSerToPlSocket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
