package util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

//  ���˷�WAV���ļ� ���ļ���������
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
