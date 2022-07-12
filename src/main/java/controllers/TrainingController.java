package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.utils.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

import dto.TrainingDTO;
import dto.TrainingHistoryDTO;
import model.Facility;
import model.Training;
import model.TrainingHistory;
import model.User;
import model.UserRole;

import static app.SparkAppMain.faciltyService;
import static app.SparkAppMain.gson;
import static app.SparkAppMain.trainingService;
import static app.SparkAppMain.userService;

public class TrainingController {
	
	public static Route GetTraingsForFacility = (Request request, Response response) -> {
		String id = request.params(":id");
		Facility facility = faciltyService.getById(UUID.fromString(id));
		if(facility != null) {
			response.type("application/json");
			return gson.toJson(trainingService.getAllForFacility(facility.getId()));
		}
		response.status(400);
		return "";
	};
	
	public static Route CreateNewTraining = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID id = sesion.attribute("user");
		User user = userService.GetUser(id);
		if(user != null) {
			if(user.getUserRole() == UserRole.MANAGER) {
				TrainingDTO dto = gson.fromJson(request.body(), TrainingDTO.class);
				Training training = trainingService.createNewTraining(dto);
				if(training != null) {
					response.type("application/json");
					return gson.toJson(training);
				}
				response.status(400);
				return "Name taken";
			}
		}
		response.status(403);
		return "";
	};
	
	public static Route UploadTrainingImage = (Request request, Response response) -> {
		String id = request.params(":id");
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		Path path = Paths.get("./data/trainings/", id + ".jpg");
		try (InputStream is = request.raw().getPart("file").getInputStream()) {
	    	Files.copy(is, path);
	    }
	    return "File uploaded";
	};
	
	public static Route GetTrainingImage = (Request request, Response response) -> {
		try {
			String id = request.params(":id");
			String imageFile = "./data/trainings/" + id + ".jpg";
			Path p = Paths.get(imageFile);
			
			InputStream is = new FileInputStream(p.toFile());
			
			response.raw().setContentType(Files.probeContentType(p));
			response.raw().setHeader("Content-Disposition", "inline; filename=" + p.getFileName());
			
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) > 0) {
				response.raw().getOutputStream().write(buffer, 0, len);
			}
	
			String result = IOUtils.toString(is);
			is.close();
			return result.trim();
			
		} catch(Exception e) 
		{
			response.status(500);
			return "";
		}
	};
	
	public static Route UpdateTraining = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID userId = sesion.attribute("user");
		User user = userService.GetUser(userId);
		if(user != null) {
			if(user.getUserRole() == UserRole.MANAGER) {
				String id = request.params(":id");
				TrainingDTO dto = gson.fromJson(request.body(), TrainingDTO.class);
				Training training = trainingService.updateTraining(dto, id);
				if(training != null) {
					response.type("application/json");
					return gson.toJson(training);
				}
				response.status(400);
				return "Name taken";
			}
		}
		response.status(403);
		return "";
	};
	
	public static Route GetTrainingForCoach = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID userId = sesion.attribute("user");
		User user = userService.GetUser(userId);
		if(user != null) {
			if(user.getUserRole() == UserRole.COACH) {
				response.type("application/json");
				return gson.toJson(trainingService.getAllForCoach(user.getId()));
			}
		}
		response.status(403);
		return "";
	};
	
	public static Route TrainingCheckIn = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID userId = sesion.attribute("user");
		User user = userService.GetUser(userId);
		if(user != null) {
			if(user.getUserRole() == UserRole.CUSTOMER) {
				TrainingHistoryDTO dto = gson.fromJson(request.body(), TrainingHistoryDTO.class);
				TrainingHistory th = trainingService.checkIn(dto, userId);
				if(th != null) {
					response.type("application/json");
					return gson.toJson(th);
				}
				response.status(400);
				return "";
			}
		}
		response.status(403);
		return "";
	};
	
	public static Route CanCheckIn = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID userId = sesion.attribute("user");
		User user = userService.GetUser(userId);
		if(user != null) {
			if(user.getUserRole() == UserRole.CUSTOMER) {
				if(trainingService.canCheckIn(userId)) {
					return "";
				}
				response.status(400);
				return "";
			}
		}
		response.status(403);
		return "";
	};
	
	public static Route GetTraining = (Request request, Response response) -> {
		String id = request.params(":id");
		Training t = trainingService.getById(UUID.fromString(id));
		if(t != null) {
			response.type("application/json");
			return gson.toJson(t);
		}
		response.status(400);
		return "";
	};
	
	public static Route GetTrainingHistory = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID userId = sesion.attribute("user");
		User user = userService.GetUser(userId);
		if(user != null) {
			if(user.getUserRole() == UserRole.CUSTOMER) {
				ArrayList<TrainingHistory> collection = trainingService.getTrainingHistory(userId);
				response.type("application/json");
				return gson.toJson(collection);
			}
		}
		response.status(403);
		return "";
	};

}
