package com.txtms.osgi.salesmanagerconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import customerService.CustomerService;
import model.Order;
import salesmanagerservice.SalesManagerService;
import transportDatabase.TransportData;
import transportmodel.Transport;


public class Activator implements BundleActivator {

	ServiceReference SaleManagerServiceReference;
	ServiceReference CustomerServiceReference;
	Scanner input = new Scanner(System.in);

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("================== Textile Management Sales Manager Consumer Started ==================");
		SaleManagerServiceReference = bundleContext.getServiceReference(SalesManagerService.class.getName());
		SalesManagerService salesManagerService = (SalesManagerService) bundleContext.getService(SaleManagerServiceReference);
		SaleManagerServiceReference = bundleContext.getServiceReference(CustomerService.class.getName());
		CustomerService customerService = (CustomerService) bundleContext.getService(SaleManagerServiceReference);
		
		
		int mainOpt = 0;
		while(mainOpt != 3) {
			
			System.out.println("================== Welcome to Order and Transport Management ==================");
			
			System.out.println("Select an option to continue");
			System.out.println("Options");
			System.out.println("1.Order Management");
			System.out.println("2.Transport Management");
			System.out.println("3.Exit");
			
			System.out.println("Enter your selection to continue...");
			mainOpt = input.nextInt();
			
			String backnav= "home";
			/**
			 * Order Management Menu
			 */
			if(mainOpt == 1) {
				int orderMopt = 0;
				while(orderMopt != 4) {
				
					System.out.println("================== Welcome to Order Management ==================");
					System.out.println("Select an option to continue");
					System.out.println("Options");
					System.out.println("1.Approve or Reject Orders");
					System.out.println("2.View Orders");
					System.out.println("3.Search Orders By ID");
					System.out.println("4.Go Back to Main Menu");
					
					System.out.println("Enter your selection to continue...");
					orderMopt = input.nextInt();
					
					/**
					 * Approve or Reject Orders Made by Customers
					 */
					if(orderMopt == 1) {
						System.out.println("=======Approve or Reject Orders Made by Customers=======\n");
						
						List<Order> oList =customerService.viewOrders();
						System.out.println("----------------------------------Pending Customer Order List for Approval  ----------------------------------\n");
						System.out.println("ID"+"\t\t"+"Item Name"+"\t"+"Quantity"+"\t"+"Odered Date"+"\t"+"Order Status"+"\t"+"Transport Location"+"\t"+"Transport Status");
						for(Order order : oList) {
							if(order.getOrderStatus().equals("pending")) {
								System.out.println(order.getId()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t"+order.getOrderDate()+"\t"+order.getOrderStatus()+"         "+order.getTransportLocation()+"                   "+order.getTransportStatus());																
								}
						}
						System.out.println("------------------------------------------------------------------------------------");
						
						System.out.println("Enter Order ID :");
						int id = input.nextInt();
						System.out.println("Select Options \n1-Approve  2-Reject");
						int decision = input.nextInt();
						
						String status = null;
						if(decision == 1) {
							status = "approved";
						}else if (decision == 2) {
							status = "rejected";
						}
						
						int result = customerService.editOrders(id, null, 0, status, null, null);
						if(result == 1 && decision == 1) {
							System.out.println("======The Order Made by the Customer is Approved======");
							Order order =customerService.searchOrders(id);
								System.out.println("---------------------------------- Order List ----------------------------------\n");
								System.out.println("ID"+"\t\t"+"Item Name"+"\t"+"Quantity"+"\t"+"Odered Date"+"\t"+"Order Status"+"\t"+"Transport Location"+"\t"+"Transport Status");
								System.out.println();
								System.out.println(order.getId()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t"+order.getOrderDate()+"\t"+order.getOrderStatus()+"         "+order.getTransportLocation()+"                   "+order.getTransportStatus());																
								System.out.println("------------------------------------------------------------------------------------");
								System.out.println("Proceed to Transport this Order");
						}else if((result == 1 && decision == 2)) {
							System.out.println("The Order No "+id+" is Rejected");
						}else {
							System.out.println("Error Try again.......");
						}
												
					}
					/**
					 * Displaying all order made by the Customers
					 */
					else if(orderMopt == 2) {
						
						System.out.println("=======Displaying all order made by the Customers=======\n");
						
						List<Order> oList =customerService.viewOrders();
						System.out.println("---------------------------------- Order List ----------------------------------\n");
						System.out.println("ID"+"\t\t"+"Item Name"+"\t"+"Quantity"+"\t"+"Odered Date"+"\t"+"Order Status"+"\t"+"Transport Location"+"\t"+"Transport Status");
						for(Order order : oList) {
							System.out.println(order.getId()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t"+order.getOrderDate()+"\t"+order.getOrderStatus()+"         "+order.getTransportLocation()+"                   "+order.getTransportStatus());																
						}
						System.out.println("------------------------------------------------------------------------------------");
						
						System.out.println("Enter 0 to go back to Menu");
						backnav = input.next();
						
					}
					/**
					 * Search order made by the Customers
					 */
					else if(orderMopt == 3) {
						System.out.println("=======Search order made by the Customers=======\n");
						backnav = "home";
						while(!(backnav.equals("0"))) {
							
							System.out.println("Enter Order ID :");
							int id = input.nextInt();
							
							Order order =customerService.searchOrders(id);
							
							if(order == null) {
								System.out.println("Searched Order Details Not Exist");
							}else {
								System.out.println("---------------------------------- Order List ----------------------------------\n");
								System.out.println("ID"+"\t\t"+"Item Name"+"\t"+"Quantity"+"\t"+"Odered Date"+"\t"+"Order Status"+"\t"+"Transport Location"+"\t"+"Transport Status");
								System.out.println();
								System.out.println(order.getId()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t"+order.getOrderDate()+"\t"+order.getOrderStatus()+"         "+order.getTransportLocation()+"                   "+order.getTransportStatus());																
								System.out.println("------------------------------------------------------------------------------------");
								}
							
							System.out.println("Enter any key to continue or enter 0 to go back to Menu");
							backnav = input.next();
						}
						
						backnav = "home";
						
					}
					/**
					 * Exit from the Order Management Menu
					 */
					else if (orderMopt == 4){
						break;
					}
					else {
						System.out.println("Invalid input try again......");
					}
				}
			}
			/**
			 * Transport Management Menu
			 */
			else if(mainOpt == 2) {
				int tranMopt = 0;
				while(tranMopt != 6) {
					
					System.out.println("================== Welcome to Transport Management ==================");
					System.out.println("Select an option to continue");
					System.out.println("Options");
					System.out.println("1.Add New Transport Detials");
					System.out.println("2.View All Transport Detials");
					System.out.println("3.Search Transport Detials by ID");
					System.out.println("4.Update an Exisisting Transport Detials");
					System.out.println("5.remove an Exisisting Transport Detials");
					System.out.println("6.Go Back to Main Menu");
					
					System.out.println("Enter your selection to continue...");
					tranMopt = input.nextInt();
					
					/**
					 * Add New Transport Details
					 */
					if(tranMopt == 1) {
						
						while(!(backnav.equals("0"))) {
							
							
							List<Order> oList =customerService.viewOrders();
							System.out.println("---------------------------------- Pending Customer Orders List For Transport ----------------------------------\n");
							System.out.println("ID"+"\t\t"+"Item Name"+"\t"+"Quantity"+"\t"+"Odered Date"+"\t"+"Order Status"+"\t"+"Transport Location"+"\t"+"Transport Status");
							System.out.println();
							for(Order order : oList) {
								if(order.getTransportStatus().equals("pending") && order.getOrderStatus().equals("approved")) {
									System.out.println(order.getId()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t"+order.getOrderDate()+"\t"+order.getOrderStatus()+"         "+order.getTransportLocation()+"                   "+order.getTransportStatus());																
								}
							}
							System.out.println("----------------------------------------------------------------------------------------------------------------");
							
							System.out.println("=======Add New Transport Details=======\n");
							
							System.out.println("Enter Customer Order ID :");
							int id = input.nextInt();
							
							System.out.println("Location");
							String location = input.next();
							
							System.out.println("Shipping Date Format:DD-MM-YYY");
							String shipDate = input.next();
							
							System.out.println("Reciving Date Format:DD-MM-YYY");
							String reciveDate = input.next();
							
							int result = salesManagerService.addTransport(location, shipDate, reciveDate);
							if(result == 1) {
								System.out.println("Transport Details Successfully Added");
								int res = customerService.editOrders(id, null, 0, null, "Shipped", null);
								/**
								 * Display order updated details after new transport details added to the given customer order
								 */
								if(res == 1) {
									Order order =customerService.searchOrders(id);
									System.out.println("Customer Order Transport Status Updated");
									System.out.println("---------------------------------- Order List ----------------------------------\n");
									System.out.println("ID"+"\t\t"+"Item Name"+"\t"+"Quantity"+"\t"+"Odered Date"+"\t"+"Order Status"+"\t"+"Transport Location"+"\t"+"Transport Status");
									System.out.println();
									System.out.println(order.getId()+"\t\t"+order.getItem()+"\t\t"+order.getQty()+"\t\t"+order.getOrderDate()+"\t"+order.getOrderStatus()+"         "+order.getTransportLocation()+"                   "+order.getTransportStatus());																
									System.out.println("------------------------------------------------------------------------------------");
									
									
								}
							}
							
							System.out.println("Enter any key to continue or enter 0 to go back to Menu");
							
							backnav = input.next();
						}
						
						backnav = "home";
					}
					/**
					 * Displaying all Transport Details
					 */
					else if(tranMopt == 2) {
						
						System.out.println("=======Displaying all Transport Details=======\n");
						
						List<Transport> tList =TransportData.TransportList;
						System.out.println("---------------------------------- Transport List ----------------------------------\n");
						System.out.println("ID"+"\t\t"+"Shipped Date"+"\t\t"+"Receving Date"+"\t\t"+"Shipping Location");
						System.out.println();
						for(Transport transport : tList) {
							System.out.println(transport.getID()+"\t\t"+transport.getShippedDate()+"\t\t"+transport.getRecevingDate()+"\t\t"+transport.getLocation());
						}
						System.out.println("------------------------------------------------------------------------------------");
						
						System.out.println("Enter 0 to go back to Menu");
						backnav = input.next();
						
					}
					/**
					 * Search Transport Details
					 */
					else if(tranMopt == 3) {
						System.out.println("=======Search Transport Details=======\n");
						backnav = "home";
						while(!(backnav.equals("0"))) {
							
							System.out.println("Enter Transport ID :");
							int id = input.nextInt();
							
							Transport transport = salesManagerService.searchTransport(id);
							
							if(transport == null) {
								System.out.println("Searched Transport Details Not Exist");
							}else {
								System.out.println("---------------------------------- Transport Details ----------------------------------\n");
								System.out.println("ID"+"\t\t"+"Shipped Date"+"\t\t"+"Receving Date"+"\t\t"+"Shipping Location");
								System.out.println();
								System.out.println(transport.getID()+"\t\t"+transport.getShippedDate()+"\t\t"+transport.getRecevingDate()+"\t\t"+transport.getLocation());
								System.out.println("---------------------------------------------------------------------------------------");
							}
							
							System.out.println("Enter any key to continue or enter 0 to go back to Menu");
							backnav = input.next();
						}
						
						backnav = "home";
					}
					/**
					 * Update Existing Transport Details
					 */
					else if (tranMopt == 4){

						System.out.println("=======Update Existing Transport Details=======\n");
						
						while(!(backnav.equals("0"))) {
						
							System.out.println("Enter Transport ID :");
							int id = input.nextInt();
							
							System.out.println("Enter 0 to Skip to next Attribute");
							
							System.out.println("New  Location :");
							String uLocation = input.next();
							
							System.out.println("New Shipping Date Format:DD-MM-YYY :");
							String uShipDate = input.next();
							
							System.out.println("New Reciving Date Format:DD-MM-YYY : ");
							String uReciveDate = input.next();
							
							if(uLocation.equals("0")) {
								uLocation = null;
							}
							if(uShipDate.equals("0")) {
								uShipDate = null;
							}
							if(uReciveDate.equals("0")) {
								uReciveDate = null;
							}
							
							int result = salesManagerService.updateTransport(id, uLocation, uShipDate, uReciveDate);
							
							if(result == 1) {
								/**
								 * Displaying after updated
								 */
								Transport transport = salesManagerService.searchTransport(id);
								System.out.println("Transport Details Successfully Updated");
								System.out.println("----------------------------------Updated Transport Details ----------------------------------\n");
								System.out.println("ID"+"\t\t"+"Shipped Date"+"\t\t"+"Receving Date"+"\t\t"+"Shipping Location");
								System.out.println();
								System.out.println(transport.getID()+"\t\t"+transport.getShippedDate()+"\t\t"+transport.getRecevingDate()+"\t\t"+transport.getLocation());
								System.out.println("----------------------------------------------------------------------------------------------");
								
							}else if(result == -1){
								System.out.println("Transport Details Not Updated");
							}
							
							System.out.println("Enter any key to continue or enter 0 to go back to Menu");
							backnav = input.next();
						}
						
						backnav = "home";
						
					}
					/**
					 * Remove Existing Transport Details
					 */
					else if (tranMopt == 5){
						
						System.out.println("=======Remove Existing Transport Details=======\n");
						
						System.out.println("Enter Transport ID :");
						int id = input.nextInt();
						
						int result = salesManagerService.removeTransport(id);
						
						if(result == 1) {
							System.out.println("Transport Details Removed");
							
						}else if(result == -1){
							System.out.println("Error Try again.....");
						}
					}
					/**
					 * Exit from Transport Management Menu
					 */
					else if (tranMopt == 6){
						break;
					}
					else {
						System.out.println("Invalid input try again......");
					}
					backnav = "home";
				}
				
			}
			/**
			 * Exit from Main Menu
			 */
			else if(mainOpt == 3){
				break;
			}
			else {
				System.out.println("Invalid input try again......");
			}
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("================== Textile Management Sales Manager Consumer Stoped ==================");
		bundleContext.ungetService(SaleManagerServiceReference);
	}

}
