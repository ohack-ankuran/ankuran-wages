package com.ankuran.wages.provider;

import java.util.List;

import com.ankuran.wages.model.response.EmployeeResponseDTO;

public interface EmployeeProvider {

    public EmployeeResponseDTO fetchEmployeeByCentreIDAndFullName(Long centreId, String fullName);

	EmployeeResponseDTO fetchEmployeeByCentreIDAndEmployeeId(Long centreId, Long employeeId);

	public List<EmployeeResponseDTO> fetchEmployeesByCentreID(Long centreId);
}