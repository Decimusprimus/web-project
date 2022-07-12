package repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Training;


public class TrainingRepository extends Repository<Training, UUID> {

	public TrainingRepository(String path) {
		super(path, new TypeToken<List<Training>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Training create(Training entity) {
		ArrayList<Training> collection = (ArrayList<Training>) getAll();
		for(Training t : collection) {
			if(t.getName().equals(entity.getName())) {
				return null;
			}
		}
		collection.add(entity);
		saveAll(collection);
		return entity;
	}
	
	public ArrayList<Training> getAllForFacility(UUID id){
		ArrayList<Training> collection = (ArrayList<Training>) getAll();
		ArrayList<Training> retCollection = new ArrayList<Training>(); 
		for(Training t : collection) {
			if(t.getFacilityId().equals(id) && !t.isDeleted()) {
				retCollection.add(t);
			}
		}
		return retCollection;
	}

	public Training updateT(Training entity) {
		ArrayList<Training> collection = (ArrayList<Training>) getAll();
		for(Training t : collection) {
			if(t.getName().equals(entity.getName())) {
				if(!t.getId().equals(entity.getId())) {
					return null;
				}
			}
		}
		for(Training t : collection) {
			if(t.getId().equals(entity.getId())) {
				collection.set(collection.indexOf(t), entity);
				break;
			}
		}
		saveAll(collection);
		return entity;
	}
	
	public ArrayList<Training> getForCoach(UUID id) {
		ArrayList<Training> collection = (ArrayList<Training>) getAll();
		ArrayList<Training> retCollection = new ArrayList<Training>(); 
		for(Training t : collection) {
			if(t.getCoachId() != null) {
				if(t.getCoachId().equals(id)) {
					retCollection.add(t);
				}
			}
		}
		return retCollection;
	}
	
	

}
