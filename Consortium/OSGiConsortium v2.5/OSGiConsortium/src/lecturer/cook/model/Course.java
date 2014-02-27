package lecturer.cook.model;

import java.util.ArrayList;

import entity.Session;

public class Course {
	
	private int courseID;
	private String courseCode;
	private String courseName;
	private int lecturerID;
	private ArrayList<Session> sList;

	
	

	public Course(int courseID, String courseCode, String courseName) {
		this.courseID = courseID;
		this.courseCode = courseCode;
		this.courseName = courseName;
	}

	public Course(int courseID, String courseCode, String courseName,int lectureID, ArrayList<Session> sList) {
		this.courseID = courseID;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.lecturerID = lectureID;
		this.sList = sList;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getLectureID() {
		return lecturerID;
	}

	public void setLectureID(int lectureID) {
		this.lecturerID = lectureID;
	}
	
	public ArrayList<Session> getsList() {
		return sList;
	}

	public void setsList(ArrayList<Session> sList) {
		this.sList = sList;
	}
	
	public void add_Session(Session s){
		sList.add(0, s);
	}
	
	public String displayCourse(){
		return courseCode + "\t\t" + padding(40, courseName) + "\t" + lecturerID;
	}
	
	public static String padding (int length, String word){
		String temp = word;
		while(temp.length() < length){
			temp += " ";
		}
		return temp;
	}

}
