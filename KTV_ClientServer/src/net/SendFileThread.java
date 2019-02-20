package net;


import java.io.*;
import java.net.Socket;


//发送文件线程
public class SendFileThread implements Runnable {
	private Socket socket = null;
	private String IP = null;
	private RecvThread recvThread = null;
	private int Port = 0;

	public SendFileThread(String IP, int Port,RecvThread recvThread) {
		this.IP = IP;
		this.Port = Port;
		this.recvThread = recvThread;
	}

	@Override
	public void run() {
		while (true){
			BufferedInputStream bi = null;
			BufferedOutputStream bo = null;

			try {
				socket = new Socket(IP, Port);
				String Path = "./Util/JSon.txt";
				File file = new File(Path);
				if (file.exists()) {
					bi = new BufferedInputStream(new FileInputStream(Path));
					bo = new BufferedOutputStream(socket.getOutputStream());

					byte[] buf = new byte[1024 * 1024];
					while (true) {
						int ret = bi.read(buf);
						if (ret < 0) {
							break;
						}
						bo.write(buf,0,ret);
						bo.flush();
					}
					bo.close();
					bi.close();
					socket.close();
					break;
				}
			} catch (IOException e) {
				if (socket == null){
					continue;
				}
				e.printStackTrace();

			}
		}



	}
}
