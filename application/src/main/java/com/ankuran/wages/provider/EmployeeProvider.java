package com.ankuran.wages.provider;

import java.util.List;

import com.ankuran.wages.model.response.EmployeeResponseDTO;

public interface EmployeeProvider {

    public EmployeeResponseDTO fetchEmployeeByCentreIDAndFullName(Long centreId, String fullName);

	EmployeeResponseDTO fetchEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId);

	public List<EmployeeResponseDTO> fetchEmployeesByCentreID(Long centreId);
	
	Long addEmployee(EmployeeResponseDTO employeeResponseDTO);
	
	EmployeeResponseDTO deleteEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId);
	
	EmployeeResponseDTO patchEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId, EmployeeResponseDTO employeeResponseDTO);
}