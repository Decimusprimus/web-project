package repositories;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.TrainingHistory;


public class TrainingHistoryRepository extends Repository<TrainingHistory, UUID> {

	public TrainingHistoryRepository(String path) {
		super(path, new TypeToken<List<TrainingHistory>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}
	
	public boolean canCheckIn(UUID id) {
		ArrayList<TrainingHistory> collection = (ArrayList<TrainingHistory>) getAll();
		for(TrainingHistory t : collection) {
			if(t.getCustomerId().equals(id)) {
				if(t.getDateTimeOfSubscription().compareTo(LocalDate.now()) >= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<TrainingHistory> getTrainingHistory(UUID id) {
		ArrayList<TrainingHistory> collection = (ArrayList<TrainingHistory>) getAll();
		ArrayList<TrainingHistory> retCollection = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : collection) {
			if(t.getCustomerId().equals(id) && t.getDateTimeOfSubscription().compareTo(LocalDate.now().minusMonths(1)) > 0) {
				retCollection.add(t);
			}
		}
		return retCollection;
	}

}
