package com.ankuran.wages.model.response;

public class EmployeeShare {

	private EmployeeResponseDTO employee;
	private Double amount;
	public EmployeeResponseDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeResponseDTO employee) {
		this.employee = employee;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
