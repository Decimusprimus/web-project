package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static app.SparkAppMain.userService;

import com.google.gson.Gson;

import dto.LoginDTO;
import dto.RegisterCustomerDTO;
import model.Customer;
import model.User;

public class UsersController {
	private static Gson gson = new Gson();
	
	public static Route HandleLogin = (Request request, Response response) -> {
		response.type("application/json");
		LoginDTO loginDTO = gson.fromJson(request.body(), LoginDTO.class);
		User user = userService.checkUserLogin(loginDTO.getUsername(), loginDTO.getPassword());
		if(user != null) {
			request.session().attribute("user", user.getId());
			return gson.toJson(user);
		}
		response.status(400);
		return "";
	};
	
	public static Route HandleLogout = (Request request, Response response) -> {
		Session sesion = request.session();
		request.session().removeAttribute("user");
		response.type("application/json");
		return "";
	};
	
	public static Route RegisterCustomer = (Request request, Response response) -> {
		RegisterCustomerDTO registerDTO = gson.fromJson(request.body(), RegisterCustomerDTO.class);
		response.type("application/json");
		if(!registerDTO.getPassword().equals(registerDTO.getPasswordConfirm())) {
			response.status(400);
			return "";
		}
		Customer customer = userService.createNewCustomer(registerDTO);
		if(customer == null) {
			response.status(400);
			return "";
		}
		return gson.toJson(customer);
	};
	
}
