package model;

public class Address {
	private String streetName;
	private String number;
	private String city;
	private int postalCode;
	
	public Address(String streetName, String number, String city, int postalCode) {
		super();
		this.streetName = streetName;
		this.number = number;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public Address() {
		
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	public boolean check(String location) {
		if(streetName.toLowerCase().contains(location.toLowerCase()) || city.toLowerCase().contains(location.toLowerCase())) {
			return true;
		}
		return false;
	}
	
}
