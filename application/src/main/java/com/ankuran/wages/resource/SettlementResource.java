package com.ankuran.wages.resource;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankuran.wages.model.response.SettlementDTO;
import com.ankuran.wages.model.response.SettlementsDTO;

@Controller
@RequestMapping(path="/centres/{centreId}")
public interface SettlementResource {

	@PostMapping("/settlements")
	public ResponseEntity<SettlementsDTO> addSettlement(@PathVariable("centreId") Long centreId, @RequestBody SettlementDTO settlementDTO);
	
	@GetMapping("/settlements")
	public ResponseEntity<SettlementsDTO> getSettlements(@PathVariable("centreId") Long centreId,
			@RequestParam(value="lowerTimeCreated", required=false) String lowerTimeCreated,
			@RequestParam(value="upperTimeCreated", required=false) String upperTimeCreated) throws ParseException;
}
