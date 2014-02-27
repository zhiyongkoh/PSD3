package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Course;
import entity.Session;
import entity.Slot;
import entity.User;

public class DBController {
	private Connection connect = null;
	//private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	//private ResultSet resultSet = null;
	
	//private String sqlusername = "mympuco2_psd3usr";
	//private String sqlpassword = "situog2014";
	//private String database = "mympuco2_psd3sprint";
	
	private String sqlusername = "root";
	private String sqlpassword = "891636";
	private String database = "consortium";
	
	public DBController() throws SQLException{
		connect = DriverManager.getConnection("jdbc:mysql://localhost/"+database, sqlusername, sqlpassword);
	}
	
	
	public ArrayList<Course> getCourses(User user) throws Exception {
		ArrayList<Course> cList = new ArrayList<Course>();;
		String query = "SELECT * FROM studcourse s INNER JOIN course c ON s.courseid=c.courseid WHERE studentid=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, user.getUserId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			cList.add(new Course(rs.getInt("courseId"),rs.getString("courseCode"),rs.getString("courseName")));
			//int courseID, String courseCode, String courseName,int lectureID, ArrayList<Session> sList
		}
		
		stmt.close();
		return cList;
	}
	public ArrayList<Slot> getTimetableSlot(int courseId,int userId) throws Exception {
		ArrayList<Slot> sL = new ArrayList<Slot>();
		String query = "SELECT * FROM course c INNER JOIN session s ON c.courseId=s.courseId INNER JOIN timeslot t ON s.sessionId=t.sessionId "
				+ "INNER JOIN room r ON t.roomId=r.roomId WHERE c.courseId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, courseId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			sL.add(new Slot(rs.getInt("slotId"),rs.getString("location"),rs.getInt("sessionId"),rs.getInt("capacity"),rs.getString("startdate"),rs.getString("enddate"),checkBooking(rs.getInt("slotId"),userId)));
		}
		
		return sL;
	}
	public boolean checkBooking(int slotId, int userId) throws SQLException{
		String query = "SELECT * FROM bookslot where slotId=? and userId =?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, slotId);
		stmt.setInt(2, userId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			return true;
		}
		
		stmt.close();
		return false;
	}
	public void bookSlot(int slotId, int userId) throws SQLException{
		String query = "INSERT INTO bookslot(slotId,userId) VALUES (?,?)";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, slotId);
		stmt.setInt(2, userId);
		stmt.executeUpdate();
		stmt.close();
	}
	
	public ArrayList<Course> getCompCourse(int userid) throws Exception {
		
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		String query = "SELECT * FROM studcourse "
				+ "inner join course ON studcourse.courseId = course.courseId "
				+ "inner join session ON studcourse.courseId = session.courseId WHERE session.compulsory = 1 and studcourse.studentid=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, userid);
		//stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs);
		while(rs.next()) {
		//while() AL session
			courseList.add(new Course(rs.getInt("courseId"),rs.getString("courseCode"), rs.getString("courseName"), rs.getInt("lecturerId"), getCompSession(rs.getInt("courseId"),userid)) );
		//array course; course array session; session array slot
		//	ArrayList<Session> sList = new ArrayList<Session>();
		//	session = new session(rs.getInt("userId"),rs.getString("username"),rs.getString("password"),rs.getString("usertype"),rs.getString("name"));
		}
		stmt.close();
		//close();
		return courseList;
	}
	public ArrayList<Session> getCompSession(int courseId,int userId) throws SQLException{
		ArrayList<Session> s = new ArrayList<Session>();
		String query = "SELECT * FROM session s INNER JOIN user u ON s.tutorId=u.userId where compulsory = 1 and courseId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, courseId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			s.add(new Session(rs.getInt("sessionId"), rs.getInt("tutorId"),rs.getString("sessionType"),rs.getInt("compulsory"), rs.getInt("courseId"),getCompTimeTable(rs.getInt("sessionId"),userId),rs.getString("name")));
		}
		stmt.close();
		return s;
		
	}
	public ArrayList<Slot> getCompTimeTable(int sessionId,int userid) throws SQLException{
		
		ArrayList<Slot> slotList = new ArrayList<Slot>();
		String query = "SELECT * FROM timeslot t INNER JOIN room r ON t.roomId=r.roomId where sessionId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, sessionId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			slotList.add(new Slot(rs.getInt("slotId"), rs.getInt("roomId"), rs.getInt("sessionId"),rs.getInt("capacity"), rs.getString("startDate"), rs.getString("endDate"), checkBooking(rs.getInt("slotId"),userid),rs.getString("location")));
		}
		stmt.close();
		return slotList;
	}
	
	public boolean tBookTimeSlot(int studId, int slotId) throws SQLException{
		//ArrayList<Slot> sL = new ArrayList<Slot>();
		String query = "SELECT * FROM studcourse sc "
				+ "INNER JOIN course c ON sc.courseId = c.courseid "
				+ "INNER JOIN session s ON c.courseId = s.courseId "
				+ "INNER JOIN timeslot sl ON sl.sessionId = s.sessionId "
				+ "WHERE sc.studentid=? and sl.slotId=?";
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setInt(1, studId);
		stmt.setInt(2, slotId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			return true;
		}
		return false;
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
