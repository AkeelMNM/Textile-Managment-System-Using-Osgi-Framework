package com.txtms.osgi.customerconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import customerService.CustomerService;
import customerService.CustomerServiceImpl;
import model.Order;
import orderDatabase.OrderData;

public class Activator implements BundleActivator {

	ServiceReference CustomerServiceReference;
	Scanner input = new Scanner(System.in);
	
	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println();
		System.out.println("====================== Textile Managment Customer Consumer Started. ======================");
		CustomerServiceReference = bundleContext.getServiceReference(CustomerService.class.getName());
		CustomerService customerService = (CustomerService) bundleContext.getService(CustomerServiceReference);
		
		int no = 0;
	
		while(no != 6) {
		
			System.out.println("====================== Welcome to Order Managment ======================");
			
			System.out.println("Please Select an option to continue....");
			System.out.println("Options");
			System.out.println("*** ******** ***");
			System.out.println("1.Place order ");
			System.out.println("2.Edit order");
			System.out.println("3.View orders");
			System.out.println("4.Remove order");
			System.out.println("5.Search order");
			System.out.println("6.Exit");
			System.out.println("*** ******** ***");
			
			System.out.println("Enter your selection to continue...");
			no = input.nextInt();
			
			String backToHome = "Home";
			
			//Place Order
			if(no == 1) {
				while(!(backToHome.equals("99"))) {
					
					System.out.println("Add New Order Deatils.");
					System.out.println("*** *************** ***");
					
					System.out.println("Enter Order Date : ");
					String orderDate = input.next();
					
					System.out.println("*** *************** ***");
					System.out.println("Available Products.");
					System.out.println(" T-Shirt \n Shirt \n Denim");
					System.out.println("*** *************** ***");
					
					System.out.println("Enter Item : ");
					String item = input.next();
					
					System.out.println("Enter quantity : ");
					double qty = input.nextDouble();
					
					System.out.println("Enter Transport Location :");
					String TransportLocation = input.next();
					
					String orderStatus = "pending";
					
					String TransportStatus = "pending";
					
					// add order array
					int result = customerService.placeOrders(orderDate, item, qty, orderStatus,TransportStatus,TransportLocation);
					if(result == 1) {
						System.out.println("Order added Successfully.");
					}
					
					System.out.println("Enter any key to continue or enter 99 to go back to Menu");
					
					backToHome = input.next();
				}
				backToHome = "Home"; 
			}
			
			// update order
			else if (no == 2) {
				while(!(backToHome.equals("99"))) {
					
					System.out.println();
					System.out.println("Update Order Deatils.");
					System.out.println("*** *************** ***");
					
					System.out.println("Enter order ID :");
					int id = input.nextInt();
					
					System.out.println("Enter 0 to skip to next Attribute.");

					System.out.println("New Order Date Format:DD-MM-YYY :");
					String uOrdaerDate = input.next();
					
					System.out.println("New quantity :");
					Double uQty = input.nextDouble();
					
					System.out.println("New Transport Location :");
					String uTransportLocation = input.next();
					
					String uOrderStatus = "pending";
					
					String uTransportStatus = "pending";
					
					if(uOrdaerDate.equals("0")) {
						uOrdaerDate = null;
					}
					if(uQty.equals("0")) {
						uQty = null;
					}
					if(uTransportLocation.equals("0")) {
						uTransportLocation = null;
					}
					
					// update and add to order array
					int result = customerService.editOrders(id, uOrdaerDate, uQty,uOrderStatus, uTransportLocation, uTransportStatus);
					if(result == 1) {
						Order order = customerService.searchOrders(id);
						System.out.println("Order Deatils Successfully Updated");
						System.out.println("---------------------------------------------------- Update Order list ----------------------------------------------------");
						System.out.println("ID"+"\t\t"+"Order Date"+"\t\t"+"Item"+"\t\t"+"Quantity"+"\t"+"Transport Location"+"\t"+"Transport Status"+"\t"+"Order Status");
						System.out.println();
						System.out.println(order.getId()+"\t\t"+order.getOrderDate()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t\t"+order.getTransportLocation()+"\t\t"+order.getTransportStatus()+"\t\t\t"+order.getOrderStatus());
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					}
					else if(result == -1) {
						System.out.println("Order Deatils Not Updated");
					}
					
					System.out.println("Enter any key to continue or enter 99 to go back to Menu");
					
					backToHome = input.next();
				}
				backToHome = "Home"; 
			}
			
			//list of orders details
			else if(no == 3) {
				
				System.out.println();
				System.out.println("Orders List Deatils.");
				System.out.println("*** *************** ***");
				
				List<Order> Orderlist = customerService.viewOrders();
				System.out.println("----------------------------------- Order list --------------------------------------------");
				System.out.println("ID"+"\t\t"+"Order Date"+"\t\t"+"Item"+"\t\t"+"Quantity"+"\t"+"Transport Location"+"\t"+"Transport Status"+"\t"+"Order Status");
				System.out.println();
				for(Order order: Orderlist) {
					System.out.println(order.getId()+"\t\t"+order.getOrderDate()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t\t"+order.getTransportLocation()+"\t\t"+order.getTransportStatus()+"\t\t\t"+order.getOrderStatus());
				}
				System.out.println("--------------------------------------------------------------------------------------------");
			
			}
			
			// Remove order details
			else if(no == 4) {
				
				System.out.println();
				System.out.println("Remove Order Deatils.");
				System.out.println("*** *************** ***");
				
				System.out.println("Enter Oerder ID :");
				int id = input.nextInt();
				
				int result = customerService.removeOrder(id);
				
				if(result == 1) {
					System.out.println("Order Details Removed");
				}
				else if(result == -1) {
					System.out.println("Error Try agin....");
				}
				
			}
			
			//Search order details by order id
			else if(no == 5) {
				while(!(backToHome.equals("99"))) {
				
					System.out.println();
					System.out.println("Search Order.");
					System.out.println("*** *************** ***");
					
					System.out.println("Enter Order ID : ");
					int id = input.nextInt();
					
					Order order = customerService.searchOrders(id);
					
					if(order == null) {
						System.out.println("Search Order Deatails Not Exist");
					}
					else {
						System.out.println("------------------------------------------------------- Order list -------------------------------------------------------");
						System.out.println("ID"+"\t\t"+"Order Date"+"\t\t"+"Item"+"\t\t"+"Quantity"+"\t"+"Transport Location"+"\t"+"Transport Status"+"\t"+"Order Status");
						System.out.println();
						System.out.println(order.getId()+"\t\t"+order.getOrderDate()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t\t"+order.getTransportLocation()+"\t\t"+order.getTransportStatus()+"\t\t\t"+order.getOrderStatus());
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
					}
					
					System.out.println("Enter any key to continue or enter 99 to go back Menu");
					backToHome = input.next();
				}
				backToHome = "Home";
			}
			
			// Exit 
			else if(no == 6) {
				break;
			}
			
			//Error msg "invalid selection" 
			else {
				System.out.println("Please enter a valid selection");
			}
			
			
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("====================== Textile Managment Customer Consumer Stopped. ======================");
		bundleContext.ungetService(CustomerServiceReference);
	}

}
