package util;

import java.io.*;


//文件传输公共类
public class FileUtil {
	public static void copyFile(String sPath, String oPath) {
		// 文件拷贝
		BufferedInputStream bi = null;
		BufferedOutputStream bo = null;
		try {
			bi = new BufferedInputStream(new FileInputStream(sPath));
			bo = new BufferedOutputStream(new FileOutputStream(oPath));

			byte buf[] = new byte[1024 * 1024];

			while (true) {
				int ret = bi.read(buf);
				if (ret < 0) {
					break;
				}
				bo.write(buf, 0, ret);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bi != null) {
					bi.close();
				}
				if (bo != null) {
					bo.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
