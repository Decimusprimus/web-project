package repositories;

import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Admin;

public class AdminRepository extends Repository<Admin, UUID>  {

	public AdminRepository(String path) {
		super(path,  new TypeToken<List<Admin>>(){}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}

}
