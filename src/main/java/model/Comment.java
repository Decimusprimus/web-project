package model;

import java.util.UUID;

public class Comment {
	private UUID id;
	private String text;
	private int score;
	
	private UUID facilityId;
	private UUID customerId;
	
	public Comment(UUID id, String text, int score, UUID facilityId, UUID customerId) {
		super();
		this.id = id;
		this.text = text;
		this.score = score;
		this.facilityId = facilityId;
		this.customerId = customerId;
	}	
	
	public Comment() {
		
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public UUID getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(UUID facilityId) {
		this.facilityId = facilityId;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}
	
	
	

}
