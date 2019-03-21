package com.ankuran.wages.resource.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ankuran.wages.model.response.EmployeeResponseDTO;

@Component
@Scope("request")
public class EmployeeResourceCache {
	Map<MultiKey<Long>, EmployeeResponseDTO> idEmployeeMap = new HashMap<MultiKey<Long>, EmployeeResponseDTO>();
	
	public void insert(Long centreId, Long employeeId, EmployeeResponseDTO employee) {
		MultiKey<Long> id = new MultiKey<Long>(centreId, employeeId);
		idEmployeeMap.putIfAbsent(id, employee);
	}
	
	public EmployeeResponseDTO getEmployeeResponseDTO(Long centreId, Long employeeId) {
		return idEmployeeMap.getOrDefault(new MultiKey<Long>(centreId, employeeId), null);
	}
}
