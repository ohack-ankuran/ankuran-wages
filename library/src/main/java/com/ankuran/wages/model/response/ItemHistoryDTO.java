package com.ankuran.wages.model.response;

import java.util.Date;

import com.ankuran.wages.enums.ItemHistoryEnum;

public class ItemHistoryDTO {

	private Long id;
	private Date timeCreated;
	private Long centreId;
	private Long itemId;
	private Long activityId;
	private ItemHistoryEnum.HistoryType type;
	private ItemHistoryEnum.HistoryReason reason;
	private String actorFullName;
	private Long units;
	private Double actualUnitSalePrice;
	private Double totalAmount;
	private String notes;
	private Boolean deleted;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
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
	public ItemHistoryEnum.HistoryType getType() {
		return type;
	}
	public void setType(ItemHistoryEnum.HistoryType type) {
		this.type = type;
	}
	public ItemHistoryEnum.HistoryReason getReason() {
		return reason;
	}
	public void setReason(ItemHistoryEnum.HistoryReason reason) {
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
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
