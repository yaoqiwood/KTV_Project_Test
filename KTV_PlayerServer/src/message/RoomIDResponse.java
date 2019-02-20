package message;

import bean.RoomID;

import java.util.ArrayList;

//房间ID回应
public class RoomIDResponse {
	private String type = Message.ROOMSELECT;
	private ArrayList<RoomID> roomIDArrayList;

	public RoomIDResponse(ArrayList<RoomID> roomIDArrayList) {
		this.roomIDArrayList = roomIDArrayList;
	}

	public RoomIDResponse() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<RoomID> getRoomIDArrayList() {
		return roomIDArrayList;
	}

	public void setRoomIDArrayList(ArrayList<RoomID> roomIDArrayList) {
		this.roomIDArrayList = roomIDArrayList;
	}
}
