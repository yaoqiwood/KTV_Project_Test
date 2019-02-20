package net;

public class TempServerThread implements Runnable {
//	重连零食线程
	@Override
	public void run() {
		new TempServiceServer();
	}
}
