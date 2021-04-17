package transportmodel;

public class Transport {

	private int ID;
	private String location;
	private String shippedDate;
	private String recevingDate;
	
	public Transport() {
		
	}
	public Transport(int iD, String location, String shippedDate, String recevingDate) {
		super();
		ID = iD;
		this.location = location;
		this.shippedDate = shippedDate;
		this.recevingDate = recevingDate;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}
	public String getRecevingDate() {
		return recevingDate;
	}
	public void setRecevingDate(String recevingDate) {
		this.recevingDate = recevingDate;
	}
	
	
}
