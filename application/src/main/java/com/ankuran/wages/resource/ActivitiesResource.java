package com.ankuran.wages.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.model.response.ActivityStoreResponseDTO;

@Controller
@RequestMapping(path="/centres/{centreId}/employees/{employeeId}")
public interface ActivitiesResource {

	@PostMapping(path="/activities")
	public ResponseEntity<ActivityStoreResponseDTO> addActivity(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId, @RequestBody ActivityResponseDTO activity);
}
