package entity;

import java.util.*;

public class Slot {
	int slot_ID;
	int room_ID;
	int session_ID;
	int capacity;
	String start_Time;
	String end_Time;
	boolean bookingstates;
	String roomName;
	
	public Slot(int slot_ID, int room_ID, int session_ID, int capacity,
			String start_Time, String end_Time) {
		this.slot_ID = slot_ID;
		this.room_ID = room_ID;
		this.session_ID = session_ID;
		this.capacity = capacity;
		this.start_Time = start_Time;
		this.end_Time = end_Time;
	}
	public Slot(int slot_ID, String roomName, int session_ID, int capacity,
			String start_Time, String end_Time,boolean bookingstates) {
		this.slot_ID = slot_ID;
		this.roomName = roomName;
		this.session_ID = session_ID;
		this.capacity = capacity;
		this.start_Time = start_Time;
		this.end_Time = end_Time;
		this.bookingstates=bookingstates;
	}
	public Slot(int slot_ID, int room_ID, int session_ID, int capacity,
			String start_Time, String end_Time,
			String roomName) {
		this.slot_ID = slot_ID;
		this.room_ID = room_ID;
		this.session_ID = session_ID;
		this.capacity = capacity;
		this.start_Time = start_Time;
		this.end_Time = end_Time;
		this.roomName = roomName;
	}
	public Slot(int slot_ID, int room_ID, int session_ID, int capacity, String start_Time, String end_Time, boolean bookingstates,String roomName) {

		this.slot_ID = slot_ID;
		this.room_ID = room_ID;
		this.session_ID = session_ID;
		this.capacity = capacity;
		this.start_Time = start_Time;
		this.end_Time = end_Time;
		this.bookingstates = bookingstates;
		this.roomName = roomName;
	}
	public int getSlot_ID() {
		return slot_ID;
	}
	public void setSlot_ID(int slot_ID) {
		this.slot_ID = slot_ID;
	}
	public int getRoom_ID() {
		return room_ID;
	}
	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}
	public int getSession_ID() {
		return session_ID;
	}
	public void setSession_ID(int session_ID) {
		this.session_ID = session_ID;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStart_Time() {
		return start_Time;
	}
	public void setStart_Time(String start_Time) {
		this.start_Time = start_Time;
	}
	public String getEnd_Time() {
		return end_Time;
	}
	public void setEnd_Time(String end_Time) {
		this.end_Time = end_Time;
	}
	public boolean getBookingstates() {
		return bookingstates;
	}
	public void setBookingstates(boolean bookingstates) {
		this.bookingstates = bookingstates;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	

}
