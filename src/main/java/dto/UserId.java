package dto;

import java.util.UUID;

public class UserId {
	private UUID id;

	public UserId(UUID id) {
		super();
		this.id = id;
	}

	public UserId() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	

}
