package dto;

import java.util.UUID;

public class TrainingHistoryDTO {
	private UUID trainingId;
	private UUID coachId;
	
	
	public TrainingHistoryDTO(UUID trainginId, UUID coachId) {
		super();
		this.trainingId = trainginId;
		this.coachId = coachId;
	}


	public UUID getTrainginId() {
		return trainingId;
	}


	public void setTrainginId(UUID trainginId) {
		this.trainingId = trainginId;
	}


	public UUID getCoachId() {
		return coachId;
	}


	public void setCoachId(UUID coachId) {
		this.coachId = coachId;
	}
	
	
	
	
}
