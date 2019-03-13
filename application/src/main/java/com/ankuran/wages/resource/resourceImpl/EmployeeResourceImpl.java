package com.ankuran.wages.resource.resourceImpl;

import com.ankuran.wages.model.response.CentreResponseDTO;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.provider.EmployeeProvider;
import com.ankuran.wages.resource.EmployeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResourceImpl implements EmployeeResource {


    @Autowired
    private EmployeeProvider employeeProvider;

    @Override
    public ResponseEntity getEmployee(String centreId, String fullName) {
        Long centreID = Long.parseLong(centreId);
        EmployeeResponseDTO employeeResponseDTO = employeeProvider.fetchEmployeeByCentreIDAndFullName(centreID, fullName);

        if (employeeResponseDTO != null) {
            return new ResponseEntity<EmployeeResponseDTO>(employeeResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<EmployeeResponseDTO>(HttpStatus.NO_CONTENT);
    }

}