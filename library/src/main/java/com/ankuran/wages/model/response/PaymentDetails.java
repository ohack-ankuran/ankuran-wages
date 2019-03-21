package com.ankuran.wages.model.response;

public class PaymentDetails extends ActivityRecordDetails {

	private Double currentDue;
	private Double remainingDue;
	
	public Double getCurrentDue() {
		return currentDue;
	}
	public void setCurrentDue(Double currentDue) {
		this.currentDue = currentDue;
	}
	public Double getRemainingDue() {
		return remainingDue;
	}
	public void setRemainingDue(Double remainingDue) {
		this.remainingDue = remainingDue;
	}
}
