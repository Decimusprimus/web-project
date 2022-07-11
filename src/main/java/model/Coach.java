package model;

import java.time.LocalDate;
import java.util.UUID;

public class Coach extends UserInfo{

	public Coach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coach(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth, UserRole userRole,
			Gender gender, boolean status) {
		super(id, username, firstName, lastName, dateOfBirth, userRole, gender, status);
		// TODO Auto-generated constructor stub
	}
	
	
}
