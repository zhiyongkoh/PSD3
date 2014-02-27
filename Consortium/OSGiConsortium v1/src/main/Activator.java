package main;

//import java.util.Scanner;

import login.ILogin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import entity.*;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[MAIN] START");
	
		User user = null;
		ServiceReference logSvRf = context.getServiceReference (ILogin.class.getName ());
		ILogin loginSv = (ILogin)context.getService(logSvRf);
		if(loginSv==null){
			 throw new Exception ("[MAIN] Error! Login service could not be found!");
		}
		while(user==null){
			user = loginSv.displayLogin();
			if (user == null) {
				System.out.println("*** Invalid User ***\n");
			}
		}
		
		System.out.println("You have logged in as " + user.getName());
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[MAIN] STOP");
	}
}
