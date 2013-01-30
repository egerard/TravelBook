package util;


import data.Flight;
import data.FlightBook;
import data.FlightSegment;
import execution.AddressInformation;
import execution.BookingInstruction;
import execution.CreditCardInformation;
import execution.PassengerInformation;

public class SampleData {

	private static Flight flight;
	private static FlightBook book;
	private static AddressInformation addressInfo;
	private static CreditCardInformation ccInfo;
	private static PassengerInformation passInfo;
	
	public static FlightBook getBook(){
		
		if(book == null){
			book = new FlightBook();
			book.addSegment("123", getSegment("123"));
			book.addSegment("456", getSegment("456"));
		}
		
		return book;
	}
	
	public static Flight getFlight(){
		
		if(flight == null){
			flight = new Flight();
			flight.set_displayCurrencyCode("USD");
			flight.set_displayeBaseFare(1);
			flight.set_displayTotalPrice(1);
			flight.set_nativeBaseFare(1);
			flight.set_nativeCurrencyCode("USD");
			flight.set_nativeTotalPrice(1);
			flight.set_supplierType("S");
			flight.set_ticketType("r");
			flight.set_tripType("r");
			
			flight.addOutgoingFlightSegment("123");
			flight.addReturnFlightSegment("456");
		}
		
		return flight;
	}
	
	private static FlightSegment getSegment(String key){
		FlightSegment fs = new FlightSegment();
		fs.set_airline("Air Tran");
		fs.set_airlineCode("AT");
		fs.set_arrivalDateTime("01/01/2013 12:00 PM");
		fs.set_class("E");
		fs.set_connection(null);
		fs.set_departureDateTime("01/02/2013 12:00 PM");
		fs.set_destinationCity("Miami");
		fs.set_destinationCityCode("MIA");
		fs.set_destinationCountry("USA");
		fs.set_destinationStateProvince("FL");
		fs.set_equipmentCode("p");
		fs.set_flightNumber(key);
		fs.set_key(key);
		fs.set_numStops("0");
		fs.set_originCity("NYC");
		fs.set_originCityCode("NYC");
		fs.set_originStateProvince("NY");
		fs.setConnection(null);
		
		return fs;
	}

	public static BookingInstruction getBookInstruction() {
		BookingInstruction instr = new BookingInstruction();
		instr.set_address(getAddressInformation());
		instr.set_creditCard(getCreditCardInformation());
		instr.set_passenger(getPassengerInformation());
		
		return instr;
	}

	private static PassengerInformation getPassengerInformation() {
		if(passInfo == null){
			passInfo = new PassengerInformation("Test Booking",
					"Test Booking",
					"false",
					"ADT",
					"",
					"01/01/1980",
					"1234567",
					"m");
		}
		
		return passInfo;
	}

	private static CreditCardInformation getCreditCardInformation() {
		if(ccInfo == null){
			ccInfo = new CreditCardInformation("test@test.com",
					"Test Booking",
					"Test Booking",
					"555-555-5555",
					"555-555-5555",
					"4005550000000019",
					"Visa",
					"Test Booking",
					"01",
					"20");
		}
		return ccInfo;
	}

	private static AddressInformation getAddressInformation() {
		if(addressInfo == null){
			addressInfo = new AddressInformation("h",
					"true",
					"travelnow",
					"travelnow",
					"travelnow",
					"US",
					"55555");
		}
		
		return addressInfo;
	}
	
	
	
}
