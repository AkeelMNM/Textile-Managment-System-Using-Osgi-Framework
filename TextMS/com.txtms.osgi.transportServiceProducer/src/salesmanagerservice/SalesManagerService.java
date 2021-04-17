package salesmanagerservice;

import java.util.List;

import transportmodel.Transport;

public interface SalesManagerService {

	/*----------- Add new Transport to the TransportList ------------*/
	public int addTransport(String location,String shippedDate,String recevingDate);
	
	/*----------- Update Transport in the TransportList ------------*/
	public int updateTransport(int id ,String updateLocation,String updateShippedDate,String updateRecevingDate);
	
	/*----------- Remove Transport from the TransportList ------------*/
	public int removeTransport(int id);
	
	/*----------- Add new Transport to the TransportList ------------*/
	public Transport searchTransport(int id);
	
	/*----------- Get Transport Details in the TransportList ------------*/
	public List<Transport> viewTransport();
	
	
}
