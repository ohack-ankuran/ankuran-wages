package com.ankuran.wages.resource.resourceImpl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeesResponseDTO;
import com.ankuran.wages.provider.EmployeeProvider;
import com.ankuran.wages.resource.EmployeeResource;

@Component
public class EmployeeResourceImpl implements EmployeeResource {


    @Autowired
    private EmployeeProvider employeeProvider;

    @Override
    public ResponseEntity<EmployeeResponseDTO> getEmployee(Long centreId, Long employeeId) {
        EmployeeResponseDTO employeeResponseDTO = employeeProvider.fetchEmployeeByCentreIDAndEmployeeId(centreId, employeeId);

        if (employeeResponseDTO != null) {
            return new ResponseEntity<EmployeeResponseDTO>(employeeResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.NO_CONTENT);
    }

	@Override
	public ResponseEntity<EmployeesResponseDTO> getEmployees(Long centreId) {
		List<EmployeeResponseDTO> employeeResponseDTOs = employeeProvider.fetchEmployeesByCentreID(centreId);
        if (CollectionUtils.isNotEmpty(employeeResponseDTOs)) {
            return new ResponseEntity<EmployeesResponseDTO>(new EmployeesResponseDTO(employeeResponseDTOs), HttpStatus.OK);
        }
        return new ResponseEntity<EmployeesResponseDTO>(HttpStatus.NO_CONTENT);
	}

}