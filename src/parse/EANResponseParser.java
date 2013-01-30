package parse;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import request.FlightParameters;
import data.Flight;
import data.FlightBook;
import data.FlightSegment;

public class EANResponseParser {
	
	DocumentBuilderFactory _dbFactory;
	DocumentBuilder _dBuilder;
	
	public EANResponseParser(){
		_dbFactory = DocumentBuilderFactory.newInstance();
		try {
			_dBuilder = _dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public FlightBook parse(HttpResponse response, FlightParameters parameters){
		
		FlightBook book = new FlightBook();
		
		book.setflightParameters(parameters);
		
		String payload = "";
		try {
			payload = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(payload.length() == 0){
			return book;
		}
		
//		System.out.println(payload);
		
		InputSource inStream = new InputSource();
	    inStream.setCharacterStream(new StringReader(payload));

		Document doc = null;
		try {
			doc = _dBuilder.parse(inStream);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(doc == null){
			return book;
		}
		
		doc.getDocumentElement().normalize(); 
		
		NodeList segments = doc.getElementsByTagName("Segment");
		loadAllFlightSegments(book, segments);
		
		System.out.println("Loaded "+book.getSegments().size() + " segments");
 
		NodeList availabilities = doc.getElementsByTagName("AirAvailabilityReply");
		loadAllAvailabilities(book, availabilities);
		
		System.out.println("Loaded "+book.getEntries().size() + " entries");
		
		return book;
	}
	
	private void loadAllFlightSegments(FlightBook book, NodeList segments) {
		for (int temp = 0; temp < segments.getLength(); temp++) {
			 
			   Node node = segments.item(temp);
			   if (node.getNodeType() == Node.ELEMENT_NODE) {
				   
				  String key = node.getAttributes().getNamedItem("key").getNodeValue();
				  FlightSegment segment = new FlightSegment();
				  segment.set_airlineCode(getNodeValue(node, "airlineCode"));
				  segment.set_airline(getNodeValue(node, "airline"));
				  segment.set_flightNumber(getNodeValue(node, "flightNumber"));
				  segment.set_originCityCode(getNodeValue(node, "originCityCode"));
				  segment.set_destinationCityCode(getNodeValue(node, "destinationCityCode"));
				  segment.set_departureDateTime(getNodeValue(node, "departureDateTime"));
				  segment.set_arrivalDateTime(getNodeValue(node, "arrivalDateTime"));
				  segment.set_equipmentCode(getNodeValue(node, "equipmentCode"));
				  segment.set_numStops(getNodeValue(node, "numberOfStops"));
				  segment.set_originCity(getNodeValue(node, "originCity"));
				  segment.set_originStateProvince(getNodeValue(node, "originStateProvince"));
				  segment.set_destinationCity(getNodeValue(node, "destinationCity"));
				  segment.set_destinationStateProvince(getNodeValue(node, "destinationStateProvince"));
				  segment.set_destinationCountry(getNodeValue(node, "destinationCountry"));
				  
			      book.addSegment(key, segment);
			   }
		}
	}

	private void loadAllAvailabilities(FlightBook book, NodeList availabilities) {
		for (int temp = 0; temp < availabilities.getLength(); temp++) {
			 
			   Node node = availabilities.item(temp);
			   if (node.getNodeType() == Node.ELEMENT_NODE) {
	 
				  Flight flight = new Flight();
				  flight.set_supplierType(getNodeValue(node, "supplierType"));
				  
				  flight.set_nativeBaseFare(Double.parseDouble(getNodeValue(node, "nativeBaseFare")));
				  flight.set_nativeCurrencyCode(getNodeValue(node, "nativeCurrencyCode"));
				  flight.set_nativeTotalPrice(Double.parseDouble(getNodeValue(node, "nativeTotalPrice")));
				  
				  flight.set_displayeBaseFare(Double.parseDouble(getNodeValue(node, "displayBaseFare")));
				  flight.set_displayCurrencyCode(getNodeValue(node, "displayCurrencyCode"));
				  flight.set_displayTotalPrice(Double.parseDouble(getNodeValue(node, "displayTotalPrice")));
				  
				  flight.set_ticketType(getNodeValue(node, "ticketType"));
				  flight.set_tripType(getNodeValue(node, "tripType"));
				  
				  // handle all of the flight segments
			      NodeList flightSegments = ((Element)node).getElementsByTagName("FlightSegment");
			      for(int i = 0; i < flightSegments.getLength(); i++){
			    	  Node fs = flightSegments.item(i);
			    	  String key = getNodeValue(fs, "segmentKey");
			    	  String fareClass = getNodeValue(fs, "fareClass");
			    	  boolean isOutgoing = Boolean.parseBoolean(getNodeValue(fs, "segmentOutgoing"));
			    	  
			    	  if(isOutgoing){
			    		  flight.addOutgoingFlightSegment(key);
			    	  }else{
			    		  flight.addReturnFlightSegment(key);
			    	  }
			    	   
			      }
			      
			      book.addEntry(flight);
			   }
		}
	}

	public String getNodeValue(Node node, String tagName){
	      Element eElement = (Element) node;
	      NodeList temp = eElement.getElementsByTagName(tagName);
	      
	      if(temp == null){
	    	  return "";
	      }
	      
	      Node n = temp.item(0);
	      
	      if(n == null){
	    	  return "";
	      }
          NodeList nlList = n.getChildNodes();
	  	  Node nValue = (Node) nlList.item(0);
	  	  
	  	  if(nValue == null)
	  		  return "";
	  	  
	  	  return nValue.getNodeValue();
	}
	
}
