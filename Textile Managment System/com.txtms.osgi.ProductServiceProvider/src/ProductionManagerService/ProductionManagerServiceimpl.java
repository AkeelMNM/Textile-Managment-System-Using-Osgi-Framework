package ProductionManagerService;

import java.util.List;
import ProductionDatabase.Productiondata;
import Model.Product;

public class ProductionManagerServiceimpl implements ProductionManagerService {

	public synchronized int addProductiondetails(String ProductName, double Price, String size, String StoredDate, double Quantity) {
		// TODO Auto-generated method stub
		
		int id=Productiondata.ProductionList.size()+1;
		Product AddProduct = new Product(id, ProductName,Price,size,StoredDate,Quantity);
		Productiondata.ProductionList.add(AddProduct);
		
		return 1;
	}

	@Override
	public int updateProductiondetails(int ProductionId, double updatedProductPrice,
			String updateProductsize, String updateStoredDate, double updateProductQuantity) {
		// TODO Auto-generated method stub
		
		Product UpdateProduct=searchProductiondetails(ProductionId);
		
	
		if(updatedProductPrice!= 0) {
			UpdateProduct.setPrice(updatedProductPrice);
			
		}
		if(updateProductsize!= null) {
			UpdateProduct.setSize(updateProductsize);
			
		}
		if(updateStoredDate!= null) {
			UpdateProduct.setStoredDate(updateStoredDate);
			
		}
		if(updateProductQuantity!= 0) {
			UpdateProduct.setQuantity(updateProductQuantity);
			
		}
		
		int listSize=Productiondata.ProductionList.size();
		
		if((ProductionId-1)>=0 && ProductionId<listSize) {
			Productiondata.ProductionList.set((ProductionId-1),UpdateProduct);
			return 1;
		}
		else {
			return -1;
		}
		
	
	}

	@Override
	public int removeProductiondetails(int ProductionId) {
		// TODO Auto-generated method stub
		
		
		Product DeleteProduct=null;
		
		
		
		
		int listSize=Productiondata.ProductionList.size();
		
		if((ProductionId-1)>=0 && ProductionId<listSize) {
			Productiondata.ProductionList.remove((ProductionId-1));
			return 1;
		}
		else {
			return -1;
		}
	}

	@Override
	public Product searchProductiondetails(int ProductionId) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Product data =new Product();
		
		for(int i=0;i<Productiondata.ProductionList.size();i++) {
			if(Productiondata.ProductionList.get(i).getProductid()==ProductionId) {
				data=Productiondata.ProductionList.get(i);
				flag=true;
	
			}
		}
		if(flag==true) {
			return data;
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<Product> ViewProductionDetails() {
		// TODO Auto-generated method stub
		return  Productiondata.ProductionList;
	}

	

}
