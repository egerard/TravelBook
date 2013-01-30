package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import request.FlightParameters;

public class FlightBook {

	private List<Flight> _entries;
	private Map<String, FlightSegment> _segments;
	
	private FlightParameters _params;
	
	public FlightBook(){
		_entries = new ArrayList<Flight>();
		_segments = new HashMap<String, FlightSegment>();
	}
	
	public FlightBook(FlightParameters params){
		_entries = new ArrayList<Flight>();
		_segments = new HashMap<String, FlightSegment>();
		_params = params;
	}
	
	public FlightParameters getFlightParameters(){
		return _params;
	}
	
	public void addEntry(Flight entry){
		_entries.add(entry);
	}
	
	public Flight getCheapestFlight(){
		Flight cheapest = null;
		for(Flight entry : _entries){
			if(cheapest == null || cheapest.get_displayTotalPrice() > entry.get_displayTotalPrice()){
				cheapest = entry;
			}
		}
		return cheapest;
	}
	
//	public Entry getCheapestFlight(UserPreference preferences){
//		
//	}
	
	@Override
	public String toString(){
		String ret = "";
		int i = 1;
		for(Flight flight : _entries){
			ret += "Flight "+i+":";
			ret += flight;
			ret += "\n";
			ret += "---------------";
			ret += "\n";
			i++;
		}
		return ret;
	}

	public void addSegment(String key, FlightSegment segment) {
		_segments.put(key, segment);
	}
	
	public FlightSegment getSegment(String key){
		return _segments.get(key);
	}

	public Map<String, FlightSegment> getSegments() {
		return _segments;
	}

	public List<Flight> getEntries() {
		return _entries;
	}

	public void setflightParameters(FlightParameters parameters) {
		_params = parameters;
	}
	
}
