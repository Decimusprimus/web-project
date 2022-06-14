package model;

import java.time.LocalDate;
import java.util.UUID;

public class Admin extends UserInfo {

	public Admin() {
		super();
	}

	public Admin(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth, UserRole userRole,
			Gender gender, boolean status) {
		super(id, username, firstName, lastName, dateOfBirth, userRole, gender, status);
	}
	
	
}
