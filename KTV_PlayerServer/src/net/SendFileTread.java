package net;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

//发送音乐文件线程
public class SendFileTread implements Runnable {

	private Socket socket = null;
	private String IP = null;
	private int Port = 0;
	private String SPath = null;

	public SendFileTread(String IP, int Port, String SPath) {
		this.IP = IP;
		this.Port = Port;
		this.SPath = SPath;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(IP, Port);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(SPath));
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

			byte[] buf = new byte[1024 * 1024];
			while (true) {
				int ret = bufferedInputStream.read(buf);
				if (ret < 0) {
					break;
				}
				bufferedOutputStream.write(buf,0,ret);
				bufferedOutputStream.flush();
			}
			bufferedInputStream.close();
			bufferedOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
