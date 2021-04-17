package model;

public class Order {

	private int id;
	private String orderDate;
	private String item;
	private double qty;
	private String orderStatus;
	private String transportStatus;
	private String transportLocation;
	
	public Order() {
	}
	
	public Order(int id, String orderDate, String item, double qty, String orderStatus, String transportStatus,
			String transportLocation) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.item = item;
		this.qty = qty;
		this.orderStatus = orderStatus;
		this.transportStatus = transportStatus;
		this.transportLocation = transportLocation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTransportStatus() {
		return transportStatus;
	}

	public void setTransportStatus(String transportStatus) {
		this.transportStatus = transportStatus;
	}

	public String getTransportLocation() {
		return transportLocation;
	}

	public void setTransportLocation(String transportLocation) {
		this.transportLocation = transportLocation;
	}

	
}
