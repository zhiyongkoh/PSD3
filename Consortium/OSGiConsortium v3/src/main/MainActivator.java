package main;

//import java.util.Scanner;

import java.util.Scanner;

import lecturer.ILecturer;
import login.ILogin;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import admin.IAdmin;
import student.IStudent;
import entity.*;

public class MainActivator implements BundleActivator {

	private ILogin loginSv = null;
	private IStudent studSv = null;
	private IAdmin admSv = null;
	private ILecturer lectSv = null;
	
	
	private Scanner sc = new Scanner(System.in);
	private User user = null;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[MAIN] START");
		sc = new Scanner(System.in);
		user = null;
		//login interface
		ServiceReference logSvRf = context.getServiceReference(ILogin.class.getName());
		loginSv = (ILogin) context.getService(logSvRf);
		if (loginSv == null) {
			throw new Exception("[MAIN] Error! Login service could not be found!");
		}
		//student interface
		ServiceReference studSvRf = context.getServiceReference(IStudent.class.getName());
		studSv = (IStudent) context.getService(studSvRf);
		if (studSv == null) {
			throw new Exception("[MAIN] Error! Student service could not be found!");
		}
		//admin interface
		ServiceReference admSvRf = context.getServiceReference(IAdmin.class.getName());
		admSv = (IAdmin) context.getService(admSvRf);
		if(admSv==null){
			throw new Exception("[MAIN] Error! Admin service could not be found!");
		}
		//lecturer interface
		ServiceReference lectSvRf = context.getServiceReference(ILecturer.class.getName());
		lectSv = (ILecturer) context.getService(lectSvRf);
		if(lectSv==null){
			throw new Exception("[MAIN] Error! Lecturer service could not be found!");
		}
		
		while (true) {

			System.out.println("Undergraduate Teaching Session Booking System\n");
			System.out.println("[1] Login to System");
			System.out.println("[0] Exit System");
			String option = sc.nextLine();

			if (option.equals("1")) {
				// LOGIN
				user = loginSv.displayLogin();

				if (user == null) {
					System.out.println("*** Invalid User ***\n");
				} else {
					System.out.println("You have logged in as "+ user.getUserType() + ": "+ user.getName() + "\n");

					while (user!=null) {
						switch (user.getUserType()) {
						case "admin":
							user = admSv.displayMenu(user);
							break;
						case "lecturer":
							//user = null;
							user = lectSv.displayMenu(user);
							break;
						case "student":
							//user = null;
							user = studSv.displayMenu(user);
							break;
						}
					}
				}

			} else if (option.equals("0")) {
				// Exit System
				System.out.println("Exiting System...");
				break;
			} else {
				System.out.println("Wrong Input Option");
			}
		}
		stop(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[MAIN] STOP");
	}
}
