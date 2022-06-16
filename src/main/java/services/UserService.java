package services;

import model.User;
import repositories.UserRepository;

public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public User checkUserLogin(String username, String password) {
		if(username.isEmpty() || password.isEmpty()) {
			return null;
		}
		User user = userRepository.getByUsername(username);
		if(user == null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	
}
