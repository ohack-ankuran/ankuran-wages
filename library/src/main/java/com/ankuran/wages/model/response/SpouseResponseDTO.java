package com.ankuran.wages.model.response;

public class SpouseResponseDTO {

	private String fullName;
    private String bslEmployeeId;
	
	public SpouseResponseDTO(String fullName, String bslEmployeeId) {
		super();
		this.fullName = fullName;
		this.bslEmployeeId = bslEmployeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBslEmployeeId() {
		return bslEmployeeId;
	}

	public void setBslEmployeeId(String bslEmployeeId) {
		this.bslEmployeeId = bslEmployeeId;
	}
}
