package test.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import entity.*;

public class SystemSteps {
	private int slotId;
	private int userId;
	private boolean status;
	
	/***************** STUDENT ***********************/
	
	//student - book timeslot
	@Given("a timetable slot $slotId")
	public void StudGivenBookSlot(int slotId){
		this.slotId = slotId;
	}
	@When("the timetable slot belong to $studentId")
	public void StudWhenBookSlot(String studentId) throws SQLException{
		student.DBController db = new student.DBController();
		this.status = db.tBookTimeSlot(Integer.parseInt(studentId), this.slotId);
		db.close();
	}
	@Then("a timetable slot can be book is $expected")
	public void StudThenBookSlot(Boolean expected){
		assertThat(this.status, equalTo(expected));
	}

	//student - check compulsory
	@Given("a list of timetable slots which belong to student $studentId")
	public void StudGivenCheckComp(int studentId){
		this.userId = studentId;
	}
	@When("all the compulsory timetable slots are booked")
	public void StudWhenCheckComp() throws Exception{
		boolean checkstatus = true;
		student.DBController db = new student.DBController();
		ArrayList<Course> cL = db.getCompCourse(this.userId);
		outerloop:
		for(Course c : cL){
			for(Session s : c.getsList()){
				for(Slot st : s.getSlotList()){
					checkstatus = db.checkBooking(st.getSlot_ID(), this.userId);
					if(checkstatus==false){
						break outerloop;
					}
				}
			}
		}
		this.status = checkstatus;
		db.close();
	}
	@Then("student will not fail the course is $expected")
	public void StudThenCheckComp(Boolean expected){
		assertThat(this.status, equalTo(expected));
	}
	
	
	
	/***************** LECTURER ***********************/
	
	//lecturer - see details of session for all of his/her respective courses
	@Given("a list of timetable which belongs to lecturer id is $lecturerId")
	public void LectGivenViewDetails(int lecturerId){
		this.userId = lecturerId;
	}
	@When("courses is managed by the lecturer")
	public void LectWhenViewDetails() throws Exception{
		boolean checkstatus = true;
		lecturer.DBController db = new lecturer.DBController();
		ArrayList<Course> cL = db.getLectCourse(this.userId);
		outerloop:
		for(Course c : cL){
			if(c.getLectureID()!=this.userId){
				checkstatus=false;
				break outerloop;
			}
		}
		db.close();
		this.status = checkstatus;
	}
	@Then("timetable details for the courses will be showned is $expected")
	public void LectThenViewDetails(Boolean expected){
		assertThat(this.status, equalTo(expected));
	}
}


