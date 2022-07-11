package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.utils.IOUtils;

import static app.SparkAppMain.faciltyService;
import static app.SparkAppMain.gson;
import static app.SparkAppMain.userService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

import com.google.gson.Gson;

import dto.FacilityDTO;
import model.Facility;
import model.FacilityType;
import model.User;
import model.UserRole;

public class FacilityController {
	private static Gson gson = new Gson();
	
	public static Route GetAll = (Request request, Response response) -> {
		ArrayList<Facility> collection = faciltyService.getAll(); 
		response.type("application/json");
		return gson.toJson(collection);
	};

	public static Route GetLogo = (Request request, Response response) -> {
		try
		{
			String id = request.params(":id");
			String imageFile = faciltyService.GetLogo(id);
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
	
	public static Route SearchFacilities = (Request request, Response response) -> {
		String name = request.queryParams("name");
		String location = request.queryParams("location");
		String type = request.queryParams("type");
		ArrayList<Facility> collection = faciltyService.search(name, location, type); 
		response.type("application/json");
		return gson.toJson(collection);
	};
	
	public static Route CreateNewFacility = (Request request, Response response) -> {
		Session sesion = request.session();
		UUID id = sesion.attribute("user");
		User user = userService.GetUser(id);
		if(user != null) {
			if(user.getUserRole() == UserRole.ADMIN) {
				FacilityDTO dto = gson.fromJson(request.body(), FacilityDTO.class);
				Facility facility = faciltyService.createNewFacility(dto);
				response.type("application/json");
				return gson.toJson(facility);
			}
		}
		response.status(403);
		return "";
	};
	
	
	public static Route UploadLogoImage = (Request request, Response response) -> {
		String id = request.params(":id");
		request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		Path path = Paths.get("./data/facilities/", id, "logo.jpg");
	    try (InputStream is = request.raw().getPart("file").getInputStream()) {
	    	Files.copy(is, path);
	    }
	    return "File uploaded";
	};
	

}
