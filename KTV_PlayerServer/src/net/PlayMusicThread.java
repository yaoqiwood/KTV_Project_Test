package net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;


//≤•∑≈“Ù¿÷œﬂ≥Ã
public class PlayMusicThread implements Runnable {
	private Socket socket = null;
	private String IP = null;
	private int Port = 0;
	private String SPath = null;

	public PlayMusicThread(String IP, int port, String SPath) {
		this.IP = IP;
		Port = port;
		this.SPath = SPath;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(IP, Port);

			BufferedInputStream bi = new BufferedInputStream(new FileInputStream(SPath));
			BufferedOutputStream bo = new BufferedOutputStream(socket.getOutputStream());

			byte[] buf = new byte[1024 * 1024];
			while (true) {
				int ret = bi.read(buf);
				if (ret < 0) {
					break;
				}
				bo.write(buf, 0, ret);
				bo.flush();
			}


			bi.close();
			bo.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
