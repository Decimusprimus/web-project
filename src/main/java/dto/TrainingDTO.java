package dto;

import java.util.UUID;

import model.TrainingType;

public class TrainingDTO {
	private String name;
	private TrainingType trainingType; 
	private int duration;
	private String description;
	private double price;
	private UUID facilityId;
	private UUID coachId;

	public TrainingDTO(String name, TrainingType trainingType, int duration, String description, double price,
			UUID facilityId, UUID coachId) {
		super();
		this.name = name;
		this.trainingType = trainingType;
		this.duration = duration;
		this.description = description;
		this.price = price;
		this.facilityId = facilityId;
		this.coachId = coachId;
	}

	public TrainingDTO() {
		super();
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
