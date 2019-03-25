package com.ankuran.wages.model.response;

import java.util.Date;
import java.util.List;

public class ItemResponseDTO {

	private Long id;
	private String name;
	private String category;
	private String picture;
	private List<String> labels;
	private Date timeCreated;
	private EmployeeResponseDTO addedBy;
	private Boolean active;
	private Long availableUnits;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	public EmployeeResponseDTO getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(EmployeeResponseDTO addedBy) {
		this.addedBy = addedBy;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Long getAvailableUnits() {
		return availableUnits;
	}
	public void setAvailableUnits(Long availableUnits) {
		this.availableUnits = availableUnits;
	}
}
