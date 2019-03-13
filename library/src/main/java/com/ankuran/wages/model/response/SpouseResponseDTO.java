package com.ankuran.wages.model.response;

public class SpouseResponseDTO {

	private PersonNameDTO name;
    private String bslEmployeeId;
	public PersonNameDTO getName() {
		return name;
	}
	public void setName(PersonNameDTO name) {
		this.name = name;
	}
	public String getBslEmployeeId() {
		return bslEmployeeId;
	}
	public void setBslEmployeeId(String bslEmployeeId) {
		this.bslEmployeeId = bslEmployeeId;
	}
	public SpouseResponseDTO(PersonNameDTO name, String bslEmployeeId) {
		super();
		this.name = name;
		this.bslEmployeeId = bslEmployeeId;
	}
}
