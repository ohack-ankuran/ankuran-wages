package com.ankuran.wages.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "settlement")
public class SettlementDao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
	
	@Column(name = "centre_id")
	private Long centreId;
	
	@Column(name = "time_created")
    private Date timeCreated;
    
    @Column(name = "time_updated")
    private Date timeUpdated;
    
    @Column(name = "amount_before")
    private Double amountBefore;
    
    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "amount_after")
    private Double amountAfter;
    
    @Column(name = "actor")
    private Long actor;
    
    @Column(name = "counterparty")
    private Long counterparty;
    
    @Column(name = "correction")
    private int correction;
    
    @Column(name = "notes")
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

	public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
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

	public Long getActor() {
		return actor;
	}

	public void setActor(Long actor) {
		this.actor = actor;
	}

	public Long getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(Long counterparty) {
		this.counterparty = counterparty;
	}

	public int getCorrection() {
		return correction;
	}

	public void setCorrection(int correction) {
		this.correction = correction;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
}
