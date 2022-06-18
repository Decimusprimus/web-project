package model;

import java.time.LocalDate;
import java.util.UUID;

public class Customer extends UserInfo {
	private CustomerTypeName type;
	private int points;

	public Customer(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth,
			UserRole userRole, Gender gender, boolean status, CustomerTypeName type, int points) {
		super(id, username, firstName, lastName, dateOfBirth, userRole, gender, status);
		this.type = type;
		this.points = points;
	}
	
	public Customer(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth,
			UserRole userRole, Gender gender, boolean deleted) {
		super(id, username, firstName, lastName, dateOfBirth, userRole, gender, deleted);
		this.type = CustomerTypeName.BRONZE;
		this.points = 0;
	}

	public Customer() {
		super();
	}

	public CustomerTypeName getType() {
		return type;
	}

	public void setType(CustomerTypeName type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	

}
