package model;

import java.util.UUID;

public class User implements IIdentifiable<UUID> {
	private UUID id;
	private String username;
	private UserRole userRole;
	private boolean deleted;
	
	public User(UUID id, String username, UserRole userRole) {
		super();
		this.id = id;
		this.username = username;
		this.userRole = userRole;
		this.deleted = false;
	}

	public User() {
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

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		
	}

	@Override
	public boolean isDeleted() {
		return this.deleted;
	}
	
}
