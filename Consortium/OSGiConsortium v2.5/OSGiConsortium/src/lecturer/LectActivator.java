package lecturer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class LectActivator implements BundleActivator
{
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[LECTUERER] BUNDLE START");
		
		Lecturer lect = new Lecturer();
		context.registerService(ILecturer.class.getName(), lect, null);

		System.out.println("[LECTUERER] LECTURER services were registered.");

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[LECTUERER] BUNDLE STOP");
	}
}
