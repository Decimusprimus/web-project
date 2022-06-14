package model;

import java.util.UUID;

public class Facility {
	private UUID id;
	private String name;
	//private ? content; TODO Create new class?
	private FacilityType faciltyType;
	private boolean status;
	private Location location;
	private double avrageScore;
	
	private UUID managerId;

	public Facility(UUID id, String name, FacilityType faciltyType, boolean status, Location location,
			double avrageScore, UUID managerId) {
		super();
		this.id = id;
		this.name = name;
		this.faciltyType = faciltyType;
		this.status = status;
		this.location = location;
		this.avrageScore = avrageScore;
		this.managerId = managerId;
	}

	public Facility() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FacilityType getFaciltyType() {
		return faciltyType;
	}

	public void setFaciltyType(FacilityType faciltyType) {
		this.faciltyType = faciltyType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getAvrageScore() {
		return avrageScore;
	}

	public void setAvrageScore(double avrageScore) {
		this.avrageScore = avrageScore;
	}

	public UUID getManagerId() {
		return managerId;
	}

	public void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}
	
	
	
	
}
