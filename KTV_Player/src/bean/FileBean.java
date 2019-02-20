package bean;

public class FileBean {
	//ÎÄ¼þÊôÐÔ
	private String file_id;
	private String file_name;
	private String file_path;
	private String file_size;
	
	
	public FileBean() {

	}

	public FileBean(String file_id, String file_name, String file_path, String file_size) {
		super();
		this.file_id = file_id;
		this.file_name = file_name;
		this.file_path = file_path;
		this.file_size = file_size;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	
}
