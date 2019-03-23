package com.ankuran.wages.provider;

import java.util.Date;
import java.util.List;

import com.ankuran.wages.enums.EmployeeActivityEnum;
import com.ankuran.wages.model.response.ActivityResponseDTO;

public interface ActivityProvider {

	ActivityResponseDTO addActivity(Long centreId, Long employeeId, ActivityResponseDTO activity);
	
	List<ActivityResponseDTO> getActivities(Long centreId, Long employeeId, Date lowerTimeCreated, Date upperTimeCreated, List<EmployeeActivityEnum.ActivityType> types);
	
	List<ActivityResponseDTO> getPaymentActivities(Long centreId, Date lowerTimeCreated, Date upperTimeCreated);
	
}
