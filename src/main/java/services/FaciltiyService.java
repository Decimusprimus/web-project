package services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dto.FacilityDTO;
import model.Facility;
import model.FacilityType;
import repositories.FacilityRepository;

import static app.SparkAppMain.userService;

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
	
	public ArrayList<Facility> search(String name, String location, String type) {
		if(name.isBlank() && location.isBlank() && type.isBlank()) {
			return (ArrayList<Facility>) facilityRepository.getAll();
		}
		return facilityRepository.search(name, location, type);
	}
	
	public Facility createNewFacility(FacilityDTO dto) {
		Facility newFacility = new Facility(dto.getName(), dto.getFacilityType(), dto.getLocation(), dto.getWorkingHours(), dto.getManagerId());
		Facility facility = facilityRepository.create(newFacility);
		String facilityDirectory = "./data/facilities/" + facility.getId().toString();
		File f = new File(facilityDirectory);
		f.mkdir();
		userService.assignManagerToFacility(dto.getManagerId(), facility.getId());
		return facility;
	}

}
