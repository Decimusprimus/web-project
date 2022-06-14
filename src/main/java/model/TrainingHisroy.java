package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingHisroy {
	private UUID id;
	private LocalDateTime dateTime;
	
	private UUID coachId;
	private UUID customerId;
	private UUID trainginId;

	public TrainingHisroy(UUID id, LocalDateTime dateTime, UUID coachId, UUID customerId,
			UUID trainginId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.coachId = coachId;
		this.customerId = customerId;
		this.trainginId = trainginId;
	}

	public TrainingHisroy() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getDateTimeOfSubscription() {
		return dateTime;
	}

	public void setDateTimeOfSubscription(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public UUID getCoachId() {
		return coachId;
	}

	public void setCoachId(UUID coachId) {
		this.coachId = coachId;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public void setCustomerId(UUID customerId) {
		this.customerId = customerId;
	}

	public UUID getTrainginId() {
		return trainginId;
	}

	public void setTrainginId(UUID trainginId) {
		this.trainginId = trainginId;
	}
	
	
	
	
}
