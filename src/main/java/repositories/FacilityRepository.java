package repositories;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Facility;

public class FacilityRepository extends Repository<Facility, UUID> {

	public FacilityRepository(String path) {
		super(path, new TypeToken<List<Facility>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}

}
