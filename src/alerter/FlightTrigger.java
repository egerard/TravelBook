package alerter;

import data.FlightBook;

public interface FlightTrigger {

	public boolean triggered(FlightBook book);
	
	public void react(FlightBook book);
}
