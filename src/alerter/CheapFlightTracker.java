package alerter;

import java.sql.Time;

import data.Flight;
import data.FlightBook;

public class CheapFlightTracker implements FlightTrigger{

	private EmailMessageSender _sender;
	
	private double _currPrice;
	
	private String[] _recipients;
	
	public CheapFlightTracker(String recipient){
		_currPrice = Double.MAX_VALUE;
		_sender = new EmailMessageSender();
		_recipients = new String[]{recipient};
	}
	
	public CheapFlightTracker(String[] recipients){
		_currPrice = Double.MAX_VALUE;
		_sender = new EmailMessageSender();
		_recipients = recipients;
	}
	
	public void react(FlightBook book) {
		if(book == null) return;
		Flight flight = book.getCheapestFlight();
		
		String priceChange = "";
		
		if(flight != null){
			
			System.out.println(new Time(System.currentTimeMillis()) + ": "+flight);

			if(_currPrice != flight.get_nativeTotalPrice()){
				System.out.println("Price change found");
				
				priceChange += "Price Change Alert: " + flight.print(book);
				_currPrice = flight.get_nativeTotalPrice();
				
				for(String recipient : _recipients){
					_sender.sendMessage(recipient, priceChange);
				}
			}
			
		}
		
	}

	public boolean triggered(FlightBook book) {
		
		if(book == null) 
			return false;
		
		Flight flight = book.getCheapestFlight();
		
		if(flight == null) 
			return false;
		
		return flight.get_nativeBaseFare() != _currPrice;
	}

	
}
