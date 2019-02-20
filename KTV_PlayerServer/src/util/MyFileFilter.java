package util;

import java.io.File;
import java.io.FileFilter;

//�ļ�������������
public class MyFileFilter implements FileFilter{
	private String endName;
	
	
	
	public MyFileFilter(String endName) {
		super();
		this.endName = endName;
	}

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		if (pathname.getName().endsWith(endName)) {
			return true;
		}
		
		return false;
	}
	
}
