package com.ankuran.wages.resource;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping(path="/{employeeId}/activities")
	public ResponseEntity<List<ActivityResponseDTO>> getActivities(@PathVariable("centreId") Long centreId, 
			@PathVariable("employeeId") Long employeeId, 
			@RequestParam(value="lowerTimeCreated", required=false) String lowerTimeCreated,
			@RequestParam(value="upperTimeCreated", required=false) String upperTimeCreated,
			@RequestParam(value="types", required=false) List<String> types) throws ParseException;
	
	@GetMapping(path="/activities/{activityId}")
	public ResponseEntity<ActivityResponseDTO> getGroupActivity(@PathVariable("centreId") Long centreId, @PathVariable("employeeId") Long employeeId, @PathVariable("activityId") BigInteger activityId);
}
