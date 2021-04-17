package ProductionManagerService;

import java.util.List;

import Model.Product;

public interface ProductionManagerService {

	public  int addProductiondetails(String ProductName,double Price,String size,String StoredDate,double Quantity );//Adds the new Production Details.
	public  int updateProductiondetails(int ProductionId,double updatedProductPrice,String updateProductsize,String updateStoredDate,double updateProductQuantity);//Updates the Production details.
	public  int removeProductiondetails(int ProductionId);//Removes the Production details from the list
	public Product searchProductiondetails(int ProductionId);//Search the Production details by name.
	public   List<Product> ViewProductionDetails();//View the Production Details
}
