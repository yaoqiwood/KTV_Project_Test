package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//��ʳ�����̷߳���
public class TempServiceServer {
	ServerSocket ListServerToPlayerServer;

	public TempServiceServer() {
		try {
//		    --------------------�㲥�������벥�ŷ���������------------------
			ListServerToPlayerServer = new ServerSocket(10002);
			while (true){
				System.out.println("��ʳ���������ڵȴ����ݣ�");
				Socket ListSerToPlSocket = ListServerToPlayerServer.accept();
				System.out.println("��ʳ���������յ�"+ListSerToPlSocket);
				new Thread(new ListPlayerServer_Recver(ListSerToPlSocket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
