package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import dto.ChangePasswordDTO;
import dto.RegisterCustomerDTO;
import dto.UserInfoDTO;
import model.Admin;
import model.Coach;
import model.Customer;
import model.Manager;
import model.User;
import model.UserInfo;
import model.UserRole;
import repositories.AdminRepository;
import repositories.CoachRepository;
import repositories.CustomerRepository;
import repositories.ManagerRepository;
import repositories.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private CustomerRepository customerRepository;
	private AdminRepository adminRepository;
	private ManagerRepository managerRepository;
	private CoachRepository coachRepository;
	
	public UserService(UserRepository userRepository, CustomerRepository customerRepository, AdminRepository adminRepository, ManagerRepository managerRepository, CoachRepository coachRepository) {
		super();
		this.userRepository = userRepository;
		this.customerRepository = customerRepository;
		this.adminRepository = adminRepository;
		this.managerRepository = managerRepository;
		this.coachRepository = coachRepository;
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
	
	public Manager createNewManager(RegisterCustomerDTO dto) {
		User newUser = userRepository.createNewManager(dto.getUsername(), dto.getPassword());
		if(newUser == null) {
			return null;
		}
		LocalDate date = LocalDate.parse(dto.getDateOfBirth());
		Manager manager = new Manager(newUser.getId(), newUser.getUsername(), dto.getFirstName(), dto.getLastName(), date, UserRole.MANAGER, dto.getGender(), false);
		managerRepository.create(manager);
		return manager;
		
	}
	
	public Coach createNewCoach(RegisterCustomerDTO dto) {
		User newUser = userRepository.createNewCoach(dto.getUsername(), dto.getPassword());
		if(newUser == null) {
			return null;
		}
		LocalDate date = LocalDate.parse(dto.getDateOfBirth());
		Coach coach = new Coach(newUser.getId(), newUser.getUsername(), dto.getFirstName(), dto.getLastName(), date, UserRole.COACH, dto.getGender(), false);
		coachRepository.create(coach);
		return coach;
		
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
			case COACH:
				Coach coach = coachRepository.getById(user.getId());
				return coach;
			case MANAGER: 
				Manager manager = managerRepository.getById(user.getId());
				return manager;
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
			case COACH:
				Coach coach = coachRepository.getById(user.getId());
				coach.updateUserInfo(userInfoDTO);
				return coachRepository.update(coach);
			case MANAGER:
				Manager manager = managerRepository.getById(user.getId());
				manager.updateUserInfo(userInfoDTO);
				return managerRepository.update(manager);
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
			case COACH:
				Coach coach = coachRepository.getById(user.getId());
				coach.setUsername(username);
				return coachRepository.update(coach);
			case MANAGER:
				Manager manager = managerRepository.getById(user.getId());
				manager.setUsername(username);
				return managerRepository.update(manager);
			default:
				return null;
			}
		}
		return null;
	}
	
	public boolean ChangePassword(String userId, ChangePasswordDTO changePasswordDTO) {
		User user = userRepository.getById(UUID.fromString(userId));
		if(user.getPassword().compareTo(changePasswordDTO.getOldPassword()) == 0 && changePasswordDTO.CheckNewPassword()) {
			user.setPassword(changePasswordDTO.getNewPassword());
			userRepository.update(user);
			return true;
		}
		return false;
	}
	
	public User GetUser(UUID id) {
		return userRepository.getById(id);
	}
	
	public ArrayList<Manager> GetAllFreeManagers() {
		return managerRepository.getAllWithoutFacility();
	}
	
	
	public void assignManagerToFacility(UUID managerId, UUID faciilityId) {
		Manager manager = managerRepository.getById(managerId);
		manager.setFacilityId(faciilityId);
		managerRepository.update(manager);
	}
	
	public ArrayList<Coach> getAllCoaches() {
		return (ArrayList<Coach>) coachRepository.getAllActive();
	}

}
