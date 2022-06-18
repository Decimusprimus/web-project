package app;

import java.io.File;
import java.io.IOException;
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
	public static CustomerRepository customerRepository;
	public static UserService userService;

	public static void main(String[] args) throws IOException {
		
		userRepository = new UserRepository("./data/users.json");
		customerRepository = new CustomerRepository("./data/customer.json");
		userService = new UserService(userRepository, customerRepository);
		
		port(8080);
		staticFiles.externalLocation(new File("src/main/webapp").getCanonicalPath());
		
		post("/user/login", UsersController.HandleLogin);
		post("/user/logout", UsersController.HandleLogout);
		post("/user/register/customer", UsersController.RegisterCustomer);
		
	}

}
