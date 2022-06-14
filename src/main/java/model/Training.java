package model;

import java.time.LocalTime;
import java.util.UUID;

public class Training {
	private UUID id;
	private String name;
	private String trainingType; //TODO check later is enum needed
	private LocalTime duration;
	private String description;
	
	private UUID facilityId;
	private UUID coachId;
	
	public Training(UUID id, String name, String trainingType, LocalTime duration, String description, UUID facilityId,
			UUID coachId) {
		super();
		this.id = id;
		this.name = name;
		this.trainingType = trainingType;
		this.duration = duration;
		this.description = description;
		this.facilityId = facilityId;
		this.coachId = coachId;
	}

	public Training() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(UUID facilityId) {
		this.facilityId = facilityId;
	}

	public UUID getCoachId() {
		return coachId;
	}

	public void setCoachId(UUID coachId) {
		this.coachId = coachId;
	}
	
	
	
	
}
