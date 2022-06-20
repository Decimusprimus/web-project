package model;

import java.util.ArrayList;
import java.util.UUID;

public class Facility implements IIdentifiable<UUID> {
	private UUID id;
	private String name;
	//private ? content; TODO Create new class?
	private FacilityType facilityType;
	private Location location;
	private ArrayList<WrokHour> workingHours;
	private double averageScore;
	private boolean deleted;
	
	private UUID managerId;

	public Facility(UUID id, String name, FacilityType facilityType, Location location,
			ArrayList<WrokHour> workingHours, double averageScore, boolean deleted, UUID managerId) {
		super();
		this.id = id;
		this.name = name;
		this.facilityType = facilityType;
		this.location = location;
		this.workingHours = workingHours;
		this.averageScore = averageScore;
		this.deleted = deleted;
		this.managerId = managerId;
	}

	public Facility() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public UUID getManagerId() {
		return managerId;
	}

	public void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;		
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	public ArrayList<WrokHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(ArrayList<WrokHour> workingHours) {
		this.workingHours = workingHours;
	}
	
	public boolean compereType(String type) {
		if(type.isBlank()) {
			return false;
		}
		FacilityType fType = FacilityType.valueOf(type);
		if(this.facilityType.compareTo(fType)==0) {
			return true;
		}
		return false;
		
	}
	
	
}
