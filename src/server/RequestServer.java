package server;

import alerter.CheapFlightTracker;

public class RequestServer {
	
	private int m_numRequestsPerHour;
	
	public RequestServer(){
		m_numRequestsPerHour = 2;
	}
	
	public void addRequest()
	
	public void start(){
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EANDataCollector server = new EANDataCollector(2);
		
		CheapFlightTracker tracker = new CheapFlightTracker(new String[]{
				"egerard23@gmail.com",
				"cweitzn1@gmail.com"
		});
		
		server.addTrigger(tracker);
		
//		server.addFlightInformation("02/20/2012", "02/25/2012", 
//				"NWK", "DEN", "06:00 AM", "09:00 PM", true);
		server.addFlightInformation("02/20/2012", "02/25/2012", 
				"NYC", "DEN", "06:00 AM", "09:00 PM", true);
		
		server.run();
		
	}

	
}
