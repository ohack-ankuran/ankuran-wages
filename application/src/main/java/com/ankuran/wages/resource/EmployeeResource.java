package com.ankuran.wages.resource;

import com.ankuran.wages.model.EmployeeDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller


@RequestMapping(path = "/centre", method = RequestMethod.GET)
public interface EmployeeResource {

    @GetMapping("/{centreId}/employees/{employeeFullName}")
    public ResponseEntity<EmployeeDao> getEmployee(@PathVariable("centreId") String centreId, @PathVariable("employeeFullName") String employeeFullName);
}