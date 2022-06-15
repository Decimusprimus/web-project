package repositories;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Coach;

public class CoachRepository extends Repository<Coach, UUID> {

	public CoachRepository(String path) {
		super(path,new TypeToken<List<Coach>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}

}
