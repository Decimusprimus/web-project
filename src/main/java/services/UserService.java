package services;

import java.time.LocalDate;

import dto.RegisterCustomerDTO;
import model.Customer;
import model.User;
import model.UserRole;
import repositories.CustomerRepository;
import repositories.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private CustomerRepository customerRepository;
	
	public UserService(UserRepository userRepository, CustomerRepository customerRepository) {
		super();
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
	}

	public User checkUserLogin(String username, String password) {
		if(username.isEmpty() || password.isEmpty()) {
			return null;
		}
		User user = userRepository.getByUsername(username);
		if(user == null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public Customer createNewCustomer(RegisterCustomerDTO dto) {
		User newUser = userRepository.createNewCustomer(dto.getUsername(), dto.getPassword());
		if(newUser == null) {
			return null;
		}
		LocalDate date = LocalDate.parse(dto.getDateOfBirth());
		
		Customer customer = new Customer(newUser.getId(), newUser.getUsername(), dto.getFirstName(), dto.getLastName(), date, UserRole.CUSTOMER, dto.getGender(), false);
		customerRepository.create(customer);
		return customer;
	}

}
