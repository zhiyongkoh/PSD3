

public class Slot {
	
	int slot_ID;
	int room_ID;
	int session_ID;
	int capacity;
	String start_Time;
	String end_Time;
	
	public Slot(int slot_ID, int room_ID, int session_ID, int capacity, String start_Time, String end_Time) {
		super();
		this.slot_ID = slot_ID;
		this.room_ID = room_ID;
		this.session_ID = session_ID;
		this.capacity = capacity;
		this.start_Time = start_Time;
		this.end_Time = end_Time;
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
	

}
