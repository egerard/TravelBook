package server;

import java.io.IOException;

import org.apache.http.HttpResponse;

import parse.EANBookingParser;
import util.SampleData;
import util.XmlPostRequestor;
import data.Flight;
import data.FlightBook;
import data.FlightSegment;
import execution.BookingInstruction;


public class EANFlightBooker extends XmlPostRequestor{

	private EANBookingParser _parser;
	
	public EANFlightBooker(){
		try {
			setSecure();
		} catch (IOException e) {
			e.printStackTrace();
		}
		set_baseUrl("https://book.api.ean.com/external/xmlinterface.jsp");
		_parser = new EANBookingParser();
	}

	public boolean book(Flight flight, FlightBook book, BookingInstruction instructions){
		
		String xml = getXml(flight, book, instructions);
		addParam("cid", "55505");
		addParam("resType","air");
		addParam("intfc","ws");
		addParam("xml", xml);
		
		HttpResponse response = post();
		
		if(response == null){
			System.out.println("no response");
			return false;
		}
		
		return _parser.parse(response);
	}
	
	public String getXml(Flight flight, FlightBook book, BookingInstruction instruction){
		StringBuilder builder = new StringBuilder();
		
		FlightSegment firstDepartureSegment = book.getSegment(flight.getOutgoingSegments().get(0));
		FlightSegment firstReturnSegment = book.getSegment(flight.getReturnSegments().get(0));
		
		builder.append("<AirSessionRequest method=\"createNewAirReservation\"><AirReservationRequest>");
		builder.append("<supplierType>"+flight.get_supplierType()+"</supplierType>");
		builder.append("<departureDateTime>"+firstDepartureSegment.get_departureDateTime()+"</departureDateTime>");
		builder.append("<returnDateTime>"+firstReturnSegment.get_departureDateTime()+"</returnDateTime>");
		builder.append("<tripType>"+flight.get_tripType()+"</tripType>");
		builder.append("<shippingMethod>"+flight.get_supplierType()+"</shippingMethod>");
		
		//TODO
		builder.append("<shippingMethod>4</shippingMethod>");
		builder.append("<cacheKey>TEST</cacheKey>");
		builder.append("<cacheLocation>test</cacheLocation>");
		
		builder.append("<FlightKeys><id>");
		for(String id : flight.getOutgoingSegments()){
			builder.append(id);
		}
		builder.append("</id><segmentOutgoing>true</segmentOutgoing></FlightKeys>");
		
		builder.append("<FlightKeys><id>");
		for(String id : flight.getReturnSegments()){
			builder.append(id);
		}
		builder.append("</id><segmentOutgoing>false</segmentOutgoing></FlightKeys>");
		
		builder.append(instruction.get_creditCard());
		builder.append(instruction.getAddress());
		
		builder.append("<RateInfo>");
		builder.append("<nativeBaseFare>"+ flight.get_nativeBaseFare() +"</nativeBaseFare>");
		builder.append("<nativeTotalPrice>"+ flight.get_nativeTotalPrice() +"</nativeTotalPrice>");
		
		//TODO
		builder.append("<nativeProcessingFeeAmount>10</nativeProcessingFeeAmount>");
		
		builder.append("<nativeCurrencyCode>"+ flight.get_nativeCurrencyCode() +"</nativeCurrencyCode>");
		builder.append("<displayCurrencyCode>"+ flight.get_displayCurrencyCode() +"</displayCurrencyCode>");
		builder.append("</RateInfo>");
		
		builder.append(instruction.get_passenger());
		
		builder.append("</AirReservationRequest></AirSessionRequest>");
		
		return builder.toString();
	}
	
	public static void main(String[] args){
		EANFlightBooker booker = new EANFlightBooker();
		
		// use our SampleData suite
		Flight flight = SampleData.getFlight();
		FlightBook book = SampleData.getBook();
		book.addEntry(flight);
		BookingInstruction instructions = SampleData.getBookInstruction();
		
		String xml = booker.getXml(flight, book, instructions);
//		System.out.println(xml);
		
		booker.book(flight, book, instructions);
	}

}
