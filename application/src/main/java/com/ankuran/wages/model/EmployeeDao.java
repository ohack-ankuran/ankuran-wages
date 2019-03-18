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
@Table(name = "employee")
public class EmployeeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile")
    private String mobileNo;

    @Column(name = "joining_time")
    private Date joiningTime;

    @Column(name = "spouse_full_name")
    private String spouseFullName;

    @Column(name = "spouse_employee_id")
    private String spouseEmployeeId;

    @Column(name = "centre")
    private Long centreId;

    @Column(name = "status")
    private Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getJoiningTime() {
        return joiningTime;
    }

    public void setJoiningTime(Date joiningTime) {
        this.joiningTime = joiningTime;
    }

    public String getSpouseFullName() {
        return spouseFullName;
    }

    public void setSpouseFullName(String spouseFullName) {
        this.spouseFullName = spouseFullName;
    }

    public String getSpouseEmployeeId() {
        return spouseEmployeeId;
    }

    public void setSpouseEmployeeId(String spouseEmployeeId) {
        this.spouseEmployeeId = spouseEmployeeId;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
