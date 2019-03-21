package com.ankuran.wages.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Sushil Mittal.
 * @Created At 24/02/19.
 */
@Entity
@Table(name = "group_wages_activity")
public class GroupWagesActivityDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "linked_activity_id")
    private Long linkedActivityId;

    @Column(name = "centre_id")
    private Long centreId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "due_per_item")
    private Double duePerItem;

    @Column(name = "item_quantity")
    private Long itemQuantity;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "misc_info")
    private String miscInfo;

    @Column(name = "actor_employee_id")
    private Long actorEmployeeId;

    @Column(name = "changed")
    private Byte changed;

    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "time_updated")
    private Date timeUpdated;

    @Column(name = "status")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLinkedActivityId() {
        return linkedActivityId;
    }

    public void setLinkedActivityId(Long linkedActivityId) {
        this.linkedActivityId = linkedActivityId;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getDuePerItem() {
        return duePerItem;
    }

    public void setDuePerItem(Double duePerItem) {
        this.duePerItem = duePerItem;
    }

    public Long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMiscInfo() {
        return miscInfo;
    }

    public void setMiscInfo(String miscInfo) {
        this.miscInfo = miscInfo;
    }

    public Long getActorEmployeeId() {
        return actorEmployeeId;
    }

    public void setActorEmployeeId(Long actorEmployeeId) {
        this.actorEmployeeId = actorEmployeeId;
    }

    public Byte getChanged() {
        return changed;
    }

    public void setChanged(Byte changed) {
        this.changed = changed;
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

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
