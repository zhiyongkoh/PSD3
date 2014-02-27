import java.util.ArrayList;


public class Course {
	
	int course_id;
	String course_code;
	String course_name;
	int lecturer_id;
	ArrayList<Session> sessionList;
	
	public Course(String course_code, String course_name, int lecture_id) {
		super();
		this.course_code = course_code;
		this.course_name = course_name;
		this.lecturer_id = lecture_id;
		this.sessionList = new ArrayList<Session>();
	}

	public int getCourse_id() {
		return course_id;
	}
	
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCourse_code() {
		return course_code;
	}

	public String getCourse_name() {
		return course_name;
	}

	public int getLecturer_id() {
		return lecturer_id;
	}

	public ArrayList<Session> getSessionList() {
		return sessionList;
	}
	
	public void add_Session(Session s){
		sessionList.add(s);
	}
	
	public String displayCourse(){
		return course_code + "\t\t" + course_name + "\t\t" + lecturer_id;
	}
	

}
