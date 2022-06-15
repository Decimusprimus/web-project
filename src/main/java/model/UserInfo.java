package model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class UserInfo implements IIdentifiable<UUID> {
	private UUID id;
	private String username;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private UserRole userRole;
	private Gender gender;
	private boolean deleted;
	
	public UserInfo(UUID id, String username, String firstName, String lastName, LocalDate dateOfBirth,
			UserRole userRole, Gender gender, boolean deleted) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.userRole = userRole;
		this.gender = gender;
		this.deleted = deleted;
	}

	public UserInfo() {
		
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}


	
	

}
