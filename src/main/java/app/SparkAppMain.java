package app;

import java.util.List;

import model.User;
import repositories.UserRepository;
import services.UserService;

public class SparkAppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("before rep");
		UserRepository userRepository = new UserRepository("./data/users.json");
		System.out.println("rep");
		
		List<User> collection = userRepository.getAll();
		System.out.println(collection.toString());
		//UserService userService = new UserServcie(".data/users.json");

	}

}
