package entity;

public class Room {
	int roomID;
	int capacity;
	String location;
	
	public Room(int roomID, int capacity, String location) {
		super();
		this.roomID = roomID;
		this.capacity = capacity;
		this.location = location;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
