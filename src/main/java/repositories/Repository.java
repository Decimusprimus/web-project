package repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.IIdentifiable;

public class Repository<T extends IIdentifiable<Id>, Id> implements IRepository<T, Id>{
	
	private String path;
	private Type classType;
	private Gson gson;
	
	public Repository(String path, Type classType, Gson gson) {
		super();
		this.path = path;
		this.classType = classType;
		this.gson = gson;
		checkForFIle();
	}

	@Override
	public List<T> getAll() {
		Collection<T> collection = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(path));
			JsonReader jsonReader = new JsonReader(reader);
			collection =  gson.fromJson(jsonReader, classType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(collection == null) {
			return new ArrayList<T>();
		}
		return new ArrayList<T>(collection);
	}

	@Override
	public T getById(Id id) {
		ArrayList<T> collection = (ArrayList<T>) getAll();
		for(T entity : collection) {
			if(entity.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public T create(T entity) {
		ArrayList<T> collection = (ArrayList<T>) getAll();
		collection.add(entity);
		saveAll(collection);
		return entity;
	}

	@Override
	public T update(T entity) {
		ArrayList<T> collection = (ArrayList<T>) getAll();
		for(T ent : collection) {
			if(ent.getId().equals(entity.getId())){
				collection.set(collection.indexOf(ent), entity);
				break;
			}
		}
		saveAll(collection);
		return entity;
	}

	@Override
	public boolean delete(T entity) {
		ArrayList<T> collection = (ArrayList<T>) getAll();
		for(T ent : collection) {
			if(ent.getId().equals(entity.getId())) {
				ent.setDeleted(true);
				break;
			}
		}
		saveAll(collection);
		return true;
	}

	@Override
	public void saveAll(List<T> collection) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(path));
			String collectionJsonString = gson.toJson(collection, classType);
			out.println(collectionJsonString);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<T> getAllActive() {
		ArrayList<T> collection = (ArrayList<T>) getAll();
		ArrayList<T> activeCollection = new ArrayList<T>();
		for(T entity : collection) {
			if(!entity.isDeleted()) {
				activeCollection.add(entity);
			}
		}
		return activeCollection;
	}
	
	private void checkForFIle() {
		try {
			File file = new File(path);
			if(file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
