package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static app.SparkAppMain.userService;
import static app.SparkAppMain.gson;

import com.google.gson.Gson;

import dto.ChangePasswordDTO;
import dto.LoginDTO;
import dto.RegisterCustomerDTO;
import dto.UserInfoDTO;
import model.Customer;
import model.User;
import model.UserInfo;

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
}
