package customerService;

import java.util.List;

import model.Order;
import orderDatabase.OrderData;

public class CustomerServiceImpl implements CustomerService{
	
	//Add Order
	@Override
	public synchronized int placeOrders(String orderDate, String item, double qty, String orderStatus,String transportLocation, String transportStatus) {
		
		int id = OrderData.orderList.size() + 1;
		Order order = new Order(id, orderDate, item, qty, orderStatus,transportLocation,transportStatus);
		OrderData.orderList.add(order);
	
		return 1;
	}

	//Update Order
	@Override
	public synchronized int editOrders(int id, String updateOrderDate, double updateQty, String updateOrderStatus,String UpdateTransportLocation, String UpdateTransportStatus) {
		
		Order updateOrder = searchOrders(id);
		
		if(updateOrderDate != null) {
			updateOrder.setOrderDate(updateOrderDate);
		}
		if(updateQty != 0) {
			updateOrder.setQty(updateQty);
		}
		if(updateOrderStatus != null) {
			updateOrder.setOrderStatus(updateOrderStatus);
		}
		if(UpdateTransportLocation != null) {
			updateOrder.setTransportLocation(UpdateTransportLocation);
		}
		if(UpdateTransportStatus != null) {
			updateOrder.setTransportStatus(UpdateTransportStatus);
		}
		
		int listsize = OrderData.orderList.size();
		
		if((id-1) >= 0 && (id-1) < listsize) {
			OrderData.orderList.set((id-1), updateOrder);
			return 1;
		}else {
			return -1;
		}
		
	}
	
	//Remove Order
	@Override
	public synchronized int removeOrder(int id) {
		
		int listSize = OrderData.orderList.size();
		
		if ((id-1) >= 0 && (id-1) < listSize) {
			OrderData.orderList.remove((id-1));
			return 1;
		}		
		else {
			return -1;
		}
	}
	
	//View Order
	@Override
	public List<Order> viewOrders() {
	
		return OrderData.orderList;
	}

	//Search order by id
	@Override
	public Order searchOrders(int id) {

		boolean flag = false;
		Order data = new Order();
		
		for(int i = 0 ; i<OrderData.orderList.size(); i++) {
			if(OrderData.orderList.get(i).getId() == id) {
				data = OrderData.orderList.get(i);
				flag = true;
			}
		}
		
		if(flag == true) {
			return data;
		}else {
			return null;
		}
	}

	
	
}
