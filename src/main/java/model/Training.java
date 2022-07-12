package model;

import java.time.LocalTime;
import java.util.UUID;

public class Training implements IIdentifiable<UUID> {
	private UUID id;
	private String name;
	private TrainingType trainingType; 
	private int duration;
	private String description;
	private double price;
	private boolean deleted;
	
	private UUID facilityId;
	private UUID coachId;
	
	public Training(UUID id, String name, TrainingType trainingType, int duration, String description, UUID facilityId,
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
	
	public Training(UUID id, String name, TrainingType trainingType, int duration, String description, boolean deleted,
			UUID facilityId, UUID coachId) {
		super();
		this.id = id;
		this.name = name;
		this.trainingType = trainingType;
		this.duration = duration;
		this.description = description;
		this.deleted = deleted;
		this.facilityId = facilityId;
		this.coachId = coachId;
	}
	
	public Training(UUID id, String name, TrainingType trainingType, int duration, String description, double price,
			boolean deleted, UUID facilityId, UUID coachId) {
		super();
		this.id = id;
		this.name = name;
		this.trainingType = trainingType;
		this.duration = duration;
		this.description = description;
		this.price = price;
		this.deleted = deleted;
		this.facilityId = facilityId;
		this.coachId = coachId;
	}

	public Training() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainingType getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
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

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;		
	}

	@Override
	public boolean isDeleted() {
		return this.deleted;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
