import java.util.ArrayList;

public class Lecturer extends User{
	
	ArrayList<Integer> myCourse;
	
	public Lecturer(int user_id, String username, USERTYPE user_type, String name) {
		super(user_id, username, user_type, name);
	}
	
	public void assign_Course(int course_id){
		myCourse.add(course_id);
	}

}
