package com.ankuran.wages.mapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ankuran.wages.enums.EmployeeActivityEnum.ActivityStatus;
import com.ankuran.wages.enums.EmployeeActivityEnum.ActivityType;
import com.ankuran.wages.enums.EmployeeActivityEnum.DueDistributionType;
import com.ankuran.wages.model.GroupWagesActivityDao;
import com.ankuran.wages.model.WagesActivityDao;
import com.ankuran.wages.model.response.ActivityResponseDTO;
import com.ankuran.wages.model.response.DueDetails;
import com.ankuran.wages.model.response.EmployeeResponseDTO;
import com.ankuran.wages.model.response.EmployeeShare;
import com.ankuran.wages.model.response.ItemDetails;
import com.ankuran.wages.model.response.PaymentDetails;

@Component
public class ActivityMapper {
	/*
	 * Activity type to Long var map
	 */
	public static final int ACTIVITY_TYPE_PAYMENT = 1;
	public static final int ACTIVITY_TYPE_DUE = 2;
	/*
	 * Activity Status to Long var map
	 */
	public static final int ACTIVITY_STATUS_CORRECT = 1;
	public static final int ACTIVITY_STATUS_INCORRECT = 2;
	public static final int ACTIVITY_STATUS_DELETED = 3;

	public WagesActivityDao mapActivityRequestToIndividualWagesDao(Long centreId, Long employeeId, ActivityResponseDTO activity, BigInteger wagesActivityKey) {
		WagesActivityDao wagesActivityDao = new WagesActivityDao();
		wagesActivityDao.setCentreId(centreId);
		wagesActivityDao.setEmployeeId(employeeId);
		wagesActivityDao.setWagesActivityKey(wagesActivityKey);
		wagesActivityDao.setTimeCreated(activity.getTimeCreated());
		wagesActivityDao.setStatus(Long.valueOf(ACTIVITY_STATUS_CORRECT));
		wagesActivityDao.setChanged(Byte.valueOf("0"));
		
		if (ActivityType.DUE.equals(activity.getType())) {
			DueDetails dueDetails = activity.getDueDetails();
			wagesActivityDao.setType(Long.valueOf(ACTIVITY_TYPE_DUE));
			if (dueDetails.getItem() != null) {
				ItemDetails itemDetails = dueDetails.getItem();
				wagesActivityDao.setItemId(itemDetails.getId());
				wagesActivityDao.setItemName(itemDetails.getName());
			}
			wagesActivityDao.setDuePerItem(dueDetails.getDuePerItem());
			wagesActivityDao.setItemQuantity(dueDetails.getQuantity());
			wagesActivityDao.setTotalAmount(dueDetails.getAmount());
			
		} else if (ActivityType.PAYMENT.equals(activity.getType())) {
			PaymentDetails paymentDetails = activity.getPaymentDetails();
			wagesActivityDao.setType(Long.valueOf(ACTIVITY_TYPE_PAYMENT));
			wagesActivityDao.setMiscInfo(paymentDetails.getNote());
			wagesActivityDao.setTotalAmount(paymentDetails.getAmount());
		}
		
		return wagesActivityDao;
	}
	
	public GroupWagesActivityDao mapActivityRequestToGroupWagesDao(Long centreId, Long employeeId, ActivityResponseDTO activity, BigInteger wagesActivityKey) {
		GroupWagesActivityDao groupWagesActivityDao = new GroupWagesActivityDao();
		/*
		 * Type will be DUE and distribution type will be GROUP by default if reading from Group schema
		 * We'll connect this with it's distributions via group_wage_id in wages_activity which will contain the id for this group wage row.
		 * We'll also store the comma separated list of individual wage activity id in misc_info column in group wage row.
		 */
		DueDetails dueDetails = activity.getDueDetails();
		groupWagesActivityDao.setCentreId(centreId);
		groupWagesActivityDao.setChanged(Byte.valueOf("0"));
		groupWagesActivityDao.setStatus(ACTIVITY_STATUS_CORRECT);
		groupWagesActivityDao.setTimeCreated(activity.getTimeCreated());
		
		if (dueDetails != null) {
			groupWagesActivityDao.setDuePerItem(dueDetails.getDuePerItem());
			groupWagesActivityDao.setItemQuantity(dueDetails.getQuantity());
			groupWagesActivityDao.setTotalAmount(dueDetails.getAmount());
			
			ItemDetails item = dueDetails.getItem();
			if (item != null) {
				groupWagesActivityDao.setItemId(item.getId());
				groupWagesActivityDao.setItemName(item.getName());
			}
		}
		
		return groupWagesActivityDao;
	}
	
	public List<WagesActivityDao> mapGroupActivityRequestToMultiIndividualWagesDao(Long centreId, Long employeeId, ActivityResponseDTO activity, BigInteger wagesActivityKey) {
		List<WagesActivityDao> wageActivities = new ArrayList<WagesActivityDao>();
		List<EmployeeShare> employeeShares = activity.getDueDetails().getDistribution();
		WagesActivityDao baseWageActivity = mapActivityRequestToIndividualWagesDao(centreId, employeeId, activity, wagesActivityKey);
		for (EmployeeShare employeeShare : employeeShares) {
			if (employeeShare.getAmount() != null && employeeShare.getAmount() > 0 
					&& employeeShare.getEmployee() != null && employeeShare.getEmployee().getId() != null && employeeShare.getEmployee().getId() > 0) {
				WagesActivityDao wageActivityDao = copyBaseWageActivityWith(baseWageActivity, employeeShare.getAmount(), employeeShare.getEmployee().getId());
				wageActivities.add(wageActivityDao);
			}
		}
		
		return wageActivities;
	}
	private WagesActivityDao copyBaseWageActivityWith(WagesActivityDao baseWageActivity, Double amount, Long id) {
		WagesActivityDao wageActivity = new WagesActivityDao();
		wageActivity.setEmployeeId(id);
		wageActivity.setTotalAmount(amount);
		wageActivity.setCentreId(baseWageActivity.getCentreId());
		wageActivity.setTimeCreated(baseWageActivity.getTimeCreated());
		wageActivity.setWagesActivityKey(getWagesActivityKey(baseWageActivity.getTimeCreated().getTime(), baseWageActivity.getCentreId(), id));
		wageActivity.setStatus(baseWageActivity.getStatus());
		wageActivity.setChanged(baseWageActivity.getChanged());
		
		wageActivity.setType(baseWageActivity.getType());
		wageActivity.setItemId(baseWageActivity.getItemId());
		wageActivity.setItemName(baseWageActivity.getItemName());
		wageActivity.setDuePerItem(baseWageActivity.getDuePerItem());
		wageActivity.setItemQuantity(baseWageActivity.getItemQuantity());

		return wageActivity;
	}

	public ActivityResponseDTO mapIndividualWagesDaoToActivityResponseDTO(WagesActivityDao wagesActivityDao) {
		ActivityResponseDTO activity = new ActivityResponseDTO();
		if (wagesActivityDao.getChanged() != null && wagesActivityDao.getChanged() > 0) {
			activity.setChangeHistory(true);
		} else { 
			activity.setChangeHistory(false);
		}
		activity.setId(wagesActivityDao.getWagesActivityKey());
		activity.setStatus(getStatus(wagesActivityDao.getStatus()));
		activity.setTimeCreated(wagesActivityDao.getTimeCreated());
		activity.setType(getType(wagesActivityDao.getType()));
		
		if (ActivityType.DUE.equals(activity.getType())) {
			DueDetails dueDetails = new DueDetails();
			dueDetails.setId(String.valueOf(wagesActivityDao.getId()));
			dueDetails.setAmount(wagesActivityDao.getTotalAmount());
			dueDetails.setDuePerItem(wagesActivityDao.getDuePerItem());
			dueDetails.setQuantity(wagesActivityDao.getItemQuantity());
			if (wagesActivityDao.getGroupWageId() != null && wagesActivityDao.getGroupWageId() > 0) {
				dueDetails.setDistributionType(DueDistributionType.GROUP);
			} else {
				dueDetails.setDistributionType(DueDistributionType.INDIVIDUAL);
			}
			
			ItemDetails item = new ItemDetails();
			item.setId(wagesActivityDao.getItemId());
			item.setName(wagesActivityDao.getItemName());
			dueDetails.setItem(item);
			
			activity.setDueDetails(dueDetails);
			
		} else if (ActivityType.PAYMENT.equals(activity.getType())) {
			PaymentDetails paymentDetails = new PaymentDetails();
			paymentDetails.setAmount(wagesActivityDao.getTotalAmount());
			paymentDetails.setId(String.valueOf(wagesActivityDao.getId()));
			paymentDetails.setNote(wagesActivityDao.getMiscInfo());
			
			EmployeeResponseDTO employee = new EmployeeResponseDTO();
			employee.setCentre(wagesActivityDao.getCentreId());
			employee.setId(wagesActivityDao.getEmployeeId());
			paymentDetails.setRecipient(employee);
			
			activity.setPaymentDetails(paymentDetails);
		}
		
		return activity;
	}
	
	

	private ActivityType getType(Long type) {
		if (type != null && type == ACTIVITY_TYPE_DUE) {
			return ActivityType.DUE;
		} else if (type != null && type == ACTIVITY_TYPE_PAYMENT) {
			return ActivityType.PAYMENT;
		} else {
			return null;
		}
	}

	private ActivityStatus getStatus(Long status) {
		if (status != null && status == ACTIVITY_STATUS_CORRECT) {
			return ActivityStatus.CORRECT;
		} else if (status != null && status == ACTIVITY_STATUS_INCORRECT) {
			return ActivityStatus.INCORRECT;
		} else if (status != null && status == ACTIVITY_STATUS_DELETED) {
			return ActivityStatus.DELETED;
		} else {
			return null;
		}
	}
	
	private BigInteger getWagesActivityKey(long time, long centreId, long employeeId) {
		BigInteger wagesActivityKey = new BigInteger("0");

		wagesActivityKey = BigInteger.valueOf(time).shiftLeft(72);
		wagesActivityKey = wagesActivityKey.add(BigInteger.valueOf(centreId).shiftLeft(64));
		wagesActivityKey = wagesActivityKey.add(BigInteger.valueOf(employeeId));
		
		return wagesActivityKey;
	}
}
