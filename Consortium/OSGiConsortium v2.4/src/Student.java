import java.util.ArrayList;

public class Student extends User {
	
	ArrayList<Integer> myCourse;
	
	public Student(int user_id, String username, USERTYPE user_type, String name) {
		super(user_id, username, user_type, name);
	}
	
	public void add_Course(int course_id){
		myCourse.add(course_id);
	}

}
