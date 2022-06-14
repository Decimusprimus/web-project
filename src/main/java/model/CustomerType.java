package model;

public class CustomerType {
	private CustomerTypeName name;
	private double discount;
	private int points;
	
	
	public CustomerType(CustomerTypeName name, double discount, int points) {
		super();
		this.name = name;
		this.discount = discount;
		this.points = points;
	}


	public CustomerType() {
		super();
	}


	public CustomerTypeName getName() {
		return name;
	}


	public void setName(CustomerTypeName name) {
		this.name = name;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
}
