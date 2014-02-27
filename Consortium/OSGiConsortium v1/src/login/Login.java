package login;

import java.util.Scanner;

import entity.User;

public class Login implements ILogin{
	public Login(){
		
	}
	
	@ Override
	public User displayLogin() throws Exception{
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Undergraduate Teaching Session Booking System\n");
		
		System.out.println("--------- LOGIN ---------");
		System.out.println("Username:");
		String username = sc.nextLine();
		System.out.println	("Password:");
		String password = sc.nextLine();
		
		User user = loginValid(username, password);
		//sc.close();
		return user;
		
	}
	
	private static User loginValid(String username,String password) throws Exception{
		DBController db = new DBController();
		User user = db.UserLogin(username, password);
		db.close();
		return user;
	}
}
