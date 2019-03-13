package com.ankuran.wages.model.response;

public class PersonNameDTO {

	private String lastName;
	private String firstName;
	private String fullName;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public PersonNameDTO(String lastName, String firstName, String fullName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.fullName = fullName;
	}
	
}
