package net;

public class TempServerThread implements Runnable {
//	������ʳ�߳�
	@Override
	public void run() {
		new TempServiceServer();
	}
}
