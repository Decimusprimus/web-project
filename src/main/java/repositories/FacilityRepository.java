package repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Facility;
import model.FacilityType;
import model.User;

public class FacilityRepository extends Repository<Facility, UUID> {

	public FacilityRepository(String path) {
		super(path, new TypeToken<List<Facility>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Facility> search(String name, String location, String type) {
		ArrayList<Facility> collection = (ArrayList<Facility>) getAll();
		ArrayList<Facility> result = new ArrayList<Facility>();
		for(Facility f : collection) {
			if((f.getName().toLowerCase().contains(name.toLowerCase()) && !name.isBlank()) || (f.compereType(type) && !type.isBlank()) || (f.getLocation().getAddress().check(location)) && !location.isBlank()) {
				result.add(f);
			}
		}
		return result;
	}
	
	public ArrayList<Facility> searchByName(String name) {
		ArrayList<Facility> collection = (ArrayList<Facility>) getAll();
		ArrayList<Facility> result = new ArrayList<Facility>();
		for(Facility f : collection) {
			if(f.getName().contains(name)) {
				result.add(f);
			}
		}
		return result;
	}
	
	public ArrayList<Facility> searchByLocation(String location) {
		ArrayList<Facility> collection = (ArrayList<Facility>) getAll();
		ArrayList<Facility> result = new ArrayList<Facility>();
		for(Facility f : collection) {
			if(f.getLocation().getAddress().check(location)) {
				result.add(f);
			}
		}
		return result;
	}
	
	public ArrayList<Facility> searchByType(String type) {
		ArrayList<Facility> collection = (ArrayList<Facility>) getAll();
		ArrayList<Facility> result = new ArrayList<Facility>();
		for(Facility f : collection) {
			if(f.compereType(type)) {
				result.add(f);
			}
		}
		return result;
	}
	
	public Facility getFacilityForManagerId(UUID id) {
		ArrayList<Facility> collection = (ArrayList<Facility>) getAll();
		for(Facility f : collection) {
			if(f.getManagerId().equals(id) && !f.isDeleted()) {
				return f;
			}
		}
		return null;
	}
	
	


}
