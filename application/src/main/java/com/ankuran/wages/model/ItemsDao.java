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
@Table(name = "item")
public class ItemsDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "picture")
    private String picture;
    
    @Column(name = "time_created")
    private Date timeCreated;
    
    @Column(name = "time_updated")
    private Date timeUpdated;

    @Column(name = "actor_employee_id")
    private Integer actorEmployeeId;
    
    @Column(name = "status")
    private Long status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public Integer getActorEmployeeId() {
		return actorEmployeeId;
	}

	public void setActorEmployeeId(Integer actorEmployeeId) {
		this.actorEmployeeId = actorEmployeeId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
