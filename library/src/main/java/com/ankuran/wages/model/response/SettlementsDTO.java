package com.ankuran.wages.model.response;

import java.util.List;

public class SettlementsDTO {

	private List<SettlementDTO> settlements;
	private Double outstandingSettlement;

	public Double getOutstandingSettlement() {
		return outstandingSettlement;
	}

	public void setOutstandingSettlement(Double outstandingSettlement) {
		this.outstandingSettlement = outstandingSettlement;
	}

	public List<SettlementDTO> getSettlements() {
		return settlements;
	}

	public void setSettlements(List<SettlementDTO> settlements) {
		this.settlements = settlements;
	}

	public SettlementsDTO(List<SettlementDTO> settlements, Double outstandingSettlement) {
		super();
		this.settlements = settlements;
		this.outstandingSettlement = outstandingSettlement;
	}
	
}
