package customerService;

import java.util.List;

import model.Order;

public interface CustomerService {

	//Add Order Method
	public int placeOrders(String orderDate, String item, double qty, String orderStatus,String transportLocation, String transportStatus);
	
	//Update Order Method
	public int editOrders(int id, String updateOrderDate, double updateQty, String updateOrderStatus,String UpdateTransportLocation, String UpdateTransportStatus);
	
	//Remove order Method
	public int removeOrder(int id);
	
	//View Order Method
	public List<Order> viewOrders();
	
	//Search by order id Method
	public Order searchOrders(int id);
		
	
}
