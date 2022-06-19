package services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.Facility;
import repositories.FacilityRepository;

public class FaciltiyService {
	private FacilityRepository facilityRepository;

	public FaciltiyService(FacilityRepository facilityRepository) {
		super();
		this.facilityRepository = facilityRepository;
	}
	
	public ArrayList<Facility> getAll() {
		return (ArrayList<Facility>) facilityRepository.getAllActive();
	}
	
	public String GetLogo(String id) throws IOException {
		String facilityDirectory = "./data/facilities/" + id;
		File[] files = new File(facilityDirectory).listFiles();
		for(File file : files) {
			if(file.isFile() && file.getName().contains("logo")) {
				return file.getPath();
			}
		}
		return null;
	}

}
