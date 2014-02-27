import java.util.ArrayList;

public class Lecturer extends User{
	
	ArrayList<Integer> myCourse;
	
	public Lecturer(int user_id, String username, String password, USERTYPE user_type, USERTYPE admin, String name) {
		super(user_id, username, password, user_type, admin, name);
	}
	
	public void assign_Course(int course_id){
		myCourse.add(course_id);
	}

}
