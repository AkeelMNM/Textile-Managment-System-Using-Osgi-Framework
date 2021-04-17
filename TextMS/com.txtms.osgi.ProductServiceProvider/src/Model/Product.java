package Model;

public class Product {
	
	private int Productid;//id of the product
	private String Name;//name of the product
	private double Price;//price of the product
	private String size;//size of the product
	private String StoredDate;//Storing Date of the product
	private double Quantity;//Quantity of the product
	
		
	public Product() {
			
		}
	
	public Product(int productid, String name, double price, String size, String storedDate, double quantity) {
		super();
		Productid = productid;
		Name = name;
		Price = price;
		this.size = size;
		StoredDate = storedDate;
		Quantity = quantity;
	}
	
	
	public int getProductid() {
		return Productid;
	}
	public void setProductid(int productid) {
		Productid = productid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getStoredDate() {
		return StoredDate;
	}
	public void setStoredDate(String storedDate) {
		StoredDate = storedDate;
	}
	public double getQuantity() {
		return Quantity;
	}
	public void setQuantity(double quantity) {
		Quantity = quantity;
	}
	
	
	
	
	
	
	
	
	

}
