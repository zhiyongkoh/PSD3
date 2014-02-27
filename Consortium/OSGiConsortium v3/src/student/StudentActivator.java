package student;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class StudentActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[STUDENT] BUNDLE START");
		
		Student stud = new Student();
		context.registerService(IStudent.class.getName(), stud, null);
		System.out.println("[STUDENT] STUDENT services were registered.");

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[STUDENT] BUNDLE STOP");
	}
}
