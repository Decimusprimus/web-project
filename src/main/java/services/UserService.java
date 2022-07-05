package services;

import java.time.LocalDate;
import java.util.UUID;

import dto.RegisterCustomerDTO;
import dto.UserInfoDTO;
import model.Admin;
import model.Customer;
import model.User;
import model.UserInfo;
import model.UserRole;
import repositories.AdminRepository;
import repositories.CustomerRepository;
import repositories.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private CustomerRepository customerRepository;
	private AdminRepository adminRepository;
	
	public UserService(UserRepository userRepository, CustomerRepository customerRepository, AdminRepository adminRepository) {
		super();
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
		this.adminRepository = adminRepository;
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
	
	public UserInfo GetUserInfo(String userId) {
		User user = userRepository.getById(UUID.fromString(userId));
		if(user != null) {
			switch (user.getUserRole()) {
			case CUSTOMER:
				Customer customer = customerRepository.getById(user.getId());
				return customer;
			case ADMIN:
				Admin admin = adminRepository.getById(user.getId());
				return admin;
			default:
				return null;
			}
		}
		return null;
	}
	
	public UserInfo UpdateUserInfo(String userId, UserInfoDTO userInfoDTO) {
		User user = userRepository.getById(UUID.fromString(userId));
		if(user != null) {
			switch (user.getUserRole()) {
			case CUSTOMER:
				Customer customer = customerRepository.getById(user.getId());
				customer.updateUserInfo(userInfoDTO);
				return customerRepository.update(customer);
			case ADMIN:
				Admin admin = adminRepository.getById(user.getId());
				admin.updateUserInfo(userInfoDTO);
				return adminRepository.update(admin);
			default:
				return null;
			}
		}
		return null;
	}
	
	public UserInfo UpdateUsername(String userId, String username) {
		User user = userRepository.getById(UUID.fromString(userId));
		if(user != null) {
			user.setUsername(username);
			userRepository.update(user);
			switch (user.getUserRole()) {
			case CUSTOMER:
				Customer customer = customerRepository.getById(user.getId());
				customer.setUsername(username);
				return customerRepository.update(customer);
			case ADMIN:
				Admin admin = adminRepository.getById(user.getId());
				admin.setUsername(username);
				return adminRepository.update(admin);
			default:
				return null;
			}
		}
		return null;
	}
	

}
