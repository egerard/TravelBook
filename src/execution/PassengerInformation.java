package execution;

public class PassengerInformation{ 
	
	String firstName;
	String lastName;
	String isChild;
	String passengerTypeCode;
	String middleName;
	String dateOfBirth;
	String redressNumber;
	String gender;
	
	public PassengerInformation(String firstName,
			String lastName,
			String isChild,
			String passengerTypeCode,
			String middleName,
			String dateOfBirth,
			String redressNumber,
			String gender){
		this.firstName = firstName;
		this.lastName = lastName;
		this.isChild = isChild;
		this.passengerTypeCode = passengerTypeCode;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.redressNumber = redressNumber;
		this.gender = gender;
	}
	
	@Override
	public String toString(){
		return "<Passenger>"+
		 "<firstName>"+firstName+"</firstName>"+
		 "<lastName>"+lastName+"</lastName>"+
		 "<isChild>"+isChild+"</isChild>"+
		 "<passengerTypeCode>"+passengerTypeCode+"</passengerTypeCode>"+
		 "<middleName>"+middleName+"</middleName>"+
		 "<dateOfBirth>"+dateOfBirth+"</dateOfBirth>"+
		 "<redressNumber>"+redressNumber+"</redressNumber> "+
		 "<gender>"+gender+"</gender>"+
		"</Passenger>";
	}
}