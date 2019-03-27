package com.ankuran.wages.model.response;

import java.util.Date;

public class SettlementDTO {

	private Long id;
	private Long centreId;
	private Date timeCreated;
	private Double amountBefore;
	private Double amount;
	private Double amountAfter;
	private EmployeeResponseDTO settledBy;
	private EmployeeResponseDTO counterparty;
	private Boolean correction;
	private String notes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCentreId() {
		return centreId;
	}
	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	public Double getAmountBefore() {
		return amountBefore;
	}
	public void setAmountBefore(Double amountBefore) {
		this.amountBefore = amountBefore;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmountAfter() {
		return amountAfter;
	}
	public void setAmountAfter(Double amountAfter) {
		this.amountAfter = amountAfter;
	}
	public EmployeeResponseDTO getSettledBy() {
		return settledBy;
	}
	public void setSettledBy(EmployeeResponseDTO settledBy) {
		this.settledBy = settledBy;
	}
	public EmployeeResponseDTO getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(EmployeeResponseDTO counterparty) {
		this.counterparty = counterparty;
	}
	public Boolean getCorrection() {
		return correction;
	}
	public void setCorrection(Boolean correction) {
		this.correction = correction;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
