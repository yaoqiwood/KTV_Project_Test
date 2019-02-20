package net;



import ctrl.SyncMusic;
import music.MusicUtil;
import view.MusicPlayerJFrame;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;


//音乐打开线程播放
public class MusicPlayThread implements Runnable {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private String OPath = null;
	private int file_length = 0;

	public MusicPlayThread(ServerSocket serverSocket, String OPath, int file_length) {
		this.OPath = OPath;
		this.serverSocket = serverSocket;
		this.file_length = file_length;
	}

	@Override
	public void run() {
		try {
			socket = serverSocket.accept();
			BufferedInputStream bi = new BufferedInputStream(socket.getInputStream());
			BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(OPath));

			byte[] buf = new byte[1024 * 1024];
			int total = 0;
			while (true) {
				int ret = bi.read(buf);
				if (ret < 0) {
					break;
				}
				bo.write(buf, 0, ret);
				bo.flush();
				total += ret;
			}

			if (total == file_length) {
				MusicUtil.getMusicUtil().PlayMusic(OPath);
				File file = new File(OPath);
				String time = MusicUtil.getWavTime(file);
				MusicPlayerJFrame.getMusicPlayerJFrame().getMusicPlayerJPanel().getEndTimeLabel().setText(time);
			}

			bi.close();
			bo.close();
			socket.close();
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
