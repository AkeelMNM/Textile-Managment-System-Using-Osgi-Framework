package com.txtms.osgi.productserviceprovider;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import ProductionManagerService.ProductionManagerService;
import ProductionManagerService.ProductionManagerServiceimpl;

public class Activator implements BundleActivator {
	
	ServiceRegistration serviceRegistration;


	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("============Textile Managment Product Service Started.============");
		ProductionManagerService productionmanagerservice= new ProductionManagerServiceimpl();
		
		//Registering the ProductionManager Service
		serviceRegistration=bundleContext.registerService(ProductionManagerService.class.getName(),productionmanagerservice,null);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("============Textile Managment Product Service Stopped.============");
		serviceRegistration.unregister();
	}

}
