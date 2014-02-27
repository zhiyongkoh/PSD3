package entity;

public class Session {
	
	int session_id;
	int course_id;
	String session_type;
	int tutor_id;
	boolean compulsory;
	
	public Session(int session_id, int course_id, String session_type, boolean compulsory) {
		super();
		this.session_id = session_id;
		this.course_id = course_id;
		this.session_type = session_type;
		this.compulsory  = compulsory;
	}

	public int getSession_id() {
		return session_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public String getSession_type() {
		return session_type;
	}

	public int getTutor_id() {
		return tutor_id;
	}
	
	
	

}
