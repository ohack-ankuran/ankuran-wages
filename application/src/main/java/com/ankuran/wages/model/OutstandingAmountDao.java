package com.ankuran.wages.model;

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
@Table(name = "outstanding_amount")
public class OutstandingAmountDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "centre_id")
    private Long centreId;

    @Column(name = "outstanding_amount")
    private Double outstanding_amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getOutstanding_amount() {
        return outstanding_amount;
    }

    public void setOutstanding_amount(Double outstanding_amount) {
        this.outstanding_amount = outstanding_amount;
    }
}

