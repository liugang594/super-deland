package org.superdeland.studio.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	private static BundleContext context;

	public static final String IMG_NODE = "icons/codecount.gif";

	public static final String PLUGIN_ID = "org.superdeland.studio.ui";
	
	private static Activator plugin = null;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		plugin = null;
		getImageRegistry().dispose();
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
		ImageDescriptor nodeImageDesc = imageDescriptorFromPlugin(PLUGIN_ID,
				IMG_NODE);
		reg.put(IMG_NODE, nodeImageDesc);
	}
	
	public static ImageDescriptor getImageDescriptor(String ID){
		return plugin.getImageRegistry().getDescriptor(ID);
	}

}
