package com.ankuran.wages.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.enums.EmployeeActivityEnum.ActivityType;
import com.ankuran.wages.enums.EmployeeActivityEnum.DueDistributionType;
import com.ankuran.wages.mapper.ActivityMapper;
import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.provider.ActivityProvider;
import com.ankuran.wages.repository.ActivityRepository;

@Component
public class ActivityProviderImpl implements ActivityProvider {

	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	ActivityMapper activityMapper;

	@Override
	public ActivityResponseDTO addActivity(Long centreId, Long employeeId, ActivityResponseDTO activity) {
		ActivityResponseDTO storeResponse = null;
		if (activity != null && ActivityType.DUE.equals(activity.getType()) && activity.getDueDetails() != null 
				&& DueDistributionType.GROUP.equals(activity.getDueDetails().getDistributionType())
				&& activity.getTimeCreated() != null) {
			
		} else if (activity != null && activity.getTimeCreated() != null) {
			
		}
		return storeResponse;
	}
	
}
