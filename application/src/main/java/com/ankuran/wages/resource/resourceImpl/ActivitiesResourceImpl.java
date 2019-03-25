package com.ankuran.wages.resource.resourceImpl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ankuran.wages.HelperUtil;
import com.ankuran.wages.enums.EmployeeActivityEnum.ActivityType;
import com.ankuran.wages.enums.EmployeeActivityEnum.DueDistributionType;
import com.ankuran.wages.model.response.Activities;
import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.model.response.ActivityStoreResponseDTO;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeeShare;
import com.ankuran.wages.model.response.ItemResponseDTO;
import com.ankuran.wages.model.response.OutstandingAmountResponseDTO;
import com.ankuran.wages.provider.ActivityProvider;
import com.ankuran.wages.provider.EmployeeProvider;
import com.ankuran.wages.provider.ItemProvider;
import com.ankuran.wages.provider.OutstandingAmountProvider;
import com.ankuran.wages.resource.ActivitiesResource;

@Component
public class ActivitiesResourceImpl implements ActivitiesResource {
	
	@Autowired
	ActivityProvider activityProvider;
	
	@Autowired
	OutstandingAmountProvider outstandingAmountProvider;
	
	@Autowired
	ItemProvider itemProvider;
	
	@Autowired
	private EmployeeProvider employeeProvider;
	

	@Override
	public ResponseEntity<ActivityStoreResponseDTO> addIndividualActivity(Long centreId, Long employeeId, ActivityResponseDTO activity) {
		if (employeeId != null && employeeId > 0 && centreId != null && centreId > 0 && activity != null) {
			ActivityResponseDTO activityResponseDTO = activityProvider.addActivity(centreId, employeeId, activity);
			if (activityResponseDTO != null && activityResponseDTO.getId() != null && BigInteger.ZERO.compareTo(activityResponseDTO.getId()) < 0) {
				if (ActivityType.PAYMENT.equals(activityResponseDTO.getType()) && activityResponseDTO.getPaymentDetails() != null 
						&& activityResponseDTO.getPaymentDetails().getAmount() != null 
						&& activityResponseDTO.getPaymentDetails().getAmount() > 0) {
					//Update amounts
					Double amount = activityResponseDTO.getPaymentDetails().getAmount();
					Double currentDueAmount = outstandingAmountProvider.fetchOutstandingAmountByCentreIDAndEmployeeId(centreId, employeeId).getOutstandingDue();
					Double remainingDueAmount = currentDueAmount - amount;
					outstandingAmountProvider.patchOutstandingAmountByCentreIDAndEmployeeId(new OutstandingAmountResponseDTO(remainingDueAmount, centreId, employeeId));
					activityResponseDTO.getPaymentDetails().setCurrentDue(currentDueAmount);
					activityResponseDTO.getPaymentDetails().setRemainingDue(remainingDueAmount);
				} else if (ActivityType.DUE.equals(activityResponseDTO.getType()) && activityResponseDTO.getDueDetails() != null 
						&& activityResponseDTO.getDueDetails().getAmount() != null 
						&& activityResponseDTO.getDueDetails().getAmount() > 0) {
					//Update amounts
					Double amount = activityResponseDTO.getDueDetails().getAmount();
					Double currentDueAmount = outstandingAmountProvider.fetchOutstandingAmountByCentreIDAndEmployeeId(centreId, employeeId).getOutstandingDue();
					Double remainingDueAmount = currentDueAmount + amount;
					outstandingAmountProvider.patchOutstandingAmountByCentreIDAndEmployeeId(new OutstandingAmountResponseDTO(remainingDueAmount, centreId, employeeId));
				}
				return new ResponseEntity<ActivityStoreResponseDTO>(new ActivityStoreResponseDTO(centreId, employeeId, activityResponseDTO.getId()), HttpStatus.OK);
			} else {
				return new ResponseEntity<ActivityStoreResponseDTO>(HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<ActivityStoreResponseDTO>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ActivityStoreResponseDTO> addGroupActivity(Long centreId, ActivityResponseDTO activity) {
		if (centreId != null && centreId > 0 && activity != null && ActivityType.DUE.equals(activity.getType()) && activity.getDueDetails() != null 
				&& DueDistributionType.GROUP.equals(activity.getDueDetails().getDistributionType())) {
			ActivityResponseDTO activityResponseDTO = activityProvider.addActivity(centreId, Long.valueOf(0), activity);
			if (activityResponseDTO != null && activityResponseDTO.getId() != null && BigInteger.ZERO.compareTo(activityResponseDTO.getId()) < 0) {
				if (activityResponseDTO.getDueDetails() != null && CollectionUtils.isNotEmpty(activityResponseDTO.getDueDetails().getDistribution())) {
					//Update amounts
					for (EmployeeShare employeeShare : activityResponseDTO.getDueDetails().getDistribution()) {
						Long employeeId = employeeShare.getEmployee().getId();
						Double amount = employeeShare.getAmount();
						Double currentDueAmount = outstandingAmountProvider.fetchOutstandingAmountByCentreIDAndEmployeeId(centreId, employeeId).getOutstandingDue();
						Double remainingDueAmount = currentDueAmount + amount;
						outstandingAmountProvider.patchOutstandingAmountByCentreIDAndEmployeeId(new OutstandingAmountResponseDTO(remainingDueAmount, centreId, employeeId));
					}
				}
				return new ResponseEntity<ActivityStoreResponseDTO>(new ActivityStoreResponseDTO(centreId, activityResponseDTO.getId()), HttpStatus.OK);
			} else {
				return new ResponseEntity<ActivityStoreResponseDTO>(HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<ActivityStoreResponseDTO>(HttpStatus.BAD_REQUEST);
	}


	@Override
	public ResponseEntity<Activities> getActivities(Long centreId, Long employeeId, String lowerTimeCreated,
			String upperTimeCreated, List<String> types) throws ParseException {
		Pair<Date, Date> timeRange = HelperUtil.getTimeRange(lowerTimeCreated, upperTimeCreated);
		List<ActivityType> activityTypes = getActivityTypes(types);
		List<ActivityResponseDTO> activities = activityProvider.getActivities(centreId, employeeId, timeRange.getLeft(), timeRange.getRight(), activityTypes); 
		activities.forEach(act -> populateItemDetails(act));
		activities.forEach(act -> populateRecipientDetails(centreId, employeeId, act));
		Activities activitiesResponse = new Activities();
		activitiesResponse.setActivities(activities);
		return new ResponseEntity<Activities>(activitiesResponse, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Activities> getPaymentActivities(Long centreId, String lowerTimeCreated, String upperTimeCreated) throws ParseException {
		Pair<Date, Date> timeRange = HelperUtil.getTimeRange(lowerTimeCreated, upperTimeCreated);
		List<ActivityResponseDTO> activities = activityProvider.getPaymentActivities(centreId, timeRange.getLeft(), timeRange.getRight()); 
		activities.forEach(act -> populateRecipientDetails(centreId, act.getPaymentDetails().getRecipient().getId(), act));
		Activities activitiesResponse = new Activities();
		activitiesResponse.setActivities(activities);
		return new ResponseEntity<Activities>(activitiesResponse, HttpStatus.OK);
	}

	private ActivityResponseDTO populateItemDetails(ActivityResponseDTO activity) {
		ActivityResponseDTO prettyActivity = activity;
		if(activity != null && activity.getDueDetails() != null && activity.getDueDetails().getItem() != null && 
				(activity.getDueDetails().getItem().getId() != null || activity.getDueDetails().getItem().getId() != 0L)) {
			Long itemId = activity.getDueDetails().getItem().getId();
			Optional<ItemResponseDTO> itemResponseDTO = Optional.ofNullable(itemProvider.getProductById(itemId));
			itemResponseDTO.ifPresent(item -> prettyActivity.getDueDetails().setItem(item));
		}
		return prettyActivity;
	}
	
	private void populateRecipientDetails(Long centreId, Long employeeId, ActivityResponseDTO activity) {
		EmployeeResponseDTO employee = null;
//		EmployeeResponseDTO employee = employeeResourceCache.getEmployeeResponseDTO(centreId, employeeId);
		if(employee == null)
			employee = employeeProvider.fetchEmployeeByCentreIDAndEmployeeId(centreId, employeeId);
		if (ActivityType.PAYMENT.equals(activity.getType())) {
			activity.getPaymentDetails().setRecipient(employee);
		} else if (ActivityType.DUE.equals(activity.getType())) {
			activity.getDueDetails().setRecipient(employee);
		}
	}

	private List<ActivityType> getActivityTypes(List<String> types) {
		List<ActivityType> activityTypes = new ArrayList<ActivityType>();
		if(CollectionUtils.isNotEmpty(types)) {
			activityTypes.addAll(types.stream().map(t -> ActivityType.valueOf(t)).collect(Collectors.toList()));
		} else {
			activityTypes.addAll(Arrays.asList(ActivityType.values()));
		}
		return activityTypes;
	}
	
	@Override
	public ResponseEntity<ActivityResponseDTO> getIndividualActivity(Long centreId, Long employeeId,
			BigInteger activityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ActivityResponseDTO> getGroupActivity(Long centreId, Long employeeId, BigInteger activityId) {
		// TODO Auto-generated method stub
		return null;
	}

}
