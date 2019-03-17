package com.ankuran.wages.model.response;


import java.util.Date;

public class EmployeeResponseDTO {

    private Long id;
    private String fullName;
    private String mobile;
    private Date timeOfJoining;
    private SpouseResponseDTO husband;
    private Long centre;
    private boolean active;
    private Double outstandingDue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getTimeOfJoining() {
		return timeOfJoining;
	}
	public void setTimeOfJoining(Date timeOfJoining) {
		this.timeOfJoining = timeOfJoining;
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
	public Double getOutstandingDue() {
		return outstandingDue;
	}
	public void setOutstandingDue(Double outstandingDue) {
		this.outstandingDue = outstandingDue;
	}
    
	
}