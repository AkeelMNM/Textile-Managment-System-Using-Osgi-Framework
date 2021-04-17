package com.txtms.osgi.orderserviceproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import customerService.CustomerService;
import customerService.CustomerServiceImpl;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration;
	
	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("====================== Textile Managment Prodect Service Started. ======================");
		CustomerService customerService = new CustomerServiceImpl();
	
		//Registering the Customer service
		serviceRegistration = bundleContext.registerService(CustomerService.class.getName(), customerService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		
		System.out.println("====================== Textile Managment Prodect Service Stopped. ======================");
		//unregister customer service
		serviceRegistration.unregister();
	}

}
