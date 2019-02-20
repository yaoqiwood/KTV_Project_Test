package service;

import bean.MusicBean;
import dao.ServiceDaoImpl;
import net.sf.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


//文件写  Json写本文件专用
public class FileWriter {
	private static FileWriter fileWriter;
	public static FileWriter getFileWriter(){
		if (fileWriter == null){
			fileWriter = new FileWriter();
		}
		return fileWriter;
	}

	//	--------------------------------------
	public void FileWriter(String path, String content) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			java.io.FileWriter fileWriter = new java.io.FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(content);
			bw.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialization(ArrayList<MusicBean> musicBeanArrayList){
//		-----------------------------------------
		String str = "";
		for (MusicBean i : musicBeanArrayList) {
			str += JSONObject.fromObject(i).toString();
			str += '\n';
		}
		String path = "./Util/JSon.txt";
		FileWriter.getFileWriter().FileWriter(path, str);
//		-----------------------------------------
	}
}
