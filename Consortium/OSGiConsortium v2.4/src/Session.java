
public class Session {
	
	int session_id;
	int course_id;
	String session_type;
	int tutor_id;
	int compulsory;
	
	
	public Session(int session_id, int course_id, int compulsory) {
		super();
		this.session_id = session_id;
		this.course_id = course_id;
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

	public void setSession_type(String session_type) {
		this.session_type = session_type;
	}

	public void setTutor_id(int tutor_id) {
		this.tutor_id = tutor_id;
	}
	
	
	
	
	
	

}
