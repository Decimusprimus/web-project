package services;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import dto.TrainingDTO;
import io.jsonwebtoken.io.IOException;
import model.Training;
import repositories.TrainingRepository;

public class TrainingService {
	private TrainingRepository trainingRepository;

	public TrainingService(TrainingRepository trainingRepository) {
		super();
		this.trainingRepository = trainingRepository;
	}
	
	public Training createNewTraining(TrainingDTO dto) {
		Training training = new Training(UUID.randomUUID(), dto.getName(), dto.getTrainingType(), dto.getDuration(), dto.getDescription(), false, dto.getFacilityId(), dto.getCoachId());
		return trainingRepository.create(training);
	}
	
	public ArrayList<Training> getAllForFacility(UUID id) {
		return trainingRepository.getAllForFacility(id);
	}	
	
}
