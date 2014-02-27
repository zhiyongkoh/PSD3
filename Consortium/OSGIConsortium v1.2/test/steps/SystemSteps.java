package test.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import admin.DBController;
import entity.*;

public class SystemSteps {
	private int slotid;
	private int roomid;
	private String roomname;
	private int capacity;
	private boolean status;
	
	//add room
	@Given("a $roomname")
	public void givenARoomName(String roomname){
		this.roomname = roomname;
	}
	@Given("$roomcapacity capacity")
	public void givenRoomCapacity(int roomcapacity){
		this.capacity = roomcapacity;
	}
	@When("$roomname is new")
	public void whenRoomNameIsNew() throws Exception{
		DBController db = new DBController();
		ArrayList<Room> rooms = db.getRooms();
		ArrayList<String> locations = new ArrayList<String>();
		for(int i = 0; i < rooms.size(); i++)
		{
			locations.add(rooms.get(i).getLocation());
		}
		this.status = true;
		if(locations.contains(this.roomname))
			this.status = false;
		db.close();
	}
	@Then("$roomname is added to database")
	public void thenAddRoom(String roomname){
		assertThat(this.status, equalTo(true));
	}
	
	//add room
	@Given("a $slotid")
	public void givenASlotID(int slotid){
		this.slotid = slotid;
	}
	@When("$roomid available")
	public void whenRoomIDAvailable() throws Exception{
		DBController db = new DBController();
		ArrayList<Room> rooms = db.getRooms();
		ArrayList<Integer> IDs = new ArrayList<Integer>();
		for(int i = 0; i < rooms.size(); i++)
		{
			IDs.add(rooms.get(i).getRoomID());
		}
		this.status = true;
		if(IDs.contains(this.roomid))
			this.status = false;
		db.close();
	}
	@Then("a room can be assign to that timetable slot")
	public void thenARoomCanBeAssignToThatTimetableSlot(){
		assertThat(this.status, equalTo(true));
	}
}


