package com.ankuran.wages.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "outstanding_settlement")
public class OutstandingSettlementDao {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="outstanding_settlement")
	private Double outstandingSettlement;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOutstandingSettlement() {
		return outstandingSettlement;
	}

	public void setOutstandingSettlement(Double outstandingSettlement) {
		this.outstandingSettlement = outstandingSettlement;
	}
	
	
}
