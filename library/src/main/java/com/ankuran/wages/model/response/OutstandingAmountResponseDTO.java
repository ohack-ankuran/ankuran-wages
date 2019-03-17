package com.ankuran.wages.model.response;

public class OutstandingAmountResponseDTO {

	Double outstandingDue;
	Long centreId;
	Long employeeId;
	public Double getOutstandingDue() {
		return outstandingDue;
	}
	public void setOutstandingDue(Double outstandingDue) {
		this.outstandingDue = outstandingDue;
	}
	public Long getCentreId() {
		return centreId;
	}
	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
