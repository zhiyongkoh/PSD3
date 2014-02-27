package lecturer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Course;
import entity.Session;
import entity.Slot;
import entity.User;

public class DBController {
	private static Connection connect = null;
	private String sqlusername = "root";
	private String sqlpassword = "891636";
	private String database = "consortium";
	
	public DBController() throws SQLException{
		connect = DriverManager.getConnection("jdbc:mysql://localhost/"+database, sqlusername, sqlpassword);
	}
	
	public ArrayList<Course> getLectCourse(User user) throws Exception{
		ArrayList<Course> cL = new ArrayList<Course>();
		String query = "SELECT * FROM course WHERE lecturerId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, user.getUserId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			cL.add(new Course(rs.getInt("courseId"),rs.getString("courseCode"),rs.getString("courseName"),rs.getInt("lecturerId"),getLectSession(rs.getInt("courseId"))));
		}
		return cL;
	}
	public ArrayList<Course> getLectCourse(int userId) throws Exception{
		ArrayList<Course> cL = new ArrayList<Course>();
		String query = "SELECT * FROM course WHERE lecturerId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			cL.add(new Course(rs.getInt("courseId"),rs.getString("courseCode"),rs.getString("courseName"),rs.getInt("lecturerId"),getLectSession(rs.getInt("courseId"))));
		}
		return cL;
	}
	public ArrayList<Session> getLectSession(int courseId) throws Exception{
		ArrayList<Session> sL = new ArrayList<Session>();
		String query = "SELECT * FROM session WHERE courseId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, courseId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			sL.add(new Session(rs.getInt("sessionId"),rs.getInt("tutorId"),rs.getString("sessionType"),rs.getInt("compulsory"),rs.getInt("courseId"),retrieveTutor(rs.getInt("tutorId"))));
		}
		return sL;
	}
	public ArrayList<Slot> getSessionSlot(int sessionId) throws Exception{
		ArrayList<Slot> s = new ArrayList<Slot>();
		String query = "SELECT * FROM timeslot s INNER JOIN room r ON s.roomId=r.roomId WHERE sessionId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, sessionId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			s.add(new Slot(rs.getInt("slotId"),rs.getInt("roomId"),rs.getInt("sessionId"),rs.getInt("capacity"),rs.getString("startdate"),rs.getString("enddate"),rs.getString("location")));
		}
		return s;
	}
	public String retrieveTutor(int tutorId) throws Exception{
		String name = "";
		String query = "SELECT * FROM user WHERE userid=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, tutorId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			name = rs.getString("name");
		}
		return name;
	}
	
	public void reloadDatabase() throws SQLException {
		System.out.println("\nRefreshing database......");
		Lecturer.listCourse.clear();
		Lecturer.courseMap.clear();
		Lecturer.listLecturer.clear();
		Lecturer.loadCourseData(queryDB("SELECT * FROM course"));
		Lecturer.loadSessionData(queryDB("SELECT * FROM session s, user u WHERE u.userid = s.tutorid"));
		Lecturer.loadLecturerData(queryDB("SELECT * FROM User WHERE usertype = \"lecturer\""));
		Lecturer.loadRoomData(queryDB("SELECT * FROM room"));
		System.out.println("Refresh completed");
	}
	
	public static ResultSet queryDB(String query) throws SQLException{
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	public int updateQueryDB(String query) throws SQLException{
		PreparedStatement stmt = connect.prepareStatement(query);
		int temp = stmt.executeUpdate();
		return temp;
	}
	public void close() {
		try {
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
