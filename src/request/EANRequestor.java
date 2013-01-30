package request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;

import parse.EANResponseParser;
import util.XmlPostRequestor;
import data.FlightBook;


public class EANRequestor extends XmlPostRequestor implements IRequestor {

	// TravelBook
//	public final static String key = "jdmyxss7kdvkfr5hvqdgdznr";
//	public final static String secret = "sSeqH2mZ";
	
	// Agent
	public final static String key = "5cqy9c8urss2m79gukxqnbds";
	public final static String secret = "hbjPVVCw";
	
	private EANResponseParser _parser;
	private DateFormat _formatter;
	
	public EANRequestor(){
		
		// TODO configure this
		set_baseUrl("http://api.ean.com/ean-services/rs/air/200919/xmlinterface.jsp");
		
		_parser = new EANResponseParser();
		
		_formatter = new SimpleDateFormat("MM/dd/yyyy");
		
	}
	
	public FlightBook getBook(FlightParameters params){
		
		String xml = createXml(params);
		
		addParam("cid", "55505");
		addParam("resType", "air");
		addParam("intfc", "ws");
		addParam("apiKey", key);
		addParam("xml", xml);
		
		HttpResponse response = post();
		
		if(response == null){
			// make this throw
			return null;
		}
		
		return _parser.parse(response, params);
	}
	
	private String createXml(FlightParameters params){
		StringBuilder builder = new StringBuilder();
		builder.append("<AirSessionRequest method=\"getAirAvailability\">");
		builder.append("<AirAvailabilityQuery>");
		builder.append("<originCityCode>");
		builder.append(params.get_origin());
		builder.append("</originCityCode>");
		builder.append("<destinationCityCode>");
		builder.append(params.get_destination());
		builder.append("</destinationCityCode>");
		builder.append("<departureDateTime>");
		builder.append(_formatter.format(params.get_departureDate())).append(" ").append(params.get_timeDeparture());
		builder.append("</departureDateTime>");
		builder.append("<returnDateTime>");
		builder.append(_formatter.format(params.get_returnDate())).append(" ").append(params.get_timeReturn());
		builder.append("</returnDateTime>");
		builder.append("<fareClass>Y</fareClass>");
		
		String tripType = params.is_isRoundTrip() ? "R" : "O";
		builder.append("<tripType>").append(tripType).append("</tripType>");
		
		builder.append("<Passengers><adultPassengers>1</adultPassengers></Passengers>");
		builder.append("<xmlResultFormat>2</xmlResultFormat><searchType>2</searchType></AirAvailabilityQuery></AirSessionRequest>");
		
		return builder.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
}
