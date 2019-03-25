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
 * @Created At 12/03/19.
 */
@Entity
@Table(name = "wages_activity")
public class WagesActivityDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
    @Column(name = "wages_activity_key")
    private Long wagesActivityKey;

    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "centre_id")
    private Long centreId;

    @Column(name = "linked_activity_id")
    private Long linkedActivityId;

    @Column(name = "type")
    private Long type;

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

    @Column(name = "changed")
    private Byte changed;

    @Column(name = "misc_info")
    private String miscInfo;

    @Column(name = "actor_employee_id")
    private Integer actorEmployeeId;

    @Column(name = "time_updated")
    private Date timeUpdated;

    @Column(name = "status")
    private Long status;
    
    @Column(name = "group_wage_id")
    private Long groupWageId;
    
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

	public Long getWagesActivityKey() {
        return wagesActivityKey;
    }

    public void setWagesActivityKey(Long wagesActivityKey) {
        this.wagesActivityKey = wagesActivityKey;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public Long getLinkedActivityId() {
        return linkedActivityId;
    }

    public void setLinkedActivityId(Long linkedActivityId) {
        this.linkedActivityId = linkedActivityId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
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

    public Byte getChanged() {
        return changed;
    }

    public void setChanged(Byte changed) {
        this.changed = changed;
    }

    public String getMiscInfo() {
        return miscInfo;
    }

    public void setMiscInfo(String miscInfo) {
        this.miscInfo = miscInfo;
    }

    public Integer getActorEmployeeId() {
        return actorEmployeeId;
    }

    public void setActorEmployeeId(Integer actorEmployeeId) {
        this.actorEmployeeId = actorEmployeeId;
    }

    public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

	public Long getGroupWageId() {
		return groupWageId;
	}

	public void setGroupWageId(Long groupWageId) {
		this.groupWageId = groupWageId;
	}
}
