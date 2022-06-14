package model;

public class Address {
	private String streetName;
	private int number;
	private String City;
	private int postalCode;
	
	public Address(String streetName, int number, String city, int postalCode) {
		super();
		this.streetName = streetName;
		this.number = number;
		City = city;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	
	

}
