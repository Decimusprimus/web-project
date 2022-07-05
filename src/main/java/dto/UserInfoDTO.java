package dto;

import java.time.LocalDate;

import model.Gender;

public class UserInfoDTO {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Gender gender;
	
	public UserInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserInfoDTO(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

}
