package com.ankuran.wages.provider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankuran.wages.enums.EmployeeActivityEnum.ActivityType;
import com.ankuran.wages.enums.EmployeeActivityEnum.DueDistributionType;
import com.ankuran.wages.mapper.ActivityMapper;
import com.ankuran.wages.model.GroupWagesActivityDao;
import com.ankuran.wages.model.WagesActivityDao;
import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.provider.ActivityProvider;
import com.ankuran.wages.repository.GroupWagesActivityRepository;
import com.ankuran.wages.repository.WagesActivityRepository;

@Component
public class ActivityProviderImpl implements ActivityProvider {

	@Autowired
	WagesActivityRepository wagesActivityRepository;
	
	@Autowired
	GroupWagesActivityRepository groupWagesActivityRepository;
	
	@Autowired
	ActivityMapper activityMapper;

	@Override
	public ActivityResponseDTO addActivity(Long centreId, Long employeeId, ActivityResponseDTO activity) {
		ActivityResponseDTO storeResponse = null;
		if (activity != null && ActivityType.DUE.equals(activity.getType()) && activity.getDueDetails() != null 
				&& DueDistributionType.GROUP.equals(activity.getDueDetails().getDistributionType())
				&& activity.getTimeCreated() != null) {
			GroupWagesActivityDao groupWagesActivityDao = activityMapper.mapGroupActivityRequestToGroupWagesDao(centreId, activity);
			if (!hasExistingRecord(centreId, activity, groupWagesActivityDao)) {
				groupWagesActivityRepository.save(groupWagesActivityDao);
				
				if (groupWagesActivityDao.getId() != null && groupWagesActivityDao.getId() > 0) {
					List<WagesActivityDao> wagesActivityDaos = activityMapper.mapGroupActivityRequestToMultiIndividualWagesDao(centreId, activity, groupWagesActivityDao.getId());
					wagesActivityRepository.saveAll(wagesActivityDaos);
					
					String wagesActivityIds = wagesActivityDaos.stream().map(wageActivity -> String.valueOf(wageActivity.getId())).collect(Collectors.joining(","));
					groupWagesActivityDao.setMiscInfo(wagesActivityIds);
					groupWagesActivityRepository.save(groupWagesActivityDao);
					
					storeResponse = activityMapper.mapGroupWagesToActivityResponseDTO(groupWagesActivityDao, wagesActivityDaos);
				}
			}
		} else if (activity != null && activity.getTimeCreated() != null && employeeId != null && employeeId > 0) {
			WagesActivityDao wageActivity = activityMapper.mapActivityRequestToIndividualWagesDao(centreId, employeeId, activity);
			if (!hasExistingRecord(centreId, employeeId, activity, wageActivity)) {
				wagesActivityRepository.save(wageActivity);
				
				storeResponse = activityMapper.mapIndividualWagesDaoToActivityResponseDTO(wageActivity);
			}
		}
		return storeResponse;
	}

	private boolean hasExistingRecord(Long centreId, Long employeeId, ActivityResponseDTO activity,
			WagesActivityDao wageActivity) {
		Date lowerTimeCreated = new Date(activity.getTimeCreated().getTime() - 1000);
		Date upperTimeCreated = new Date(activity.getTimeCreated().getTime() + 1000);
		WagesActivityDao existingWageActivity = wagesActivityRepository.findByCentreIdAndEmployeeIdAndTimeCreatedBetween(centreId, employeeId, lowerTimeCreated, upperTimeCreated);
		return existingWageActivity != null && existingWageActivity.getTotalAmount() != null && wageActivity.getTotalAmount() != null
				&& existingWageActivity.getTotalAmount().equals(wageActivity.getTotalAmount());
	}

	private boolean hasExistingRecord(Long centreId, ActivityResponseDTO activity, GroupWagesActivityDao groupWagesActivityDao) {
		Date lowerTimeCreated = new Date(activity.getTimeCreated().getTime() - 1000);
		Date upperTimeCreated = new Date(activity.getTimeCreated().getTime() + 1000);
		GroupWagesActivityDao existingGroupWagesActivityDao = groupWagesActivityRepository.findByCentreIdAndTimeCreatedBetween(centreId, lowerTimeCreated, upperTimeCreated);
		return existingGroupWagesActivityDao != null && groupWagesActivityDao.getTotalAmount() != null && existingGroupWagesActivityDao.getTotalAmount() != null 
				&& existingGroupWagesActivityDao.getTotalAmount().equals(groupWagesActivityDao.getTotalAmount());
	}

	@Override
	public List<ActivityResponseDTO> getActivities(Long centreId, Long employeeId, Date lowerTimeCreated, Date upperTimeCreated, List<ActivityType> types) {
		List<ActivityResponseDTO> activities = new ArrayList<>();
		List<Long> activityTypes = types.stream().map(type -> activityMapper.getValue(type).longValue()).filter(value -> value != null).collect(Collectors.toList());
		List<WagesActivityDao> wagesActivities = wagesActivityRepository.findByCentreIdAndEmployeeIdAndTimeCreatedBetweenAndTypeIn(centreId, employeeId, lowerTimeCreated, upperTimeCreated, activityTypes);
		
		if(CollectionUtils.isNotEmpty(wagesActivities)) {
			activities = wagesActivities.stream().map(w -> activityMapper.mapIndividualWagesDaoToActivityResponseDTO(w)).collect(Collectors.toList());
		}
		
		return activities;
	}

	@Override
	public List<ActivityResponseDTO> getPaymentActivities(Long centreId, Date lowerTimeCreated, Date upperTimeCreated) {
		List<ActivityType> types = new ArrayList<>();
		types.add(ActivityType.PAYMENT);
		List<ActivityResponseDTO> activities = new ArrayList<>();
		List<Long> activityTypes = types.stream().map(type -> activityMapper.getValue(type).longValue()).filter(value -> value != null).collect(Collectors.toList());
		List<WagesActivityDao> wagesActivities = wagesActivityRepository.findByCentreIdAndTimeCreatedBetweenAndTypeIn(centreId, lowerTimeCreated, upperTimeCreated, activityTypes);
		
		if(CollectionUtils.isNotEmpty(wagesActivities)) {
			activities = wagesActivities.stream().map(w -> activityMapper.mapIndividualWagesDaoToActivityResponseDTO(w)).collect(Collectors.toList());
		}
		
		return activities;
	}
}
