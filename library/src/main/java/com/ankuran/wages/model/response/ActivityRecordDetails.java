package com.ankuran.wages.model.response;

public class ActivityRecordDetails {

	private String id;
	private String note;
	private Double amount;
	private EmployeeResponseDTO recipient;
	
	public EmployeeResponseDTO getRecipient() {
		return recipient;
	}
	public void setRecipient(EmployeeResponseDTO recipient) {
		this.recipient = recipient;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
