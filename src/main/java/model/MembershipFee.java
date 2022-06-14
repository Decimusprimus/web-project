package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class MembershipFee {
	private String id;
	private MembershipType membershipType;
	private LocalDate dateOfPayment;
	private LocalDateTime validUntil;
	private double price;
	private boolean status;
	private int counter; //TODO maybe change later
	
	private UUID customerId;

	public MembershipFee(String id, MembershipType membershipType, LocalDate dateOfPayment, LocalDateTime validUntil,
			double price, boolean status, int counter, UUID customerId) {
		super();
		this.id = id;
		this.membershipType = membershipType;
		this.dateOfPayment = dateOfPayment;
		this.validUntil = validUntil;
		this.price = price;
		this.status = status;
		this.counter = counter;
		this.customerId = customerId;
	}


	public MembershipFee() {
		super();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}


	public MembershipType getMembershipType() {
		return membershipType;
	}


	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	
	
	
	
	
	
}
