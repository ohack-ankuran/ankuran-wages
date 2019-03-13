package com.ankuran.wages.model.response;


import java.util.Date;

public class EmployeeResponseDTO {

    private Long id;
    private String mobileNo;
    private Date dateOfJoining;
    private SpouseResponseDTO husband;
    private Long centre;
    private boolean active;
    private PersonNameDTO name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public SpouseResponseDTO getHusband() {
		return husband;
	}
	public void setHusband(SpouseResponseDTO husband) {
		this.husband = husband;
	}
	public Long getCentre() {
		return centre;
	}
	public void setCentre(Long centre) {
		this.centre = centre;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public PersonNameDTO getName() {
		return name;
	}
	public void setName(PersonNameDTO name) {
		this.name = name;
	}


}