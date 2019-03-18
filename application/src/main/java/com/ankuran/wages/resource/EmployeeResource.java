package com.ankuran.wages.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeeStoreResponseDTO;
import com.ankuran.wages.model.response.EmployeesResponseDTO;

@Controller
@RequestMapping(path = "/centres")
public interface EmployeeResource {

    @GetMapping("/{centreId}/employees/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId);

    @GetMapping("/{centreId}/employees/")
    public ResponseEntity<EmployeesResponseDTO> getEmployees(@PathVariable("centreId") Long centreId);
    
    @PostMapping("/{centreId}/employees/")
    public ResponseEntity<EmployeeStoreResponseDTO> addEmployee(@PathVariable("centreId") Long centreId, @RequestBody EmployeeResponseDTO employee);
    
    @DeleteMapping("/{centreId}/employees/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId);
    
    @PatchMapping("/{centreId}/employees/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> patchEmployee(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId, @RequestBody EmployeeResponseDTO employee);
}