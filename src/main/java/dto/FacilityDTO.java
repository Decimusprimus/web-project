package dto;

import java.util.ArrayList;
import java.util.UUID;

import model.FacilityType;
import model.Location;
import model.WrokHour;

public class FacilityDTO {
	private String name;
	private FacilityType facilityType;
	private Location location;
	private ArrayList<WrokHour> workingHours;
	private UUID managerId;
	
	public FacilityDTO(String name, FacilityType facilityType, Location location, ArrayList<WrokHour> workingHours,
			UUID managerId) {
		super();
		this.name = name;
		this.facilityType = facilityType;
		this.location = location;
		this.workingHours = workingHours;
		this.managerId = managerId;
	}

	public FacilityDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<WrokHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(ArrayList<WrokHour> workingHours) {
		this.workingHours = workingHours;
	}

	public UUID getManagerId() {
		return managerId;
	}

	public void setManagerId(UUID managerId) {
		this.managerId = managerId;
	}

	
	
}
