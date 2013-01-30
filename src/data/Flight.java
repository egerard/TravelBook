package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import request.FlightParameters;

public class Flight {
	
	private String _supplierType;
	private double _nativeBaseFare;
	private double _displayeBaseFare;
	private String _nativeCurrencyCode;
	private String _displayCurrencyCode;
	private double _nativeTotalPrice;
	private double _displayTotalPrice;
	private String _tripType;
	private String _ticketType;
	private List<String> _returnSegments;
	private List<String> _outgoingSegments;
	
	public Flight(){
		_returnSegments = new ArrayList<String>();
		_outgoingSegments = new ArrayList<String>();
	}
	
	public String get_supplierType() {
		return _supplierType;
	}

	public void set_supplierType(String type) {
		_supplierType = type;
	}

	public double get_nativeBaseFare() {
		return _nativeBaseFare;
	}

	public void set_nativeBaseFare(double baseFare) {
		_nativeBaseFare = baseFare;
	}

	public double get_displayeBaseFare() {
		return _displayeBaseFare;
	}

	public void set_displayeBaseFare(double baseFare) {
		_displayeBaseFare = baseFare;
	}

	public String get_nativeCurrencyCode() {
		return _nativeCurrencyCode;
	}

	public void set_nativeCurrencyCode(String currencyCode) {
		_nativeCurrencyCode = currencyCode;
	}

	public String get_displayCurrencyCode() {
		return _displayCurrencyCode;
	}

	public void set_displayCurrencyCode(String currencyCode) {
		_displayCurrencyCode = currencyCode;
	}

	public double get_nativeTotalPrice() {
		return _nativeTotalPrice;
	}

	public void set_nativeTotalPrice(double totalPrice) {
		_nativeTotalPrice = totalPrice;
	}

	public double get_displayTotalPrice() {
		return _displayTotalPrice;
	}

	public void set_displayTotalPrice(double totalPrice) {
		_displayTotalPrice = totalPrice;
	}
	
	public List<String> getReturnSegments(){
		return _returnSegments;
	}
	
	public List<String> getOutgoingSegments(){
		return _outgoingSegments;
	}

	public void addOutgoingFlightSegment(String key){
		_outgoingSegments.add(key);
	}
	
	public void addReturnFlightSegment(String key){
		_returnSegments.add(key);
	}

	public String get_tripType() {
		return _tripType;
	}

	public void set_tripType(String type) {
		_tripType = type;
	}

	public String get_ticketType() {
		return _ticketType;
	}

	public void set_ticketType(String type) {
		_ticketType = type;
	}
	
	public int getNumberConnection(FlightSegment seg){
		int i = 0;
		FlightSegment conn = seg.get_connection();
		while(conn != null){
			conn = conn.get_connection();
			i++;
		}
		return i;
	}
	
	public String print(FlightBook book){
		StringBuilder builder = new StringBuilder();
		
		builder.append("\n");
		builder.append("-----------------------\n \n");
		builder.append("Trip Information: \n \n");
		
		FlightParameters fp = book.getFlightParameters();
		builder.append("\t "+fp.get_origin()+" to "+fp.get_destination()+"\n");
		
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yy");
		if(fp.is_isRoundTrip()){
			builder.append("\t "+formatter.format(fp.get_departureDate())+" - "+formatter.format(fp.get_returnDate())+"\n");
		}else{
			builder.append("\t "+formatter.format(fp.get_departureDate())+"(One Way)"+"\n");
		}
		
		builder.append("\t ");
		builder.append("Total Price: $"+_nativeTotalPrice);
		builder.append("\n");
		builder.append("\n");
		builder.append("Outgoing: \n");
		builder.append("------------------- \n");
		for(String key : _outgoingSegments){
			FlightSegment seg = book.getSegment(key);
			builder.append(seg);
		}
		builder.append("\n");
		builder.append("Return: \n");
		builder.append("------------------- \n");
		for(String key : _returnSegments){
			FlightSegment seg = book.getSegment(key);
			builder.append(seg);
		}
		builder.append("\n");
		
		return builder.toString();
	}
	
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		
		builder.append("-----------------------\n");
		builder.append("Total Price: $"+_nativeTotalPrice);
		builder.append("-----------------------");
		
		return builder.toString();
	}
	
}
