package request;

import data.FlightBook;

public interface IRequestor {
	
	public FlightBook getBook(FlightParameters params);
	
}
