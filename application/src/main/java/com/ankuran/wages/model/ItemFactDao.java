package com.ankuran.wages.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sushil Mittal.
 * @Created At 24/02/19.
 */
@Entity
@Table(name = "item_fact")
public class ItemFactDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "centre_id")
    private Long centreId;
    
    @Column(name = "item_id")
    private Long itemId;
    
    @Column(name = "activity_id")
    private Long activityId;
    
    @Column(name = "type")
    private Long type;
    
    @Column(name = "reason")
    private Long reason;
    
    @Column(name = "actor_full_name")
    private String actorFullName;
    
    @Column(name = "units")
    private Long units;
    
    @Column(name = "actual_unit_sale_price")
    private Double actualUnitSalePrice;
    
    @Column(name = "total_amount")
    private Double totalAmount;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "deleted")
    private int deleted;
    
    @Column(name = "time_created")
    private Date timeCreated;
    
    @Column(name = "time_updated")
    private Date timeUpdated;

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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getReason() {
		return reason;
	}

	public void setReason(Long reason) {
		this.reason = reason;
	}

	public String getActorFullName() {
		return actorFullName;
	}

	public void setActorFullName(String actorFullName) {
		this.actorFullName = actorFullName;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	public Double getActualUnitSalePrice() {
		return actualUnitSalePrice;
	}

	public void setActualUnitSalePrice(Double actualUnitSalePrice) {
		this.actualUnitSalePrice = actualUnitSalePrice;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
}
