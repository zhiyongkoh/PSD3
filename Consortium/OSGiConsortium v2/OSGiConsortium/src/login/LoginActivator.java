package login;

//import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class LoginActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[LOGIN] BUNDLE START");
		Login login = new Login();
		context.registerService(ILogin.class.getName(), login, null);
		System.out.println("[LOGIN] LOGIN services were registered.");
		
		
		/*User user = null;

		while (user == null) {
			user = LoginFunction.displayLogin();
			if (user == null) {
				System.out.println("*** Invalid User ***\n");
				user = LoginFunction.displayLogin();
			}
		}
		System.out.println("You have logged in as " + user.getName());*/
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[LOGIN] BUNDLE STOP");
	}
}
