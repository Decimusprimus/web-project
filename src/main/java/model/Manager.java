package model;

import java.time.LocalDate;
import java.util.UUID;

public class Manager extends UserInfo {
	
	private UUID facilityId;

	public Manager() {
		super();
	}

	public Manager(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth,
			UserRole userRole, Gender gender, boolean status) {
		super(id, username, firstName, lastName, dateOfBirth, userRole, gender, status);
	}

	public Manager(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth,
			UserRole userRole, Gender gender, boolean status, UUID facilityId) {
		super(id, username, firstName, lastName, dateOfBirth, userRole, gender, status);
		this.facilityId = facilityId;
	}
	

	public UUID getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(UUID facilityId) {
		this.facilityId = facilityId;
	}
	
	

	
	
}
