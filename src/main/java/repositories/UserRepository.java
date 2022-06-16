package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.User;

public class UserRepository extends Repository<User, UUID> {

	public UserRepository(String path) {
		super(path, new TypeToken<List<User>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}
	
	public User getByUsername(String username) {
		ArrayList<User> collection = (ArrayList<User>) getAll();
		for(User u : collection) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

}
