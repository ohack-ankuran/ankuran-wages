package com.ankuran.wages.model.response;

import java.util.List;

public class SettlementsDTO {

	private List<SettlementDTO> settlements;

	public List<SettlementDTO> getSettlements() {
		return settlements;
	}

	public void setSettlements(List<SettlementDTO> settlements) {
		this.settlements = settlements;
	}

	public SettlementsDTO(List<SettlementDTO> settlements) {
		super();
		this.settlements = settlements;
	}
	
}
