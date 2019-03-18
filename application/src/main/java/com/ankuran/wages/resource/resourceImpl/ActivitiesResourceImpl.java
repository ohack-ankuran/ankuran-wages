package com.ankuran.wages.resource.resourceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ankuran.wages.model.response.EmployeeActivityDTO;
import com.ankuran.wages.resource.ActivitiesResource;

public class ActivitiesResourceImpl implements ActivitiesResource{

	@Override
	public ResponseEntity<EmployeeActivityDTO> addActivity(Long centreId, Long employeeId, EmployeeActivityDTO activity) {
		if (employeeId != null && employeeId > 0 && centreId != null && centreId > 0 && activity != null) {
			
		}
		return new ResponseEntity<EmployeeActivityDTO>(HttpStatus.BAD_REQUEST);
	}

}
