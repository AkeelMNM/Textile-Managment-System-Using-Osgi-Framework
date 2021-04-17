package com.txtms.osgi.transportserviceproducer;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import salesmanagerservice.SalesManagerService;
import salesmanagerservice.SalesManagerServiceImpl;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration;


	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("================== Textile Management Transport Service Started ==================");
		SalesManagerService salesManagerService = new SalesManagerServiceImpl();
		//Registering the SaleManager Service
		serviceRegistration = bundleContext.registerService(SalesManagerService.class.getName(), salesManagerService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("================== Textile Management Transport Service Stoped ==================");
		serviceRegistration.unregister();
	}

}
