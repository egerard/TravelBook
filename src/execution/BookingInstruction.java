package execution;

public class BookingInstruction {

	private CreditCardInformation _creditCard;
	
	private AddressInformation _address;
	
	private PassengerInformation _passenger;
	
	public BookingInstruction(){
		
	}
	
	public CreditCardInformation get_creditCard() {
		return _creditCard;
	}

	public void set_creditCard(CreditCardInformation card) {
		_creditCard = card;
	}

	public AddressInformation getAddress() {
		return _address;
	}

	public void set_address(AddressInformation _address) {
		this._address = _address;
	}

	public PassengerInformation get_passenger() {
		return _passenger;
	}

	public void set_passenger(PassengerInformation _passenger) {
		this._passenger = _passenger;
	}

	public AddressInformation get_address() {
		return _address;
	};
	

}
