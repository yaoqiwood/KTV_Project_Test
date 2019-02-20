package util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

//  过滤非WAV的文件 （文件管理器）
public class WavFileFliter extends FileFilter {

	@Override
	public String getDescription() {
		return "*.wav";
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()){
			return true;
		}else {
			String name = f.getName();
			return name.endsWith(".wav");
		}
	}
}
