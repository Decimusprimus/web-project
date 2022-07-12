package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static app.SparkAppMain.membershipService;
import static app.SparkAppMain.gson;
import static app.SparkAppMain.userService;

import java.util.UUID;

import model.Membership;
import model.User;
import model.UserRole;

public class MembershipController {
	
	public static Route CreateMembership = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID id = sesion.attribute("user");
		User user = userService.GetUser(id);
		if(user != null) {
			if(user.getUserRole() == UserRole.CUSTOMER) {
				String option = request.params("option");
				Membership membership = membershipService.createMembership(option, user.getId());
				if(membership != null) {
					response.type("application/json");
					return gson.toJson(membership);
				}
				response.status(400);
				return "";
			}
		}
		response.status(403);
		return "";
	};
	
}
