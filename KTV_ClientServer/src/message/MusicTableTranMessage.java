package message;


//JSon “Ù¿÷±Ì
public class MusicTableTranMessage {
	private String type = Message.MUSICTXTTABLE;
	private boolean updata = false;
	public MusicTableTranMessage() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isUpdata() {
		return updata;
	}

	public void setUpdata(boolean updata) {
		this.updata = updata;
	}


}
