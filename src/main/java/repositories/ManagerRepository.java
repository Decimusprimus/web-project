package repositories;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Manager;

public class ManagerRepository extends Repository<Manager, UUID> {

	public ManagerRepository(String path) {
		super(path, new TypeToken<List<Manager>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Manager> getAllWithoutFacility() {
		ArrayList<Manager> collection = (ArrayList<Manager>) getAll();
		ArrayList<Manager> retCollection = new ArrayList<Manager>();
		for(Manager m : collection) {
			if(m.getFacilityId() == null && !m.isDeleted()) {
				retCollection.add(m);
			}
		}
		return retCollection;
	}

}
