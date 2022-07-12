package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingHistory implements IIdentifiable<UUID> {
	private UUID id;
	private LocalDate dateTime;
	private boolean deleted;
	
	private UUID coachId;
	private UUID customerId;
	private UUID trainingId;

	public TrainingHistory(UUID id, LocalDate dateTime, UUID coachId, UUID customerId,
			UUID trainginId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.coachId = coachId;
		this.customerId = customerId;
		this.trainingId = trainginId;
	}
	
	public TrainingHistory(UUID id, LocalDate dateTime, boolean deleted, UUID coachId, UUID customerId,
			UUID trainginId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.deleted = deleted;
		this.coachId = coachId;
		this.customerId = customerId;
		this.trainingId = trainginId;
	}



	public TrainingHistory() {
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

	public LocalDate getDateTimeOfSubscription() {
		return dateTime;
	}

	public void setDateTimeOfSubscription(LocalDate dateTime) {
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
		return trainingId;
	}

	public void setTrainginId(UUID trainginId) {
		this.trainingId = trainginId;
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
