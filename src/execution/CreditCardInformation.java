package execution;

public class CreditCardInformation{
	
	String email;
	String firstName;
	String lastName;
	String homePhone;
	String workPhone;
	String creditCardNum;
	String creditCardType;
	String creditCardLastName;
	String creditCardExpirationMonth;
	String creditCArdExpirationYear;
	
	public CreditCardInformation(String email,
			String firstName,
			String lastName,
			String homePhone,
			String workPhone,
			String creditCardNum,
			String creditCardType,
			String creditCardLastName,
			String creditCardExpirationMonth,
			String creditCArdExpirationYear){
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
		this.creditCardNum = creditCardNum;
		this.creditCardType = creditCardType;
		this.creditCardLastName = creditCardLastName;
		this.creditCArdExpirationYear = creditCArdExpirationYear;
		this.creditCardExpirationMonth = creditCardExpirationMonth;
	}
	
	@Override
	public String toString(){
		return "<ReservationInfo>"+
		 "<email>"+email+"</email>"+
		 "<firstName>"+firstName+"</firstName>"+
		 "<lastName>"+lastName+"</lastName>"+
		 "<homePhone>"+homePhone+"</homePhone>"+
		 "<workPhone>"+workPhone+"</workPhone>"+
		 "<creditCardNumber>"+creditCardNum+"</creditCardNumber>"+
		 "<creditCardType>"+creditCardType+"</creditCardType>"+
		 "<creditCardLastName>"+creditCardLastName+"</creditCardLastName>"+
		 "<creditCardExpirationMonth>"+creditCardExpirationMonth+"</creditCardExpirationMonth>"+
		 "<creditCardExpirationYear>"+creditCArdExpirationYear+"</creditCardExpirationYear>"+
		"</ReservationInfo>";
	}
	
}