package com.ankuran.wages.model.response;

import java.math.BigInteger;

public class EmployeeShare {

	private EmployeeResponseDTO employee;
	private Double amount;
	private BigInteger id;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
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
