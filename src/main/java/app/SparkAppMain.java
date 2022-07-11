package app;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import repositories.AdminRepository;
import repositories.CoachRepository;
import repositories.CustomerRepository;
import repositories.FacilityRepository;
import repositories.ManagerRepository;
import repositories.UserRepository;
import services.FaciltiyService;
import services.UserService;
import controllers.FacilityController;
import controllers.UsersController;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;
import spark.Route;
import util.LocalDateDeserializer;
import util.LocalDateSerializer;


public class SparkAppMain {
	
	public static Gson gson;
	
	public static UserRepository userRepository;
	public static CustomerRepository customerRepository;
	public static AdminRepository adminRepository;
	public static ManagerRepository managerRepository;
	public static CoachRepository coachRepository;
	public static FacilityRepository facilityRepository;
	
	public static UserService userService;
	public static FaciltiyService faciltyService;
	

	public static void main(String[] args) throws IOException {
		
		gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
				.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
				.serializeNulls()
				.create();
		
		userRepository = new UserRepository("./data/users/users.json");
		customerRepository = new CustomerRepository("./data/users/customer.json");
		adminRepository = new AdminRepository("./data/users/admins.json");
		managerRepository = new ManagerRepository("./data/users/managers.json");
		coachRepository = new CoachRepository("./data/users/coach.json");
		facilityRepository = new FacilityRepository("./data/facilities/facilities.json");
		
		userService = new UserService(userRepository, customerRepository, adminRepository, managerRepository, coachRepository);
		faciltyService = new FaciltiyService(facilityRepository);
		
		port(8080);
		staticFiles.externalLocation(new File("src/main/webapp").getCanonicalPath());
		
		post("/user/login", UsersController.HandleLogin);
		post("/user/relogin", UsersController.Relogin);
		post("/user/logout", UsersController.HandleLogout);
		post("/user/register/customer", UsersController.RegisterCustomer);
		post("/user/register/manager", UsersController.RegisterManager);
		post("/user/register/coach", UsersController.RegisterCoach);
		get("/user/:id", UsersController.GetUserInfo);
		put("/user/:id", UsersController.UpdateUserInfo);
		post("/user/:id/username", UsersController.UpdateUsername);
		post("/user/:id/password", UsersController.ChangePassword);
		
		get("/users/manager/free", UsersController.GetFreeManagers);
		
		get("/facilities", FacilityController.GetAll);
		get("/facilities/:id", FacilityController.GetFacilityById);
		get("/facilities/:id/logo", FacilityController.GetLogo);
		get("/facilities/search", FacilityController.SearchFacilities);
		post("/facilities/new", FacilityController.CreateNewFacility);
		post("/facilities/:id/logo", FacilityController.UploadLogoImage);
		
		
	}

}
