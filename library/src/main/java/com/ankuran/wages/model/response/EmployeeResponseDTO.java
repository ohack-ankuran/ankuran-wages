package com.ankuran.wages.model.response;


import java.util.Date;

public class EmployeeResponseDTO {

    private Long id;

    private String fullName;

    private String mobileNo;

    private Date joiningTime;

    private String spouseFullName;

    private String spouseEmployeeId;

    private Long centerId;

    private Byte status;

    private Date createdAt;


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

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}