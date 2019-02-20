package net;

import bean.MusicBean;
import dao.daoServiceImpl;
import net.sf.json.JSONObject;
import view.PlayMusicListFrame;
import view.ProgressBarFrame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


//接受文件线程
public class RecvFileThread implements Runnable {
	private ServerSocket serverSocket = null;
	private BufferedInputStream bi = null;
	private BufferedOutputStream bo = null;
	private int length = 0;

	public RecvFileThread(ServerSocket serverSocket, int length) {
		this.serverSocket = serverSocket;
		this.length = length;
	}

	@Override
	public void run() {
		ProgressBarFrame.getProgressBarFrame().setVisible(true);
		String OPath = "./MusicTxt/JSonTxt.txt";

		ProgressBarFrame.getProgressBarFrame().getjProgressBar().setMaximum(length);
		try {
			Socket socket = serverSocket.accept();
			bi = new BufferedInputStream(socket.getInputStream());
			bo = new BufferedOutputStream(new FileOutputStream(OPath));

			byte[] buf = new byte[1024 * 1024];
			int total = 0;
			while (true) {
				int ret = bi.read(buf);
				if (ret < 0) {
					break;
				}
				total += ret;
				bo.write(buf, 0, ret);
				bo.flush();
				ProgressBarFrame.getProgressBarFrame().getjProgressBar().setValue(total);
			}
			if (total == length) {
				ProgressBarFrame.getProgressBarFrame().setVisible(false);
				readFile(OPath);
			}
			bo.close();
			bi.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFile(String path) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = bufferedReader.readLine()) != null){
				MusicBean musicBean = (MusicBean) JSONObject.toBean(JSONObject.fromObject(line), MusicBean.class);
				boolean flag = new daoServiceImpl().UpdataTableClient(musicBean);
				if (flag){
					System.out.println(flag);
					PlayMusicListFrame.getPlayMusicListFrame().getPlayMusicListPanel().RefreshTable(0,5);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
