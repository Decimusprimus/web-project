package model;

import java.util.UUID;

public class User implements IIdentifiable<UUID> {
	private UUID id;
	private String username;
	private String password;
	private UserRole userRole;
	private boolean deleted;

	public User(UUID id, String username, String password, UserRole userRole, boolean deleted) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
		this.deleted = deleted;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
