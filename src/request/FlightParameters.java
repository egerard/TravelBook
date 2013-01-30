package request;

import java.util.Date;

public class FlightParameters {

	private boolean _isRoundTrip;
	private String  _origin;
	private String  _destination;
	private Date    _departureDate;
	private Date    _returnDate;
	private String  _timeDeparture;
	private String  _timeReturn;
	
	public boolean is_isRoundTrip() {
		return _isRoundTrip;
	}

	public void set_isRoundTrip(boolean roundTrip) {
		_isRoundTrip = roundTrip;
	}

	public String get_origin() {
		return _origin;
	}

	public void set_origin(String _origin) {
		this._origin = _origin;
	}

	public String get_destination() {
		return _destination;
	}

	public void set_destination(String _destination) {
		this._destination = _destination;
	}

	public Date get_departureDate() {
		return _departureDate;
	}

	public void set_departureDate(Date date) {
		_departureDate = date;
	}

	public Date get_returnDate() {
		return _returnDate;
	}

	public void set_returnDate(Date date) {
		_returnDate = date;
	}

	public String get_timeDeparture() {
		return _timeDeparture;
	}

	public void set_timeDeparture(String ofDayDeparture) {
		_timeDeparture = ofDayDeparture;
	}

	public String get_timeReturn() {
		return _timeReturn;
	}

	public void set_timeReturn(String ofDayReturn) {
		_timeReturn = ofDayReturn;
	}
	
	@Override
	public String toString(){
		return _origin+" on "+ _departureDate + " to " + _destination + " on " + _returnDate + " RoundTrip=" + is_isRoundTrip();
	}

}
