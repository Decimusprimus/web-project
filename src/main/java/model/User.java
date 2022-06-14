package model;

import java.util.UUID;

public class User {
	private UUID id;
	private String username;
	private UserRole userRole;
	
	public User(UUID id, String username, UserRole userRole) {
		super();
		this.id = id;
		this.username = username;
		this.userRole = userRole;
	}

	public User() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}
