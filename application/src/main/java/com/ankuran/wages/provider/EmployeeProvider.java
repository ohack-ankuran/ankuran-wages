package com.ankuran.wages.provider;

import com.ankuran.wages.model.response.EmployeeResponseDTO;

public interface EmployeeProvider {

    public EmployeeResponseDTO fetchEmployeeByCentreIDAndFullName(Long centreId, String fullName);
}