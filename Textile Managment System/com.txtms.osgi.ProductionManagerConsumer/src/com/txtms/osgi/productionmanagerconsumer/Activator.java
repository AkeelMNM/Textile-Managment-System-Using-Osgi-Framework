package com.txtms.osgi.productionmanagerconsumer;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import Model.Product;
import ProductionManagerService.ProductionManagerService;


public class Activator implements BundleActivator {
	
	ServiceReference ProductionManagerServiceReference;
	Scanner input = new Scanner(System.in);
	

	public void start(BundleContext bundleContext) throws Exception {
	
		System.out.println("============Textile Production Manager Consumer Started.============");
		ProductionManagerServiceReference=bundleContext.getServiceReference(ProductionManagerService.class.getName());
		ProductionManagerService productionmanagerservice =(ProductionManagerService)bundleContext.getService(ProductionManagerServiceReference);
		
		int i=0;
			while (i!=6) {
				
				System.out.println("----------------------------Welcome to the Product Management-------------------------------");
				
				System.out.println("Please Select an option to continue.....");
				System.out.println("Options");
				System.out.println("******************************************");
				System.out.println("1.Add a new Production Details. ");
				System.out.println("2.Update an exsisiting  Production Details.");
				System.out.println("3.View Production Details.");
				System.out.println("4.Delete exsisiting  Production Details.");
				System.out.println("5.Search  Production Details.");
				System.out.println("6.Exit");
				System.out.println("******************************************");
				System.out.println("Enter Your Selection to Proceed the Service=>");
				i= input.nextInt();
				 
				String backtohome ="backtohome";
					switch (i) {
						case 1: //Handles the adding process of new Product to the list
							while(!(backtohome.equals("0"))) {
								System.out.println("Adding a New Product");
								System.out.println("********************");
								System.out.println("Product Name");
								String ProductName = input.next();
			
								System.out.println("Product Price");
								double Price = input.nextDouble();
			
								System.out.println("Size");
								String Size = input.next();
							
				
								System.out.println("StoringDate");
								String StoringDate = input.next();
								
								System.out.println("Quantity");
								double Quantity = input.nextDouble();
								
								int result=productionmanagerservice.addProductiondetails(ProductName, Price, Size, StoringDate, Quantity);
								if(result==1) {
									System.out.println("====Production Details Added Succesfully====");
								}else {
									System.out.println("====Production Details Faild To Add====");
									
								}
								System.out.println(" Press 0 to go back to home or press any other key to continue....");
								backtohome=input.next();
								
							}	
							backtohome="backtohome";
							break;
						
						case 2: //Handles the updating Process of Production Details
							while(!(backtohome.equals("0"))) {
							System.out.println("Update an exisiting Product");
							System.out.println("***************************");
							System.out.println("Enter Production Id");
							int ProductionId=input.nextInt();
						
							System.out.println("New Product Price");
							double Price = input.nextDouble();
		
							System.out.println("New Size");
							String Size = input.next();
						
			
							System.out.println("New StoringDate");
							String StoringDate = input.next();
							
							System.out.println("New Quantity");
							double Quantity = input.nextDouble();
							
							int result=productionmanagerservice.updateProductiondetails(ProductionId,Price,Size, StoringDate,Quantity);
							if(result==1) {
								System.out.println("=======Production Details Updated Succesfully=======");
								Product tempProduct = productionmanagerservice.searchProductiondetails(ProductionId);
								
								System.out.println("==============================================================");
								
								System.out.println("=======================Production list==============================");
								
								System.out.println("ProductionId"+"\t\t "+"Name:"+"\t\t " +"Price:"+"\t\t " +"Size: "+"\t\t " +"Storing Date:"+"\t\t " +"Quantity:"+"\t\t ");
								System.out.println(tempProduct.getProductid()+ "\t\t\t"+tempProduct.getName()+ "\t\t"+tempProduct.getPrice()+ "\t\t "+tempProduct.getSize()+ "\t\t  "+tempProduct.getStoredDate()+ "\t\t "+tempProduct.getQuantity()+"\t ");
								
								System.out.println("==============================================================");
							
							
								}
							else if(result==-1) {
								System.out.println("Production Details Faild To Update");
								
							}
							System.out.println(" Press 0 to go back to home or press any other key to continue....");
							backtohome=input.next();
							
							}
						
							break;
						case 3: //Handles View Production Details
							System.out.println("View Production Details");
							System.out.println("***********************");
							List<Product> ProductionList =productionmanagerservice.ViewProductionDetails();//Consumes the productionmanagerservice ViewProductionDetails()
							System.out.println("======================================Production list===============================================");
								System.out.println("ProductionId"+"\t\t "+"Name:"+"\t\t " +"Price:"+"\t\t " +"Size: "+"\t\t " +"Storing Date:"+"\t\t " +"Quantity:"+"\t\t ");
							
							for(Product tempProduct: ProductionList ) {
								System.out.println(tempProduct.getProductid()+ "\t\t\t"+tempProduct.getName()+ "\t\t"+tempProduct.getPrice()+ "\t\t "+tempProduct.getSize()+ "\t\t  "+tempProduct.getStoredDate()+ "\t\t "+tempProduct.getQuantity()+"\t ");
												
							}
							
							System.out.println("=====================================================================================================");
				            System.out.println("Press 0 to navigate back to home or press any other key to continue....");
							
							backtohome=input.nextLine();
							
							
							break;
						case 4: //Handles Delete Production Details
							System.out.println("Delete Production Details");
							System.out.println("***********************************");
							System.out.println("Enter the Production ID To Delete:");
							int id1=input.nextInt();
		
							//String proName = input.nextLine();
							int result1 =productionmanagerservice.removeProductiondetails((id1-1));//Consumes the Production ManagerService removeProductionDetails()
							
							if(result1==1) {
								System.out.println("=======Successfully Removed the Production Details!=======" );
								
							}
							else {
								System.out.println("Error in Removing the Production Details! " );
							}
							 
							  
							  
				              System.out.println("Press 0 to navigate back to home or press any other key to continue....");
							
							backtohome=input.nextLine();
							
							break;
							
							
						case 5: //Handles Search Production Details
							System.out.println("Search production by ID");
							System.out.println("************************");
							System.out.println("Enter the production ID");
							int id=input.nextInt();
							
		
							//String PName = input.nextLine();
							Product result =productionmanagerservice.searchProductiondetails(id);//Consumes the Production ManagerService SearchProductionDetails()
							if(result!=null) {// this should be changed to data ------------------------------------------------------********
								System.out.println("=======Production Details Found=======");
								System.out.println("*************************Production list*******************************");
								System.out.println("ProductionId"+"\t\t "+"Name:"+"\t\t " +"Price:"+"\t\t " +"Size: "+"\t\t " +"Storing Date:"+"\t\t " +"Quantity:"+"\t\t ");	
								
								System.out.println(result.getProductid()+ "\t\t\t"+result.getName()+ "\t\t"+result.getPrice()+ "\t\t "+result.getSize()+ "\t\t  "+result.getStoredDate()+ "\t\t "+result.getQuantity()+"\t ");
								System.out.println("***********************************************************************");
							}else {
								
								System.out.println("=======Production Details  Not Found=======");
							}
		
							System.out.println("Press 0 to navigate back to home or press any other key to continue....");
							
							backtohome=input.nextLine();
							break;
				
						default:
							throw new IllegalArgumentException("Unexpected value: " + i);
						}
	
			}
	
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("============Textile Production Manager Consumer Stopped.============");
		bundleContext.ungetService(ProductionManagerServiceReference);
		
	}

}
