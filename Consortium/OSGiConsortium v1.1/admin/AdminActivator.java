package admin;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class AdminActivator implements BundleActivator{
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("[ADMIN] BUNDLE START");
		
		Admin adm = new Admin();
		context.registerService(IAdmin.class.getName(), adm, null);

		System.out.println("[ADMIN] STUDENT services were registered.");

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("[ADMIN] BUNDLE STOP");
	}
}
