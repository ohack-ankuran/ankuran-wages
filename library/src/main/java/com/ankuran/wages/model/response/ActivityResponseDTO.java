package com.ankuran.wages.model.response;

import java.math.BigInteger;
import java.util.Date;

import com.ankuran.wages.enums.EmployeeActivityEnum;

public class ActivityResponseDTO {

	private BigInteger id;
	private Date timeCreated;
	private EmployeeActivityEnum.ActivityType type;
	private EmployeeActivityEnum.ActivityStatus status;
	private boolean changeHistory;
	private DueDetails dueDetails;
	private PaymentDetails paymentDetails;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	public EmployeeActivityEnum.ActivityType getType() {
		return type;
	}
	public void setType(EmployeeActivityEnum.ActivityType type) {
		this.type = type;
	}
	public EmployeeActivityEnum.ActivityStatus getStatus() {
		return status;
	}
	public void setStatus(EmployeeActivityEnum.ActivityStatus status) {
		this.status = status;
	}
	public boolean isChangeHistory() {
		return changeHistory;
	}
	public void setChangeHistory(boolean changeHistory) {
		this.changeHistory = changeHistory;
	}
	public DueDetails getDueDetails() {
		return dueDetails;
	}
	public void setDueDetails(DueDetails dueDetails) {
		this.dueDetails = dueDetails;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
}
