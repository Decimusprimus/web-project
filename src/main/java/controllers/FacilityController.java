package controllers;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import spark.utils.IOUtils;

import static app.SparkAppMain.faciltyService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Facility;

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

}
