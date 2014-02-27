package lecturer.cook.model;

import java.util.ArrayList;

import entity.Slot;

public class Session {
	private int sessionID;
	private int tutorID;
	private String sessionType;
	private int compulsory;
	private int courseID;
	private ArrayList<Slot> slotList;
	private String tutorName;
	
	public Session(int sessionID, int tutorID, String sessionType,int compulsory, int courseID,String tutorName) {
		this.sessionID = sessionID;
		this.tutorID = tutorID;
		this.sessionType = sessionType;
		this.courseID = courseID;
		this.compulsory = compulsory;
		this.tutorName = tutorName;
	}
	public Session(int sessionID, int tutorID, String sessionType, int compulsory, int courseID, ArrayList<Slot> slotList,String tutorName) {
		this.sessionID = sessionID;
		this.courseID = courseID;
		this.compulsory = compulsory;
		this.sessionType = sessionType;
		this.tutorID = tutorID;
		this.slotList = slotList;
		this.tutorName = tutorName;
	}
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	public int getTutorID() {
		return tutorID;
	}
	public void setTutorID(int tutorID) {
		this.tutorID = tutorID;
	}
	public int getCompulsory() {
		return compulsory;
	}
	public void setCompulsory(int compulsory) {
		this.compulsory = compulsory;
	}
	public ArrayList<Slot> getSlotList() {
		return slotList;
	}
	public void setSlotList(ArrayList<Slot> slotList) {
		this.slotList = slotList;
	}
	public String getTutorName() {
		return tutorName;
	}
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
}
