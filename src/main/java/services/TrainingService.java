package services;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import dto.TrainingDTO;
import dto.TrainingHistoryDTO;
import io.jsonwebtoken.io.IOException;
import model.Membership;
import model.Training;
import model.TrainingHistory;
import repositories.MembershipRepositroy;
import repositories.TrainingHistoryRepository;
import repositories.TrainingRepository;

public class TrainingService {
	private TrainingRepository trainingRepository;
	private TrainingHistoryRepository trainingHistoryRepository;
	private MembershipRepositroy membershipRepositroy;

	public TrainingService(TrainingRepository trainingRepository, TrainingHistoryRepository trainingHistoryRepository, MembershipRepositroy membershipRepositroy) {
		super();
		this.trainingRepository = trainingRepository;
		this.trainingHistoryRepository = trainingHistoryRepository;
		this.membershipRepositroy = membershipRepositroy;
	}
	
	public Training createNewTraining(TrainingDTO dto) {
		Training training = new Training(UUID.randomUUID(), dto.getName(), dto.getTrainingType(), dto.getDuration(), dto.getDescription(), false, dto.getFacilityId(), dto.getCoachId());
		return trainingRepository.create(training);
	}
	
	public ArrayList<Training> getAllForFacility(UUID id) {
		return trainingRepository.getAllForFacility(id);
	}	
	
	public Training updateTraining(TrainingDTO dto, String id) {
		Training training = trainingRepository.getById(UUID.fromString(id));
		if(training != null) {
			training.setName(dto.getName());
			training.setDescription(dto.getDescription());
			training.setDuration(dto.getDuration());
			training.setTrainingType(dto.getTrainingType());
			training.setCoachId(dto.getCoachId());
			training.setPrice(dto.getPrice());
			return trainingRepository.updateT(training);
		}
		return null;
	}
	
	public ArrayList<Training> getAllForCoach(UUID id) {
		return trainingRepository.getForCoach(id);
	}
	
	public TrainingHistory checkIn(TrainingHistoryDTO dto, UUID id) {
		Membership m = membershipRepositroy.checkForMembership(id);
		if(m == null) {
			return null;
		}
		if(!trainingHistoryRepository.canCheckIn(id)) {
			return null;
		}
		TrainingHistory th = new TrainingHistory(UUID.randomUUID(), LocalDate.now(), false, dto.getCoachId(), id, dto.getTrainginId());
		m.setCounter(m.getCounter()-1);
		membershipRepositroy.update(m);
		return trainingHistoryRepository.create(th);
	}
	
	public boolean canCheckIn(UUID id) {
		Membership m = membershipRepositroy.checkForMembership(id);
		if(m == null) {
			return false;
		}
		if(!trainingHistoryRepository.canCheckIn(id)) {
			return false;
		}
		return true;
	}
	
	public Training getById(UUID id) {
		return trainingRepository.getById(id);
	}
	
	public ArrayList<TrainingHistory> getTrainingHistory(UUID id) {
		return trainingHistoryRepository.getTrainingHistory(id);
	}
}
