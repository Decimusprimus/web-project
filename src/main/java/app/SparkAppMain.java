package app;

import java.util.List;

import repositories.CustomerRepository;
import repositories.UserRepository;
import services.UserService;
import controllers.UsersController;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;


public class SparkAppMain {
	
	public static UserRepository userRepository;
	public static UserService userService;

	public static void main(String[] args) {
		
		userRepository = new UserRepository("./data/users.json");
		userService = new UserService(userRepository);
		
		port(8080);
		
		post("/user/login", UsersController.HandleLogin);
		post("/user/logout", UsersController.HandleLogout);
		
	}

}
