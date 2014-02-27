package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Slot;
import entity.Room;

public class DBController {
	private Connection connect = null;
	//private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	//private ResultSet resultSet = null;
	
	//private String sqlusername = "mympuco2_psd3usr";
	//private String sqlpassword = "situog2014";
	//private String database = "mympuco2_psd3sprint";
	
	private String sqlusername = "root";
	private String sqlpassword = "";
	private String database = "consortium";
	
	public DBController() throws SQLException{
		// try {
			//Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/"+database, sqlusername, sqlpassword);
		//} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
	}
	
	public ArrayList<Slot> getSlots() throws Exception	{
		ArrayList<Slot> slots = new ArrayList<Slot>();
		String query = "SELECT * FROM timeslot";
		PreparedStatement stmt = connect.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			slots.add(new Slot(
					rs.getInt("slotId"), 
					rs.getInt("roomId"), 
					rs.getInt("sessionId"), 
					rs.getInt("capacity"),
					rs.getString("startdate"), 
					rs.getString("enddate")));
		}
		return slots;
	}
	
	public ArrayList<Room> getRooms() throws Exception {
		ArrayList<Room> rooms = new ArrayList<Room>();
		String query = "SELECT * FROM room";
		PreparedStatement stmt = connect.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			rooms.add(new Room(
					rs.getInt("roomId"),  
					rs.getInt("roomCapacity"),
					rs.getString("location")));
		}
		return rooms;
	}
	
	public boolean setRoomToSlot(int slotID, int roomID) throws Exception {
		String query = "UPDATE timeslot SET roomId = ? WHERE slotId = ?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, roomID);
		stmt.setInt(2, slotID);
		int a = stmt.executeUpdate();
		if(a != 0)
			return true;
		return false;
	}
	
	public boolean addRoom(int capacity, String location) throws SQLException
	{
		String query = "INSERT INTO room (roomCapacity, location) VALUES (?, ?)";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, capacity);
		stmt.setString(2, location);
		int a = stmt.executeUpdate();
		if(a != 0)
			return true;
		return false;
	}
	
	public void close() {
		try {
			/*if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			*/
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
