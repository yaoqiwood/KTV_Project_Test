package bean;
//  ����bean ���ڴ�����JSon��Ϣ
public class ClassifyBean {
	private String ClassifyID;
	private String ClassifyName;

	public ClassifyBean(String classifyID, String classifyName) {
		ClassifyID = classifyID;
		ClassifyName = classifyName;
	}

	public ClassifyBean() {
	}

	public String getClassifyID() {
		return ClassifyID;
	}

	public void setClassifyID(String classifyID) {
		ClassifyID = classifyID;
	}

	public String getClassifyName() {
		return ClassifyName;
	}

	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}
}
