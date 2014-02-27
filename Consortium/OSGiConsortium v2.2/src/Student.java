import java.util.ArrayList;

public class Student extends User {
	
	ArrayList<Integer> myCourse;
	
	public Student(int user_id, String username, String password, USERTYPE user_type, USERTYPE admin, String name) {
		super(user_id, username, password, user_type, admin, name);
	}
	
	public void add_Course(int course_id){
		myCourse.add(course_id);
	}

}
