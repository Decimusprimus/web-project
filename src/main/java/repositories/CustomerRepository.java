package repositories;

import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Customer;

public class CustomerRepository extends Repository<Customer, UUID> {

	public CustomerRepository(String path) {
		super(path, new TypeToken<List<Customer>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}
	
	

}
