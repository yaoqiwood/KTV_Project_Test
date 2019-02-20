package net;


import view.MusicPlayerJFrame;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//接受文件线程
public class RecvFileThread implements Runnable {
	private ServerSocket server = null;
	private int Port = 0;
	private String OPath;
	private String file_length;

	public RecvFileThread(int Port, String OPath, String file_length) {
		this.Port = Port;
		this.OPath = OPath;
		this.file_length = file_length;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(Port);
			Socket socket = server.accept();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(OPath));

//			JProgressBar progressBar = FileFrame.getFileFrame().getFileJPanel().getjProgressBar();
//			progressBar.setMaximum(Integer.parseInt(file_length));
			byte[] buf = new byte[1024 * 1024];
			int total = 0;
			while (true) {
				int ret = bufferedInputStream.read(buf);
				if (ret < 0) {
					break;
				}
				bufferedOutputStream.write(buf, 0, ret);
				bufferedOutputStream.flush();
				total += ret;
//				progressBar.setValue(total);

				if (total == Integer.parseInt(file_length)){
					JOptionPane.showMessageDialog(MusicPlayerJFrame.getMusicPlayerJFrame(), "文件接收成功");
//					progressBar.setValue(0);
				}
			}

			server.close();
			socket.close();
			bufferedInputStream.close();
			bufferedOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
