package server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import request.EANRequestor;
import request.FlightParameters;
import alerter.CheapFlightTracker;
import alerter.FlightTrigger;
import data.FlightBook;

public class EANDataCollector implements Runnable{

	private EANRequestor _requestor;
	private SimpleDateFormat _formatter;
	
	private double _totalNumQueries;
	private double _minutesBetweenQueries;
	private int _hoursToRun;
	
	private List<FlightParameters> _trackedFlights;
	
	private List<FlightTrigger> _triggers;
	
	public EANDataCollector(int queriesPerHour, int hoursToRun){
		_requestor = new EANRequestor();
		_formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		_totalNumQueries = queriesPerHour * hoursToRun;
		_minutesBetweenQueries = 60 / queriesPerHour;
		_hoursToRun = hoursToRun;
		
		_trackedFlights = new ArrayList<FlightParameters>();
		
		_triggers = new ArrayList<FlightTrigger>();
	}
	
	public EANDataCollector(int queriesPerHour){
		_requestor = new EANRequestor();
		_formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		_totalNumQueries = Integer.MAX_VALUE;
		_minutesBetweenQueries = 60 / queriesPerHour;
		_hoursToRun = Integer.MAX_VALUE;
		
		_trackedFlights = new ArrayList<FlightParameters>();
		
		_triggers = new ArrayList<FlightTrigger>();
	}
	
	public void addFlightInformation(String departureDate,
			String returnDate,
			String origin,
			String destination,
			String departureTOD,
			String returnTOD,
			boolean isRoundTrip){
		FlightParameters params = new FlightParameters();
		try {
			params.set_departureDate(_formatter.parse(departureDate));
			params.set_returnDate(_formatter.parse(returnDate));
			params.set_origin(origin);
			params.set_destination(destination);
			params.set_timeDeparture(departureTOD);
			params.set_timeReturn(returnTOD);
			params.set_isRoundTrip(true);
		} catch (Exception e) {
			return;
		}
		_trackedFlights.add(params);
	}
	
	public void addTrigger(FlightTrigger trigger){
		_triggers.add(trigger);
	}
			
	
	public void run() {
		int i = 0;
		
		System.out.println("-------");
		System.out.println("Scheduled "+_totalNumQueries+" queries to EAN over the next "+_hoursToRun+" hours");
		System.out.println("Pausing "+_minutesBetweenQueries+" minutes after each query");
		System.out.println("Checking a total of "+_trackedFlights.size()+" flights");
		for(FlightParameters f : _trackedFlights){
			System.out.println("   "+f);
		}
		System.out.println("-------");
		
		while(i < _totalNumQueries){
			
			try {
				for(FlightParameters f : _trackedFlights){
					FlightBook book = _requestor.getBook(f);
					
					for(FlightTrigger trigger : _triggers){
						if(trigger.triggered(book)){
							trigger.react(book);
						}
					}
					
				}
			
				Thread.sleep((int)(1000*60*_minutesBetweenQueries));
			} catch (InterruptedException e) {
				return;	
			}
			i++;
		}
	} 
	
	public static void main(String[] args){
		EANDataCollector server = new EANDataCollector(2, 100);
		
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
		
//		EANDataCollector server2 = new EANDataCollector(2, 100);
//		
//		CheapFlightTracker tracker2 = new CheapFlightTracker(new String[]{
//				"rlosquadro@gmail.com",
//				"egerard23@gmail.com"
//		});
//		
//		server2.addTrigger(tracker2);
//		
//		server2.addFlightInformation("02/20/2012", "02/25/2012", 
//				"IAD", "DEN", "06:00 AM", "09:00 PM", true);
//		
//		server2.run();
		
	}
}
