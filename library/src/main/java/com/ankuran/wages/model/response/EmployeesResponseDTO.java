package com.ankuran.wages.model.response;


import java.util.List;

public class EmployeesResponseDTO {

    private List<EmployeeResponseDTO> employees;

	public EmployeesResponseDTO(List<EmployeeResponseDTO> employees) {
		super();
		this.employees = employees;
	}

	public List<EmployeeResponseDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeResponseDTO> employees) {
		this.employees = employees;
	}


}