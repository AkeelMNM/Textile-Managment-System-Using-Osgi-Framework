package salesmanagerservice;

import java.util.List;

import transportDatabase.TransportData;
import transportmodel.Transport;

public class SalesManagerServiceImpl implements SalesManagerService {

	
	/**
	 * Adding new Transport Details
	 */
	@Override
	public synchronized int addTransport(String location, String shippedDate, String recevingDate) {
	
		int id = TransportData.TransportList.size() + 1;
		Transport transport = new Transport(id, location, shippedDate, recevingDate);
		TransportData.TransportList.add(transport);
		
		return 1;
	}

	/**
	 * Updating Existing Transport Details
	 */
	@Override
	public synchronized int updateTransport(int id, String updateLocation, String updateShippedDate, String updateRecevingDate) {
		
		Transport udpatedTransport = new Transport();
		udpatedTransport = searchTransport(id);
		
		if(updateLocation != null) {
			udpatedTransport.setLocation(updateLocation);
		}
		if(updateShippedDate !=null) {
			udpatedTransport.setShippedDate(updateShippedDate);
		}
		if (updateRecevingDate != null) {
			udpatedTransport.setRecevingDate(updateRecevingDate);			
		}
		
		int listSize = TransportData.TransportList.size();
		
		if ((id - 1) >= 0 && (id - 1) < listSize) {
			TransportData.TransportList.set((id-1), udpatedTransport);
			return 1;
		}else {
			return -1;
		}
		
		
	}

	/**
	 * Removing Existing Transport Details
	 */
	@Override
	public synchronized int removeTransport(int id) {
		
		int listSize = TransportData.TransportList.size();
		
		if ((id - 1) >= 0 && (id - 1) < listSize) {
			TransportData.TransportList.remove((id - 1));
			return 1;
		}else {
			return -1;
		}
	}

	/**
	 * Search Existing Transport Details
	 */
	@Override
	public Transport searchTransport(int id) {
		
		boolean flag = false;
		Transport data = new Transport();
		
		for(int i = 0 ; i <TransportData.TransportList.size(); i++) {
			if (TransportData.TransportList.get(i).getID() == id) {
				data = TransportData.TransportList.get(i);
				flag = true;
			}
		}
		
		if(flag == true) {
			return data;
		}else {
			return null;
		}
	}

	/**
	 * View Transport Details
	 */
	@Override
	public List<Transport> viewTransport() {
		return TransportData.TransportList;
	}
}