package execution;

public class AddressInformation{
	
	String type;
	String isPrimary;
	String address;
	String city;
	String stateProvince;
	String country;
	String postalCode;
	
	public AddressInformation(String type,
			String isPrimary,
			String address,
			String city,
			String statePRovince,
			String country,
			String postalCode){
		this.type = type;
		this.isPrimary = isPrimary;
		this.address = address;
		this.city = city;
		this.stateProvince = statePRovince;
		this.country = country;
		this.postalCode = postalCode;
	}
	
	@Override
	public String toString(){
	return "<AddressInfo>"+
	 "<type>"+ type +"</type>"+
	 "<isPrimary>"+isPrimary+"</isPrimary>"+
	 "<address1>"+ address +"</address1>"+
	 "<city>"+city+"</city>"+
	 "<stateProvince>"+stateProvince+"</stateProvince>"+
	 "<country>"+country+"</country>"+
	 "<postalCode>"+postalCode+"</postalCode>"+
	"</AddressInfo>";
	}
}