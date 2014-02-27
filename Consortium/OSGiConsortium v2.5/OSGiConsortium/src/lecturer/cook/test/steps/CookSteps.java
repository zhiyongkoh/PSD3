package lecturer.cook.test.steps;

import java.util.ArrayList;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;




import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

import lecturer.cook.model.*;

public class CookSteps {
	
	
	private Course myCourse;
	private Session mySession;
	private Session tempSession;
	private int courseID;
	private int tutorID;
	private String tutorName;
	private String sessionType;
	private int compulsory;
	private ArrayList<Session> mySessionList;
	private int repeat;
	
	@Given("a set of detail of a course from a CSV file")
	public void course(){
		
	}
	@When("course id is $courseID, course code is $courseCode and course name is $courseName")
	public void createCourse(int courseID, String courseCode, String courseName){
		myCourse = new Course(courseID, courseCode, courseName);
	}
	@Then("course is created")
	public Boolean isCourseCreated(){
		if (myCourse != null){
			return true;
		}else{
			return false;
		}
	}
	
	@Given("a set of detail of a session")
	public void session(){
		
	}
	@When("the system list all the course, enter OS3 as course code which has a course id $courseID")
	public void setCourseID(int courseID){
		this.courseID = courseID;
	}
	
	@When("the system list all the tutor, user select $tutorName with a tutor id $tutorID")
	public void setTutor(int tutorID, String tutorName){
		this.tutorID = tutorID;
		this.tutorName = tutorName;
	}
	
	@When("AND the select $sessionType for session type and select $compulsory for compulsory")
	public void setSessionTypeCompulsory(String sessionType, int compulsory){
		this.sessionType = sessionType;
		this.compulsory = compulsory;
	}
	@When("AND the system will auto generate a session id which is $sessionID and create the session")
	public void setSessionID(int sessionID){
		mySession = new Session(sessionID, tutorID, sessionType, compulsory, courseID, tutorName);
		
	}
	@Then("session is created")
	public Boolean isSessionCreated(){
		if (mySession != null){
			return true;
		}else{
			return false;
		}
	}
	
	@Given("a list of session")
	public void listSession(){
		mySessionList = new ArrayList<Session>();
		
	}
	
	@When("the use select a session with a session id $sessionID")
	public void selectASession(int sessionID){
		mySessionList.add(mySession);
	}
	
	@When("the system prompt to enter number of repeat for the session and the use enter $repeat")
	public void createMultiSession (int repeat){
		this.repeat = repeat;
	}
	
	@Then("the system create all the session")
	public void creatMultiSession(){
		for (int i = 0; i < repeat; i++){
			mySessionList.add(tempSession);
		}
	}
	
	
	
	
}
