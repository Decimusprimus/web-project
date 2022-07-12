package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Membership implements IIdentifiable<UUID> {
	private UUID id;
	private MembershipType membershipType;
	private LocalDate dateOfPayment;
	private LocalDate validUntil;
	private double price;
	private boolean status;
	private int counter; 
	private boolean deleted;
	
	private UUID customerId;

	public Membership(UUID id, MembershipType membershipType, LocalDate dateOfPayment, LocalDate validUntil,
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
	
	


	public Membership(UUID id, MembershipType membershipType, LocalDate dateOfPayment, LocalDate validUntil,
			double price, boolean status, int counter, boolean deleted, UUID customerId) {
		super();
		this.id = id;
		this.membershipType = membershipType;
		this.dateOfPayment = dateOfPayment;
		this.validUntil = validUntil;
		this.price = price;
		this.status = status;
		this.counter = counter;
		this.deleted = deleted;
		this.customerId = customerId;
	}




	public Membership() {
		super();
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(LocalDate dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public LocalDate getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDate validUntil) {
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




	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		
	}




	@Override
	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return this.deleted;
	}
	
	
	
	
	
	
}
