package com.ankuran.wages.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeesResponseDTO;

@Controller
@RequestMapping(path = "/centres", method = RequestMethod.GET)
public interface EmployeeResource {

    @GetMapping("/{centreId}/employees/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId);

    @GetMapping("/{centreId}/employees/")
    public ResponseEntity<EmployeesResponseDTO> getEmployees(@PathVariable("centreId") Long centreId);
}