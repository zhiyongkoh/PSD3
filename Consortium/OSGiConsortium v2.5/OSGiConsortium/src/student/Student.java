package student;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Course;
import entity.Session;
import entity.Slot;
import entity.User;

public class Student implements IStudent{
	public Student(){
		
	}

	@Override
	public User displayMenu(User user) throws Exception {
		System.out.println("--->Student Menu\n");
		Scanner sc = new Scanner(System.in);
		String option;
		while(user!=null){
			System.out.println("===========   SELECT CHOICE   ===========\n");
			System.out.println("[1] Book Timetable for Course");
			System.out.println("[2] Check Compulsory Course(s)");
			System.out.println("[0] Logout");
			System.out.println("=========================================\n");
			System.out.println("Select Choice: ");
			
			option = sc.nextLine();
			
			if(option.equals("1")){
				viewCourse(user);
			}else if(option.equals("2")){
				viewCompulsoryCourse(user.getUserId());
			}else if(option.equals("0")){
				user = null;
				System.out.println("Log Out...!!\n");
			}else{
				System.out.println("Wrong input choice\n!!");
			}
			
		}
		
	
		return user;
	}
	private void viewCompulsoryCourse(int userId) throws Exception{
		DBController db = new DBController();
		ArrayList<Course> cL = db.getCompCourse(userId);
		System.out.println("===========   View Compulsory Timetable Slot   ===========\n");
		for(Course c : cL){
			System.out.println(c.getCourseName()+"\t"+c.getCourseCode()+"\t");
			for(Session s : c.getsList()){
				System.out.println("\t->Session Id: "+s.getSessionID()+"\tType: "+s.getSessionType()+"\tCompulsory: "+s.getCompulsory()+"\t");
				for(Slot st : s.getSlotList()){
					System.out.println("\t--->Slot Id: "+st.getSlot_ID()+"\tFrom: "+st.getStart_Time()+"\tTo: "+st.getEnd_Time()+"\tLocation: "+st.getRoomName());
				}
			}
		}
		System.out.println("=========================================\n");
		db.close();
	}
	
	private void viewCourse(User user) throws Exception{
		DBController db = new DBController();
		Scanner sc = new Scanner(System.in);
		System.out.println("===========   SELECT COURSE   ===========\n");
		ArrayList<Course> cList = db.getCourses(user);
		if(cList.size()>0){
			for(int i = 0;i<cList.size();i++){
				System.out.println( "[CourseId: "+ cList.get(i).getCourseID()+"] "+cList.get(i).getCourseName()+"\t"+cList.get(i).getCourseCode());
			}
			System.out.println("=========================================\n");
			System.out.println("Enter Course Id: ");
			int option = sc.nextInt();
			viewSlot(option,user.getUserId());

		}else{
			System.out.println("You got no course!!");
		}
		db.close();
	}
	private void viewSlot(int courseId,int userId) throws Exception{
		DBController db = new DBController();
		Scanner sc = new Scanner(System.in);
		ArrayList<Slot> sL = db.getTimetableSlot(courseId,userId);
		System.out.println("===========   SELECT TIMETABLE SLOT   ===========\n");
		for(int i = 0;i<sL.size();i++){
			System.out.println( "[TimeSlotId: "+ sL.get(i).getSlot_ID()+"] From: "+sL.get(i).getStart_Time()+"\tTo: "+sL.get(i).getEnd_Time()+"\tLocation: "+sL.get(i).getRoomName()+"\tBooked:"+sL.get(i).getBookingstates());
		}
		System.out.println("=================================================\n");
		System.out.println("Book Slot Id: ");
		int option = sc.nextInt();

		for(int i = 0;i<sL.size();i++){
			if(sL.get(i).getSlot_ID()==option){
				if(!sL.get(i).getBookingstates()){
					bookSlot(option,userId);
				}else{
					System.out.println("This slot already booked\n\n");
				}
			}
		}
			
		
		db.close();
	}
	private void bookSlot(int slotId , int userId) throws Exception{
		DBController db = new DBController();
		Scanner sc = new Scanner(System.in);
		db.bookSlot(slotId, userId);
		System.out.println("SLOT BOOKED!!~");
		db.close();
	}
	
	
	
	
}
