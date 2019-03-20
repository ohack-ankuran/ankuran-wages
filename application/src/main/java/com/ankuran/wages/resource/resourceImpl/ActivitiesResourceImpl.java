package com.ankuran.wages.resource.resourceImpl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ankuran.wages.enums.EmployeeActivityEnum.ActivityType;
import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.model.response.ActivityStoreResponseDTO;
import com.ankuran.wages.model.response.OutstandingAmountResponseDTO;
import com.ankuran.wages.provider.ActivityProvider;
import com.ankuran.wages.provider.OutstandingAmountProvider;
import com.ankuran.wages.resource.ActivitiesResource;

public class ActivitiesResourceImpl implements ActivitiesResource {
	
	@Autowired
	ActivityProvider activityProvider;
	
	@Autowired
	OutstandingAmountProvider outstandingAmountProvider;

	@Override
	public ResponseEntity<ActivityStoreResponseDTO> addActivity(Long centreId, Long employeeId, ActivityResponseDTO activity) {
		if (employeeId != null && employeeId > 0 && centreId != null && centreId > 0 && activity != null) {
			ActivityResponseDTO activityResponseDTO = activityProvider.addActivity(centreId, employeeId, activity);
			if (activityResponseDTO != null && activityResponseDTO.getId() != null && BigInteger.ZERO.compareTo(activityResponseDTO.getId()) < 0) {
				if (ActivityType.PAYMENT.equals(activityResponseDTO.getType()) && activityResponseDTO.getPaymentDetails() != null 
						&& activityResponseDTO.getPaymentDetails().getAmount() != null 
						&& activityResponseDTO.getPaymentDetails().getAmount() > 0) {
					outstandingAmountProvider.patchOutstandingAmountByCentreIDAndEmployeeId(new OutstandingAmountResponseDTO(activityResponseDTO.getPaymentDetails().getAmount(), centreId, employeeId));
					return new ResponseEntity<ActivityStoreResponseDTO>(new ActivityStoreResponseDTO(centreId, employeeId, activityResponseDTO.getId()), HttpStatus.OK);
				} else if (ActivityType.DUE.equals(activityResponseDTO.getType()) && activityResponseDTO.getDueDetails() != null 
						&& activityResponseDTO.getDueDetails().getAmount() != null 
						&& activityResponseDTO.getDueDetails().getAmount() > 0) {
					outstandingAmountProvider.patchOutstandingAmountByCentreIDAndEmployeeId(new OutstandingAmountResponseDTO(activityResponseDTO.getDueDetails().getAmount(), centreId, employeeId));
					return new ResponseEntity<ActivityStoreResponseDTO>(new ActivityStoreResponseDTO(centreId, employeeId, activityResponseDTO.getId()), HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<ActivityStoreResponseDTO>(HttpStatus.CONFLICT);
			}
		}
		return new ResponseEntity<ActivityStoreResponseDTO>(HttpStatus.BAD_REQUEST);
	}

}
