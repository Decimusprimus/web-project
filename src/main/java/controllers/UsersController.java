package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static app.SparkAppMain.userService;

import java.util.UUID;

import static app.SparkAppMain.gson;

import com.google.gson.Gson;

import dto.ChangePasswordDTO;
import dto.LoginDTO;
import dto.RegisterCustomerDTO;
import dto.UserId;
import dto.UserInfoDTO;
import model.Customer;
import model.Manager;
import model.User;
import model.UserInfo;
import model.UserRole;

public class UsersController {
	
	
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
	
	public static Route Relogin = (Request request, Response response) -> {
		UserId userId = gson.fromJson(request.body(), UserId.class);
		User user = userService.GetUser(userId.getId());
		if(user != null) {
			request.session().attribute("user", user.getId());
			return gson.toJson(user);
		}
		response.status(400);
		return "Invalid user";
	};
	
	public static Route RegisterCustomer = (Request request, Response response) -> {
		RegisterCustomerDTO registerDTO = gson.fromJson(request.body(), RegisterCustomerDTO.class);
		response.type("application/json");
		if(!registerDTO.getPassword().equals(registerDTO.getPasswordConfirm())) {
			response.status(400);
			return "Passwords don't mach";
		}
		Customer customer = userService.createNewCustomer(registerDTO);
		if(customer == null) {
			response.status(400);
			return "Username already taken";
		}
		return gson.toJson(customer);
	};
	
	public static Route RegisterManager = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID id = sesion.attribute("user");
		User user = userService.GetUser(id);
		if(user != null) {
			if(user.getUserRole() == UserRole.ADMIN) {
				RegisterCustomerDTO registerDTO = gson.fromJson(request.body(), RegisterCustomerDTO.class);
				Manager manager = userService.createNewManager(registerDTO);
				response.type("application/json");
				return gson.toJson(manager);
			}
		}
		response.status(403);
		return "";
	};

	public static Route GetUserInfo = (Request request, Response response) -> {
		String userId = request.params(":id");
		if(!userId.isEmpty()) {
			UserInfo userInfo = userService.GetUserInfo(userId);
			if(userInfo != null) {
				response.type("application/json");
				return gson.toJson(userInfo);
			}
		}
		response.status(400);
		return "";
	};
	
	public static Route UpdateUserInfo = (Request request, Response response) -> {
		String userId = request.params(":id");
		UserInfoDTO userInfoDTO = gson.fromJson(request.body(), UserInfoDTO.class);
		UserInfo userInfo  = userService.UpdateUserInfo(userId, userInfoDTO);
		if(userInfo != null) {
			response.type("application/json");
			return gson.toJson(userInfo);
		}
		response.status(400);
		return "";
	};
	
	public static Route UpdateUsername = (Request request, Response response) -> {
		String userId = request.params(":id");
		String username = gson.fromJson(request.body(), String.class);
		UserInfo userInfo = userService.UpdateUsername(userId, username);
		if(userInfo != null) {
			response.type("application/json");
			return gson.toJson(userInfo);
		}
		response.status(400);
		return "";
	};
	
	public static Route ChangePassword =  (Request request, Response response) -> {
		String userId = request.params(":id");
		ChangePasswordDTO changePasswordDTO = gson.fromJson(request.body(), ChangePasswordDTO.class);
		if(!userService.ChangePassword(userId, changePasswordDTO)) {
			response.status(400);
		}
		return "";
	};
	
	public static Route GetFreeManagers =  (Request request, Response response) -> {
		response.type("application/json");
		return gson.toJson(userService.GetAllFreeManagers());
	};
}
