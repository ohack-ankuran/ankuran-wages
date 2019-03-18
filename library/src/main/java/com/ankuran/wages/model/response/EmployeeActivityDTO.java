package com.ankuran.wages.model.response;

import java.util.Date;

import com.ankuran.wages.enums.EmployeeActivityEnum;

public class EmployeeActivityDTO {

	private Date timeCreated;
	private EmployeeActivityEnum.ActivityType type;
	private PaymentDetails paymentDetails;
	private DueDetails dueDetails;
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
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public DueDetails getDueDetails() {
		return dueDetails;
	}
	public void setDueDetails(DueDetails dueDetails) {
		this.dueDetails = dueDetails;
	}
}
