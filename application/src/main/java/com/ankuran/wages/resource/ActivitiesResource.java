package com.ankuran.wages.resource;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.model.response.ActivityStoreResponseDTO;

@Controller
@RequestMapping(path="/centres/{centreId}/employees")
public interface ActivitiesResource {

	@PostMapping(path="/{employeeId}/activities")
	public ResponseEntity<ActivityStoreResponseDTO> addIndividualActivity(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId, @RequestBody ActivityResponseDTO activity);
	
	@PostMapping(path="/activities")
	public ResponseEntity<ActivityStoreResponseDTO> addGroupActivity(@PathVariable("centreId") Long centreId, @RequestBody ActivityResponseDTO activity);
	
	@GetMapping(path="/{employeeId}/activities/{activityId}")
	public ResponseEntity<ActivityResponseDTO> getIndividualActivity(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId, @PathVariable("activityId") BigInteger activityId);
	
	@GetMapping(path="/activities/{activityId}")
	public ResponseEntity<ActivityResponseDTO> getGroupActivity(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId, @PathVariable("activityId") BigInteger activityId);
}
