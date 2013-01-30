package data;


public class FlightSegment {
	
	private String _key;
	private String _class;
	private FlightSegment _connection;
	private String _airlineCode;
	private String _airline;
	private String _flightNumber;
	private String _originCityCode;
	private String _destinationCityCode;
	private String _departureDateTime;
	private String _arrivalDateTime;
	private String _equipmentCode;
	private String _numStops;
	private String _originCity;
	private String _originStateProvince;
	private String _originCount;
	private String _destinationCity;
	private String _destinationStateProvince;
	private String _destinationCountry;
	
	
	public FlightSegment(){
		_connection = null;
	}
	
	public void setConnection(FlightSegment segment){
		_connection = segment;
	}
	
	public String get_key() {
		return _key;
	}

	public void set_key(String _key) {
		this._key = _key;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public FlightSegment get_connection() {
		return _connection;
	}

	public void set_connection(FlightSegment _connection) {
		this._connection = _connection;
	}

	public String get_airlineCode() {
		return _airlineCode;
	}

	public void set_airlineCode(String code) {
		_airlineCode = code;
	}

	public String get_airline() {
		return _airline;
	}

	public void set_airline(String _airline) {
		this._airline = _airline;
	}

	public String get_flightNumber() {
		return _flightNumber;
	}

	public void set_flightNumber(String number) {
		_flightNumber = number;
	}

	public String get_originCityCode() {
		return _originCityCode;
	}

	public void set_originCityCode(String cityCode) {
		_originCityCode = cityCode;
	}

	public String get_destinationCityCode() {
		return _destinationCityCode;
	}

	public void set_destinationCityCode(String cityCode) {
		_destinationCityCode = cityCode;
	}

	public String get_departureDateTime() {
		return _departureDateTime;
	}

	public void set_departureDateTime(String dateTime) {
		_departureDateTime = dateTime;
	}

	public String get_arrivalDateTime() {
		return _arrivalDateTime;
	}

	public void set_arrivalDateTime(String dateTime) {
		_arrivalDateTime = dateTime;
	}

	public String get_equipmentCode() {
		return _equipmentCode;
	}

	public void set_equipmentCode(String code) {
		_equipmentCode = code;
	}

	public String get_numStops() {
		return _numStops;
	}

	public void set_numStops(String stops) {
		_numStops = stops;
	}

	public String get_originCity() {
		return _originCity;
	}

	public void set_originCity(String city) {
		_originCity = city;
	}

	public String get_originStateProvince() {
		return _originStateProvince;
	}

	public void set_originStateProvince(String stateProvince) {
		_originStateProvince = stateProvince;
	}

	public String get_originCount() {
		return _originCount;
	}

	public void set_originCount(String count) {
		_originCount = count;
	}

	public String get_destinationCity() {
		return _destinationCity;
	}

	public void set_destinationCity(String city) {
		_destinationCity = city;
	}

	public String get_destinationStateProvince() {
		return _destinationStateProvince;
	}

	public void set_destinationStateProvince(String stateProvince) {
		_destinationStateProvince = stateProvince;
	}

	public String get_destinationCountry() {
		return _destinationCountry;
	}

	public void set_destinationCountry(String country) {
		_destinationCountry = country;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\t Flight "+_airlineCode+_flightNumber +"\n");
		builder.append("\t From: "+_originCityCode+" "+_departureDateTime+"\n");
		builder.append("\t To: "+_destinationCityCode+" "+_arrivalDateTime+"\n");
		builder.append("------------------- \n");
		if(_connection != null){
			builder.append(_connection);
		}
		
		return builder.toString();
	}
}
