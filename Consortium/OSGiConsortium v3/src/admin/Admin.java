package admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import admin.DBController;
import entity.Room;
import entity.Slot;
import entity.User;

public class Admin implements IAdmin{
	public Admin(){
		
	}
	
	public User displayMenu(User user) throws Exception {
		Scanner sc = new Scanner(System.in);
		String option;
		while(user != null)
		{
			System.out.println("===========   SELECT CHOICE   ===========\n");
			System.out.println("[1] Add New Room");
			System.out.println("[2] Assign Room to Slot");
			System.out.println("[0] Logout");
			System.out.println("=========================================\n");
			System.out.println("Select Choice: ");
			
			option = sc.nextLine();
			
			if(option.equals("1")){
				addRoom();
			}else if(option.equals("2")){
				setRoomToSlot();
			}else if(option.equals("0")){
				user = null;
				System.out.println("Log Out...!!\n");
			}else{
				System.out.println("Wrong input choice\n!!");
			}
		}
		return user;
	}
	
	@Override
	public void setRoomToSlot() throws Exception {
		ArrayList<Slot> slots = dbGetSlots();
		ArrayList<Room> rooms = dbGetRooms();
		Room room;
		Slot slot;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("----ASSIGN ROOM TO SLOT----");
		System.out.println("SlotID\t|\tSessionID\t|\tRoomID\t|\tStart Time\t|\tEnd Time");
		while(!slots.isEmpty())
		{
			slot = slots.remove(0);
			System.out.println(slot.getSlot_ID() + "	\t|\t" + slot.getSession_ID() + "\t|\t" + slot.getRoom_ID() + "\t|\t" + slot.getStart_Time() + "\t|\t" + slot.getEnd_Time());
		}
		System.out.println("Please select which slot to assign room (Input SlotID): ");
		
		int slotID = sc.nextInt();
		
		System.out.println("RoomID\t|\tCapacity\t|\tLocation");
		while(!rooms.isEmpty())
		{
			room = rooms.remove(0);
			System.out.println(room.getRoomID() + "\t|\t" + room.getCapacity() + "\t|\t" + room.getLocation());
		}
		System.out.println("Please select which room to assign (Input RoomID): ");
		
		int roomID = sc.nextInt();
		
		if(dbSetRoomToSlot(slotID, roomID))
			System.out.println("Slot's Room has been changed successfully");
		else
			System.out.println("Change is Unsuccessful");
		System.out.println("Press Enter to Continue...");
		sc.nextLine();
	}
	
	public void addRoom() throws Exception
	{
		ArrayList<Room> rooms = dbGetRooms();
		Room room;

		Scanner sc = new Scanner(System.in);
		
		System.out.println("----ASSIGN ROOM TO SLOT----");
		System.out.println("RoomID\t|\tCapacity\t|\tLocation");
		while(!rooms.isEmpty())
		{
			room = rooms.remove(0);
			System.out.println(room.getRoomID() + "\t|\t" + room.getCapacity() + "\t|\t" + room.getLocation());
		}
		System.out.println("Please input Location of Room: ");
		String location = sc.nextLine();
		
		System.out.println("Please input capacity of room: ");
		int capacity = sc.nextInt();
		
		if(dbAddRoom(capacity, location))
			System.out.println("Room added successfully");
		else
			System.out.println("Addition is Unsuccessful");
		System.out.println("Press Enter to Continue...");
		sc.nextLine();
	}
	
	private ArrayList<Slot> dbGetSlots() throws Exception	{
		DBController db = new DBController();
		ArrayList<Slot> slots = db.getSlots();
		db.close();
		return slots;
	}
	
	private ArrayList<Room> dbGetRooms() throws Exception	{
		DBController db = new DBController();
		ArrayList<Room> rooms = db.getRooms();
		db.close();
		return rooms;
	}
	
	private boolean dbSetRoomToSlot(int slotID, int roomID) throws Exception	{
		DBController db = new DBController();
		boolean b = db.setRoomToSlot(slotID, roomID);
		db.close();
		return b;
	}
	
	private boolean dbAddRoom(int capacity, String location) throws Exception {
		DBController db = new DBController();
		boolean b = db.addRoom(capacity, location);
		db.close();
		return b;
	}
}
